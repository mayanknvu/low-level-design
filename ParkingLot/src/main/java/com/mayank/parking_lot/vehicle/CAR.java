package com.mayank.parking_lot.vehicle;

import com.mayank.parking_lot.vehicle.enums.VehicleType;

public class CAR extends Vehicle {
  public CAR(String licenseNumber) {
    super(licenseNumber, VehicleType.LMV);
  }
}
