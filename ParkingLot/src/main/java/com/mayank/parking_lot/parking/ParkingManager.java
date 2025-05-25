package com.mayank.parking_lot.parking;

import com.mayank.parking_lot.exceptions.ParkingFullException;
import com.mayank.parking_lot.parking.parking_spot.ParkingSpot;
import com.mayank.parking_lot.parking.parking_spot.enums.ParkingSpotType;
import com.mayank.parking_lot.parking.strategies.spot_assignment.SpotAssignmentStrategy;
import com.mayank.parking_lot.vehicle.Vehicle;
import com.mayank.parking_lot.vehicle.enums.VehicleType;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParkingManager {
  private final Map<ParkingSpotType, Deque<ParkingSpot>> availableSpots;
  private final Map<String, ParkingSpot> usedSpots;
  private SpotAssignmentStrategy spotAssignmentStrategy;

  public ParkingManager() {
    availableSpots = new HashMap<>();
    usedSpots = new HashMap<>();
    availableSpots.put(ParkingSpotType.HANDICAPPED, new ConcurrentLinkedDeque<>());
    availableSpots.put(ParkingSpotType.LMV, new ConcurrentLinkedDeque<>());
    availableSpots.put(ParkingSpotType.HMV, new ConcurrentLinkedDeque<>());
    availableSpots.put(ParkingSpotType.TWO_WHEELER, new ConcurrentLinkedDeque<>());
  }

  public void setSpotAssignmentStrategy(SpotAssignmentStrategy strategy) {
    this.spotAssignmentStrategy = strategy;
  }

  public void addParkingSpot(final ParkingSpot parkingSpot) {
    final Optional<ParkingSpot> spot =
        availableSpots
            .get(parkingSpot.getType())
            .stream()
            .filter(pS -> pS.getId().equals(parkingSpot.getId()))
            .findAny();
    if (spot.isPresent()) return;
    availableSpots.get(parkingSpot.getType()).add(parkingSpot);
  }

  public synchronized ParkingSpot getParkingSpot(final Vehicle vehicle)
          throws ParkingFullException {
    final ParkingSpotType parkingSpotType = getParkingSpotTypeForVehicle(vehicle.getVehicleType());

    if (!canPark(vehicle)) {
      throw new ParkingFullException("Sorry! No parking spots available at this moment!");
    }
    ParkingSpot parkingSpot;
    // Use strategy if available, otherwise fall back to FIFO
    if (spotAssignmentStrategy != null) {
      List<ParkingSpot> availableSpotList = new ArrayList<>(availableSpots.get(parkingSpotType));
      parkingSpot = spotAssignmentStrategy.assignParkingSpot(availableSpotList, vehicle);
      // Remove the selected spot from available spots
      availableSpots.get(parkingSpotType).remove(parkingSpot);
    } else {
      // Default FIFO behavior
      parkingSpot = availableSpots.get(parkingSpotType).poll();
    }

    parkingSpot.assignVehicle(vehicle);
    usedSpots.put(parkingSpot.getId(), parkingSpot);
    return parkingSpot;
  }

  public Optional<ParkingSpot> vacateParkingSpot(final String parkingSpotId) {
    final ParkingSpot parkingSpot = usedSpots.get(parkingSpotId);
    if (Objects.nonNull(parkingSpot)) {
      parkingSpot.vacateSpot();
      availableSpots.get(parkingSpot.getType()).addFirst(parkingSpot);
    }
    return Optional.ofNullable(parkingSpot);
  }

  public boolean canPark(final Vehicle vehicle) {
    return availableSpots.get(getParkingSpotTypeForVehicle(vehicle.getVehicleType())).size() > 0;
  }

  private ParkingSpotType getParkingSpotTypeForVehicle(final VehicleType vehicleType) {
    switch (vehicleType) {
      case TWO_WHEELER:
        return ParkingSpotType.TWO_WHEELER;
      case LMV:
        return ParkingSpotType.LMV;
      case HMV:
        return ParkingSpotType.HMV;
      default:
        throw new UnsupportedOperationException(
            "No parking spots available for this type of vehicle!");
    }
  }

  // Getter for available spots (useful for strategies)
  public Map<ParkingSpotType, Deque<ParkingSpot>> getAvailableSpots() {
    return Collections.unmodifiableMap(availableSpots);
  }
}
