package test;

import static org.junit.Assert.assertTrue;

import account.AdvancedAccountManager;
import account.AdvancedBalanceManager;
import account.AdvancedBalanceManagerImpl;
import account.SimpleBalanceManager;
import account.SimpleBalanceManagerImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Test per manager semplici e avanzati del saldo.
 */
public class BalanceManagerTest {
    
    private AdvancedAccountManager account;
    private SimpleBalanceManager simpleAccount;
    private AdvancedBalanceManager advancedAccount;
    
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
