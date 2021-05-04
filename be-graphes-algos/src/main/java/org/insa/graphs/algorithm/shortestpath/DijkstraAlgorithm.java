package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;



public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        Graph g = data.getGraph();
        List <Label> labels = new ArrayList<Label>();
        BinaryHeap <Label> tas = new BinaryHeap <Label>();
        
        for (Node node : g.getNodes()) {
        	labels.add(node.getId(), new Label (node)); //liste de label contenant tous les noeuds accessible par leurs noeuds
        }
        
        Label Origin = labels.get(data.getOrigin().getId());
        Label Destination = labels.get(data.getDestination().getId());
        
        Origin.setCost(0);
        tas.insert(Origin);
        Origin.setDansTas(true);
        
        Label x = null; //label actuel
        Label y = null; //successeur
        while (!tas.isEmpty()) {
        	x = tas.deleteMin();
        	x.setMarque(true);
        	x.setDansTas(false);
        	
        	if (x.getSommet() == Destination.getSommet()) {
        		break;
        	}
        	
        	for (Arc successeur : x.getSommet().getSuccessors()) {
        		y=labels.get(successeur.getDestination().getId());
        		if (!y.getMarque()) {
        			if (y.getCost() > x.getCost() + (double)successeur.getLength()) {
        				y.setCost(x.getCost() + (double)successeur.getLength());
        				y.setPere(successeur);
        				if (y.getDansTas()) {
        					tas.remove(y); //on le supprime pour l'insérer ensuite à la bonne place
        					tas.insert(y);
        				}
        				else {
        					tas.insert(y);
        					y.setDansTas(true);
        				}
        			}
        		}
        	}
        }
        
        List <Arc> arcs = new ArrayList<Arc>();	
        
        Label k = Destination;
        while (k.getSommet() != Origin.getSommet()) {
        	arcs.add(k.getPere());
        	k = labels.get(k.getPere().getOrigin().getId());
        }
        Collections.reverse(arcs);
        solution = new ShortestPathSolution(data,Status.OPTIMAL, new Path(g, arcs));
              
        return solution;
    }
}
