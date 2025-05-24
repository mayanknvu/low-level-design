package com.mayank.elevator_system;

import java.util.List;

public class ElevatorSystem {
  List<ElevatorController> elevatorControllers;
  IElevatorSelectionStrategy elevatorSelectionStrategy;
  IElevatorMovementStrategy elevatorMovementStrategy;


  public ElevatorSystem(IElevatorSelectionStrategy elevatorSelectionStrategy, IElevatorMovementStrategy elevatorMovementStrategy) {
    this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    this.elevatorMovementStrategy = elevatorMovementStrategy;
  }
  public void addElevator(ElevatorController controller) {
    elevatorControllers.add(controller);
  }

  public void setElevatorSelectionStrategy(IElevatorSelectionStrategy strategy) {
    this.elevatorSelectionStrategy = strategy;
  }

  public void setElevatorMovementStrategy(IElevatorMovementStrategy elevatorMovementStrategy){
    this.elevatorMovementStrategy = elevatorMovementStrategy;
  }

  public void handleRequests(ElevatorRequest request){
    elevatorSelectionStrategy.assignElevator(elevatorControllers, request).addRequest(request);
  }

}
