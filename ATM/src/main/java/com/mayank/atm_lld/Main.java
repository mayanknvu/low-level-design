package com.mayank.atm_lld;

import com.mayank.atm_lld.model.Account;
import com.mayank.atm_lld.model.Card;

public class Main {
  public static void main(String[] args) {
    // Create test data
    Account account1 = new Account("123456789", 50000);
    Card card1 = new Card("1234", account1);

    Account account2 = new Account("987654321", 1000);
    Card card2 = new Card("9876", account2);

    ATMContext atmContext = ATMContext.getInstance();
    ATM atm = ATM.getInstance();

    System.out.println("=== ATM Simulation Started ===");
    atm.displayCashInventory();

    // Test Case 1: Successful withdrawal with large amount (uses 2000 notes)
    System.out.println("\n--- Test Case 1: Large Withdrawal (₹15000) ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 15000);
    atm.displayCashInventory();

    // Test Case 2: Medium withdrawal (uses mixed denominations)
    System.out.println("\n--- Test Case 2: Medium Withdrawal (₹2700) ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 2700);
    atm.displayCashInventory();

    // Test Case 3: Small withdrawal (uses smaller denominations)
    System.out.println("\n--- Test Case 3: Small Withdrawal (₹800) ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 800);
    atm.displayCashInventory();

    // Test Case 4: Balance inquiry
    System.out.println("\n--- Test Case 4: Balance Inquiry ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("BALANCE", 0);

    // Test Case 5: Insufficient funds
    System.out.println("\n--- Test Case 5: Insufficient Funds ---");
    atmContext.insertCard(card2);
    atmContext.enterPin("9876");
    atmContext.requestTransaction("WITHDRAW", 2000);

    // Test Case 6: Attempt to exhaust a denomination
    System.out.println("\n--- Test Case 6: Large Withdrawal to Test Denomination Logic ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 25000);
    atm.displayCashInventory();

    // Test Case 7: Try withdrawal when certain denominations are low
    System.out.println("\n--- Test Case 7: Withdrawal with Limited Denominations ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 3000);
    atm.displayCashInventory();

    // Test Case 8: Wrong PIN
    System.out.println("\n--- Test Case 8: Wrong PIN ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("0000");

    // Test Case 9: Invalid amount (not multiple of 100)
    System.out.println("\n--- Test Case 9: Invalid Amount ---");
    atmContext.insertCard(card1);
    atmContext.enterPin("1234");
    atmContext.requestTransaction("WITHDRAW", 150);

    System.out.println("\n=== ATM Simulation Ended ===");
    atm.displayCashInventory();

    // Demonstrate cash refilling
    System.out.println("\n--- Maintenance: Refilling Cash ---");
    atm.addCash(2000, 20);
    atm.addCash(500, 50);
    atm.displayCashInventory();
  }
}
