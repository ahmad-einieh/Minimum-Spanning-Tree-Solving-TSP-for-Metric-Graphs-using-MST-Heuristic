import java.util.ArrayList;

public class OptimalTravellingSalesmanProblem {

    public GraphBuilder optTSPGraph;
    private ArrayList<Vertex> minPath;

    public OptimalTravellingSalesmanProblem() {
        this.optTSPGraph = new GraphBuilder();
        this.minPath = new ArrayList<>();
    }

    public int optTSPSolution(GraphBuilder originalGraph, int start) {
        ArrayList<Integer> perVertices = new ArrayList<>();
        ArrayList<Vertex> currentPath = new ArrayList<>();

        ArrayList<Vertex> vertices = originalGraph.getVertices();
        for (var v : vertices) {
            optTSPGraph.addVertex(v);
        }

        ArrayList<Integer>[] adjMatrix = originalGraph.toAdjacencyMatrix();

        for (int i = 0; i < adjMatrix.length; i++) {
            if (i != start) {
                perVertices.add(i);
            }
        }

        int minPathWeight = Integer.MAX_VALUE;

        do {
            int currentPathWeight = 0;
            currentPath.removeAll(currentPath);

            int currentVertexId = start;

            for (Integer perVertic : perVertices) {

                currentPathWeight += adjMatrix[currentVertexId].get(perVertic);
                currentVertexId = perVertic;
                Vertex currentVertex = optTSPGraph.isVertexObjectById(currentVertexId);
                currentPath.add(currentVertex);

            }
            currentPathWeight += adjMatrix[currentVertexId].get(start);
            minPathWeight = min(minPathWeight, currentPathWeight, currentPath);


        } while (findNextPermutation(perVertices));

        Vertex startVertex = optTSPGraph.isVertexObjectById(start);
        minPath.add(0, startVertex);
        minPath.add(startVertex);

        for (int i = 0; i < minPath.size() - 1; i++) {
            Vertex src = minPath.get(i);
            Vertex des = minPath.get(i + 1);
            int weight = adjMatrix[src.id].get(des.id);
            optTSPGraph.addEdge(src, des, weight);

        }

        return minPathWeight;
    }


    private boolean findNextPermutation(ArrayList<Integer> data) {
        if (data.size() <= 1) return false;

        int last = data.size() - 2;

        while (last >= 0) {
            if (data.get(last) < data.get(last + 1)) break;
            last--;
        }

        if (last < 0) return false;

        int nextGreater = data.size() - 1;
        for (int i = data.size() - 1; i > last; i--) {
            if (data.get(i) > data.get(last)) {
                nextGreater = i;
                break;
            }
        }

        data = swap(data, nextGreater, last);

        data = reverse(data, last + 1, data.size() - 1);

        return true;

    }


    private ArrayList<Integer> swap(ArrayList<Integer> data, int left, int right) {
        int temp = data.get(left);
        data.set(left, data.get(right));
        data.set(right, temp);
        return data;
    }

    private ArrayList<Integer> reverse(ArrayList<Integer> data, int left, int right) {
        while (left < right) {
            int temp = data.get(left);
            data.set(left, data.get(right));
            left++;
            data.set(right, temp);
            right--;
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    private int min(int min_pathWeight, int current_pathWeight, ArrayList<Vertex> current_path) {
        if (min_pathWeight > current_pathWeight) {
            minPath.removeAll(minPath);
            minPath = (ArrayList<Vertex>) current_path.clone();
            return current_pathWeight;
        }
        return min_pathWeight;
    }


    public void printOptPath() {

        System.out.print("Optimal cost path = [");
        for (int i = 0; i < minPath.size(); i++) {
            Vertex vertex = minPath.get(i);
            if (i == minPath.size() - 1)
                System.out.print(" " + vertex.id + " ");
            else
                System.out.print(" " + vertex.id + ",");
        }
        System.out.println("]");

    }
}
