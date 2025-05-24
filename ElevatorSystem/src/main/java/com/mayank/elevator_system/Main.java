package com.mayank.elevator_system;

import com.mayank.elevator_system.core.ElevatorCar;
import com.mayank.elevator_system.core.ElevatorController;
import com.mayank.elevator_system.core.ElevatorSystem;
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
    // Start the system
    system.startSystem();
  }
}
