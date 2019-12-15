package com.vendingmachine;

public enum VendingMachineState {
    DEFAULT_STATE {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.defaultCommandState();
        }
    },
    INSERT_COINS_STATE {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.insertCoins();
        }
    },
    PURCHASE_ITEM_STATE {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.purchaseAItem();
        }
    },
    CHECK_ITEM_INVENTORY {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.purchaseAItem();
        }
    },
    RETURN_COINS_STATE {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.returnChange();
            // restart another transaction
            vendingMachine.defaultCommandState();
        }
    },
    STOP {
        public void execute(VendingMachineImpl vendingMachine) {
            vendingMachine.stop();
        }
    };

    public abstract void execute(VendingMachineImpl vendingMachine);
}
