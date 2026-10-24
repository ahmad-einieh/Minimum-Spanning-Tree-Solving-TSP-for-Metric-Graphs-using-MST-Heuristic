# MST-Based TSP Heuristic

A Java console experiment comparing an exhaustive traveling-salesman solution with an MST-based, Christofides-style tour heuristic on generated geometric graphs.

## Workflow

1. Generate a graph with random vertices and distance-based weights.
2. Compute an optimal tour through the exhaustive solver.
3. Build a minimum spanning tree with Kruskal's algorithm.
4. Pair odd-degree vertices, combine edges, construct an Eulerian circuit, and attempt shortcutting.
5. Print paths, costs, graph edges, and an approximation/optimal cost ratio.

## Run

Use **JDK 17 or later**: the source calls `Random.nextInt(origin, bound)`. From the repository directory:

```bash
javac -d out src/*.java
java -cp out Main
```

No external libraries or build tool are required. The current main program runs eight randomly chosen, distinct graph sizes from 3 through 12 vertices. Exact TSP enumeration can become very slow at the larger sizes; reduce the sample sizes in `Main.java` when experimenting.

## Source guide

- [`GraphBuilder.java`](src/GraphBuilder.java): graph generation and data handling.
- [`MinimumSpanningTree.java`](src/MinimumSpanningTree.java): MST construction.
- [`ApproximationAlgorithm.java`](src/ApproximationAlgorithm.java): matching/combination/shortcut experiment.
- [`EulerianCircuit.java`](src/EulerianCircuit.java): circuit traversal.
- [`OptimalTravellingSalesmanProblem.java`](src/OptimalTravellingSalesmanProblem.java): exhaustive comparison solver.

## Important distinction

Despite its method name, `minWeightPerfectMatch` pairs consecutive odd-degree vertices; it does **not** solve minimum-weight perfect matching. Therefore this implementation must not be presented as a verified Christofides algorithm or assigned its theoretical approximation guarantee. Metric assumptions and shortcut correctness also need validation. This is coursework code, and comprehensive algorithm/runtime testing was not performed for this documentation refresh.
