package com.mayank.parking_lot.parking.parking_spot;

import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;

public class HandicappedParkingSpot extends ParkingSpot {
  public HandicappedParkingSpot(String id, int floor) {
    super(id, ParkingSpotType.HANDICAPPED, floor);
  }
}
