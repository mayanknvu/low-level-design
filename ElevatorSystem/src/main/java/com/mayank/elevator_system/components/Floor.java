package com.mayank.elevator_system.components;

import com.mayank.elevator_system.components.panel.HallPanel;
import com.mayank.elevator_system.core.enums.ElevatorDirection;

public class Floor {
  private final int floorNumber;
  private final HallPanel upPanel;
  private final HallPanel downPanel;

  public Floor(int floorNumber) {
    this.floorNumber = floorNumber;
    this.upPanel = new HallPanel(floorNumber, ElevatorDirection.UP);
    this.downPanel = new HallPanel(floorNumber, ElevatorDirection.DOWN);
  }

  public int getFloorNumber() {
    return floorNumber;
  }

  public HallPanel getUpPanel() {
    return upPanel;
  }

  public HallPanel getDownPanel() {
    return downPanel;
  }
}
