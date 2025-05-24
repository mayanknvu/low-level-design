package com.mayank.elevator_system.strategies.selection;

import com.mayank.elevator_system.core.ElevatorController;
import com.mayank.elevator_system.core.ElevatorRequest;
import com.mayank.elevator_system.core.enums.ElevatorDirection;
import java.util.List;

public class ClosestElevatorSelectionStrategy implements IElevatorSelectionStrategy {
  @Override
  public ElevatorController assignElevator(
      List<ElevatorController> elevators, ElevatorRequest request) {
    ElevatorController closest = null;
    int minDistance = Integer.MAX_VALUE;

    for (ElevatorController elevator : elevators) {
      // Prefer idle elevators
      if (elevator.getElevator().getDirection() == ElevatorDirection.IDLE) {
        int distance = Math.abs(elevator.getElevator().getCurrentFloor() - request.getFloor());
        if (distance < minDistance) {
          minDistance = distance;
          closest = elevator;
        }
      }
    }

    // If no idle elevator found, pick the closest moving elevator
    if (closest == null) {
      for (ElevatorController elevator : elevators) {
        int distance = Math.abs(elevator.getElevator().getCurrentFloor() - request.getFloor());
        if (distance < minDistance) {
          minDistance = distance;
          closest = elevator;
        }
      }
    }

    return closest;
  }
}
