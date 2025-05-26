package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.model.Card;

public class MaintenanceState implements ATMState {
  private static final MaintenanceState instance = new MaintenanceState();

  private MaintenanceState() {}

  public static MaintenanceState getInstance() {
    return instance;
  }

  @Override
  public void insertCard(ATMContext context, Card card) {
    System.out.println("ATM under maintenance.");
  }

  @Override
  public void enterPin(ATMContext context, String pin) {
    System.out.println("ATM under maintenance.");
  }

  @Override
  public void requestTransaction(ATMContext context, String type, int amount) {
    System.out.println("ATM under maintenance.");
  }

  @Override
  public void cancel(ATMContext context) {
    System.out.println("ATM under maintenance.");
  }
}
