package com.mayank.parking_lot.parking.strategies;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;
import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {
  private static final double PRICE_PER_HOUR = 1.0;

  @Override
  public double calculateAmountCharged(final ParkingTicket parkingTicket) {
    final long hoursParked =
        Duration.between(parkingTicket.getCreatedAt(), parkingTicket.getVacatedAt()).toHours();
    return PRICE_PER_HOUR + hoursParked > 0 ? (hoursParked - 1) * PRICE_PER_HOUR : 0;
  }
}
