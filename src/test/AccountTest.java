package test;

import static org.junit.Assert.assertTrue;

import account.AccountManager;
import account.AccountManagerImpl;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {

    private AccountManager account;
    
    @Before
    public void initAccount() {
        this.account = new AccountManagerImpl();
    }
    
    
    //test deposito
    @Test
    public void testDeposit() {
        assertTrue(false);
    }

    
    //test ritiro
    @Test
    public void testWithdraw() {
        assertTrue(true);
    }
    
}
