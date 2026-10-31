
import java.util.*;

class Edge {
    int u, v;

    Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}

public class BiconnectedComponents {

    static final int MAX = 100;
    static int[][] graph = new int[MAX][MAX];
    static boolean[] visited = new boolean[MAX];
    static int[] discovery = new int[MAX];
    static int[] low = new int[MAX];
    static int[] parent = new int[MAX];
    static int time;

    static Stack<Edge> stack = new Stack<>();

    static void dfs(int u, int n) {
        visited[u] = true;
        discovery[u] = low[u] = ++time;
        int children = 0;

        for (int v = 0; v < n; v++) {
            if (graph[u][v] == 1) {

                if (!visited[v]) {
                    children++;
                    parent[v] = u;
                    stack.push(new Edge(u, v));

                    dfs(v, n);

                    low[u] = Math.min(low[u], low[v]);

                    // Check articulation point condition
                    if ((parent[u] == -1 && children > 1) ||
                        (parent[u] != -1 &&
                         low[v] >= discovery[u])) {

                        System.out.println(
                            "Biconnected Component:");

                        Edge edge;
                        do {
                            edge = stack.pop();
                            System.out.println(
                                "(" + edge.u + ", " + edge.v + ")");
                        } while (edge.u != u || edge.v != v);
                    }

                } else if (v != parent[u] &&
                           discovery[v] < low[u]) {

                    low[u] = discovery[v];
                    stack.push(new Edge(u, v));
                }
            }
        }
    }

    static void biconnectedComponents(int n) {
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        time = 0;
        stack.clear();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n);

                // Print remaining edges
                if (!stack.isEmpty()) {
                    System.out.println(
                        "Biconnected Component:");

                    while (!stack.isEmpty()) {
                        Edge edge = stack.pop();
                        System.out.println(
                            "(" + edge.u + ", " + edge.v + ")");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;

        int[][] graphMatrix = {
            {0, 1, 0, 0, 0},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 1},
            {0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        // Copy adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = graphMatrix[i][j];
            }
        }

        biconnectedComponents(n);
    }
}