package com.mayank.elevator_system.components;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import lombok.Data;

@Data
public class Display {
  private int floor;
  private ElevatorDirection dir;
}
