package com.vendingmachine;

public class Item {
    private String itemName; // Assuming name is unique for different items
    private int itemPrice;

    public Item(String name, int price){
        this.itemName = name;
        this.itemPrice = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemId(){
        return itemName.hashCode();
    }
}
