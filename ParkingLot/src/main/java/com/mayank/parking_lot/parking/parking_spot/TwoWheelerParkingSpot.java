package com.mayank.parking_lot.parking.parking_spot;

import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;

public class TwoWheelerParkingSpot extends ParkingSpot {
  public TwoWheelerParkingSpot(String id, int floor) {
    super(id, ParkingSpotType.TWO_WHEELER, floor);
  }
}
