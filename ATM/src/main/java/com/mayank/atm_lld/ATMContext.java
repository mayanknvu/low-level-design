package com.mayank.atm_lld;

import com.mayank.atm_lld.model.Card;
import com.mayank.atm_lld.state.ATMState;
import com.mayank.atm_lld.state.IdleState;

public class ATMContext {
  private static final ATMContext instance = new ATMContext(ATM.getInstance());

  private ATMState currentState;
  private final ATM atm;

  private ATMContext(ATM atm) {
    this.atm = atm;
    this.currentState = IdleState.getInstance();
  }

  public static ATMContext getInstance() {
    return instance;
  }

  public void setState(ATMState state) {
    this.currentState = state;
  }

  public void insertCard(Card card) {
    currentState.insertCard(this, card);
  }

  public void enterPin(String pin) {
    currentState.enterPin(this, pin);
  }

  public void requestTransaction(String type, int amount) {
    currentState.requestTransaction(this, type, amount);
  }

  public void cancel() {
    currentState.cancel(this);
  }

  public ATM getATM() {
    return atm;
  }
}
