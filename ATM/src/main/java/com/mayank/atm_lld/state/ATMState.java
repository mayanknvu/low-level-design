package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.model.TransactionType;

public interface ATMState {
    void insertCard(ATMContext context, Card card);
    void enterPin(ATMContext context, String pin);
    void requestTransaction(ATMContext context, TransactionType type);
    void cancel(ATMContext context);
}
