package org.insa.graphs.model;

public class LabelStar extends Label implements Comparable <Label>{
	protected double cout_estime;
	
	public LabelStar(Node sommet) {
		super(sommet);
		this.cout_estime=0.0;		
	}

	public double getCoutEstime() {
		return this.cout_estime;		
	}
	
	public void setCoutEstime(double cout) {
		this.cout_estime=cout;
	}
	
	public double getTotalCost() {
		return this.getCoutEstime() + this.getCost();
	}
}
