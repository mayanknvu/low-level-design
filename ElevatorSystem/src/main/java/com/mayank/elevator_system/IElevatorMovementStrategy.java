package com.mayank.elevator_system;

import java.util.List;

public interface IElevatorMovementStrategy {
    List<Integer> getNextStops(List<Integer> currentRequests, int currentFloor, ElevatorDirection direction);
}
