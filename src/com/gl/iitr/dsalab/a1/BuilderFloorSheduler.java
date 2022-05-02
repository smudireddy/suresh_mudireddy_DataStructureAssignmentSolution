package com.gl.iitr.dsalab.a1;

import java.util.Comparator;
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

	static PriorityQueue<FloorSizeDeliveryDetails> readDeliveryPlan(Scanner scanner) {

		System.out.println("Enter the total no of floors in the building");
		int noOfFloors = scanner.nextInt();

		PriorityQueue<FloorSizeDeliveryDetails> floorSizeQueue = new PriorityQueue<FloorSizeDeliveryDetails>(noOfFloors,
				new FloorSizeDetailsComparator());

		for (int day = 1; day <= noOfFloors; day++) {

			System.out.println("Enter the floor size given on day : " + day);
			int size = scanner.nextInt();

			FloorSizeDeliveryDetails detail = new FloorSizeDeliveryDetails(size, day);
			floorSizeQueue.add(detail);
		}

		return floorSizeQueue;
	}

	static Queue<FloorSizeDeliveryDetails> makeFoloorBuildPlan(Queue<FloorSizeDeliveryDetails> deliveryDetailQ) {

		FloorSizeDeliveryDetails previousRef = null;

		// Instead of priniting the plan here, lets return another list to use by
		// display method
		Queue<FloorSizeDeliveryDetails> schedule = new LinkedList<FloorSizeDeliveryDetails>();

		while (!deliveryDetailQ.isEmpty()) {

			FloorSizeDeliveryDetails detail = deliveryDetailQ.poll();

			if (previousRef == null) {
				previousRef = detail;
			} else if (detail.dayOfDelivery > previousRef.dayOfDelivery) {
				previousRef = detail;
			}

			detail.setTargetDayOfBuilding(previousRef.dayOfDelivery);
			schedule.offer(detail);
		}
		return schedule;
	}

	static void displayFloorBuildPlan(Queue<FloorSizeDeliveryDetails> builderPlanQ) {

		FloorSizeDeliveryDetails previousRef = null;

		while (!builderPlanQ.isEmpty()) {

			FloorSizeDeliveryDetails detail = builderPlanQ.poll();
			if (previousRef == null) {
				for (int day = 1; day <= detail.getTargetDayOfBuilding(); day++) {
					System.out.println("\n\nDay: " + day);
				}
				System.out.print(detail.floorSize + " ");
				previousRef = detail;
			} else if (previousRef.getTargetDayOfBuilding() == detail.getTargetDayOfBuilding()) {
				System.out.print(detail.floorSize + " ");
			} else {
				for (int day = previousRef.getTargetDayOfBuilding() + 1; day <= detail
						.getTargetDayOfBuilding(); day++) {
					System.out.println("\n\nDay: " + day);
				}
				System.out.print(detail.floorSize + " ");
				previousRef = detail;
			}
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int option = 0;

		do {

			// Note: We can solve this problem by many ways. here used PriorityQ to use a
			// custom comparator.

			// 1- fetch the delivery plan
			PriorityQueue<FloorSizeDeliveryDetails> floorSizeQ = readDeliveryPlan(scanner);

			// 2- Make the build plan based on floor size delivery plan
			Queue<FloorSizeDeliveryDetails> schedulePlanQ = makeFoloorBuildPlan(floorSizeQ);

			// 3-Display the prepared plan in the expected format
			displayFloorBuildPlan(schedulePlanQ);

			// 4-Repeat for another plan
			System.out.println("\n\nDo you want to repeat? :[ No-0, Yes-Any other value]");
			if (scanner.hasNextInt()) {
				option = scanner.nextInt();
			} else {
				option = Integer.parseInt(scanner.nextLine());
			}

		} while (option != 0);

		scanner.close();
	}
}
