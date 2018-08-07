package aStar;

public class AStarRow {
	private boolean visited = false;
	private int vertex, prev;
	private double hTime, gTime, fTime;
	public AStarRow(int vertex, double hTime) {
		this.vertex = vertex;
		this.hTime = hTime;
		this.gTime = 100_000;
		this.fTime = 100_000;
		this.prev = -1;
	}
	
	// returns the curr vertex
	public int getVertex(){
		return this.vertex;
	}
	
	// returns the prevVertex
	public int getPrev() {
		return this.prev;
	}
	
	// sets the prevVertex
	public void setPrev(int prev) {
		this.prev = prev;
	}
	
	
	//returns hTime
	public double getHTime() {
		return this.hTime;
	}
	
	
	//sets gTime = current travel time from start node
	public void setGTime( double gTime) {
		this.gTime = gTime;
		setFTime();
	}
	
	//sets fTime = hTime = gTime
	private void setFTime() {
		this.fTime = this.gTime + this.hTime;
	}
	
	public void visited() {
		this.visited = true;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	public double getFTime() {
		return this.fTime;
	}
	
	public double getGTime() {
		return this.gTime;
	}
}
