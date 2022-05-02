public class Main {
    public static void main(String[] args) {

        GraphBuilder graphBuilder = new GraphBuilder();

        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        graphBuilder.addVertex();
        //graphBuilder.addVertex();

        graphBuilder.generateMetricGraph();

        System.out.println("Original Graph (TSP Graph):");
        graphBuilder.printGraph();
        System.out.println("--------------------------------\n");

        System.out.println("Optimal solution Graph:");
        OptimalTravellingSalesmanProblem optimalTSP = new OptimalTravellingSalesmanProblem();
        int min_pathWeigth = optimalTSP.optTSPSolution(graphBuilder, 0);
        System.out.println("Optimal Cost traverse = " + min_pathWeigth);
        optimalTSP.printOptPath();
        optimalTSP.optTSPGraph.printGraph();
        System.out.println("--------------------------------\n");

        ApproximationAlgorithm approxAlg = new ApproximationAlgorithm();
        int approxWeight = approxAlg.christofid(graphBuilder);
        System.out.println("Approximation solution Graph:");
        System.out.println("Approximation Cost traverse = " + approxWeight);
        approxAlg.printApproxPath();
        approxAlg.chritoGraph.printGraph();
        System.out.println("--------------------------------\n");

        System.out.println("Comparison between the optimal and approximation solution : ");
        double ratio = (approxWeight * 1.0 / min_pathWeigth);
        System.out.println(ratio);
    }
}