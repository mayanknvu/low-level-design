package com.mayank.vending_machine.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
public class Item {
  private final String name;
  private final double price;
  @Setter private int qty;

  public boolean isOutOfStock() {
    return qty <= 0;
  }

  @Override
  public String toString() {
    return name + " (₹" + price + ") x" + qty;
  }

  public void decrementQuantity() {
    if (qty > 0) qty--;
  }
}
