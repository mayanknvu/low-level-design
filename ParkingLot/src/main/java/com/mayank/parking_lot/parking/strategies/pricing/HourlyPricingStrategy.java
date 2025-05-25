package com.mayank.parking_lot.parking.strategies.pricing;

import com.mayank.parking_lot.parking.ticket.ParkingTicket;
import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {
  private static final double PRICE_PER_HOUR = 1.0;

  @Override
  public double calculateAmountCharged(final ParkingTicket parkingTicket) {
    if (parkingTicket.getVacatedAt() == null) {
      throw new IllegalArgumentException("Vacated time must be set before calculating charges");
    }

    final long hoursParked = Duration.between(
            parkingTicket.getCreatedAt(),
            parkingTicket.getVacatedAt()
    ).toHours();

    // Charge for minimum 1 hour, then additional hours
    return Math.max(1, hoursParked) * PRICE_PER_HOUR;
  }
}
