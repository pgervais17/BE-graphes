package org.insa.graphs.model;

public class Label {
	
	protected Node sommet_courant;
	protected boolean marque;
	protected int cout;
	private Arc pere;
	
	public Label (Node sommet) {
		this.sommet_courant= sommet;
		this.cout=1/0;
		this.pere=null;
	}
	
	private int getCost() {
		return this.cout;
	}

}
