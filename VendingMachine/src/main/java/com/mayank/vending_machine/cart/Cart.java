package com.mayank.vending_machine.cart;

import com.mayank.vending_machine.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Integer> items = new HashMap<>();

    public void addItem(String slotId) {
        items.put(slotId, items.getOrDefault(slotId, 0) + 1);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public double getTotal(Inventory inventory) {
        return items.entrySet().stream()
                .mapToDouble(e -> inventory.getSlot(e.getKey()).getItem().getPrice() * e.getValue())
                .sum();
    }
}
