package com.mayank.elevator_system;

// Hall panel for up/down calls
public class HallPanel implements RequestPanel {
  private final int floor;
  private final ElevatorDirection direction;

  public HallPanel(int floor, ElevatorDirection direction) {
    this.floor = floor;
    this.direction = direction;
  }

  @Override
  public ElevatorRequest createRequest() {
    return new ElevatorRequest(floor, direction, ElevatorRequestMode.DIRECTIONAL_CALL);
  }
}
