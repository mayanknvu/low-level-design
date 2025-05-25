package com.mayank.elevator_system.strategies.movement;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LOOKMovementStrategy implements IElevatorMovementStrategy {
  /*
      Like SCAN, but reverses direction immediately after serving the last request in current direction, without going to building extremes.

      Elevator Position: Floor 5, Moving UP
      Request Queue: [3, 8, 2, 7, 1, 9]
      Buliding: 10 floors (0-10)

      Service Order Calculation:
          Current: Floor 5, Direction: UP
          Requests above current floor (≥5): [8, 7, 9] → Sort ascending: [7, 8, 9]
          After serving 9 (highest UP request), immediately reverse direction
          Requests below: [3, 2, 1] → Sort descending: [3, 2, 1]

          Service Order: 7 → 8 → 9 → 3 → 2 → 1

     Movement Path
          Floor 5 → 7 → 8 → 9 → 3 → 2 → 1
          Total Distance: 2 + 1 + 1 + 6 + 1 + 1 = 12 floors
          (Same as SCAN in this example, but LOOK doesn't go to floor 10)

     Visual Representation:
          10 |
           9 |  ●  ← Reverse here (don't go to 10)
           8 |●
           7 |●
           6 |
           5 |★ (start)
           4 |      ↓
           3 |      ●
           2 |        ●
           1 |          ●
           0 |____________________
             Time: 1 2 3 4 5 6

    Pros
      -> More efficient than SCAN - No unnecessary travel to extremes
      -> No starvation - All requests eventually served
      -> Better response time - Faster direction changes
      -> Energy efficient - Less total travel distance
      -> Adaptive - Responds to actual request distribution

    Cons
      -> Slightly complex - Need to track highest/lowest requests
      -> Still has direction bias - Requests in current direction served first
      -> Edge case handling - Need special logic for boundary conditions
      -> Less predictable - Harder for users to estimate arrival time

    Best Use Case
      -> Most common choice for real elevator systems
      -> High-traffic buildings
      -> Buildings with uniform floor usage
      -> When predictability is important

  */
  @Override
  public List<Integer> getNextStops(
      List<Integer> currentRequests, int currentFloor, ElevatorDirection direction) {
    if (currentRequests.isEmpty()) {
      return new ArrayList<>();
    }

    List<Integer> result = new ArrayList<>();
    List<Integer> upRequests = new ArrayList<>();
    List<Integer> downRequests = new ArrayList<>();

    // Separate requests into up and down from current floor
    for (int floor : currentRequests) {
      if (floor >= currentFloor) {
        upRequests.add(floor);
      } else {
        downRequests.add(floor);
      }
    }

    // Sort requests
    Collections.sort(upRequests); // Ascending for upward travel
    Collections.sort(downRequests); // Ascending, will reverse for downward
    Collections.reverse(downRequests); // Descending for downward travel

    // LOOK difference: Only go in direction if there are requests in that direction
    if (direction == ElevatorDirection.UP || direction == ElevatorDirection.IDLE) {
      if (!upRequests.isEmpty()) {
        result.addAll(upRequests);
      }
      if (!downRequests.isEmpty()) {
        result.addAll(downRequests);
      }
    } else if (direction == ElevatorDirection.DOWN) {
      if (!downRequests.isEmpty()) {
        result.addAll(downRequests);
      }
      if (!upRequests.isEmpty()) {
        result.addAll(upRequests);
      }
    }

    return result;
  }
}
