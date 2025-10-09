public class Server {
    int id;
    String name;
    int currentLoad;
    int maxCapacity;
    Server next;

    public Server(int id, String name, int currentLoad, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.currentLoad = currentLoad;
        this.maxCapacity = maxCapacity;
        this.next = null;
    }

    @Override
    public String toString() {
        return name + " (Load: " + currentLoad + "/" + maxCapacity + ")";
    }
}
