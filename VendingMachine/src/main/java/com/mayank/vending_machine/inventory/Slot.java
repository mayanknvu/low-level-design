package com.mayank.vending_machine.inventory;

import com.mayank.vending_machine.exceptions.EmptySlotException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Slot {
  private final String slotId;
  private Item item;

  public Item getItem() {
    if (item == null || item.isOutOfStock()) {
      throw new EmptySlotException("Slot " + slotId + " is empty.");
    }
    return item;
  }

  public void dispense() {
    getItem().decrementQuantity();
    if (item.isOutOfStock()) item = null;
  }

  public void restock(Item newItem) {
    this.item = newItem;
  }

  public String getSlotId() {
    return slotId;
  }

  public boolean isEmpty() {
    return item == null;
  }

  public Item peek() {
    return item;
  }
}
