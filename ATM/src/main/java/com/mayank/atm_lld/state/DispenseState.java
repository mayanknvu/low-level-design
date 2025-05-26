package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.model.TransactionType;

public class DispenseState implements ATMState {
    private static final DispenseState instance = new DispenseState();

    private DispenseState() {}
    public static DispenseState getInstance() {
        return instance;
    }

    public void insertCard(ATMContext context, Card card) {
        System.out.println("Dispensing in progress. Please wait.");
    }

    public void enterPin(ATMContext context, String pin) {
        System.out.println("Dispensing in progress.");
    }

    public void requestTransaction(ATMContext context, TransactionType type) {
    }

    public void cancel(ATMContext context) {
        System.out.println("Cannot cancel during dispensing.");
    }
}
