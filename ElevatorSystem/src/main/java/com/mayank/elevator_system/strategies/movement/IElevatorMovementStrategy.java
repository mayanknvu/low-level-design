package com.mayank.elevator_system.strategies.movement;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import java.util.List;

public interface IElevatorMovementStrategy {
  List<Integer> getNextStops(
      List<Integer> currentRequests, int currentFloor, ElevatorDirection direction);
}
