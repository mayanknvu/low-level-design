package com.mayank.parking_lot.parking.strategies.spot_assignment;

import com.mayank.parking_lot.parking.parking_spot.ParkingSpot;
import com.mayank.parking_lot.vehicle.Vehicle;
import java.util.List;

public interface SpotAssignmentStrategy {
  ParkingSpot assignParkingSpot(List<ParkingSpot> availableSpots, Vehicle vehicle);

  public void vacatedParkingSpot(ParkingSpot parkingSpot);
}
