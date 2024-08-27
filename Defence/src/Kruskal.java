public class Kruskal {
    public static final int INF = Integer.MAX_VALUE;

    private static int[] parent;

    private static void initializeParent(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public static int find(int n) {
        while (parent[n] != n) {
            n = parent[n];
        }
        return n;
    }

    public static void union(int i, int j) {
        int a = find(i);
        int b = find(j);
        parent[a] = b;
    }

    public static void kruskalMST(int[][] cost, int vertexCount) {
        initializeParent(vertexCount);

        int minCost = 0; // Cost of minimum spanning tree
        int edgeCount = 0;

        while (edgeCount < vertexCount - 1) {
            int min = INF, a = -1, b = -1;

            // Find the edge with the minimum weight
            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    if (find(i) != find(j) && cost[i][j] < min && cost[i][j] != 0) {
                        min = cost[i][j];
                        a = i;
                        b = j;
                    }
                }
            }

            if (a == -1 || b == -1) {
                break;
            }

            union(a, b);
            System.out.printf("Edge %d:(%d, %d) cost:%d \n", edgeCount++, a + 1, b + 1, min);
            minCost += min;
        }

        System.out.printf("\nMinimum cost of spanning tree: %d \n", minCost);
    }
}
