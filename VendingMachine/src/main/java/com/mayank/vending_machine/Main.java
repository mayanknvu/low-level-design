package com.mayank.vending_machine;

import com.mayank.vending_machine.inventory.Item;
import com.mayank.vending_machine.payment.CardPaymentStrategy;
import com.mayank.vending_machine.payment.CashPaymentStrategy;

public class Main {
  public static void main(String[] args) {
    VendingMachine machine = VendingMachine.INSTANCE;

    // Initial stock
    machine.getInventory().addSlot("A1", Item.builder().name("Coke").price(40).qty(2).build());
    machine.getInventory().addSlot("B1", Item.builder().name("Chips").price(25).qty(1).build());
    machine.getInventory().addSlot("C1", Item.builder().name("Water").price(20).qty(3).build());

    System.out.println("===== Initial Inventory =====");
    machine.getInventory().printInventory();

    // ---- First user session ----
    System.out.println("\n===== User 1 Selecting Items =====");
    machine.selectItem("A1");
    machine.selectItem("C1");
    machine.selectItem("C1"); // selecting twice

    System.out.println("Proceeding to payment...");
    machine.makePayment(new CashPaymentStrategy());

    System.out.println("\n===== Inventory After User 1 =====");
    machine.getInventory().printInventory();

    // ---- Second user session with empty slot attempt ----
    System.out.println("\n===== User 2 Selecting Items =====");
    machine.selectItem("B1"); // only one in stock
    machine.selectItem("B1"); // this should trigger EmptySlotException

    System.out.println("Proceeding to payment...");
    machine.makePayment(new CardPaymentStrategy());

    System.out.println("\n===== Inventory After User 2 =====");
    machine.getInventory().printInventory();

    // ---- Third user session: slot C1 now has 1 left ----
    System.out.println("\n===== User 3 Selecting Item C1 x1 =====");
    machine.selectItem("C1");

    System.out.println("Proceeding to payment...");
    machine.makePayment(new CashPaymentStrategy());

    System.out.println("\n===== Final Inventory =====");
    machine.getInventory().printInventory();
  }
}
