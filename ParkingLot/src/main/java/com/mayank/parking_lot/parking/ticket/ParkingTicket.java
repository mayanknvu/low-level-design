package com.mayank.parking_lot.parking.ticket;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ParkingTicket {
  private final String id;
  private final LocalDateTime createdAt;
  @Setter private LocalDateTime vacatedAt;
  private String vehicleReg;
  private String spotId;
  private int floor;
  private double amount;

  public void updateAmount(double amt) {
    this.amount = amt;
  }
}
