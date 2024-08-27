public class Edge {
    public int v1;
    public int v2;
    public int w;  // Weight of the edge

    public Edge() {}

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getW() {
        return w;
    }
}
