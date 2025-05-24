package com.mayank.elevator_system;

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
