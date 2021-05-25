package org.insa.graphs.algorithm;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.GraphStatistics;
import org.insa.graphs.model.AccessRestrictions.AccessMode;
import org.insa.graphs.model.AccessRestrictions.AccessRestriction;

public class ArcInspectorFactory {

    /**
     * @return List of all arc filters in this factory.
     */
    public static List<ArcInspector> getAllFilters() {
        List<ArcInspector> filters = new ArrayList<>();

        // Common filters:

        // No filter (all arcs allowed):
        filters.add(new ArcInspector() {
            @Override
            public boolean isAllowed(Arc arc) {
                return true;
            }

            @Override
            public double getCost(Arc arc) {
                return arc.getLength();
            }

            @Override
            public int getMaximumSpeed() {
                return GraphStatistics.NO_MAXIMUM_SPEED;
            }

            @Override
            public Mode getMode() {
                return Mode.LENGTH;
            }

            @Override
            public String toString() {
                return "Shortest path, all roads allowed";
            }
        });

        // Only road allowed for cars and length:
        filters.add(new ArcInspector() {
            @Override
            public boolean isAllowed(Arc arc) {
                return arc.getRoadInformation().getAccessRestrictions()
                        .isAllowedForAny(AccessMode.MOTORCAR, EnumSet.complementOf(EnumSet
                                .of(AccessRestriction.FORBIDDEN, AccessRestriction.PRIVATE)));
            }

            @Override
            public double getCost(Arc arc) {
                return arc.getLength();
            }

            @Override
            public int getMaximumSpeed() {
                return GraphStatistics.NO_MAXIMUM_SPEED;
            }

            @Override
            public Mode getMode() {
                return Mode.LENGTH;
            }

            @Override
            public String toString() {
                return "Shortest path, only roads open for cars";
            }
        });

        // Only road allowed for cars and time:

        filters.add(new ArcInspector() {
            @Override
            public boolean isAllowed(Arc arc) {
                return true;
            }

            @Override
            public double getCost(Arc arc) {
                return arc.getMinimumTravelTime();
            }

            @Override
            public int getMaximumSpeed() {
                return GraphStatistics.NO_MAXIMUM_SPEED;
            }

            @Override
            public Mode getMode() {
                return Mode.TIME;
            }

            @Override
            public String toString() {
                return "Fastest path, all roads allowed";
            }
        });

        filters.add(new ArcInspector() {
            @Override
            public boolean isAllowed(Arc arc) {
                return arc.getRoadInformation().getAccessRestrictions()
                        .isAllowedForAny(AccessMode.BICYCLE, EnumSet.complementOf(EnumSet
                                .of(AccessRestriction.FORBIDDEN, AccessRestriction.PRIVATE)));
            }

            @Override
            public double getCost(Arc arc) {
            	double length = arc.getLength(); 
            	double r = 1 ; // facteur de risque (on ne différencie pas suivant le type de route)
            	if (arc.getRoadInformation().getAccessRestrictions().isAllowedForAny(AccessMode
            	.MOTORCAR, EnumSet.complementOf(EnumSet.of(AccessRestriction.FORBIDDEN,
            	AccessRestriction.PRIVATE)))) {
            	
            	r = arc.getRoadInformation().getMaximumSpeed() ; 
            	}
            	return length * r ; 
            	}

            @Override
            public int getMaximumSpeed() {
                return 20; //vitesse moyenne d'un cycliste
            }

            @Override
            public Mode getMode() {
                return Mode.LENGTH; //on a une contrainte de longeur pas de temps (de plus on part du principe qu'on est à vitessse constante)
            }

            @Override
            public String toString() {
                return "Shortest path, maximum of security for bicyle";
            }
        });

        filters.add(new ArcInspector() {
            @Override
            public boolean isAllowed(Arc arc) {
                return arc.getRoadInformation().getAccessRestrictions()
                        .isAllowedForAny(AccessMode.MOTORCAR, EnumSet.complementOf(EnumSet
                                .of(AccessRestriction.FORBIDDEN, AccessRestriction.PRIVATE)));
            }

            @Override
            public double getCost(Arc arc) {
                return arc.getMinimumTravelTime();
            }

            @Override
            public int getMaximumSpeed() {
                return GraphStatistics.NO_MAXIMUM_SPEED;
            }

            @Override
            public Mode getMode() {
                return Mode.TIME;
            }

            @Override
            public String toString() {
                return "Fastest path, only roads open for cars";
            }
        });

        // Non-private roads for pedestrian and bicycle:
        filters.add(new ArcInspector() {

            @Override
            public boolean isAllowed(Arc arc) {
                return arc.getRoadInformation().getAccessRestrictions()
                        .isAllowedForAny(AccessMode.FOOT, EnumSet.complementOf(EnumSet
                                .of(AccessRestriction.FORBIDDEN, AccessRestriction.PRIVATE)));
            }

            @Override
            public double getCost(Arc arc) {
                return arc.getTravelTime(
                        Math.min(getMaximumSpeed(), arc.getRoadInformation().getMaximumSpeed()));
            }

            @Override
            public String toString() {
                return "Fastest path for pedestrian";
            }

            @Override
            public int getMaximumSpeed() {
                return 5;
            }

            @Override
            public Mode getMode() {
                return Mode.TIME;
            }
        });

        // Add your own filters here (do not forget to implement toString()
        // to get an understandable output!):

        return filters;
    }

}
