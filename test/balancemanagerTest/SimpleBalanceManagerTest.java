package balancemanagerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.BalanceManager;
import model.account.SimpleBalanceManagerImpl;

public class SimpleBalanceManagerTest {

    private static final String DIRECTORYPATH = "users/";
    private AccountManager account;
    private BalanceManager simpleAccount;
    private File file;
    private String username; 
    private String password; 
    private String age;
    
    @Before
    public void initAccount() {
        this.account = new AdvancedAccountManagerImpl();
        this.username = "Test";
        this.password = "Password";
        this.age = "21";
        logIn();
    }
    
    
    private void logIn() {
        this.file = new File(DIRECTORYPATH + this.username + ".Json");
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
        this.account.register(this.username, this.password, this.age);
        this.account.logger(this.username, this.password);
        this.simpleAccount = new SimpleBalanceManagerImpl(this.account);
    }
    
    @Test
    public void testSimpleDeposit() {
        logIn();
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
        this.simpleAccount.deposit(2);
        assertEquals(this.simpleAccount.getBalance(), 2, 0);
        this.simpleAccount.deposit(0);
        assertEquals(this.simpleAccount.getBalance(), 2, 0);
        this.simpleAccount.deposit(1.15);
        assertEquals(this.simpleAccount.getBalance(), 3.15, 0);
        this.simpleAccount.deposit(1.006);
        assertEquals(this.simpleAccount.getBalance(), 4.16, 0);
        this.simpleAccount.deposit(10.000000006);
        assertEquals(this.simpleAccount.getBalance(), 14.16, 0);
    }

    @Test
    public void testSimpleWithdraw() {
        logIn();
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
        assertTrue(this.simpleAccount.deposit(50));
        assertEquals(this.simpleAccount.getBalance(), 50, 0);
        assertTrue(this.simpleAccount.withdraw(1));
        assertEquals(this.simpleAccount.getBalance(), 49, 0);
        assertTrue(this.simpleAccount.withdraw(0));
        assertEquals(this.simpleAccount.getBalance(), 49, 0);
        assertTrue(this.simpleAccount.withdraw(9.55));
        assertEquals(this.simpleAccount.getBalance(), 39.45, 0);
        assertTrue(this.simpleAccount.withdraw(0.456));
        assertEquals(this.simpleAccount.getBalance(), 38.99, 0);
        assertTrue(this.simpleAccount.withdraw(0.0000000009));
        assertEquals(this.simpleAccount.getBalance(), 38.99, 0);
        assertTrue(this.simpleAccount.withdraw(38.99));
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
    }
    
    @Test
    public void testSimpleChangeBalance() {
        logIn();
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
        assertTrue(this.simpleAccount.changeBalance(50));
        assertEquals(this.simpleAccount.getBalance(), 50, 0);
        assertTrue(this.simpleAccount.changeBalance(1));
        assertEquals(this.simpleAccount.getBalance(), 1, 0);
        assertTrue(this.simpleAccount.changeBalance(1000));
        assertEquals(this.simpleAccount.getBalance(), 1000, 0);
    }
    
    @Test
    public void testSimpleGetBalance() {
        logIn();
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
        this.simpleAccount.deposit(2);
        assertEquals(this.simpleAccount.getBalance(), 2, 0);
        this.simpleAccount.withdraw(1);
        assertEquals(this.simpleAccount.getBalance(), 1, 0);
        this.simpleAccount.changeBalance(5);
        assertEquals(this.simpleAccount.getBalance(), 5, 0);
        this.simpleAccount.changeBalance(0);
        assertEquals(this.simpleAccount.getBalance(), 0, 0);
    }
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
    
}
