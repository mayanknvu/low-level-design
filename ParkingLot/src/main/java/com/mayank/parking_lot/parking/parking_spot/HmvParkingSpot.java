package com.mayank.parking_lot.parking.parking_spot;

import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;

public class HmvParkingSpot extends ParkingSpot {
  public HmvParkingSpot(String id, int floor) {
    super(id, ParkingSpotType.HMV, floor);
  }
}
