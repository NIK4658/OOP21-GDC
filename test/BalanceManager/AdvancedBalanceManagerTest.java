package balancemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
import model.account.BalanceManager;

public class AdvancedBalanceManagerTest {

    private static final  int LONGINT = 123456789;
    private static final String DIRECTORYPATH = "users/";
    private AccountManager account;
    private BalanceManager advancedAccount;
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
        this.advancedAccount = new AdvancedBalanceManagerImpl(this.account);
    }
    
    @Test
    public void testAdvancedDeposit() {
        logIn();
        assertEquals(this.advancedAccount.getBalance(), 0, 0);
        assertTrue(this.advancedAccount.deposit(2));
        assertEquals(this.advancedAccount.getBalance(), 2, 0);
        assertFalse(this.advancedAccount.deposit(-2));
        assertEquals(this.advancedAccount.getBalance(), 2, 0);
        assertFalse(this.advancedAccount.deposit(LONGINT));
        assertEquals(this.advancedAccount.getBalance(), 2, 0);
        assertFalse(this.advancedAccount.deposit(0));
        assertEquals(this.advancedAccount.getBalance(), 2, 0);
        assertTrue(this.advancedAccount.deposit(2));
        assertEquals(this.advancedAccount.getBalance(), 4, 0);
    }

    @Test
    public void testAdvancedWithdraw() {
        logIn();
        assertEquals(this.advancedAccount.getBalance(), 0, 0);
        assertTrue(this.advancedAccount.deposit(50));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertFalse(this.advancedAccount.withdraw(-50));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertFalse(this.advancedAccount.withdraw(LONGINT));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertFalse(this.advancedAccount.withdraw(0));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertTrue(this.advancedAccount.withdraw(5));
        assertEquals(this.advancedAccount.getBalance(), 45, 0);
        assertFalse(this.advancedAccount.withdraw(50));
        assertEquals(this.advancedAccount.getBalance(), 45, 0);
    }
    
    @Test
    public void testAdvancedChangeBalance() {
        logIn();
        assertEquals(this.advancedAccount.getBalance(), 0, 0);
        assertTrue(this.advancedAccount.changeBalance(50));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertFalse(this.advancedAccount.changeBalance(-50));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertFalse(this.advancedAccount.changeBalance(LONGINT));
        assertEquals(this.advancedAccount.getBalance(), 50, 0);
        assertTrue(this.advancedAccount.changeBalance(0));
        assertEquals(this.advancedAccount.getBalance(), 0, 0);
    }
    
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
    
}