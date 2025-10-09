# LoadCtrl: Dynamic Load Balancer using Middle Element Approach (Java + MySQL)

This project simulates a dynamic load balancer built with Java (Core + JDBC) and MySQL, using the slow–fast pointer algorithm to find the middle server in a linked list of servers. The system distributes incoming requests evenly among the middle and its adjacent servers to maintain balanced performance and scalability.

# Features: 

1. Linked List–Based Server Management: Servers are represented as nodes in a linked list, allowing dynamic addition and removal.
2. Middle Server Selection: Implements the Find Middle Element algorithm using slow and fast pointers for efficient server selection in O(n) time.
3. Dynamic Load Distribution: Evenly distributes incoming requests to the middle server and its neighboring nodes for optimized performance.
4. Database Integration: Server details and load metrics are stored and updated in a MySQL database using JDBC.
5. Interactive Console Interface: Users can add, remove, or monitor servers, and distribute load in real time via a console menu.

# Tech Stack:

1. Language: Java (Core)
2. Database: MySQL
3. Libraries: JDBC
4. Data Structure: Linked List
5. Algorithm: Slow–Fast Pointer (to find middle element)

# How It Works:

1. Loads all available servers from the MySQL database into a linked list.
2. Finds the middle server using the slow–fast pointer technique.
3. Distributes incoming load requests evenly among the middle and its adjacent servers.
4. Updates each server’s current load in the database.
5. Allows adding or removing servers interactively from the console.
