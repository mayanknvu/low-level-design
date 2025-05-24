package com.mayank.elevator_system.strategies.movement;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import java.util.List;

public class FCFSMovementStrategy implements IElevatorMovementStrategy {
  /*
  1. FCFS (First Come First Serve)
      Algorithm
      Serve requests in the exact order they were received, regardless of elevator position or direction.
      Example Scenario

      Elevator Position: Floor 5
      Request Queue: [3, 8, 2, 7, 1, 9]
      Service Order: 3 → 8 → 2 → 7 → 1 → 9

      Movement Path
          Floor 5 (start) → 3 → 8 → 2 → 7 → 1 → 9
          Total Distance: |5-3| + |3-8| + |8-2| + |2-7| + |7-1| + |1-9| = 2+5+6+5+6+8 = 32 floors

      Visual Representation:
          10 |                                    ●
           9 |
           8 |        ●
           7 |                    ●
           6 |
           5 | ★ (start)
           4 |
           3 | ●
           2 |            ●
           1 |                        ●
           0 |____________________________________
             Time: 1    2    3    4    5    6

      Pros
      -> Simple to implement - No complex logic required
      -> Fair - No request starvation, first request gets served first
      -> Predictable - Users know their position in queue
      -> No overhead - Minimal computational complexity O(1)

      Cons
      -> Inefficient movement - Lots of unnecessary back-and-forth travel
      -> High energy consumption - Maximum elevator motor usage
      -> Poor average wait time - Especially for requests in opposite directions
      -> User frustration - Elevator may pass user's floor multiple times before stopping

      Best Use Case
      -> Very low traffic buildings
      -> Systems where fairness is more important than efficiency
      -> Simple residential elevators
  */
  @Override
  public List<Integer> getNextStops(
      List<Integer> currentRequests, int currentFloor, ElevatorDirection direction) {
    return null;
  }
}
