package com.mayank.elevator_system;

import java.util.List;

public class ClosestElevatorStrategy implements IElevatorSelectionStrategy {
  @Override
  public ElevatorCar assignElevator(List<ElevatorCar> elevators, ElevatorRequest request) {
    ElevatorCar closest = null;
    int minDistance = Integer.MAX_VALUE;

    for (ElevatorCar elevator : elevators) {
      int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
      if (distance < minDistance) {
        minDistance = distance;
        closest = elevator;
      }
    }
    return closest;
  }
}
