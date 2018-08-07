package graph;

public class Heuristic {
	private double hTime;
	private Node n1, end;
	public Heuristic(Node n1, Node end) {
		this.n1 = n1;
		this.end = end;
		calcHTime();
	}
	
	//returns hTime
	public double getHTime() {
		return this.hTime;
	}
	
	//helper method to calculate hTime
	private void calcHTime() {
		double h1 = this.calcSpeedY(this.n1.getY()) + this.calcSpeedX(this.end.getX());
		double h2 = this.calcSpeedY(this.end.getY()) + this.calcSpeedX(this.n1.getX());
		if(h1<h2){
			this.hTime = h1;
		}
		else{ 
			this.hTime = h2;
		}
	}
	
	//helper method to calculate y-speed
	private double calcSpeedY(double y) {
		double speed;
		double xChange = Math.abs(this.n1.getX() - this.end.getX());
		if( y == 5.0) {
			speed = 75.0;
		}else if(y == 2.0) {
			speed = 55.0;
		}else{
			speed = 35.0;
		}
		return xChange / speed;
	}
	
	//helper method to calculate x-speed
	private double calcSpeedX(double x) {
		double speed;
		double yChange = Math.abs(this.n1.getY() - this.end.getY());
		if( x == 4.0) {
			speed = 45.0;
		}else {
			speed = 35.0;
		}
		return yChange / speed;
	}
}
