package com.mayank.elevator_system.core;

import static java.util.Objects.isNull;

import com.mayank.elevator_system.components.Floor;
import com.mayank.elevator_system.strategies.movement.IElevatorMovementStrategy;
import com.mayank.elevator_system.strategies.selection.IElevatorSelectionStrategy;
import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
  List<ElevatorController> elevatorControllers;
  IElevatorSelectionStrategy elevatorSelectionStrategy;
  IElevatorMovementStrategy elevatorMovementStrategy;
  private List<Floor> floors;
  private boolean isRunning = false;

  public ElevatorSystem(
      IElevatorSelectionStrategy elevatorSelectionStrategy,
      IElevatorMovementStrategy elevatorMovementStrategy,
      int numberOfFloors) {
    this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    this.elevatorMovementStrategy = elevatorMovementStrategy;
    this.elevatorControllers = new ArrayList<>();
    this.floors = new ArrayList<>();

    // Initialize floors
    for (int i = 1; i <= numberOfFloors; i++) {
      floors.add(new Floor(i));
    }
  }

  public void addElevator(ElevatorController controller) {
    elevatorControllers.add(controller);
  }

  public void setElevatorSelectionStrategy(IElevatorSelectionStrategy strategy) {
    this.elevatorSelectionStrategy = strategy;
  }

  public void setElevatorMovementStrategy(IElevatorMovementStrategy elevatorMovementStrategy) {
    this.elevatorMovementStrategy = elevatorMovementStrategy;
  }

  public void handleRequest(ElevatorRequest request) {
    ElevatorController assigned =
        elevatorSelectionStrategy.assignElevator(elevatorControllers, request);
    if (!isNull(assigned)) {
      assigned.addRequest(request);
      System.out.println(
          "Request for floor "
              + request.getFloor()
              + " assigned to Elevator "
              + assigned.getElevator().getId());
    }
  }

  public void startSystem() {
    isRunning = true;
    System.out.println("Elevator System Started with " + elevatorControllers.size() + " elevators");

    // Simulate system running
    while (isRunning && hasActiveRequests()) {
      processAllElevators();
      try {
        Thread.sleep(1000); // Simulate time passing
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
    System.out.println("All requests processed. System idle.");
  }

  private void processAllElevators() {
    for (ElevatorController controller : elevatorControllers) {
      processElevatorRequests(controller);
    }
  }

  private void processElevatorRequests(ElevatorController controller) {
    if (controller.getRequestQueue().isEmpty()) {
      return;
    }
    ElevatorCar elevator = controller.getElevator();
    List<Integer> requests = new ArrayList<>(controller.getRequestQueue());

    List<Integer> nextStops =
        elevatorMovementStrategy.getNextStops(
            requests, elevator.getCurrentFloor(), elevator.getDirection());

    if (!nextStops.isEmpty()) {
      int nextFloor = nextStops.get(0);
      elevator.moveToFloor(nextFloor);
      controller.clearRequest(nextFloor);
    }
  }

  private boolean hasActiveRequests() {
    return elevatorControllers
        .stream()
        .anyMatch(controller -> !controller.getRequestQueue().isEmpty());
  }

  public void stopSystem() {
    isRunning = false;
  }

  public List<Floor> getFloors() {
    return floors;
  }

  public Floor getFloor(int floorNumber) {
    return floors
        .stream()
        .filter(floor -> floor.getFloorNumber() == floorNumber)
        .findFirst()
        .orElse(null);
  }

  public void displaySystemStatus() {
    System.out.println("\n=== Elevator System Status ===");
    for (ElevatorController controller : elevatorControllers) {
      ElevatorCar elevator = controller.getElevator();
      System.out.println(
          "Elevator "
              + elevator.id
              + " - Floor: "
              + elevator.getCurrentFloor()
              + " - Direction: "
              + elevator.getDirection()
              + " - Pending Requests: "
              + controller.getRequestQueue());
    }
    System.out.println("==============================\n");
  }
}
