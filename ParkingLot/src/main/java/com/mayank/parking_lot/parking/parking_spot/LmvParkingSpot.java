package com.mayank.parking_lot.parking.parking_spot;

import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;

public class LmvParkingSpot extends ParkingSpot {
  public LmvParkingSpot(String id, int floor) {
    super(id, ParkingSpotType.LMV, floor);
  }
}
