package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.model.TransactionType;

public class TransactionState implements ATMState {
    private static final TransactionState instance = new TransactionState();

    private TransactionState() {}
    public static TransactionState getInstance() {
        return instance;
    }

    public void insertCard(ATMContext context, Card card) {
        System.out.println("Card already inserted.");
    }

    public void enterPin(ATMContext context, String pin) {
        System.out.println("Already authenticated.");
    }

    public void requestTransaction(ATMContext context, TransactionType type) {
    }

    private void checkBalance(){

    }

    public void cancel(ATMContext context) {
        System.out.println("Transaction canceled. Card ejected.");
        context.getATM().clearCard();
        context.setState(IdleState.getInstance());
    }
}
