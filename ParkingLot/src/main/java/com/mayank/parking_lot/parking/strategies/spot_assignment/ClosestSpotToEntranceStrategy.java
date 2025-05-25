package com.mayank.parking_lot.parking.strategies.spot_assignment;

import com.mayank.parking_lot.parking.parking_spot.ParkingSpot;
import com.mayank.parking_lot.vehicle.Vehicle;
import java.util.List;

public class ClosestSpotToEntranceStrategy implements SpotAssignmentStrategy {

  /*
    Keeping a min heap for each entrance and distance of each available parking spot

  Map<String, PriorityQueue<Integer>> map = new HashMap<>();
  public ClosestSpotToEntranceStrategy() {
    for(EntrancePanel ep: ParkingLot.INSTANCE.getEntrances()){
      //add map for each entrance and logic to have dist
    }
  }
  */

  @Override
  public ParkingSpot assignParkingSpot(List<ParkingSpot> availableSpots, Vehicle vehicle) {
    return null;
  }

  // when spot is vacated by parking manager, we call this function to add to its own DS if required
  public void vacatedParkingSpot(ParkingSpot parkingSpot) {
    return;
  }
}
