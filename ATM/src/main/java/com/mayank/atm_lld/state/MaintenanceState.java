package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.model.TransactionType;

public class MaintenanceState implements ATMState {
    private static final MaintenanceState instance = new MaintenanceState();

    private MaintenanceState() {}
    public static MaintenanceState getInstance() {
        return instance;
    }

    public void insertCard(ATMContext context, Card card) {
        System.out.println("ATM under maintenance.");
    }

    public void enterPin(ATMContext context, String pin) {
        System.out.println("ATM under maintenance.");
    }

    public void requestTransaction(ATMContext context, TransactionType type) {
        System.out.println("ATM under maintenance.");
    }

    public void cancel(ATMContext context) {
        System.out.println("ATM under maintenance.");
    }
}
