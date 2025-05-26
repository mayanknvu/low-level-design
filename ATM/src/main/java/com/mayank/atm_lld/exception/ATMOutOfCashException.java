package com.mayank.atm_lld.exception;

public class ATMOutOfCashException extends Exception {
    public ATMOutOfCashException(String message) {
        super(message);
    }
}
