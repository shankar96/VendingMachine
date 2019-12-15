import com.vendingmachine.Item;
import com.vendingmachine.VendingMachine;
import com.vendingmachine.VendingMachineFactory;
import com.vendingmachine.VendingMachineImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class VendingMachineTest {
    @Test
    public void purchaseItemWithFullAmount(){
        VendingMachine vendingMachine = VendingMachineFactory.createDefaultVendingMachine();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n1\n1\n1\n3\n3\nCANNED COFFEE\nX".getBytes());
        System.setIn(in);
        ((VendingMachineImpl)vendingMachine).scanner = new Scanner(in);
        vendingMachine.startVendingMachine();
        Item item = ((VendingMachineImpl) vendingMachine).getLastItemBought();
        int change = ((VendingMachineImpl) vendingMachine).getLastChangeReturned();
        Assert.assertEquals(item.getItemName(), "CANNED COFFEE");
        Assert.assertEquals(change, 0);

        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }

    @Test
    public void purchaseItemWithLessAmount(){
        VendingMachine vendingMachine = VendingMachineFactory.createDefaultVendingMachine();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n1\n1\n3\nCANNED COFFEE\nX".getBytes());
        System.setIn(in);
        ((VendingMachineImpl)vendingMachine).scanner = new Scanner(in);
        vendingMachine.startVendingMachine();
        Item item = ((VendingMachineImpl) vendingMachine).getLastItemBought();
        int change = ((VendingMachineImpl) vendingMachine).getLastChangeReturned();
        Assert.assertEquals(item, null);
        Assert.assertEquals(change, 20);

        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }

    @Test
    public void returnMoneyInsufficientChangeLesser_10JPY_100JPY_COINS(){
        // even purchase amount is more it should not buy item
        VendingMachine vendingMachine = VendingMachineFactory.createDefaultVendingMachine();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n1\n1\n1\n3\n1\n1\n3\nCANNED COFFEE\nX".getBytes());
        System.setIn(in);
        ((VendingMachineImpl)vendingMachine).scanner = new Scanner(in);
        vendingMachine.startVendingMachine();
        Item item = ((VendingMachineImpl) vendingMachine).getLastItemBought();
        int change = ((VendingMachineImpl) vendingMachine).getLastChangeReturned();
        Assert.assertEquals(item, null);
        Assert.assertEquals(change, 130);

        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }

    @Test
    public void returnMoneyChangeMore_10JPY_100JPY_COINS(){
        // even purchase amount is more it should not buy item
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMachineWithCoinInventory();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n1\n1\n1\n3\n1\n1\n3\nCANNED COFFEE\nX".getBytes());
        System.setIn(in);
        ((VendingMachineImpl)vendingMachine).scanner = new Scanner(in);
        vendingMachine.startVendingMachine();
        Item item = ((VendingMachineImpl) vendingMachine).getLastItemBought();
        int change = ((VendingMachineImpl) vendingMachine).getLastChangeReturned();
        Assert.assertEquals(item.getItemName(), "CANNED COFFEE");
        Assert.assertEquals(change, 10);

        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }
}
