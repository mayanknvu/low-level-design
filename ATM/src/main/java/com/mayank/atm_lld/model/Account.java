package com.mayank.atm_lld.model;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (amount > balance) throw new RuntimeException("Insufficient funds in account.");
        balance -= amount;
    }

    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid deposit amount.");
        balance += amount;
    }
}
