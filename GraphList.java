
import java.util.*;

class GraphList
{
    private int vertices;
    private LinkedList<Integer>[] adjList;

    // Constructor
    public GraphList(int v)
    {
        vertices = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++)
        {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add edge (Undirected Graph)
    public void addEdge(int src, int dest)
    {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    // BFS Traversal
    public void bfs(int start)
    {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty())
        {
            int current = queue.poll();

            System.out.print(current + " ");

            for (int neighbor : adjList[current])
            {
                if (!visited[neighbor])
                {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // DFS Traversal (Recursive)
    private void dfsUtil(int vertex, boolean[] visited)
    {
        visited[vertex] = true;

        System.out.print(vertex + " ");

        for (int neighbor : adjList[vertex])
        {
            if (!visited[neighbor])
            {
                dfsUtil(neighbor, visited);
            }
        }
    }

    // DFS Method
    public void dfs(int start)
    {
        boolean[] visited = new boolean[vertices];

        dfsUtil(start, visited);
    }

    // Print Adjacency List
    public void printGraph()
    {
        for (int i = 0; i < vertices; i++)
        {
            System.out.print("Adjacency list of vertex " + i + ": ");

            for (int neighbor : adjList[i])
            {
                System.out.print(neighbor + " -> ");
            }

            System.out.println("NULL");
        }
    }

    // Main Method
    public static void main(String[] args)
    {
        GraphList g = new GraphList(6);

        // Add edges
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        // Print Adjacency List
        System.out.println("Adjacency List Representation:");
        g.printGraph();

        // BFS
        System.out.print("BFS traversal: ");
        g.bfs(0);

        // DFS
        System.out.print("\nDFS traversal: ");
        g.dfs(0);
    }
}