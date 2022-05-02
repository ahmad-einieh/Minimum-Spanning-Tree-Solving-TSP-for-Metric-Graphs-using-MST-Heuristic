import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

    public GraphBuilder MSTGraph;

    public MinimumSpanningTree() {
        this.MSTGraph = new GraphBuilder();
    }

    public void kruskalMST(GraphBuilder graph) {

        ArrayList<Vertex> vertices = graph.getVertices();
        for (var v : vertices) {
            v.dgeree = 0;
            MSTGraph.addVertex(v);
        }

        int numOfEdge = graph.getNumOfEdges();
        PriorityQueue<Edge> pq = new PriorityQueue<>(numOfEdge, Comparator.comparingInt(w -> w.weight));

        ArrayList<Edge> edges = graph.getEdges();
        pq.addAll(edges); // add all edges to pq same as loop

        int numOfVertices = graph.getNumOfVertices();
        int[] root = new int[numOfVertices];


        makeSet(root);

        int index = 0;
        while (index < numOfVertices - 1) {
            Edge edge = pq.remove();
            int srcSet = find(root, edge.source.id);
            int desSet = find(root, edge.destination.id);

            if (srcSet != desSet) {
                edge.source.dgeree++;
                edge.destination.dgeree++;
                MSTGraph.addEdge(edge.source, edge.destination, edge.weight);
                index++;
                union(root, srcSet, desSet);
            }
        }


    }

    private void makeSet(int[] root) { // before (int[] root, GraphBuilder graph)
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }

    }

    private int find(int[] root, int vertex) {
        if (root[vertex] != vertex)
            return find(root, root[vertex]);

        return vertex;
    }

    private void union(int[] root, int src, int des) {
        int srcSet = find(root, src);
        int desSet = find(root, des);
        root[desSet] = srcSet;
    }
}
