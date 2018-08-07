package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import aStar.AStarRow;
import graph.*;

// This class sets up a hashmap of nodes and edges
public class Setup {
	private Scanner nodeFile, edgeFile;
	private HashMap<Integer, Node> nodeMap;
	
	private HashMap<Integer, ArrayList<Edge>> edgeMap;
	private HashMap<Integer, AStarRow> starMap;
	private Node endNode;
	
	//manually setup a hashmap of nodes and edges
	public Setup(Node endNode) {
		this.endNode = endNode;
		openNodeFile();
		readNodeFile();
		openEdgeFile();
		readEdgeFile();
	}
	
	//helper method opens the file
	private void openNodeFile() {
		try {
			this.nodeFile = new Scanner(new File("nodes.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//helper method to read through the file and setup hashmap of nodes.
	private void readNodeFile() {
		int i = 0;
		this.nodeMap = new HashMap<Integer, Node>();
		this.starMap = new HashMap<Integer, AStarRow>();
		while(this.nodeFile.hasNext()) {
			double x = Double.parseDouble(nodeFile.next());
			double y = Double.parseDouble(nodeFile.next());
			Node currNode = new Node(i,x,y);
			this.nodeMap.put(i,currNode);
			Heuristic h = new Heuristic(currNode,this.endNode);
			AStarRow currStar = new AStarRow(i, h.getHTime());
			this.starMap.put(i, currStar);
			i++;
		}
		this.nodeFile.close();
	}
	
	//opens edge file
	private void openEdgeFile() {
		try {
			this.edgeFile = new Scanner(new File("edges.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//helper method to read through the file and setup hashmap of nodes.
	private void readEdgeFile() {
		this.edgeMap = new HashMap<Integer, ArrayList<Edge>>();
		while(this.edgeFile.hasNext()) {
			int n1 = Integer.parseInt(edgeFile.next());
			int n2 = Integer.parseInt(edgeFile.next());
			double speed = Double.parseDouble(edgeFile.next());
			Edge curr = new Edge(this.nodeMap.get(n1), this.nodeMap.get(n2), speed);
			ArrayList<Edge> list;
			if(edgeMap.containsKey(n1)) {
				edgeMap.get(n1).add(curr);
			}else {
				list = new ArrayList<Edge>();
				list.add(curr);
				this.edgeMap.put(n1, list);
			}
		}
		this.edgeFile.close();
	}
	
	
		
	//returns the map of nodes
	public HashMap<Integer, Node> getNodeMap(){
		return this.nodeMap;
	}
	
	//returns the map of edges
	public HashMap<Integer, ArrayList<Edge>> getEdgeMap(){
		return this.edgeMap;
	}
	
	//returns the aStarMap
	public HashMap<Integer, AStarRow> getStarMap(){
		return this.starMap;
	}
	
}
