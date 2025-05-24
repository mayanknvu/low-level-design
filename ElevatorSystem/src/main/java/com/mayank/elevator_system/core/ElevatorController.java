package com.mayank.elevator_system.core;

import java.util.Set;
import java.util.TreeSet;

public class ElevatorController {
  ElevatorCar elevator;
  Set<Integer> requestQueue = new TreeSet<>();

  public ElevatorController(ElevatorCar elevator) {
    this.elevator = elevator;
  }

  public ElevatorCar getElevator() {
    return elevator;
  }

  public void addRequest(ElevatorRequest request) {
    requestQueue.add(request.getFloor());
  }

  public Set<Integer> getRequestQueue() {
    return requestQueue;
  }

  public void clearRequest(int floor) {
    requestQueue.remove(floor);
  }
}
