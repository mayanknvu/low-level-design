package com.mayank.elevator_system.strategies.selection;

import com.mayank.elevator_system.core.ElevatorController;
import com.mayank.elevator_system.core.ElevatorRequest;
import java.util.List;

public interface IElevatorSelectionStrategy {
  ElevatorController assignElevator(List<ElevatorController> elevators, ElevatorRequest request);
}
