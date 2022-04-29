public class Edge {
    Vertex source;
    Vertex destination;

    int weight;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Edge e) {
        this.source = e.source;
        this.destination = e.destination;
        this.weight = e.weight;
    }


}
