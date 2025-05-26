package com.mayank.atm_lld.dispense;

public class FiveHundredDispenser extends DispenseChain {
  public void dispense(int amount) {
    if (amount >= 500) {
      int num = amount / 500;
      int remainder = amount % 500;
      System.out.println("Dispensing " + num + " x 500");
      if (remainder != 0 && next != null) next.dispense(remainder);
    } else {
      super.dispense(amount);
    }
  }
}
