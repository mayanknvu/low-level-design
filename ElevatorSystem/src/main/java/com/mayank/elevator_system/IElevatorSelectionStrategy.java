package com.mayank.elevator_system;

import java.util.List;

public interface IElevatorSelectionStrategy {
  ElevatorController assignElevator(List<ElevatorController> elevators, ElevatorRequest request);
}
