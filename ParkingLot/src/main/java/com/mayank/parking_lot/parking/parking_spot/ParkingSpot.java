package com.mayank.parking_lot.parking.parking_spot;

import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;
import com.mayank.parking_lot.vehicle.Vehicle;
import lombok.Getter;

@Getter
public abstract class ParkingSpot {
  private final String id;
  private final ParkingSpotType type;
  private final int floor;
  private boolean isFree;
  private Vehicle assignedVehicle;

  public ParkingSpot(String id, ParkingSpotType parkingSpotType, int floor) {
    this.id = id;
    this.type = parkingSpotType;
    this.isFree = true;
    this.floor = floor;
  }

  public void assignVehicle(Vehicle vehicle) {
    isFree = false;
    assignedVehicle = vehicle;
  }

  public void vacateSpot() {
    isFree = true;
    assignedVehicle = null;
  }
}
