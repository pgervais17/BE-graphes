package org.insa.graphs.algorithm.test;



import java.io.IOException;
import java.util.Arrays;



import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.RoadInformation;
import org.insa.graphs.model.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class AStarTest {
	// Small graph use for tests
	private static Graph graph;

	// List of nodes
	private static Node[] nodes;

	// List of arcs in the graph, a2b is the arc from node A (0) to B (1).
	@SuppressWarnings("unused")
	private static Arc a2b, a2c, b2d, b2e, b2f, c2a, c2b, c2f, e2c, e2d, e2f, f2e;

	@BeforeClass
	public static void initAll() throws IOException {

		// Define roads
		RoadInformation RoadInfo = new RoadInformation(RoadType.UNCLASSIFIED, null, true, 1, null);

		// Create nodes
		nodes = new Node[6];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = new Node(i, null);
		}

		// Add arcs...
		a2b = Node.linkNodes(nodes[0], nodes[1], 7, RoadInfo, null);
		a2c = Node.linkNodes(nodes[0], nodes[2], 8, RoadInfo, null);
		b2d = Node.linkNodes(nodes[1], nodes[3], 4, RoadInfo, null);
		b2e = Node.linkNodes(nodes[1], nodes[4], 1, RoadInfo, null);
		b2f = Node.linkNodes(nodes[1], nodes[5], 5, RoadInfo, null);
		c2a = Node.linkNodes(nodes[2], nodes[0], 7, RoadInfo, null);
		c2b = Node.linkNodes(nodes[2], nodes[1], 2, RoadInfo, null);
		c2f = Node.linkNodes(nodes[2], nodes[5], 2, RoadInfo, null);
		e2c = Node.linkNodes(nodes[4], nodes[2], 2, RoadInfo, null);
		e2d = Node.linkNodes(nodes[4], nodes[3], 2, RoadInfo, null);
		e2f = Node.linkNodes(nodes[4], nodes[5], 3, RoadInfo, null);
		f2e = Node.linkNodes(nodes[5], nodes[4], 3, RoadInfo, null);

		// Initialize the graph
		graph = new Graph("ID", "", Arrays.asList(nodes), null);

	}

	
	@Test
	public void testDoScenarioDistanceHG() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/haute-garonne.mapgr";
		
		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Haute-Garonne -------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ------");
		origine = 0 ;
		destination = 0;
		test.testScenario(mapName, 1,origine,destination);    
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 38926;
		destination = 59015;
		test.testScenario(mapName, 1,origine,destination);    	
	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : Existe ------------");
		origine = -1;
		destination = 59015;
		test.testScenario(mapName, 1,origine,destination);    	

		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : Existe ----------------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 38926;
		destination = 200000;
		test.testScenario(mapName, 1,origine,destination);    	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = -1;
		destination = 200000;
		test.testScenario(mapName, 1,origine,destination);    	
	}

	
	@Test
	public void testDoScenarioTempsHG() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/haute-garonne.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Haute-Garonne -------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ------");
		origine = 0 ;
		destination = 0;
		test.testScenario(mapName, 0,origine,destination);    
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 38926;
		destination = 59015;
		test.testScenario(mapName, 0,origine,destination);    	
	
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : Existe ------------");
		origine = -1;
		destination = 59015;
		test.testScenario(mapName, 0,origine,destination);    	

		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : Existe ----------------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 38926;
		destination = 200000;
		test.testScenario(mapName, 0,origine,destination);    	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = -1;
		destination = 200000;
		test.testScenario(mapName, 0,origine,destination);    	
	}

	@Test
	public void testDoScenarioDistanceINSA() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/insa.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : INSA ----------------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ------");
		origine = 300 ;
		destination = 300;
		test.testScenario(mapName, 1,origine,destination);    
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 607;
		destination = 857;
		test.testScenario(mapName, 1,origine,destination);    	
	
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : Existe ------------");
		origine = 2000;
		destination = 857;
		test.testScenario(mapName, 1,origine,destination);    	

		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : Existe ----------------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 607;
		destination = 200000;
		test.testScenario(mapName, 1,origine,destination);    	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 2000;
		destination = 2000;
		test.testScenario(mapName, 1,origine,destination);   
	}

	@Test
	public void testDoScenarioTempsINSA() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/insa.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : INSA ----------------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ------");
		origine = 300 ;
		destination = 300;
		test.testScenario(mapName, 0,origine,destination);    
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 607;
		destination = 857;
		test.testScenario(mapName, 0,origine,destination);    	
	
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : Existe ------------");
		origine = 2000;
		destination = 857;
		test.testScenario(mapName, 0,origine,destination);    	

		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : Existe ----------------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 607;
		destination = 200000;
		test.testScenario(mapName, 0,origine,destination);    	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 2000;
		destination = 2000;
		test.testScenario(mapName, 0,origine,destination);   
	}
	
	@Test
	public void testDoScenarioDistanceCarreDense() throws Exception {


		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/carre-dense.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : CARRE DENSE ---------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 0;
		destination = 204097;
		test.testScenario(mapName, 1,origine,destination);    		
	}

	@Test
	public void testDoScenarioTempsCarreDense() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/carre-dense.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : CARRE DENSE ---------------------------######");
		System.out.println("#####----- Mode : TEMPS -------------------------------######");
		System.out.println();

		System.out.println("----- Cas d'un chemin simple ------");
		origine = 0;
		destination = 204097;
		test.testScenario(mapName, 0,origine,destination);    			
	}
	
	
	@Test
	public void testDoScenarioDistanceBelgique() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/belgium.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : BELGIQUE ------------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
	
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 9922;
		destination = 34328;
		test.testScenario(mapName, 1,origine,destination);    	
	
		System.out.println("----- Cas de sommets non connexes ------");
		origine = 9950;
		destination = 15860;
		test.testScenario(mapName, 1,origine,destination);    	

	}
	
	@Test
	public void testDoScenarioTempsBelgique() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/belgium.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : BELGIQUE ------------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
	
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 9922;
		destination = 34328;
		test.testScenario(mapName, 0,origine,destination);    	
	
		System.out.println("----- Cas de sommets non connexes ------");
		origine = 9950;
		destination = 15860;
		test.testScenario(mapName, 0,origine,destination);    	

	}

	@Test
	public void testDoScenarioMinTempsDistHG() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/haute-garonne.mapgr";
		
		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité sans oracle sur une carte-----######");
		System.out.println("#####----- Carte : Haute-Garonne -------------------------######");
		System.out.println();

		System.out.println("----- Cas d'un chemin nul ------");
		origine = 0 ;
		destination = 0;
		test.testScenarioSansOracle(mapName,origine,destination);   
		
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 38926;
		destination = 59015;
		test.testScenarioSansOracle(mapName,origine,destination);    	
	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : Existe ------------");
		origine = -1;
		destination = 59015;
		test.testScenarioSansOracle(mapName,origine,destination);   	

		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : Existe ----------------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = 38926;
		destination = 200000;
		test.testScenarioSansOracle(mapName,origine,destination);    	
		
		System.out.println("----- Cas de sommets inexistants ------");
		System.out.println("----- Origine : N'existe pas ----------");
		System.out.println("----- Destination : N'existe pas ------");
		origine = -1;
		destination = 200000; 
		test.testScenarioSansOracle(mapName,origine,destination);   
	}

	@Test
	public void testDoScenarioMinTempsDistCarreDense() throws Exception {
	
		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/carre-dense.mapgr";
		
		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité sans oracle sur une carte-----######");
		System.out.println("#####----- Carte : CARRE DENSE ---------------------------######");
		System.out.println();

		System.out.println("----- Cas d'un chemin simple ------");
		origine = 0;
		destination = 100052;
		test.testScenarioSansOracle(mapName,origine,destination);    
	}
	
	@Test
	public void testDoScenarioMinTempsDistBelgique() throws Exception {

		String mapName = "C:/Users/pgerv/Desktop/Données persos/INSA/3A-4A/3A/Graphes/Maps/belgium.mapgr";

		AStarTestWithMap test = new  AStarTestWithMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité sans oracle sur une carte-----######");
		System.out.println("#####----- Carte : BELGIQUE  -----------------------------######");
		System.out.println();
	
		System.out.println("----- Cas d'un chemin simple ------");
		origine = 9922;
		destination = 34328;
		test.testScenarioSansOracle(mapName,origine,destination);    	
	
		System.out.println("----- Cas de sommets non connexes ------");
		origine = 9950;
		destination = 15860;
		test.testScenarioSansOracle(mapName,origine,destination);    
	}

}
