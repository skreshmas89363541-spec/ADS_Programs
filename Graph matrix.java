import java.util.*;

public class GraphMatrix {

    static final int V = 6;

    // Breadth First Traversal (BFT)
    public static void bfs(int[][] graph, int start) {

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < V; i++) {

                if (graph[current][i] == 1 && !visited[i]) {

                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    // Depth First Traversal (DFT)
    public static void dfsUtil(int[][] graph, boolean[] visited, int vertex) {

        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int i = 0; i < V; i++) {

            if (graph[vertex][i] == 1 && !visited[i]) {

                dfsUtil(graph, visited, i);
            }
        }
    }

    public static void dfs(int[][] graph, int start) {

        boolean[] visited = new boolean[V];

        dfsUtil(graph, visited, start);
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0}
        };

        System.out.print("BFS traversal: ");
        bfs(graph, 0);

        System.out.print("\nDFS traversal: ");
        dfs(graph, 0);
    }
}