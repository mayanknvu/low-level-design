package com.mayank.atm_lld.model;

import com.mayank.atm_lld.exception.InsufficientFundsException;
import com.mayank.atm_lld.exception.InvalidAmountException;

public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: " + balance);
        }
        balance -= amount;
        System.out.println("Account debited: " + amount + ". New balance: " + balance);
    }

    public void deposit(int amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Account credited: " + amount + ". New balance: " + balance);
    }
}
