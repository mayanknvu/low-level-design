package com.mayank.atm_lld.dispense;

public abstract class DispenseChain {
    protected DispenseChain next;

    public DispenseChain setNext(DispenseChain next) {
        this.next = next;
        return next;
    }

    public void dispense(int amount) {
        if (next != null) next.dispense(amount);
    }
}
