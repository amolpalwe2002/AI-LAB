import java.util.*;

class Node implements Comparable<Node> {
    int x, y;       // Coordinates of the node
    int heuristic;  // Heuristic value (estimated cost to goal)

    public Node(int x, int y, int heuristic) {
        this.x = x;
        this.y = y;
        this.heuristic = heuristic;
    }

    public int compareTo(Node other) {
        // Compare nodes based on the heuristic value for Best-First Search
        return Integer.compare(this.heuristic, other.heuristic);
    }
}

public class lab03b  {
    public static int[][] grid = {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    public static int[] goal = {4, 4};

    public static int[] dx = {1, -1, 0, 0}; // Right, Left, Down, Up
    public static int[] dy = {0, 0, 1, -1};

    // Calculate the Manhattan distance heuristic
    public static int heuristic(int x, int y) {
        return Math.abs(x - goal[0]) + Math.abs(y - goal[1]);
    }

    public static void bestFirstSearch() {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<Node> closedList = new HashSet<>();

        Node startNode = new Node(0, 0, heuristic(0, 0));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.x == goal[0] && current.y == goal[1]) {
                System.out.println("Best-First Search: Goal reached!");
                return;
            }

            closedList.add(current);

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 0) {
                    Node neighbor = new Node(nx, ny, heuristic(nx, ny));

                    if (!closedList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        System.out.println("Best-First Search: No path found.");
    }

    public static void main(String[] args) {
        bestFirstSearch();
    }
}
