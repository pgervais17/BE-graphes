package org.insa.graphs.algorithm.shortestpath;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        BinaryHeap <Label> labels = new BinaryHeap <Label>();
        HashMap <Node, Label> hashes = new HashMap <Node, Label> ();
        
        
        // TODO:
        return solution;
    }

}
