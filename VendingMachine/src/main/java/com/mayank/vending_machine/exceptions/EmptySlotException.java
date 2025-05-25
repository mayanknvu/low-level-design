package com.mayank.vending_machine.exceptions;

public class EmptySlotException extends RuntimeException {
    public EmptySlotException(String message) {
        super(message);
    }
}
