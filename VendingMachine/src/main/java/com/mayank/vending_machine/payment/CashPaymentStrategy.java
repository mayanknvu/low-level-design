package com.mayank.vending_machine.payment;

public class CashPaymentStrategy implements IPaymentStrategy {
  @Override
  public double pay(double amountPaid) {
    System.out.println("Paid ₹" + amountPaid + " in cash.");
    return amountPaid;
  }
}
