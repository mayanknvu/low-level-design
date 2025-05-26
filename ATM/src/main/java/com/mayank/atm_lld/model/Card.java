package com.mayank.atm_lld.model;

public class Card {
  private final String pin;
  private final Account account;

  public Card(String pin, Account account) {
    this.pin = pin;
    this.account = account;
  }

  public String getPin() {
    return pin;
  }

  public Account getAccount() {
    return account;
  }
}
