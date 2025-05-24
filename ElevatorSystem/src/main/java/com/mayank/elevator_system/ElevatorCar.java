package com.mayank.elevator_system;

public class ElevatorCar {
  int id;
  int currentFloor;
  Display display;
  ElevatorDirection dir;

  public ElevatorCar(int id, int currentFloor) {
    this.display = new Display();
    this.id = id;
    this.currentFloor = currentFloor;
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void moveToFloor(int floor) {
    if (floor > currentFloor) {
      dir = ElevatorDirection.UP;
    } else if (floor < currentFloor) {
      dir = ElevatorDirection.DOWN;
    } else {
      dir = ElevatorDirection.IDLE;
    }
    currentFloor = floor;
    dir = ElevatorDirection.IDLE;
    System.out.println("Elevator moved to floor: " + floor);
  }

  public ElevatorDirection getDirection() {
    return dir;
  }

  private void setDisplay() {
    display.setFloor(currentFloor);
    display.setDir(dir);
  }
}
