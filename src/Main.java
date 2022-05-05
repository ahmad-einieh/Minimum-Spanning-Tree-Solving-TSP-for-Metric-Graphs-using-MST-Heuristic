import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            Random r = new Random();
            int x = r.nextInt(3, 13);
            while (numbers.contains(x))
                 x = r.nextInt(3, 13);
            numbers.add(x);

            System.out.println("--- number of vertices = " + x + " ---\n");

            GraphBuilder graphBuilder = new GraphBuilder();
            for (int j = 0; j < x; j++) {
                graphBuilder.addVertex();
            }
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



            System.out.println("\n --- --- --- --- --- --- --- --- --- finish "+ (i+1) +" --- --- --- --- --- --- --- --- --- \n");

        }

        /*GraphBuilder graphBuilder = new GraphBuilder();

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
        System.out.println(ratio);*/
    }
}