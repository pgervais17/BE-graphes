package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.LabelStar;

import org.insa.graphs.algorithm.AbstractInputData.Mode;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    protected Label createLabel (Node node, ShortestPathData data) {
    	LabelStar res = new LabelStar(node);
    	
    	double val_speed = node.getPoint().distanceTo(data.getDestination().getPoint());
    	
    	if (data.getMode() == Mode.TIME) { // pour transformer la distance (en m) en un temps 
    		int vitesse = data.getMaximumSpeed();    		
    		if (vitesse == -1) { //peut valoir -1 donc on va la chercher dans graph 
    			vitesse = data.getGraph().getGraphInformation().getMaximumSpeed();
    		}
    		
    		val_speed = 3.6 * val_speed /(double)vitesse; // on doit diviser la vitesse par 3.6 car on a des km/h alors qu'on doit manipuler des metres et des secondes donc on passe en m/s
    	}
    	
    	res.setCoutEstime(val_speed);
    	return res;
    }
}
