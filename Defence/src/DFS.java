import java.util.*;

public class DFS {
    int V;
    LinkedList<Integer>[] adj;

    public DFS() {}

    public DFS(int V) {
        this.V = V;
        adj = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // Directed graph, add w to v's list
        adj[w].add(v); // Since the graph is undirected, add v to w's list as well
    }

    public Stack<Integer> startDFS(int n) {
        boolean[] visited = new boolean[V]; // Mark all vertices as not visited
        Stack<Integer> stack = new Stack<>(); // Stack for DFS
        Stack<Integer> path = new Stack<>(); // Stack to store the DFS path

        n = n - 1;

        stack.push(n);

        System.out.println("Starting DFS from vertex: " + (n + 1));
        System.out.println("DFS Traversal Path: ");

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                visited[node] = true;
                System.out.print((node + 1) + " ");
                path.push(node);

                for (int adjNode : adj[node]) {
                    if (!visited[adjNode]) {
                        stack.push(adjNode);
                    }
                }
            }
        }

        System.out.println();
        return path;
    }


    public boolean isConnected(Stack<Integer> path, int vertexCount) {
        return path.size() == V;
    }
}
