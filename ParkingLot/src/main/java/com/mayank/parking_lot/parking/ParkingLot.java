package com.mayank.parking_lot.parking;

import com.mayank.parking_lot.exceptions.ParkingFullException;
import com.mayank.parking_lot.parking.panels.EntrancePanel;
import com.mayank.parking_lot.parking.panels.ExitPanel;
import com.mayank.parking_lot.parking.parking_spot.ParkingSpot;
import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;
import com.mayank.parking_lot.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ParkingLot {
  private final String id;
  private final ParkingManager parkingManager;
  List<EntrancePanel> entrances;
  List<ExitPanel> exits;

  public static final ParkingLot INSTANCE = new ParkingLot();

  private ParkingLot() {
    this.id = UUID.randomUUID().toString();
    parkingManager = new ParkingManager();
    this.entrances = new ArrayList<>();
    this.exits = new ArrayList<>();
  }

  public void addParkingSpot(int floor, String id, ParkingSpotType type) {}

  public ParkingSpot getParkingSpot(final Vehicle vehicle) throws ParkingFullException {
    return parkingManager.getParkingSpot(vehicle);
  }

  public void vacateParkingSpot(final String parkingSpotId) {
    parkingManager.vacateParkingSpot(parkingSpotId);
  }
}
