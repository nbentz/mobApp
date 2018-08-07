package main;

public class TempNode {
	private double c;
	private int vertex;
	public TempNode(double c, int vertex) {
		this.c = c;
		this.vertex = vertex;
	}
	
	public int getVertex() {
		return this.vertex;
	}
	
	public double getC() {
		return this.c;
	}

}
