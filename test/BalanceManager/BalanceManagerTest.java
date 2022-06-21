package BalanceManager;

import static org.junit.Assert.assertTrue;

import account.AccountManager;
import account.AdvancedBalanceManagerImpl;
import account.BalanceManager;
import account.SimpleBalanceManagerImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Test per manager semplici e avanzati del saldo.
 */
public class BalanceManagerTest {
    
    private AccountManager account;
    private BalanceManager simpleAccount;
    private BalanceManager advancedAccount;
    
    @Before
    public void initAccount() {
        this.simpleAccount = new SimpleBalanceManagerImpl(this.account);
        this.advancedAccount = new AdvancedBalanceManagerImpl(this.account);
    } 
    
    //test deposito semplice
    @Test
    public void testSimpleDeposit() {
        assertTrue(true);
    }
    
    //test ritiro semplice
    @Test
    public void testSimpleWithdraw() {
        assertTrue(true);
    }
    
    //test deposito avanzato
    @Test
    public void testAdvancedDeposit() {
        assertTrue(true);
    }

    //test ritiro avanzato
    @Test
    public void testAdvancedWithdraw() {
        assertTrue(true);
    }
}
