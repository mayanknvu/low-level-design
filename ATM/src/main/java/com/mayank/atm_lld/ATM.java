package com.mayank.atm_lld;

import com.mayank.atm_lld.exception.ATMOutOfCashException;
import com.mayank.atm_lld.model.Card;
import java.util.HashMap;
import java.util.Map;

public class ATM {
  private static final ATM instance = new ATM();

  private Map<Integer, Integer> denominations; // denomination -> count
  private Card currentCard;

  private ATM() {
    initializeDenominations();
  }

  private void initializeDenominations() {
    denominations = new HashMap<>();
    // Initialize with some default cash inventory
    denominations.put(500, 100); // 100 notes of 500
    denominations.put(100, 200); // 200 notes of 100

    System.out.println("ATM initialized with cash inventory:");
    displayCashInventory();
  }

  public static ATM getInstance() {
    return instance;
  }

  public int getTotalCash() {
    return denominations
        .entrySet()
        .stream()
        .mapToInt(entry -> entry.getKey() * entry.getValue())
        .sum();
  }

  public Map<Integer, Integer> getDenominations() {
    return new HashMap<>(denominations);
  }

  public void deductCash(Map<Integer, Integer> notesToDispense) throws ATMOutOfCashException {
    // First verify we have enough notes of each denomination
    for (Map.Entry<Integer, Integer> entry : notesToDispense.entrySet()) {
      int denomination = entry.getKey();
      int required = entry.getValue();
      int available = denominations.getOrDefault(denomination, 0);

      if (available < required) {
        throw new ATMOutOfCashException(
            "Insufficient "
                + denomination
                + " notes. Required: "
                + required
                + ", Available: "
                + available);
      }
    }

    // Deduct the notes
    for (Map.Entry<Integer, Integer> entry : notesToDispense.entrySet()) {
      int denomination = entry.getKey();
      int count = entry.getValue();
      denominations.put(denomination, denominations.get(denomination) - count);
    }

    int totalAmount =
        notesToDispense
            .entrySet()
            .stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    System.out.println("ATM cash reduced by " + totalAmount + ". Remaining: " + getTotalCash());
  }

  public void setCurrentCard(Card card) {
    this.currentCard = card;
  }

  public Card getCurrentCard() {
    return currentCard;
  }

  public void clearCard() {
    this.currentCard = null;
  }

  public boolean hasSufficientCash(int amount) {
    return getTotalCash() >= amount;
  }

  public void displayCashInventory() {
    System.out.println("\n--- ATM Cash Inventory ---");
    int total = 0;
    for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
      int denomination = entry.getKey();
      int count = entry.getValue();
      int value = denomination * count;
      total += value;
      System.out.println("₹" + denomination + " x " + count + " = ₹" + value);
    }
    System.out.println("Total Cash: ₹" + total);
    System.out.println("------------------------");
  }

  // Method to add cash (for maintenance/refilling)
  public void addCash(int denomination, int count) {
    denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
    System.out.println("Added " + count + " notes of ₹" + denomination);
  }
}
