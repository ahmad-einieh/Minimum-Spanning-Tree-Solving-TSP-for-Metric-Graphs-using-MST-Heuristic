import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GraphBuilder {
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;
    private int id;

    public GraphBuilder() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        this.id = 0;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public int getNumOfEdges() {
        return edges.size();
    }

    public int getNumOfVertices() {
        return vertices.size();
    }

    public void addVertex() {
        Random r = new Random();
        int x = r.nextInt(1000), y = r.nextInt(1000);
        Vertex vertex = new Vertex(x, y, id++);
        while (true) {
            if (!isVertex(vertex))
                break;
            x = r.nextInt(1000);
            y = r.nextInt(1000);
            vertex.x = x;
            vertex.y = y;
        }
        vertices.add(vertex);
    }


    // overload method
    public void addVertex(Vertex v) {
        if (!isVertex(v)) {
            vertices.add(v);
        }
    }

    public boolean isVertex(Vertex v) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            if (vertex.id == v.id || vertex.x == v.x && vertex.y == v.y)
                return true;
        }
        return false;
    }

    public Vertex isVertexObjectById(int vertexId) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            if (vertex.id == vertexId)
                return vertex;
        }
        return null;
    }


    public boolean addEdge(Vertex source, Vertex destination, int weight) {
        if (source == destination)
            return false;

        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);

        return true;
    }

    public boolean isEdge(Vertex source, Vertex destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.source.id == source.id && edge.destination.id == destination.id
                    || edge.source.id == destination.id && edge.destination.id == source.id)
                return true;
        }
        return false;
    }

    public Edge isEdgeObjectById(int source, int destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.source.id == source && edge.destination.id == destination)
                return edge;
            else if (edge.source.id == destination && edge.destination.id == source) { // because the graph is undirected
                return new Edge(edge.destination, edge.source, edge.weight);
            }
        }

        return null;
    }

    public void removeEdge(Vertex source, Vertex destination) {
        if (isEdge(source, destination)) {
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (edge.source.id == source.id && edge.destination.id == destination.id
                        || edge.source.id == destination.id && edge.destination.id == source.id) {
                    edges.remove(i);
                    break;
                }
            }
        }
    }

    public void generateMetricGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex src = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); j++) {
                Vertex des = vertices.get(j);
                int weight = (int) Math.sqrt(Math.pow(src.x - des.x, 2) + Math.pow(src.y - des.y, 2));
                addEdge(src, des, weight);
            }

        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer>[] toAdjacencyMatrix() {
        int numOfVertices = vertices.size();
        ArrayList<Integer>[] AdjMat = new ArrayList[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            AdjMat[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                AdjMat[i].add(j, 0);
            }
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int source = edge.source.id, destination = edge.destination.id;
            AdjMat[source].set(destination, edge.weight);
            AdjMat[destination].set(source, edge.weight);
        }

        return AdjMat;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<Integer>[] toAdjacencyList() {
        int numOfVertices = vertices.size();
        LinkedList<Integer>[] Adjlist = new LinkedList[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            Adjlist[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int source = edge.source.id, destination = edge.destination.id;
            Adjlist[source].add(destination);
            Adjlist[destination].add(source);
        }

        return Adjlist;
    }



    public void printGraph() {
        System.out.print("the Cities: ");
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            if (i != vertices.size() - 1)
                System.out.print(vertex.id + ", ");
            else
                System.out.println(vertex.id);
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            System.out.println(
                    "Edge " + i + " -> city: " + edge.source.id + ", city: " + edge.destination.id + ", weight: " + edge.weight);
        }
    }

}
