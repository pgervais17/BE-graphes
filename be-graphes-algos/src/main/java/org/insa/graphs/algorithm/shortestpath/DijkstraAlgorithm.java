package org.insa.graphs.algorithm.shortestpath;

import java.util.HashMap;
import org.insa.graphs.model.Node;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        List <Label> labels = new ArrayList
        BinaryHeap <Label> tas = new BinaryHeap <Label>();
        HashMap <Node, Label> hashes = new HashMap <Node, Label> ();
        
        labels.insert(new Label(data.getOrigin()));
        
        
        graph g = data.getGraph();
        
        for (Node node : g.getNodes()) {
        	tas.put(node, new Label(node));
        }
        Label pluspetit = null;
        
        
        node dest = data.getDestination();
        while(!tas.isEmpty) {
        	
        
        	pluspetit.setMarque();
        	notifyNodeMarked(pluspetit.sommet_courant);
        	
            for (Arc arc : pluspetit.getCost().getSuccessors()) {

                // On test si le chemin n'est pas permis, on skip
                if (!data.isAllowed(arc)) continue;

                Node node = arc.getDestination();
                Label l = hashes.get(node);

                // S'il est déjà marqué, on skip
                if (l.marque) continue;

                double n_cout = pluspetit.getCost() + data.getCost(arc);

                if (l.cout > n_cout) {
                    tas.tryremove(l);
                    l.cout = n_cout;
                    l.pere = pluspetit.sommet_courant;
                    l.sommet_courant = node;
                    notifyNodeReached(node);
                    tas.insert(l);
                }
            }
        }
        
        /* while (sommet not(GetMark) {
            *     x=min(tas);
            *     x.SetMark(true);
            *     for(successors) {
            *      if !(y.getMark()){
                *     if (y.getcost() > x.getcost() + coutArc(x,y)) {
                *         cout (y) = cout(x) + coutArc(x,y);
                *         if exist(y,tas) {
                *             update(y,tas)
                *         }else{
                *                 insert(y,tas);
                *         }
            *        }
            *     }
            *    }
            *}*/
        // TODO:
        return solution;
    }

}
