package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;

public interface ATMState {
    void insertCard(ATMContext context, Card card);
    void enterPin(ATMContext context, String pin);
    void requestTransaction(ATMContext context, String type, int amount);
    void cancel(ATMContext context);
}
