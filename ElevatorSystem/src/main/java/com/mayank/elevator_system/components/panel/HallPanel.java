package com.mayank.elevator_system.components.panel;

import com.mayank.elevator_system.core.ElevatorRequest;
import com.mayank.elevator_system.core.enums.ElevatorDirection;
import com.mayank.elevator_system.core.enums.ElevatorRequestMode;

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
