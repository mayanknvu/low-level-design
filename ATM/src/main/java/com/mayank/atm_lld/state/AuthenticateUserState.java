package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.model.TransactionType;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;

public class AuthenticateUserState implements ATMState {
    private static final AuthenticateUserState instance = new AuthenticateUserState();

    private AuthenticateUserState() {}
    public static AuthenticateUserState getInstance() {
        return instance;
    }

    @Override
    public void insertCard(ATMContext context, Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATMContext context, String pin) {
        Card card = context.getATM().getCurrentCard();
        if (card.getPin().equals(pin)) {
            context.setState(TransactionState.getInstance());
            System.out.println("PIN correct. Proceed with transaction.");
        } else {
            System.out.println("Incorrect PIN. Card ejected.");
            context.getATM().clearCard();
            context.setState(IdleState.getInstance());
        }
    }

    @Override
    public void requestTransaction(ATMContext context, String type, int amount) {
        System.out.println("Enter PIN first.");
    }

    @Override
    public void cancel(ATMContext context) {
        System.out.println("Transaction canceled. Card ejected.");
        context.getATM().clearCard();
        context.setState(IdleState.getInstance());
    }
}
