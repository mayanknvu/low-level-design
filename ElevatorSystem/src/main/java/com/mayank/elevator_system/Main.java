package com.mayank.elevator_system;

import com.mayank.elevator_system.components.Floor;
import com.mayank.elevator_system.core.ElevatorCar;
import com.mayank.elevator_system.core.ElevatorController;
import com.mayank.elevator_system.core.ElevatorRequest;
import com.mayank.elevator_system.core.ElevatorSystem;
import com.mayank.elevator_system.core.enums.ElevatorRequestMode;
import com.mayank.elevator_system.strategies.movement.SCANMovementStrategy;
import com.mayank.elevator_system.strategies.selection.ClosestElevatorSelectionStrategy;

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
}
