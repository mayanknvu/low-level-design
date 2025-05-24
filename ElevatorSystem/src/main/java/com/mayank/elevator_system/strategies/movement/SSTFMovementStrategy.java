package com.mayank.elevator_system.strategies.movement;

import com.mayank.elevator_system.core.enums.ElevatorDirection;

import java.util.ArrayList;
import java.util.List;

public class SSTFMovementStrategy implements IElevatorMovementStrategy {
  /*
      Always serve the request that requires the shortest travel distance from current position.
      Example Scenario

      Elevator Position: Floor 5
      Request Queue: [3, 8, 2, 7, 1, 9]

      Service Order Calculation:
      Step 1: At floor 5, distances to each request:
          - Floor 3: |5-3| = 2 ← Shortest
          - Floor 8: |5-8| = 3
          - Floor 2: |5-2| = 3
          - Floor 7: |5-7| = 2 ← Shortest
          - Floor 1: |5-1| = 4
          - Floor 9: |5-9| = 4

          Choose Floor 3 (or 7, both equal - let's pick 3)

          Step 2: At floor 3, distances to remaining requests:
          - Floor 8: |3-8| = 5
          - Floor 2: |3-2| = 1 ← Shortest
          - Floor 7: |3-7| = 4
          - Floor 1: |3-1| = 2
          - Floor 9: |3-9| = 6

          Continue this process...
     Final Service Order: 3 → 2 → 1 → 7 → 8 → 9
     Movement Path
          Floor 5 → 3 → 2 → 1 → 7 → 8 → 9
          Total Distance: 2 + 1 + 1 + 6 + 1 + 1 = 12 floors

     Visual Representation:
          10 |                        ●
           9 |
           8 |                    ●
           7 |                ●
           6 |
           5 | ★ (start)
           4 |
           3 | ●
           2 |    ●
           1 |      ●
           0 |________________________
             Time: 1  2  3  4  5  6

    Pros
      -> Efficient movement - Minimizes total travel distance
      -> Lower energy consumption - Less motor usage than FCFS
      -> Good for nearby requests - Excellent when requests are clustered
      -> Better average response time - Compared to FCFS

    Cons
      -> Starvation problem - Distant requests may never get served
      -> Unfair - Requests far from elevator get poor service
      -> Computational overhead - Need to calculate distances repeatedly O(n²)
      -> Unpredictable - Users don't know when they'll be served

    Best Use Case
      -> Buildings with clustered floor usage patterns
      -> Systems where efficiency is prioritized over fairness
      -> Low to medium traffic scenarios

  */
  @Override
  public List<Integer> getNextStops(List<Integer> currentRequests, int currentFloor, ElevatorDirection direction) {
    if (currentRequests.isEmpty()) {
      return new ArrayList<>();
    }

    List<Integer> result = new ArrayList<>();
    List<Integer> remainingRequests = new ArrayList<>(currentRequests);
    int currentPos = currentFloor;

    // Keep selecting the closest request until all are served
    while (!remainingRequests.isEmpty()) {
      int closestFloor = findClosestFloor(remainingRequests, currentPos);
      result.add(closestFloor);
      remainingRequests.remove(Integer.valueOf(closestFloor));
      currentPos = closestFloor;
    }

    return result;
  }

  private int findClosestFloor(List<Integer> requests, int currentPosition) {
    int closest = requests.get(0);
    int minDistance = Math.abs(currentPosition - closest);

    for (int floor : requests) {
      int distance = Math.abs(currentPosition - floor);
      if (distance < minDistance) {
        minDistance = distance;
        closest = floor;
      }
    }

    return closest;
  }
}
