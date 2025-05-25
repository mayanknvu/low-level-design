package com.mayank.parking_lot.parking.strategies.pricing;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;

public interface PricingStrategy {
  double calculateAmountCharged(ParkingTicket parkingTicket);
}
