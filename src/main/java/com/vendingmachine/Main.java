package com.vendingmachine;

public class Main {

  public static void main(String[] args) {
      System.out.println("Welcome to My VENDING MACHINE");
      VendingMachine vendingMachine = VendingMachineFactory.createDefaultVendingMachine();
      vendingMachine.startVendingMachine();


  }
}
