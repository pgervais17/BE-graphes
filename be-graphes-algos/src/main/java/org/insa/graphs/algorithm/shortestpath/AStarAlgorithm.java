package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.LabelStar;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    protected Label CreateLabel (Node node) {
    	Label res = new LabelStar(node);
    	res
    	return ;
    }
}
