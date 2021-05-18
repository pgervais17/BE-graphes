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
	protected int nbSommetsVisites;

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        //this.nbSommetsVisites=0;
    }
    
    protected Label createLabel (Node node, ShortestPathData data) {
    	return new Label(node);
    }
    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        boolean fin = false;
        
        Graph g = data.getGraph();
        List <Label> labels = new ArrayList<Label>();
        BinaryHeap <Label> tas = new BinaryHeap <Label>();
        
        for (Node node : g.getNodes()) {
        	labels.add(node.getId(), createLabel(node,data)); //liste de label contenant tous les noeuds accessible par leur id
        }
        
        Label Origin = labels.get(data.getOrigin().getId());
        Label Destination = labels.get(data.getDestination().getId());
        
        Origin.setCost(0);
        tas.insert(Origin);
        Origin.setDansTas(true);
        
        notifyOriginProcessed(Origin.getSommet());
        
        Label x = null; //label actuel
        Label y = null; //label du successeur
        
        /*tant qu'il y a des sommets non marqués*/
        while (!tas.isEmpty() && !fin) {
        	x = tas.deleteMin();
    		notifyNodeMarked(x.getSommet()); //notification node marqué
        	x.setMarque(true);
        	x.setDansTas(false);
        	
        	if (x.getSommet() == Destination.getSommet()) {
        		fin = true;
        	}
        	
        	//on parcourt tous les successeurs du sommet courant
        	for (Arc successeur : x.getSommet().getSuccessors()) {
        		y=labels.get(successeur.getDestination().getId());
        		
        		//if (y == null) {
        		notifyNodeReached(y.getSommet());
        		this.nbSommetsVisites++;
        			/*y = newLabel(successeur.getDestination(), data);
        			labels.add(successeur.getDestination().getId(),y);		
        		}*/
        		
        		if (!y.getMarque() && data.isAllowed(successeur)) {//isAllowed permet de vérifier s'il l'on peut utiliser l'arc selon le mode utilisé
        			if (y.getCost() > x.getCost() + data.getCost(successeur)) { //utiliser le data.getCost pour faire la distinction entre temps et distance plutôt que (double)successeur.getLength()) 
        				y.setCost(x.getCost() + data.getCost(successeur));//(double)successeur.getLength());
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
        notifyDestinationReached(Destination.getSommet());
        
        List <Arc> arcs = new ArrayList<Arc>();	
        
        Label k = Destination;
        if (k.getPere()==null) {
        	solution = new ShortestPathSolution(data,Status.INFEASIBLE);
        }
        else {
	        while (k.getSommet() != Origin.getSommet()) {
	        	arcs.add(k.getPere());
	        	k = labels.get(k.getPere().getOrigin().getId());
	        }
	        Collections.reverse(arcs);
	        solution = new ShortestPathSolution(data,Status.OPTIMAL, new Path(g, arcs));
	        }
              
        return solution;
    }
    
	
	/* Retourne le nombre de sommets visités */
	public int getNbSommetsVisites() {
		return this.nbSommetsVisites;
	}
}
