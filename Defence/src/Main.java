import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int[][] generateArray(ArrayList<Edge> edges, int vertexCount) {
        int[][] costMatrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                costMatrix[i][j] = (i == j) ? 0 : Kruskal.INF;
            }
        }

        for (Edge edge : edges) {
            if (edge != null) {
                costMatrix[edge.v1 - 1][edge.v2 - 1] = edge.w;
                costMatrix[edge.v2 - 1][edge.v1 - 1] = edge.w; // For undirected graph
            }
        }
        return costMatrix;
    }

    public static int calculateVertices(ArrayList<Edge> edges) {
        int maxVertex = Integer.MIN_VALUE;
        for (Edge edge : edges) {
            if (edge.v1 > maxVertex) {
                maxVertex = edge.v1;
            }
            if (edge.v2 > maxVertex) {
                maxVertex = edge.v2;
            }
        }
        return maxVertex;
    }

    public static void processGraph(boolean isConnected, int[][] costMatrix, int vertexCount, ArrayList<Edge> edges) {
        if (isConnected) {
            System.out.println("The graph is connected.");
            Kruskal.kruskalMST(costMatrix, vertexCount);
        } else {
            System.out.println("The graph is not connected. Calculating minimum cost for each component:");
            DFS dfs = new DFS(vertexCount);

            for (Edge edge : edges) {
                dfs.addEdge(edge.v1 - 1, edge.v2 - 1);
            }

            boolean[] visited = new boolean[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                if (!visited[i]) {
                    ArrayList<Edge> componentEdges = new ArrayList<>();
                    Stack<Integer> path = dfs.startDFS(i + 1);

                    while (!path.isEmpty()) {
                        int node = path.pop();
                        visited[node] = true;

                        for (Edge edge : edges) {
                            if ((edge.v1 - 1 == node || edge.v2 - 1 == node) && !visited[edge.v1 - 1] && !visited[edge.v2 - 1]) {
                                componentEdges.add(edge);
                            }
                        }
                    }

                    int[][] componentCostMatrix = generateArray(componentEdges, vertexCount);

                    int componentVertexCount = calculateVertices(componentEdges);

                    if (componentVertexCount > 1) {

                        Kruskal.kruskalMST(componentCostMatrix, componentVertexCount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String connectedGraphPath = "C:\\Users\\abdel\\Downloads\\Projects\\Kruksal Project in java code + Report\\Defence\\src\\Edges connected";
        String disconnectedGraphPath = "C:\\Users\\abdel\\Downloads\\Projects\\Kruksal Project in java code + Report\\Defence\\src\\Edges disconnected";

        ArrayList<Edge> edgeList = new ArrayList<>();

        DataReader.readEdges(disconnectedGraphPath, edgeList);

        int vertexCount = calculateVertices(edgeList);
        System.out.println("Number of vertices in the graph: " + vertexCount);

        DFS dfs = new DFS(vertexCount);
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a starting vertex for Depth-First Search: ");
        int startVertex = reader.nextInt();
        reader.close();

        for (Edge edge : edgeList) {
            dfs.addEdge(edge.v1 - 1, edge.v2 - 1);
        }
        Stack<Integer> dfsPath = dfs.startDFS(startVertex);

        // Check if the graph is connected
        boolean isConnected = dfs.isConnected(dfsPath, vertexCount);
        int[][] costMatrix = generateArray(edgeList, vertexCount);

        processGraph(isConnected, costMatrix, vertexCount, edgeList);
    }
}
