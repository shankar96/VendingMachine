package com.vendingmachine;

public class VendingMachineFactory {
    public static VendingMachine createDefaultVendingMachine() {
        return new VendingMachineImpl();
    }

    public static VendingMachine createVendingMachineWithCoinInventory() {
        Inventory<Item> itemInventory = new Inventory<>();
        itemInventory.putTotal(new Item("CANNED COFFEE", 120), 2);
        itemInventory.putTotal(new Item("WATER PET BOTTLE", 100), 0);
        itemInventory.putTotal(new Item("SPORT DRINKS", 150), 3);

        Inventory<Coin> coinInventory = new Inventory<>();
        coinInventory.putTotal(Coin.JPY_10, 10);
        coinInventory.putTotal(Coin.JPY_50, 10);
        coinInventory.putTotal(Coin.JPY_100, 10);

        return new VendingMachineImpl(itemInventory, coinInventory);
    }
}
