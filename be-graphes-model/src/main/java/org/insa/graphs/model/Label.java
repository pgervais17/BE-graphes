package org.insa.graphs.model;

public class Label implements Comparable <Label> {
	
	protected Node sommet_courant;
	protected boolean marque;
	protected double cout;
	private Arc pere;
	
	@Override
	public int compareTo(Label other) {
		return Double.compare(this.cout, other.cout);
	}
	
	public Label (Node sommet) {
		this.sommet_courant= sommet;
		this.cout=1.0/0.0;
		this.pere=null;
	}
	
	private double getCost() {
		return this.cout;
	}
	
	private boolean getMarque() {
		return this.marque;
	}
	
	private void setMarque(boolean marque){
		this.marque=marque;
	}
	
	private void setArc (Arc pere) {
		this.pere=pere;
	}
	
	private void setCout (double cout) {
		this.cout=cout;
	}
	
}
