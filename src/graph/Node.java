package graph;

public class Node {
	private int id;
	private double x, y;
	
	public Node(int label, double x, double y) {
		this.id = label;
		this.x = x;
		this.y = y;
	}
	
	public int getLabel() {
		return this.id;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
}
