package com.mayank.elevator_system.strategies.movement;

import com.mayank.elevator_system.core.enums.ElevatorDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCANMovementStrategy implements IElevatorMovementStrategy {
  /*
      Move in one direction serving all requests, then reverse direction and serve all requests in the opposite direction. Like a traditional elevator.


      Elevator Position: Floor 5, Moving UP
      Request Queue: [3, 8, 2, 7, 1, 9]
      Buliding: 10 floors (0-10)

      Service Order Calculation:
          Current: Floor 5, Direction: UP
          Requests above current floor (≥5): [8, 7, 9] → Sort ascending: [7, 8, 9]
          Requests below current floor (<5): [3, 2, 1] → Sort descending: [3, 2, 1]

      Service Order: 7 → 8 → 9 → 3 → 2 → 1

     Movement Path
          Floor 5 → 7 → 8 → 9 → 3 → 2 → 1
          Total Distance: 2 + 1 + 1 + 6 + 1 + 1 = 12 floors
     Visual Representation:
          10 |    ↑
           9 |    ●
           8 |  ●
           7 |●
           6 |
           5 |★ (start)
           4 |        ↓
           3 |        ●
           2 |          ●
           1 |            ●
           0 |____________________
             Time: 1 2 3 4 5 6
             Direction: UP→ ↓DOWN
    Pros
      -> No starvation - Every request will eventually be served
      -> Predictable - Users can estimate when elevator will arrive
      -> Efficient - Good balance between fairness and efficiency
      -> Realistic - Mimics how people expect elevators to work
      -> Good throughput - Handles high traffic well

    Cons
      -> End floor penalty - Requests at building extremes wait longer
      -> Direction change overhead - Time lost when reversing direction
      -> Not optimal for sparse requests - May travel through empty floors
      -> Edge inefficiency - Unnecessary travel to building boundaries

    Best Use Case
      -> Most common choice for real elevator systems
      -> High-traffic buildings
      -> Buildings with uniform floor usage
      -> When predictability is important

  */
  @Override
  public List<Integer> getNextStops(List<Integer> currentRequests, int currentFloor, ElevatorDirection direction) {
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
    Collections.sort(upRequests);           // Ascending for upward travel
    Collections.sort(downRequests);         // Ascending, will reverse for downward
    Collections.reverse(downRequests);      // Descending for downward travel

    // Determine service order based on current direction
    if (direction == ElevatorDirection.UP || direction == ElevatorDirection.IDLE) {
      // Serve upward requests first, then downward
      result.addAll(upRequests);
      result.addAll(downRequests);
    } else {
      // Serve downward requests first, then upward
      result.addAll(downRequests);
      result.addAll(upRequests);
    }

    return result;
  }
}
