package graph;


public class Edge {
	private Node n1, n2;
	private double distance, speed, time;
	
	public Edge(Node n1, Node n2, double speed) {
		// TODO Auto-generated constructor stub
		this.n1 = n1;
		this.n2 = n2;
		this.speed = speed;
		configDist();
		configTime();
	}
	
	//Configures distance between two nodes
	private void configDist(){
		double x1 = this.n1.getX();
		double y1 = this.n1.getY();
		double x2 = this.n2.getX();
		double y2 = this.n2.getY();
		this.distance = Math.abs((x2 - x1)) + Math.abs((y2 - y1));
	}
	
	//Configures the time it takes to travel between two nodes
	private void configTime() {
		this.time = this.distance / this.speed;
	}
	
	//returns the travel time between node1 and 2
	public double getTime() {
		return this.time;
	}
	
	
	//returns node 1
	public Node getN1() {
		return this.n1;
	}
	
	//returns node 2
	public Node getN2() {
		return this.n2;
	}
}
