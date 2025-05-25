package com.mayank.parking_lot.parking;

import com.mayank.parking_lot.exceptions.ParkingFullException;
import com.mayank.parking_lot.parking.panels.EntrancePanel;
import com.mayank.parking_lot.parking.panels.ExitPanel;
import com.mayank.parking_lot.parking.parking_spot.*;
import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;
import com.mayank.parking_lot.parking.strategies.pricing.HourlyPricingStrategy;
import com.mayank.parking_lot.parking.strategies.pricing.PricingStrategy;
import com.mayank.parking_lot.parking.strategies.spot_assignment.SpotAssignmentStrategy;
import com.mayank.parking_lot.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ParkingLot {
  private final String id;
  private final ParkingManager parkingManager;
  private final List<EntrancePanel> entrances;
  private final List<ExitPanel> exits;
  private PricingStrategy pricingStrategy;

  public static final ParkingLot INSTANCE = new ParkingLot();

  private ParkingLot() {
    this.id = UUID.randomUUID().toString();
    parkingManager = new ParkingManager();
    this.entrances = new ArrayList<>();
    this.exits = new ArrayList<>();
    // Default pricing strategy
    this.pricingStrategy = new HourlyPricingStrategy();
  }

  public void addParkingSpot(int floor, String spotId, ParkingSpotType type) {
    ParkingSpot parkingSpot = createParkingSpot(spotId, floor, type);
    parkingManager.addParkingSpot(parkingSpot);
  }

  private ParkingSpot createParkingSpot(String spotId, int floor, ParkingSpotType type) {
    switch (type) {
      case HANDICAPPED:
        return new HandicappedParkingSpot(spotId, floor);
      case TWO_WHEELER:
        return new TwoWheelerParkingSpot(spotId, floor);
      case LMV:
        return new LmvParkingSpot(spotId, floor);
      case HMV:
        return new HmvParkingSpot(spotId, floor);
      default:
        throw new IllegalArgumentException("Unknown parking spot type: " + type);
    }
  }

  public ParkingSpot getParkingSpot(final Vehicle vehicle) throws ParkingFullException {
    return parkingManager.getParkingSpot(vehicle);
  }

  public void vacateParkingSpot(final String parkingSpotId) {
    parkingManager.vacateParkingSpot(parkingSpotId);
  }

  public void setSpotAssignmentStrategy(SpotAssignmentStrategy strategy) {
    parkingManager.setSpotAssignmentStrategy(strategy);
  }

  public void setPricingStrategy(PricingStrategy strategy) {
    this.pricingStrategy = strategy;
  }

  public void addEntrancePanel(EntrancePanel entrance) {
    entrances.add(entrance);
  }

  public void addExitPanel(ExitPanel exit) {
    exits.add(exit);
  }
}
