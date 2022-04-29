public class Vertex {
    int id;
    int x;
    int y;
    int dgeree;

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dgeree = 0;
    }

    public Vertex(Vertex v) {
        this.id = v.id;
        this.x = v.x;
        this.y = v.y;
        this.dgeree = v.dgeree;
    }


}
