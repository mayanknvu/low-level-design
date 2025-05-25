package com.mayank.parking_lot.parking.strategies;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;

public interface PricingStrategy {
  double calculateAmountCharged(ParkingTicket parkingTicket);
}
