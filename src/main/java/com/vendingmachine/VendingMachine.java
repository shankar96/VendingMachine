package com.vendingmachine;

import java.util.List;

public interface VendingMachine {
  void startVendingMachine();
  void insertCoins();
  void purchaseAItem();
  List <Coin> returnChange();
}
