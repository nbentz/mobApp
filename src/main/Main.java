package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import aStar.*;
import graph.*;

//All of files in the packages aStar, graph, and main were written by Nicholas Bentzen 4/8/2018
public class Main {

	public static void main(String[] args) {
		//Prompts user to enter start and end coordinates
		System.out.print("Please enter x cor of start address:");
		Scanner scan = new Scanner(System.in);
		double xStart = scan.nextDouble();
		System.out.print("Please enter y cor of start address:");
		double yStart = scan.nextDouble();
		System.out.print("Please enter x cor of end address:");
		double xEnd = scan.nextDouble();
		System.out.print("Please enter y cor of end address:");
		double yEnd = scan.nextDouble();
		scan.close();
		
		//Finds closest start and end waypoints
		FindWaypoint start = new FindWaypoint(xStart, yStart);
		FindWaypoint end = new FindWaypoint(xEnd, yEnd);
		int startVertex = start.getWaypointVertex();
		int endVertex = end.getWaypointVertex();
		
		//Setup reads files and sets up nodes, edges and aStar data structures.
		Node endNode = new Node(-1,end.getXVertex(),end.getYVertex());
		Setup setup = new Setup(endNode);
		HashMap<Integer, ArrayList<Edge>> edgeMap = setup.getEdgeMap();
		HashMap<Integer, AStarRow> starMap = setup.getStarMap();
		
		//
		System.out.println("Start: " + startVertex + " End: " + endVertex);
		double additionalTime = start.getTravelTime() + end.getTravelTime();
		System.out.println("Time from start node and end node to closest Waypoint " + additionalTime);
		
		AStar aStar = new AStar(startVertex, endVertex, edgeMap, starMap);
		aStar.printStar();
		aStar.printPath();
		aStar.printTime(additionalTime);
	}

}
