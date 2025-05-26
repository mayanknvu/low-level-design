package com.mayank.atm_lld.state;

import com.mayank.atm_lld.ATMContext;
import com.mayank.atm_lld.dispense.*;
import com.mayank.atm_lld.exception.ATMOutOfCashException;
import com.mayank.atm_lld.exception.InsufficientFundsException;
import com.mayank.atm_lld.exception.InvalidAmountException;
import com.mayank.atm_lld.model.Account;
import com.mayank.atm_lld.model.Card;
import java.util.HashMap;
import java.util.Map;

public class TransactionState implements ATMState {
  private static final TransactionState instance = new TransactionState();

  private TransactionState() {}

  public static TransactionState getInstance() {
    return instance;
  }

  @Override
  public void insertCard(ATMContext context, Card card) {
    System.out.println("Card already inserted.");
  }

  @Override
  public void enterPin(ATMContext context, String pin) {
    System.out.println("Already authenticated.");
  }

  @Override
  public void requestTransaction(ATMContext context, String type, int amount) {
    Card currentCard = context.getATM().getCurrentCard();

    try {
      switch (type.toUpperCase()) {
        case "WITHDRAW":
          withdrawCash(context, amount, currentCard.getAccount());
          break;
        case "BALANCE":
          checkBalance(currentCard.getAccount());
          break;
        default:
          System.out.println("Invalid transaction type: " + type);
          return;
      }

      // After successful transaction, eject card
      System.out.println("Transaction completed. Card ejected.");
      context.getATM().clearCard();
      context.setState(IdleState.getInstance());

    } catch (Exception e) {
      System.out.println("Transaction failed: " + e.getMessage());
      context.getATM().clearCard();
      context.setState(IdleState.getInstance());
    }
  }

  private void withdrawCash(ATMContext context, int amount, Account account)
      throws InsufficientFundsException, ATMOutOfCashException, InvalidAmountException {

    // Validate amount
    if (amount <= 0) {
      throw new InvalidAmountException("Withdrawal amount must be positive");
    }

    if (amount % 100 != 0) {
      throw new InvalidAmountException("Amount must be in multiples of 100");
    }

    // Check ATM cash availability
    if (!context.getATM().hasSufficientCash(amount)) {
      throw new ATMOutOfCashException("ATM doesn't have sufficient cash");
    }

    // Check account balance
    if (account.getBalance() < amount) {
      throw new InsufficientFundsException("Insufficient account balance");
    }

    // Calculate denominations to dispense
    Map<Integer, Integer> notesToDispense = calculateNotesToDispense(context, amount);

    // Process withdrawal
    account.withdraw(amount);
    context.getATM().deductCash(notesToDispense);

    // Dispense cash using chain of responsibility
    context.setState(DispenseState.getInstance());
    dispenseCash(amount);

    System.out.println("Withdrawal successful!");
  }

  private Map<Integer, Integer> calculateNotesToDispense(ATMContext context, int amount)
      throws ATMOutOfCashException {
    Map<Integer, Integer> availableDenominations = context.getATM().getDenominations();
    Map<Integer, Integer> notesToDispense = new HashMap<>();

    // Sort denominations in descending order (2000, 500, 200, 100)
    int[] denominations = {2000, 500, 200, 100};

    int remainingAmount = amount;

    for (int denomination : denominations) {
      if (remainingAmount >= denomination
          && availableDenominations.getOrDefault(denomination, 0) > 0) {
        int notesNeeded = remainingAmount / denomination;
        int notesAvailable = availableDenominations.get(denomination);
        int notesToUse = Math.min(notesNeeded, notesAvailable);

        if (notesToUse > 0) {
          notesToDispense.put(denomination, notesToUse);
          remainingAmount -= (denomination * notesToUse);
        }
      }
    }

    if (remainingAmount > 0) {
      throw new ATMOutOfCashException(
          "Cannot dispense the exact amount with available denominations");
    }

    return notesToDispense;
  }

  private void checkBalance(Account account) {
    System.out.println("Account Number: " + account.getAccountNumber());
    System.out.println("Current Balance: ₹" + account.getBalance());
  }

  private void dispenseCash(int amount) {
    DispenseChain dispenseChain = new FiveHundredDispenser();
    dispenseChain.setNext(new HundredDispenser());

    System.out.println("Dispensing cash...");
    dispenseChain.dispense(amount);
  }

  @Override
  public void cancel(ATMContext context) {
    System.out.println("Transaction canceled. Card ejected.");
    context.getATM().clearCard();
    context.setState(IdleState.getInstance());
  }
}
