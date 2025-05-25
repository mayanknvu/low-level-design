package com.mayank.parking_lot.parking.panels;

import com.mayank.parking_lot.parking.ParkingLot;
import com.mayank.parking_lot.parking.ticket.ParkingTicket;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExitPanel {
  private final String id;

  public void scanAndVacate(final ParkingTicket parkingTicket) {
    ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getSpotId());
    parkingTicket.setVacatedAt(LocalDateTime.now());
  }
}
