package com.mayank.vending_machine.exceptions;

public class InvalidPaymentException extends RuntimeException {
  public InvalidPaymentException(String message) {
    super(message);
  }
}
