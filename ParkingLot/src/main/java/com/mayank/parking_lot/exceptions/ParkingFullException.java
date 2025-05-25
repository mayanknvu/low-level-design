package com.mayank.parking_lot.exceptions;

public class ParkingFullException extends RuntimeException {
  public ParkingFullException(String message) {
    super(message);
  }
}
