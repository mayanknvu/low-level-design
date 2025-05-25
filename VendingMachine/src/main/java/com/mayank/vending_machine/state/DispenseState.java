package com.mayank.vending_machine.state;

import com.mayank.vending_machine.VendingMachine;
import com.mayank.vending_machine.payment.IPaymentStrategy;

import java.util.Map;

public class DispenseState implements VendingMachineState {
    private final VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void dispenseItems() {
        for (Map.Entry<String, Integer> entry : machine.getCart().getItems().entrySet()) {
            String slotId = entry.getKey();
            int qty = entry.getValue();
            for (int i = 0; i < qty; i++) {
                machine.getInventory().getSlot(slotId).dispense();
            }
            System.out.println("Dispensed " + qty + " x " + slotId);
        }
        machine.getCart().clear();
        if(machine.getChangeToReturn() > 0) {
            machine.setCurrentState(machine.getIssueChangeState());
            machine.issueChange();
        }
        else {
            machine.setCurrentState(machine.getSelectionState());
        }
    }

    @Override public void selectItem(String slotId) {}
    @Override public void makePayment(IPaymentStrategy strategy) {}
    @Override public void issueChange() {}
}
