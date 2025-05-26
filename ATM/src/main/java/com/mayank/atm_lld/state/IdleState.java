package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;

public class IdleState implements ATMState {
  private static final IdleState instance = new IdleState();

  private IdleState() {}

  public static IdleState getInstance() {
    return instance;
  }

  @Override
  public void insertCard(ATMContext context, Card card) {
    context.getATM().setCurrentCard(card);
    context.setState(AuthenticateUserState.getInstance());
    System.out.println("Card inserted. Please enter your PIN.");
  }

  public void enterPin(ATMContext context, String pin) {
    System.out.println("Insert card first.");
  }

  public void requestTransaction(ATMContext context, String type, int amount) {
    System.out.println("Insert card first.");
  }

  public void cancel(ATMContext context) {
    System.out.println("Nothing to cancel.");
  }
}
