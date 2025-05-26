package com.mayank.atm_lld.dispense;

public class HundredDispenser extends DispenseChain {
    public void dispense(int amount) {
        if (amount >= 100) {
            int num = amount / 100;
            int remainder = amount % 100;
            System.out.println("Dispensing " + num + " x 100");
            if (remainder != 0 && next != null) next.dispense(remainder);
        } else if (amount > 0) {
            throw new RuntimeException("Cannot dispense remaining amount: " + amount);
        }
    }
}

