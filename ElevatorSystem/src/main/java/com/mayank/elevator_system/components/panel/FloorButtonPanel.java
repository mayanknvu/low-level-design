package com.mayank.elevator_system.components.panel;

import com.mayank.elevator_system.core.ElevatorRequest;
import com.mayank.elevator_system.core.enums.ElevatorRequestMode;
import java.util.*;

public class FloorButtonPanel {
  private final int elevatorId;
  private final List<Integer> floorButtons;

  public FloorButtonPanel(int elevatorId, int numberOfFloors) {
    this.elevatorId = elevatorId;
    this.floorButtons = new ArrayList<>();
    for (int i = 1; i <= numberOfFloors; i++) {
      floorButtons.add(i);
    }
  }

  public ElevatorRequest pressFloor(int floor) {
    return new ElevatorRequest(floor, null, ElevatorRequestMode.DIRECTIONAL_CALL);
  }

  public List<Integer> getAvailableButtons() {
    return floorButtons;
  }
}
