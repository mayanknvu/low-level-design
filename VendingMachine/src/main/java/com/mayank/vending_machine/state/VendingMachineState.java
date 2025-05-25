package com.mayank.vending_machine.state;

import com.mayank.vending_machine.payment.IPaymentStrategy;

public interface VendingMachineState {
    void selectItem(String slotId);
    void makePayment(IPaymentStrategy strategy);
    void dispenseItems();
    void issueChange();
}
