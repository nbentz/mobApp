package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindWaypoint {
	private Scanner xFile, yFile;
	private HashMap<Double, ArrayList<TempNode>> xMap, yMap;
	double time, x,y, xCor, yCor;
	int vertex;
	
	//x, y represents the address, xCor, yCor represents waypoint location
	public FindWaypoint(double x, double y) {
		this.x = x;
		this.y = y;
		executeCorFiles();
		checkCors();
	}
	
	//returns the travel time from waypoint to address coordinates
	public double getTravelTime() {
		return this.time;
	}
	
	public int getWaypointVertex() {
		return this.vertex;
	}
	
	//opens xFile
	private void openXFile() {
		try {
			this.xFile = new Scanner(new File("x.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//opens yFile
	private void openYFile() {
		try {
			this.yFile = new Scanner(new File("y.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//executes opening and reading of x & y files
	private void executeCorFiles() {
		openXFile();
		openYFile();
		readXFile();
		readYFile();
		
	}
	
	//reads contents of xfile, stores x as a key w/ y values
	//and vertex its associated with.
	private void readXFile() {
		Scanner scan;
		this.xMap = new HashMap<Double,ArrayList<TempNode>>();
		while(this.xFile.hasNextLine()) {
			ArrayList<TempNode> yList = new ArrayList<TempNode>();
			String line = this.xFile.nextLine();
			scan = new Scanner(line);
			double x = scan.nextDouble();
			while(scan.hasNext()) {
				double y = scan.nextDouble();
				int vertex = scan.nextInt();
				TempNode temp = new TempNode(y,vertex);
				yList.add(temp);
			}
			this.xMap.put(x, yList);
			scan.close();
		}
		
		
	}
	
	//reads contents of yfile, stores y as a key w/ x values
	//and vertex its associated with.
	private void readYFile() {
		this.yMap = new HashMap<Double,ArrayList<TempNode>>();
		while(this.yFile.hasNextLine()) {
			Scanner scan;
			ArrayList<TempNode> xList = new ArrayList<TempNode>();
			String line = this.yFile.nextLine();
			scan = new Scanner(line);
			double y = scan.nextDouble();
			while(scan.hasNext()) {
				double x = scan.nextDouble();
				int vertex = scan.nextInt();
				TempNode temp = new TempNode(x,vertex);
				xList.add(temp);
			}
			this.yMap.put(y, xList);
			scan.close();
		}
		this.yFile.close();
	}
	
	private void checkCors() {
		if(this.xMap.containsKey(this.x)) {
			this.xCor = this.x;
			ArrayList<TempNode> list = this.xMap.get(this.x);
			for(int i = 0; i < list.size() - 1 ;i++) {
				if(this.y > list.get(i).getC() && this.y < list.get(i+1).getC()) {
					double y1 = Math.abs(list.get(i).getC() - this.y);
					double y2 = Math.abs(list.get(i+1).getC() - this.y);
					if(y1 <= y2) {
						this.time = calcSpeedY(y1);
						this.vertex = list.get(i).getVertex();
						this.yCor = y1;
					}else if(y1 > y2) {
						this.yCor = y2;
						this.time = calcSpeedY(y2);
						this.vertex = list.get(i+1).getVertex();
					}
					return;
				}else if(this.y == list.get(i).getC()) {
					this.yCor = this.y;
					this.time = 0;
					this.vertex = list.get(i).getVertex();
					return;
				}else if(this.y == list.get(i+1).getC()) {
					this.yCor = this.y;
					this.time = 0;
					this.vertex = list.get(i+1).getVertex();
					return;
				}
			}
		}else if(this.yMap.containsKey(this.y)){
			this.yCor = this.y;
			ArrayList<TempNode> list = this.yMap.get(this.y);
			for(int i = 0; i < list.size() - 1 ;i++) {
				if(this.x > list.get(i).getC() && this.x < list.get(i+1).getC()) {
					double x1 = Math.abs(list.get(i).getC() - this.x);
					double x2 = Math.abs(list.get(i+1).getC() - this.x);
					if(x1 <= x2) {
						this.time = calcSpeedX(x1);
						this.vertex = list.get(i).getVertex();
						this.xCor = x1;
					}else {
						this.xCor = x2;
						this.time = calcSpeedX(x2);
						this.vertex = list.get(i+1).getVertex();
					}
					return;
				}else if(this.x == list.get(i).getC()) {
					this.xCor = this.x;
					this.time = 0;
					this.vertex = list.get(i).getVertex();
					return;
				}else if(this.x == list.get(i+1).getC()) {
					this.xCor = this.x;
					this.time = 0;
					this.vertex = list.get(i+1).getVertex();
					return;
				}
			}
		}
	}
	
	private double calcSpeedY(double xChange) {
		double speed;
		if( this.y == 5.0) {
			speed = 75.0;
		}else if(this.y == 2.0) {
			speed = 55.0;
		}else{
			speed = 35.0;
		}
		return xChange / speed;
	}
	
	//helper method to calculate x-speed
	private double calcSpeedX(double yChange) {
		double speed;
		if( this.x == 4.0) {
			speed = 45.0;
		}else {
			speed = 35.0;
		}
		return yChange / speed;
	}
	
	public double getXVertex() {
		return this.xCor;
	}
	
	public double getYVertex() {
		return this.yCor;
	}
	
	//used to print out the xmap
	public void printXMap() {
		for(Map.Entry<Double, ArrayList<TempNode>> xVal : this.xMap.entrySet()) {
			ArrayList<TempNode> list = xVal.getValue();
			String rowStr =xVal.getKey() + "{";
			for(TempNode n : list) {
				rowStr += " (" + n.getC() + ", " + n.getVertex() + ")";
			}
			System.out.println(rowStr);
		}
	}
	
	//testing purposes only
	public void printYMap() {
		for(Map.Entry<Double, ArrayList<TempNode>> yVal : this.yMap.entrySet()) {
			ArrayList<TempNode> list = yVal.getValue();
			String rowStr =yVal.getKey() + "{";
			for(TempNode n : list) {
				rowStr += " (" + n.getC() + ", " + n.getVertex() + ")";
			}
			System.out.println(rowStr);
		}
	}
}
