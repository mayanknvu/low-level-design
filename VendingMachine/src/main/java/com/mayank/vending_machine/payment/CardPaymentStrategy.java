package com.mayank.vending_machine.payment;

public class CardPaymentStrategy implements IPaymentStrategy {
  @Override
  public double pay(double amountPaid) {
    System.out.println("Paid ₹" + amountPaid + " by card.");
    return amountPaid;
  }
}
