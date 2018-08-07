package aStar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import graph.Edge;

public class AStar {
	private HashMap<Integer, ArrayList<Edge>> edgeMap;
	private HashMap<Integer, AStarRow> starMap;
	private ArrayList<Integer> list;
	int startVertex, endVertex;
	
	public AStar(int startVertex, int endVertex, HashMap<Integer, ArrayList<Edge>> edgeMap,HashMap<Integer, AStarRow> starMap) {
		this.edgeMap = edgeMap;
		this.starMap = starMap;
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		runAStarAlgorithm();
	}
	
	//initial method to run the a star algorithm
	private void runAStarAlgorithm(){
		this.starMap.get(this.startVertex).setGTime(0);
		this.list = new ArrayList<Integer>();
		this.list.add(this.startVertex);
		runAStarAlgorithm(this.startVertex);
	}
	
	//runs the a star algorithm recursively
	private void runAStarAlgorithm(int parent) {
		ArrayList<Edge> list = this.edgeMap.get(parent);
		boolean flag = false;
		for(Edge e: list) {
			int child = e.getN2().getLabel();
			if(child == this.endVertex) {
				flag = true;
				this.list.add(child);
			}
			if(!this.starMap.get(child).getVisited()){
				double newGTime = e.getTime() + this.starMap.get(parent).getGTime();
				AStarRow row = starMap.get(child);
				double newFTime = row.getHTime() + newGTime;
				if( newFTime < row.getFTime()) {
					this.starMap.get(child).setGTime(newGTime);
					this.starMap.get(child).setPrev(e.getN1().getLabel());
				}
			}
		}
		this.starMap.get(parent).visited();
		if(flag) return;
		else {
			runAStarAlgorithm(determineNextNode());
		} 
	}
	
	//returns the node with the lowest f value
	private int determineNextNode() {
		int minNode = 0;
		for(int i = 0; i < this.starMap.size();i++){
			if(i != minNode) {
				AStarRow row = this.starMap.get(i);
				if(!row.getVisited() && row.getFTime() < this.starMap.get(minNode).getFTime()) {
					minNode = i;
				}
			}
		}
		this.list.add(minNode);
		return minNode;
	}
	
	//prints out contents of map.
	public void printStar() {
		String t = "\t";
		for(Map.Entry<Integer, AStarRow> row : this.starMap.entrySet()){
			AStarRow rowVal = row.getValue();
			System.out.println("Vertex: "+ row.getKey() + t + "G value: "+rowVal.getGTime() + t + "H value: "+  rowVal.getHTime() + t +"f value: " +  rowVal.getFTime() + t + "Prev Vertex: " + rowVal.getPrev() );
		}
	}
	
	//prints out how it found the path
	public void printSearch() {
		System.out.println("Fastest Path:");
		for(int i : this.list) {
			System.out.println("\t" + i);
		}
	}
	
	//prints the fastest path
	public void printPath(){
		int vertex = this.endVertex;
		Stack<Integer> stack = new Stack<Integer>();
		while(vertex != this.startVertex) {
			stack.push(vertex);
			vertex = this.starMap.get(vertex).getPrev();
			if(vertex == this.startVertex)
				stack.push(vertex);
		}
		int size = stack.size();
		String path = "";
		for(int i = 0; i < size; i++) {
			path+= stack.pop() + " ";
		}
		System.out.println("The fastest path from the start node to " + this.startVertex + " to " +this.endVertex + " to the end node: " + path);
	}
	
	//prints fastest travel time
	public void printTime(double addTime) {
		double time = addTime + this.starMap.get(this.endVertex).getGTime();
		System.out.println("The time it took to travel from " + this.startVertex + " to " + this.endVertex + ": " 
				+ time + " hour(s) = " + (time * 60) + " mins");
	}
}
