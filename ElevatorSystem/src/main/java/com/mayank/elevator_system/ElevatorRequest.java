package com.mayank.elevator_system;


public class ElevatorRequest {
  private final int floor;
  private final ElevatorDirection direction; // nullable for DESTINATION_DISPATCH
  private final ElevatorRequestMode mode;

  public ElevatorRequest(int floor, ElevatorDirection direction, ElevatorRequestMode mode) {
    this.floor = floor;
    this.direction = direction;
    this.mode = mode;
  }

  public int getFloor() {
    return floor;
  }

  public ElevatorDirection getDirection() {
    return direction;
  }

  public ElevatorRequestMode getMode() {
    return mode;
  }
}
