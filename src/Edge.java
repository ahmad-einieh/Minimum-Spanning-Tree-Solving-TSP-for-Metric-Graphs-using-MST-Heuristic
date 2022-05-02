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
        Vertex copyVertexSRC = new Vertex(e.source);
        Vertex copyVertexDES = new Vertex(e.destination);
        this.source = copyVertexSRC;
        this.destination = copyVertexDES;
        this.weight = e.weight;
    }


}
