package com.mayank.parking_lot.parking.strategies.pricing;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;

public class FlatRatePricingStrategy implements PricingStrategy {
  private static final double FLAT_RATE = 5.0;

  @Override
  public double calculateAmountCharged(ParkingTicket parkingTicket) {
    return FLAT_RATE;
  }
}
