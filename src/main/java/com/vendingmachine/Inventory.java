package com.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory<E> {
    private Map<E, Integer> inventory = new HashMap<>();
    private Map<Integer, E> itemIdMap = new HashMap<>();
    public int getTotalCount(E item) {
        return inventory.getOrDefault(item, 0);
    }

    public boolean hasItem(E item) {
        return getTotalCount(item) > 0;
    }

    public void add(E item) {
        inventory.put(item, getTotalCount(item) + 1);
        if(item instanceof Item) {
            itemIdMap.put(((Item) item).getItemId(), item);
        }
    }

    public E getInventoryByItemId(int id){
        return itemIdMap.get(id);
    }

    public void putTotal(E item, int totalCount) {
        inventory.put(item, totalCount);
        if(item instanceof Item) {
            itemIdMap.put(((Item) item).getItemId(), item);
        }
    }

    public void remove(E item) {
        if (hasItem(item)) {
            inventory.put(item, getTotalCount(item) - 1);
        }
    }

    public void clear() {
        inventory.clear();
        itemIdMap.clear();
    }

    public void printItemInventory(){
        System.out.println("\t=============================================================");
        System.out.println("\t|\tItemId | Item Name  | Item Price | Item Count");
        for (Map.Entry<E, Integer> entry: inventory.entrySet()){
            E item = entry.getKey();
            int count = entry.getValue();
            if(item instanceof Item){
                System.out.println("\t|\t<"+((Item) item).getItemId()+"> |"+((Item) item).getItemName()+" | "+ ((Item) item).getItemPrice()+" | "+ count);
            }
        }
        System.out.println("\t=============================================================");
    }

    public void printCoinInventory(){
        System.out.println("\t=============================================================");
        System.out.println("\t|\tSlNo. | Coin Name  | Coin Value | Coin Count");
        int i=1;
        for (Map.Entry<E, Integer> entry: inventory.entrySet()){
            E item = entry.getKey();
            int count = entry.getValue();
            if(item instanceof Coin){
                System.out.println("\t|\t"+i+". |"+((Coin) item).name()+" | "+ ((Coin) item).getValue()+" | "+ count);
                i++;
            }
        }
        System.out.println("\t=============================================================");
    }


}
