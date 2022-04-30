import java.util.ArrayList;

public class ApproximationAlgorithm {
    public GraphBuilder chritoGraph;

    public ApproximationAlgorithm() {
        chritoGraph = new GraphBuilder();
    }

    public int christofid(GraphBuilder graph) {

        MinimumSpanningTree mst = new MinimumSpanningTree();
        mst.kruskalMST(graph);

        GraphBuilder minWPMGraph = minWeightPerfectMatch(mst.MSTGraph);
        GraphBuilder combineGraph = combineGraph(mst.MSTGraph, minWPMGraph);

        EulerianCircuit euCircuit = new EulerianCircuit();
        euCircuit.FleuryAlgorithm(combineGraph);
        shortcutGraph(euCircuit.euGraph);

        ArrayList<Edge> edges = chritoGraph.getEdges();
        int approximateWeight = 0;
        for (Edge edge : edges) approximateWeight += edge.weight;

        return approximateWeight;
    }

    public void shortcutGraph(GraphBuilder EUCircuit) {
        ArrayList<Vertex> vertices = EUCircuit.getVertices();
        for (var v : vertices) {
            chritoGraph.addVertex(v);
        }
        boolean[] isVisited = new boolean[vertices.size()];
        ArrayList<Edge> edges = EUCircuit.getEdges();
        int i = 0;
        isVisited[0] = true;
        while (i < edges.size()) {
            int id = edges.get(i).destination.id;
            if (!isVisited[id] || i == edges.size() - 1) {
                Edge edge = edges.get(i++);
                chritoGraph.addEdge(edge.source, edge.destination, edge.weight);
                isVisited[id] = true;
            } else {
                Edge edgeBefore = edges.get(i++);
                Edge edgeAfter = edges.get(i++);
                Vertex vertexSRC = edgeBefore.source;
                Vertex vertexDES = edgeAfter.destination;
                int weight = (int) Math.sqrt(Math.pow(vertexSRC.x - vertexDES.x, 2) + Math.pow(vertexSRC.y - vertexDES.y, 2));
                chritoGraph.addEdge(vertexSRC, vertexDES, weight);
                isVisited[edgeAfter.destination.id] = true;
            }
        }

    }

    public GraphBuilder combineGraph(GraphBuilder MSTGraph, GraphBuilder mWPMGraph) {
        GraphBuilder combineGraph = new GraphBuilder();
        ArrayList<Vertex> vertices = MSTGraph.getVertices();

        for (var v : vertices) {
            combineGraph.addVertex(v);
        }

        ArrayList<Edge> edgesMST = MSTGraph.getEdges();
        for (int i = 0; i < edgesMST.size(); i++) {
            Edge edge = edgesMST.get(i);
            combineGraph.addEdge(edge.source, edge.destination, edge.weight);
        }

        ArrayList<Edge> edgesmWPM = mWPMGraph.getEdges();
        for (int i = 0; i < edgesmWPM.size(); i++) {
            Edge edge = edgesmWPM.get(i);
            combineGraph.addEdge(edge.source, edge.destination, edge.weight);
        }

        return combineGraph;
    }


    public GraphBuilder minWeightPerfectMatch(GraphBuilder mstGraph) {
        GraphBuilder mWPMGraph = new GraphBuilder();
        ArrayList<Vertex> oddVertices = findOddNodes(mstGraph);

        for (var v : oddVertices) {
            mWPMGraph.addVertex(v);
        }

        int i = 0;
        while (i < oddVertices.size()) {
            Vertex vertex1 = oddVertices.get(i++);
            Vertex vertex2 = oddVertices.get(i++);
            vertex1.dgeree++;
            vertex2.dgeree++;
            int weight = (int) Math.sqrt(Math.pow(vertex1.x - vertex2.x, 2) + Math.pow(vertex1.y - vertex2.y, 2));
            mWPMGraph.addEdge(vertex1, vertex2, weight);
        }
        return mWPMGraph;
    }


    private ArrayList<Vertex> findOddNodes(GraphBuilder graph) {
        ArrayList<Vertex> oddVertices = new ArrayList<>();
        ArrayList<Vertex> MSTVertices = graph.getVertices();

        for (int i = 0; i < MSTVertices.size(); i++) {
            Vertex vertex = MSTVertices.get(i);
            if (vertex.dgeree % 2 != 0) {
                oddVertices.add(vertex);
            }
        }


        return oddVertices;
    }


    public void printApproxPath() {
        ArrayList<Edge> approxEdges = chritoGraph.getEdges();
        System.out.print("Approximation cost path = [");
        for (int i = 0; i < approxEdges.size(); i++) {
            Vertex vertex = approxEdges.get(i).source;
            System.out.print(" " + vertex.id + ",");
        }
        Vertex vertex = approxEdges.get(0).source;
        System.out.print(" " + vertex.id + " ");
        System.out.println("]");

    }
}
