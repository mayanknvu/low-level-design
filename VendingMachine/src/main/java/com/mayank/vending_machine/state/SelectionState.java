package com.mayank.vending_machine.state;

import com.mayank.vending_machine.VendingMachine;
import com.mayank.vending_machine.inventory.Slot;
import com.mayank.vending_machine.payment.IPaymentStrategy;

public class SelectionState implements VendingMachineState {
  private final VendingMachine machine;

  public SelectionState(VendingMachine machine) {
    this.machine = machine;
  }

  @Override
  public void selectItem(String slotId) {
    try {
      Slot slot = machine.getInventory().getSlot(slotId);
      slot.getItem();
      machine.getCart().addItem(slotId);
      System.out.println("Selected item: " + slot.getItem().getName());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void makePayment(IPaymentStrategy strategy) {
    if (machine.getCart().getItems().isEmpty()) {
      System.out.println("Cart is empty. Select items first.");
      return;
    }
    machine.setCurrentState(machine.getPaymentState());
    machine.makePayment(strategy);
  }

  @Override
  public void dispenseItems() {}

  @Override
  public void issueChange() {}
}
