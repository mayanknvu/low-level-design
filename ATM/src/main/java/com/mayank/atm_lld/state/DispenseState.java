package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;

public class DispenseState implements ATMState {
    private static final DispenseState instance = new DispenseState();

    private DispenseState() {}
    public static DispenseState getInstance() {
        return instance;
    }

    @Override
    public void insertCard(ATMContext context, Card card) {
        System.out.println("Dispensing in progress. Please wait.");
    }

    @Override
    public void enterPin(ATMContext context, String pin) {
        System.out.println("Dispensing in progress.");
    }

    @Override
    public void requestTransaction(ATMContext context, String type, int amount) {
        System.out.println("Dispensing in progress. Please wait.");
    }

    @Override
    public void cancel(ATMContext context) {
        System.out.println("Cannot cancel during dispensing.");
    }
}
