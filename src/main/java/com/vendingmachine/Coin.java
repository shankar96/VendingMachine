package com.vendingmachine;

public enum Coin {
    JPY_10(10), JPY_50(50), JPY_100(100), JPY_500(500);
    private int value; // only 4 kinds of fixed coins available

    private Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
