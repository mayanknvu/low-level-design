package com.mayank.vending_machine.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
  private final Map<String, Slot> slots = new HashMap<>();

  public void addSlot(String slotId, Item item) {
    slots.put(slotId, new Slot(slotId, item));
  }

  public Slot getSlot(String slotId) {
    if (!slots.containsKey(slotId)) {
      throw new IllegalArgumentException("Slot " + slotId + " does not exist.");
    }
    return slots.get(slotId);
  }

  public void printInventory() {
    for (Slot slot : slots.values()) {
      System.out.println(
          "Slot " + slot.getSlotId() + ": " + (slot.isEmpty() ? "EMPTY" : slot.peek()));
    }
  }
}
