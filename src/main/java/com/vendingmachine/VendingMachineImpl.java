package com.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> coinInventory = new Inventory<Coin>();
    private Inventory<Item> itemInventory = new Inventory<Item>();
    private int currentCustomerBalance = 0;
    private List<Coin> currentCustomerCoinList = new ArrayList<>();
    private Item lastItemBought;
    private int lastChangeReturned;
    public Scanner scanner = new Scanner(System.in);

    public VendingMachineImpl() {
        defaultVendingMachineInventory();
    }

    public void defaultVendingMachineInventory() {
        Inventory<Item> itemInventory = new Inventory<>();
        itemInventory.putTotal(new Item("CANNED COFFEE", 120), 2);
        itemInventory.putTotal(new Item("WATER PET BOTTLE", 100), 0);
        itemInventory.putTotal(new Item("SPORT DRINKS", 150), 3);
        this.coinInventory = new Inventory<Coin>();
        this.itemInventory = itemInventory;

    }

    public VendingMachineImpl(Inventory<Item> itemInventory, Inventory<Coin> coinInventory) {
        this.coinInventory = coinInventory;
        this.itemInventory = itemInventory;
    }

    public Item getLastItemBought() {
        return lastItemBought;
    }

    public int getLastChangeReturned() {
        return lastChangeReturned;
    }

    @Override
    public void startVendingMachine() {
        executeVendingMachineState(VendingMachineState.DEFAULT_STATE);
    }

    private void executeVendingMachineState(VendingMachineState state) {
        state.execute(this);
    }

    public String getInput() {
        String input = scanner.nextLine();
        System.out.println("INPUT GIVEN:-"+input);
        return input;
    }

    private VendingMachineState getCommandStateFromInput() {
        VendingMachineState state = null;
        String input = getInput();
        if (input.equals("0")) {
            state = VendingMachineState.DEFAULT_STATE;
        } else if (input.equals("1")) {
            state = VendingMachineState.INSERT_COINS_STATE;
        } else if (input.equals("2")) {
            state = VendingMachineState.CHECK_ITEM_INVENTORY;
        } else if (input.equals("3")) {
            state = VendingMachineState.PURCHASE_ITEM_STATE;
        } else if (input.equals("4")) {
            state = VendingMachineState.RETURN_COINS_STATE;
        } else if (input.equals("X")) {
            state = VendingMachineState.STOP;
        } else {
            System.out.println("Wrong command");
            state = VendingMachineState.DEFAULT_STATE;
        }
        return state;
    }

    public void defaultCommandState() {
        System.out.println("Q. What you want to do?");
        System.out.println("Enter Command as below");
        System.out.println("\t=============================================================");
        System.out.println("\t|\tEnter <1> to Insert Coin");
        System.out.println("\t|\tEnter <2> to Check Items");
        if (currentCustomerBalance > 0) {
            System.out.println("\t|\tEnter <3> to Purchase Items");
            System.out.println("Enter <4> to Return Coin");
        }
        System.out.println("\t|\tEnter <X> to STOP");
        System.out.println("\t=============================================================");
        executeVendingMachineState(getCommandStateFromInput());
    }

    private void updateCurrentCustomerCoinInventory(Coin coin) {
        currentCustomerBalance += coin.getValue();
        currentCustomerCoinList.add(coin);
        coinInventory.add(coin);
    }

    @Override
    public void insertCoins() {
        System.out.println("Q. Choose Coin you want to insert?");
        System.out.println("\t=============================================================");
        System.out.println("\t|\tEnter <1> or <10> to Insert 10 JPY");
        System.out.println("\t|\tEnter <2> or <50> to Insert 50 JPY");
        System.out.println("\t|\tEnter <3> or <100> to Insert 100 JPY");
        System.out.println("\t|\tEnter <4> or <500> to Insert 500 JPY");
        System.out.println("\t=============================================================");
        String input = getInput();
        if (input.equals("1") || input.equals("10")) {
            updateCurrentCustomerCoinInventory(Coin.JPY_10);
        } else if (input.equals("2") || input.equals("50")) {
            updateCurrentCustomerCoinInventory(Coin.JPY_50);
        } else if (input.equals("3") || input.equals("100")) {
            updateCurrentCustomerCoinInventory(Coin.JPY_100);
        } else if (input.equals("4") || input.equals("500")) {
            updateCurrentCustomerCoinInventory(Coin.JPY_100);
        } else {
            System.out.println("Wrong Command");
            insertCoins();
            return;
        }
        System.out.println("Great your current Balance is now " + currentCustomerBalance + " JPY");
        executeVendingMachineState(VendingMachineState.DEFAULT_STATE);
    }

    private List<Coin> getChange(int changeAmount) {
        List<Coin> changes = new ArrayList<>();
        while (changeAmount > 0) {
            if (changeAmount >= Coin.JPY_10.getValue() && coinInventory.getTotalCount(Coin.JPY_10) >= 9) {
                changes.add(Coin.JPY_10);
                changeAmount -= Coin.JPY_10.getValue();
            } else if (changeAmount >= Coin.JPY_100.getValue() && coinInventory.getTotalCount(Coin.JPY_100) >= 4) {
                changes.add(Coin.JPY_100);
                changeAmount -= Coin.JPY_100.getValue();
            } else {
                return null;
            }
        }
        return changes;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void purchaseAItem() {
        itemInventory.printItemInventory();
        if (currentCustomerBalance > 0) {
            System.out.println("Select the Items you want to purchase by entering itemId");
            String input = getInput();
            if (!isInteger(input)) {
                input = input.hashCode() + "";
            }
            Item item = itemInventory.getInventoryByItemId(Integer.parseInt(input));
            if (item != null) {
                if (itemInventory.hasItem(item)) {
                    if (currentCustomerBalance < item.getItemPrice()) {
                        System.out.println("Insufficient balance to purchase you may insert more coins");
                    } else {
                        int changeAmount = currentCustomerBalance - item.getItemPrice();
                        List<Coin> changes = getChange(changeAmount);
                        if (changes == null) {
                            if (coinInventory.getTotalCount(Coin.JPY_10) < 9) {
                                System.out.println("Insufficient change you may insert more  10 JPY coins");
                            } else if (coinInventory.getTotalCount(Coin.JPY_100) < 4) {
                                System.out.println("Insufficient change you may insert more  100 JPY coins");
                            } else {
                                System.out.println("Insufficient change of coins");
                            }
                        } else {
                            currentCustomerCoinList = changes;
                            currentCustomerBalance = changeAmount;
                            lastItemBought = item;
                            System.out.println("Successfully purchased item (" + item.getItemName() + ") . Remaining balance is" + currentCustomerBalance);
                        }
                    }
                } else {
                    System.out.println("This item is sold out already");
                }
            } else {
                System.out.println("No items got selected");
            }
        }
        executeVendingMachineState(VendingMachineState.DEFAULT_STATE);
    }

    @Override
    public List<Coin> returnChange() {
        if (currentCustomerBalance > 0) {
            lastChangeReturned = currentCustomerBalance;
            System.out.println("Please Collect your change amount " + currentCustomerBalance + " JPY");
            Inventory<Coin> tempCoinInventory = new Inventory<>();// JUST for output
            List<Coin> tempCoins = currentCustomerCoinList;
            for (Coin coin : currentCustomerCoinList) {
                coinInventory.remove(coin);
                tempCoinInventory.add(coin);
            }
            tempCoinInventory.printCoinInventory();
            tempCoinInventory.clear();
            // reset
            currentCustomerBalance = 0;
            currentCustomerCoinList = new ArrayList<>();
            return tempCoins;

        }
        return null;
    }

    public void stop() {
        if (currentCustomerBalance > 0) {
            returnChange();
        }
        System.out.println("STOPPED");
    }
}
