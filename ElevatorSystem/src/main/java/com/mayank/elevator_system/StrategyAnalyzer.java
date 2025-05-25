package com.mayank.elevator_system;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import com.mayank.elevator_system.strategies.movement.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyAnalyzer {

  public static class PerformanceMetrics {
    public final int totalDistance;
    public final int directionChanges;
    public final double averageWaitTime;
    public final int maxWaitTime;

    public PerformanceMetrics(
        int totalDistance, int directionChanges, double averageWaitTime, int maxWaitTime) {
      this.totalDistance = totalDistance;
      this.directionChanges = directionChanges;
      this.averageWaitTime = averageWaitTime;
      this.maxWaitTime = maxWaitTime;
    }

    @Override
    public String toString() {
      return String.format(
          "Distance: %d, Changes: %d, Avg Wait: %.1f, Max Wait: %d",
          totalDistance, directionChanges, averageWaitTime, maxWaitTime);
    }
  }

  public static Map<String, PerformanceMetrics> compareAllStrategies(
      List<Integer> requests, int currentFloor, ElevatorDirection direction) {

    Map<String, IElevatorMovementStrategy> strategies = new HashMap<>();
    strategies.put("FCFS", new FCFSMovementStrategy());
    strategies.put("SSTF", new SSTFMovementStrategy());
    strategies.put("SCAN", new SCANMovementStrategy());
    strategies.put("LOOK", new LOOKMovementStrategy());

    Map<String, PerformanceMetrics> results = new HashMap<>();

    for (Map.Entry<String, IElevatorMovementStrategy> entry : strategies.entrySet()) {
      String name = entry.getKey();
      IElevatorMovementStrategy strategy = entry.getValue();

      List<Integer> serviceOrder = strategy.getNextStops(requests, currentFloor, direction);
      PerformanceMetrics metrics = calculateMetrics(serviceOrder, requests, currentFloor);
      results.put(name, metrics);
    }

    return results;
  }

  private static PerformanceMetrics calculateMetrics(
      List<Integer> serviceOrder, List<Integer> originalRequests, int startFloor) {
    if (serviceOrder.isEmpty()) {
      return new PerformanceMetrics(0, 0, 0.0, 0);
    }

    // Calculate total distance
    int totalDistance = 0;
    int currentPos = startFloor;
    for (int floor : serviceOrder) {
      totalDistance += Math.abs(floor - currentPos);
      currentPos = floor;
    }

    // Calculate direction changes
    int directionChanges = 0;
    currentPos = startFloor;
    Boolean lastDirection = null;
    for (int floor : serviceOrder) {
      boolean goingUp = floor > currentPos;
      if (lastDirection != null && lastDirection != goingUp) {
        directionChanges++;
      }
      lastDirection = goingUp;
      currentPos = floor;
    }

    // Calculate wait times
    int totalWaitTime = 0;
    int maxWaitTime = 0;
    currentPos = startFloor;
    int timeStep = 0;

    for (int floor : serviceOrder) {
      timeStep += Math.abs(floor - currentPos);
      totalWaitTime += timeStep;
      maxWaitTime = Math.max(maxWaitTime, timeStep);
      currentPos = floor;
    }

    double averageWaitTime =
        originalRequests.isEmpty() ? 0.0 : (double) totalWaitTime / originalRequests.size();

    return new PerformanceMetrics(totalDistance, directionChanges, averageWaitTime, maxWaitTime);
  }
}
