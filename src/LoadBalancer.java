import java.sql.*;
import java.util.*;
public class LoadBalancer {
    private Server head;
    // Loading servers from database into linked list
    public void loadServersFromDB() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM servers")) {
            Server prev = null;
            while (rs.next()) {
                Server newServer = new Server(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("current_load"),
                        rs.getInt("max_capacity")
                );
                if (head == null) {
                    head = newServer;
                } else {
                    prev.next = newServer;
                }
                prev = newServer;
            }
            System.out.println("Servers loaded successfully from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Finding middle server using slow/fast pointer
    public Server findMiddleServer() {
        if (head == null) return null;
        Server slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // Distributing load to middle and its adjacent servers
    public void distributeLoad(int incomingLoad) {
        Server middle = findMiddleServer();
        if (middle == null) {
            System.out.println(" No servers available.");
            return;
        }
        System.out.println("Middle server selected: " + middle.name);
        Server left = findPreviousServer(middle);
        Server right = middle.next;
        int distributeCount = 1;
        if (left != null) distributeCount++;
        if (right != null) distributeCount++;
        int loadPerServer = incomingLoad / distributeCount;
        try (Connection conn = DatabaseConnection.getConnection()) {
            updateServerLoad(conn, middle, loadPerServer);
            if (left != null) updateServerLoad(conn, left, loadPerServer);
            if (right != null) updateServerLoad(conn, right, loadPerServer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Load distributed evenly among " + distributeCount + " servers.");
    }
    // Updating load in DB
    private void updateServerLoad(Connection conn, Server server, int addLoad) throws SQLException {
        server.currentLoad += addLoad;
        String query = "UPDATE servers SET current_load = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, server.currentLoad);
            pstmt.setInt(2, server.id);
            pstmt.executeUpdate();
        }
        System.out.println("Updated " + server.name + " -> Current Load: " + server.currentLoad);
    }

    // Finding previous server
    private Server findPreviousServer(Server node) {
        if (head == null || head == node) return null;
        Server current = head;
        while (current.next != null && current.next != node) {
            current = current.next;
        }
        return (current.next == node) ? current : null;
    }

    // Displaying all servers
    public void displayServers() {
        Server current = head;
        System.out.println("\n Server Linked List:");
        while (current != null) {
            System.out.println("→ " + current);
            current = current.next;
        }
    }
}
