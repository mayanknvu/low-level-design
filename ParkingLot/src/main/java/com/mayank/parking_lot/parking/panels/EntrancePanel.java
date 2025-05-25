package com.mayank.parking_lot.parking.panels;

import com.mayank.parking_lot.exceptions.ParkingFullException;
import com.mayank.parking_lot.parking.ParkingLot;
import com.mayank.parking_lot.parking.parking_spot.ParkingSpot;
import com.mayank.parking_lot.parking.ticket.ParkingTicket;
import com.mayank.parking_lot.vehicle.Vehicle;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntrancePanel {
  private final int id;
  private final int floor;

  public ParkingTicket getTicket(final Vehicle vehicle) throws ParkingFullException {
    final ParkingSpot parkingSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle);
    return buildParkingTicket(
        vehicle.getLicenseNumber(), parkingSpot.getId(), parkingSpot.getFloor());
  }

  public ParkingTicket buildParkingTicket(String vehicleReg, String spotId, int floor) {
    return ParkingTicket.builder()
        .id(UUID.randomUUID().toString())
        .createdAt(LocalDateTime.now())
        .spotId(spotId)
        .floor(floor)
        .vehicleReg(vehicleReg)
        .build();
  }
}
