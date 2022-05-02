package com.gl.iitr.dsalab.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class FloorSizeDeliveryDetails {
	
	int floorSize;
	int dayOfDelivery;
	private int targetDayOfBuilding;
	
	FloorSizeDeliveryDetails(int floorSize, int dayOfDelivery) {
		this.dayOfDelivery = dayOfDelivery;
		this.floorSize = floorSize;
	}

	public int getTargetDayOfBuilding() {
		return targetDayOfBuilding;
	}

	public void setTargetDayOfBuilding(int targetDayOfBuilding) {
		this.targetDayOfBuilding = targetDayOfBuilding;
	}
}

class FloorSizeDetailsComparator implements Comparator<FloorSizeDeliveryDetails> {
	
	public int compare(FloorSizeDeliveryDetails f1, FloorSizeDeliveryDetails f2) {
		
		if(f1.floorSize < f2.floorSize) {
			return 1;
		}
		else if(f1.floorSize > f2.floorSize){
			return -1;
		}
		return 0;
	}
}

public class BuilderFloorSheduler {

	public BuilderFloorSheduler() {
		// TODO Auto-generated constructor stub
	}
	
	static void readDeliveryPlan() {

	}
	
	static void makeFoloorBuildPlan() {

	}
 	
	
	static void displayFloorBuildPlan() {
		
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		int option = 0;
		
		do {
			
			System.out.println("Enter the total no of floors in the building");
			int noOfFloors = scanner.nextInt();

			PriorityQueue<FloorSizeDeliveryDetails> floorSizeQueue = new PriorityQueue<FloorSizeDeliveryDetails>(noOfFloors, new FloorSizeDetailsComparator());

			for (int day = 1; day <= noOfFloors; day++) {
				
				System.out.println("Enter the floor size given on day : " + day);
				int size = scanner.nextInt(); 
				
				FloorSizeDeliveryDetails detail = new FloorSizeDeliveryDetails(size, day);
				floorSizeQueue.add(detail);
			}
			
			FloorSizeDeliveryDetails previousRef = null;
			
			Queue<FloorSizeDeliveryDetails> schedule = new LinkedList<FloorSizeDeliveryDetails>();
			
			while(!floorSizeQueue.isEmpty()) {
				FloorSizeDeliveryDetails detail = floorSizeQueue.poll();
				System.out.println(" ---> " + detail.dayOfDelivery + " " + detail.floorSize );
				
				if(previousRef == null) {
					previousRef = detail;
				}
				else if(detail.dayOfDelivery > previousRef.dayOfDelivery) {
					previousRef = detail;
				}
				
				detail.setTargetDayOfBuilding(previousRef.dayOfDelivery);
				schedule.offer(detail);
			}
			
			System.out.println("\n");
			while(!schedule.isEmpty()) {
				
				FloorSizeDeliveryDetails detail = schedule.poll();
				System.out.println(" ---> " + detail.floorSize + " " + detail.getTargetDayOfBuilding());
			}
			
			System.out.println("\nDo you want to repeat? :[ No-0, Yes-Any other value]");
			option = scanner.nextInt();
			

		} while (option != 0);
		
		scanner.close();
	}
}
