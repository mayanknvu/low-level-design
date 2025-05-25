package com.mayank.vending_machine;

import com.mayank.vending_machine.cart.Cart;
import com.mayank.vending_machine.inventory.Inventory;
import com.mayank.vending_machine.payment.IPaymentStrategy;
import com.mayank.vending_machine.state.*;
import lombok.Getter;
import lombok.Setter;

@Getter
public class VendingMachine {
  private final Inventory inventory = new Inventory();
  private final Cart cart = new Cart();

  private final VendingMachineState selectionState = new SelectionState(this);
  private final VendingMachineState paymentState = new PaymentState(this);
  private final VendingMachineState dispenseState = new DispenseState(this);
  private final VendingMachineState issueChangeState = new IssueChangeState(this);
  @Setter private VendingMachineState currentState = selectionState;

  private double changeToReturn = 0;

  public static final VendingMachine INSTANCE = new VendingMachine();

  private VendingMachine() {}

  public void selectItem(String slotId) {
    currentState.selectItem(slotId);
  }

  public void makePayment(IPaymentStrategy strategy) {
    currentState.makePayment(strategy);
  }

  public void dispenseItems() {
    currentState.dispenseItems();
  }

  public void issueChange() {
    currentState.issueChange();
  }

  public double getChangeToReturn() {
    return changeToReturn;
  }

  public void setChangeToReturn(double change) {
    this.changeToReturn = change;
  }

  public VendingMachineState getSelectionState() {
    return selectionState;
  }

  public VendingMachineState getPaymentState() {
    return paymentState;
  }

  public VendingMachineState getDispenseState() {
    return dispenseState;
  }

  public VendingMachineState getIssueChangeState() {
    return issueChangeState;
  }
}
