package com.mayank.vending_machine.state;

import com.mayank.vending_machine.VendingMachine;
import com.mayank.vending_machine.payment.IPaymentStrategy;

public class IssueChangeState implements VendingMachineState{

    private final VendingMachine machine;

    public IssueChangeState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void issueChange() {
        System.out.println("Issued change: ₹" + machine.getChangeToReturn());
        machine.setChangeToReturn(0);
        machine.setCurrentState(machine.getSelectionState());
    }

    @Override public void selectItem(String slotId) {}
    @Override public void makePayment(IPaymentStrategy strategy) {}
    @Override public void dispenseItems() {}
}
