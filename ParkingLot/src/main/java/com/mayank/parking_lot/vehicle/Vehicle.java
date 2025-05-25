package com.mayank.parking_lot.vehicle;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;
import com.mayank.parking_lot.vehicle.enums.VehicleType;
import lombok.Getter;

@Getter
public abstract class Vehicle {
  private final String licenseNumber;
  private final VehicleType vehicleType;
  private ParkingTicket parkingTicket;

  public Vehicle(String licenseNumber, VehicleType vehicleType) {
    this.licenseNumber = licenseNumber;
    this.vehicleType = vehicleType;
  }

  public void assignTicket(ParkingTicket parkingTicket) {
    this.parkingTicket = parkingTicket;
  }
}
