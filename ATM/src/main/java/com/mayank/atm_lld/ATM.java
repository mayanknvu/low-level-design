package com.mayank.atm_lld;

import com.mayank.atm_lld.model.Card;

public class ATM {
    private static final ATM instance = new ATM(100000);

    private int totalCash;
    private Card currentCard;

    private ATM(int initialCash) {
        this.totalCash = initialCash;
    }

    public static ATM getInstance() {
        return instance;
    }

    public int getTotalCash() {
        return totalCash;
    }

    public void deductCash(int amount) {
        if (amount > totalCash) throw new RuntimeException("ATM is out of cash.");
        totalCash -= amount;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void clearCard() {
        this.currentCard = null;
    }
}
