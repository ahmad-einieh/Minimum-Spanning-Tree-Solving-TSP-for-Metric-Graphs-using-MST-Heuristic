import java.util.ArrayList;
import java.util.LinkedList;

public class EulerianCircuit {
    public GraphBuilder euGraph;
    public LinkedList<Integer>[] adjList;

    public EulerianCircuit() {
        euGraph = new GraphBuilder();
    }

    public void FleuryAlgorithm(GraphBuilder combineGraph) {

        ArrayList<Vertex> combineVertices = combineGraph.getVertices();
        for (Vertex v : combineVertices) euGraph.addVertex(v);
        adjList = combineGraph.toAdjacencyList();

        int src = 0;
        ArrayList<Vertex> euVertices = combineGraph.getVertices();
        for (int i = 0; i < euVertices.size(); i++) {
            Vertex vertex = euVertices.get(i);
            if (vertex.dgeree % 2 == 1) {
                src = i;
                break;
            }
        }
        findEulerTour(src, combineGraph);

    }

    private void findEulerTour(Integer src, GraphBuilder combineGraph) {
        for (int i = 0; i < adjList[src].size(); i++) {
            Integer des = adjList[src].get(i);
            if (isValidNextEdge(src, des)) {
                Edge edge = combineGraph.isEdgeObjectById(src, des);
                euGraph.addEdge(edge.source, edge.destination, edge.weight);
                adjList[src].remove(des);
                adjList[des].remove(src);
                findEulerTour(des, combineGraph);
            }

        }
    }

    private boolean isValidNextEdge(Integer src, Integer des) {
        if (adjList[src].size() == 1) return true;
        boolean[] isVisited = new boolean[euGraph.getNumOfVertices()];
        int countWith = DFSCount(src, isVisited);

        adjList[src].remove(des);
        adjList[des].remove(src);

        isVisited = new boolean[euGraph.getNumOfVertices()];
        int countWithout = DFSCount(src, isVisited);

        adjList[src].add(des);
        adjList[des].add(src);

        return countWith <= countWithout;


    }

    private int DFSCount(Integer src, boolean[] isVisited) {
        isVisited[src] = true;
        int count = 1;
        for (int i = 0; i < adjList[src].size(); i++) {
            int vertex = adjList[src].get(i);
            if (!isVisited[vertex]) count += DFSCount(vertex, isVisited);
        }
        return count;
    }

}
