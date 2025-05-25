package com.mayank.elevator_system;

import com.mayank.elevator_system.components.Floor;
import com.mayank.elevator_system.core.ElevatorCar;
import com.mayank.elevator_system.core.ElevatorController;
import com.mayank.elevator_system.core.ElevatorRequest;
import com.mayank.elevator_system.core.ElevatorSystem;
import com.mayank.elevator_system.core.enums.ElevatorDirection;
import com.mayank.elevator_system.core.enums.ElevatorRequestMode;
import com.mayank.elevator_system.strategies.movement.*;
import com.mayank.elevator_system.strategies.selection.ClosestElevatorSelectionStrategy;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // Create elevator system with 10 floors
    ElevatorSystem system =
        new ElevatorSystem(new ClosestElevatorSelectionStrategy(), new SCANMovementStrategy(), 10);

    // Add 3 elevators starting at different floors
    system.addElevator(new ElevatorController(new ElevatorCar(1, 1)));
    system.addElevator(new ElevatorController(new ElevatorCar(2, 5)));
    system.addElevator(new ElevatorController(new ElevatorCar(3, 8)));

    // Simulate requests from different floors
    simulateRequests(system);

    // Start the system
    system.startSystem();

    List<Integer> requests = Arrays.asList(3, 8, 2, 7, 1, 9);
    int currentFloor = 5;
    ElevatorDirection direction = ElevatorDirection.UP;

    System.out.println("\n\n\n================== Movement Strategy Comparison ==================");
    System.out.println("Current Floor: " + currentFloor);
    System.out.println("Current Direction: " + direction);
    System.out.println("Pending Requests: " + requests);
    System.out.println();

    // Test all strategies
    testStrategy("FCFS", new FCFSMovementStrategy(), requests, currentFloor, direction);
    testStrategy("SSTF", new SSTFMovementStrategy(), requests, currentFloor, direction);
    testStrategy("SCAN", new SCANMovementStrategy(), requests, currentFloor, direction);
    testStrategy("LOOK", new LOOKMovementStrategy(), requests, currentFloor, direction);
  }

  private static void simulateRequests(ElevatorSystem system) {
    // Simulate hall panel requests (people waiting on floors)
    Floor floor3 = system.getFloor(3);
    if (floor3 != null) {
      ElevatorRequest upRequest = floor3.getUpPanel().createRequest();
      system.handleRequest(upRequest);
    }

    Floor floor7 = system.getFloor(7);
    if (floor7 != null) {
      ElevatorRequest downRequest = floor7.getDownPanel().createRequest();
      system.handleRequest(downRequest);
    }

    // Simulate destination dispatch requests (people know exactly where they want to go)
    system.handleRequest(new ElevatorRequest(9, null, ElevatorRequestMode.DESTINATION_DISPATCH));
    system.handleRequest(new ElevatorRequest(2, null, ElevatorRequestMode.DESTINATION_DISPATCH));
    system.handleRequest(new ElevatorRequest(6, null, ElevatorRequestMode.DESTINATION_DISPATCH));

    // Show initial system status
    system.displaySystemStatus();
  }

  private static void testStrategy(
      String name,
      IElevatorMovementStrategy strategy,
      List<Integer> requests,
      int currentFloor,
      ElevatorDirection direction) {
    List<Integer> serviceOrder = strategy.getNextStops(requests, currentFloor, direction);
    int totalDistance = calculateTotalDistance(serviceOrder, currentFloor);
    int directionChanges = calculateDirectionChanges(serviceOrder, currentFloor);

    System.out.println("--- " + name + " Strategy ---");
    System.out.println("Service Order: " + serviceOrder);
    System.out.println("Total Distance: " + totalDistance + " floors");
    System.out.println("Direction Changes: " + directionChanges);
    System.out.println("Movement Path: " + getMovementPath(serviceOrder, currentFloor));
    System.out.println();
  }

  private static int calculateTotalDistance(List<Integer> serviceOrder, int startFloor) {
    if (serviceOrder.isEmpty()) return 0;

    int totalDistance = 0;
    int currentPos = startFloor;

    for (int floor : serviceOrder) {
      totalDistance += Math.abs(floor - currentPos);
      currentPos = floor;
    }

    return totalDistance;
  }

  private static int calculateDirectionChanges(List<Integer> serviceOrder, int startFloor) {
    if (serviceOrder.size() <= 1) return 0;

    int changes = 0;
    int currentPos = startFloor;
    Boolean lastDirection = null;

    for (int floor : serviceOrder) {
      boolean goingUp = floor > currentPos;
      if (lastDirection != null && lastDirection != goingUp) {
        changes++;
      }
      lastDirection = goingUp;
      currentPos = floor;
    }

    return changes;
  }

  private static String getMovementPath(List<Integer> serviceOrder, int startFloor) {
    if (serviceOrder.isEmpty()) return "No movement";

    StringBuilder path = new StringBuilder();
    path.append(startFloor);

    for (int floor : serviceOrder) {
      path.append(" → ").append(floor);
    }

    return path.toString();
  }
}
