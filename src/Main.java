import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        LoadBalancer lb = new LoadBalancer();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n    Dynamic Load Balancer Menu    ");
            System.out.println("1. Load servers from database");
            System.out.println("2. Display all servers");
            System.out.println("3. Find middle server");
            System.out.println("4. Distribute load");
            System.out.println("5. Add new server");
            System.out.println("6. Remove server");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    lb.loadServersFromDB();
                    break;
                case 2:
                    lb.displayServers();
                    break;
                case 3:
                    Server middle = lb.findMiddleServer();
                    if (middle != null)
                        System.out.println(" Middle Server: " + middle.name);
                    else
                        System.out.println(" No servers available.");
                    break;
                case 4:
                    System.out.print("Enter number of incoming requests: ");
                    int requests = sc.nextInt();
                    lb.distributeLoad(requests);
                    break;
                case 5:
                    System.out.print("Enter server name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter max capacity: ");
                    int capacity = sc.nextInt();
                    lb.addServer(name, capacity);
                    break;
                case 6:
                    System.out.print("Enter server ID to remove: ");
                    int id = sc.nextInt();
                    lb.removeServer(id);
                    break;
                case 0:
                    System.out.println("Exiting");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
