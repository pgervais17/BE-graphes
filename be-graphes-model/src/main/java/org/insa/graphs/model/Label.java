package org.insa.graphs.model;

public class Label implements Comparable <Label> {
	
	protected Node sommet_courant;
	protected boolean marque;
	protected double cout;
	private Arc pere;
	private boolean dansTas; 
	
	@Override
	public int compareTo(Label other) {
		return Double.compare(this.getTotalCost(), other.getTotalCost());
	}
	
	public Label (Node sommet) {
		this.sommet_courant= sommet;
		this.marque=false;
		this.cout=1.0/0.0;
		this.pere=null;
		this.dansTas= false;
	}
	
	public Node getSommet() {
		return this.sommet_courant;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	public Arc getPere() {
		return this.pere;
	}
	
	public boolean getDansTas() {
		return this.dansTas;
	}
	
	public double getTotalCost() {
		return this.cout;
	}
	
	public void setMarque(boolean marque){
		this.marque=marque;
	}
	
	
	public void setCost (double cout) {
		this.cout=cout;
	}
	
	public void setPere (Arc pere) {
		this.pere=pere;
	}
	
	public void setDansTas (boolean dansTas) {
		this.dansTas=dansTas;
	}
	
}
