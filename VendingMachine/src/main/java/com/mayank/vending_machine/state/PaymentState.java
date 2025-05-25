package com.mayank.vending_machine.state;

import com.mayank.vending_machine.VendingMachine;
import com.mayank.vending_machine.payment.IPaymentStrategy;

public class PaymentState implements VendingMachineState {

  private final VendingMachine machine;

  public PaymentState(VendingMachine machine) {
    this.machine = machine;
  }

  @Override
  public void selectItem(String slotId) {}

  @Override
  public void makePayment(IPaymentStrategy strategy) {
    double total = machine.getCart().getTotal(machine.getInventory());
    double paid = strategy.pay(total);
    double change = paid - total;
    machine.setChangeToReturn(change > 0 ? change : 0);

    machine.setCurrentState(machine.getDispenseState());
    machine.dispenseItems();
  }

  @Override
  public void dispenseItems() {}

  @Override
  public void issueChange() {}
}
