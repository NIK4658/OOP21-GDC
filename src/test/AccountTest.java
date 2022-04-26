package test;

import static org.junit.Assert.assertTrue;

import account.SimpleAccountManager;
import account.SimpleAccountManagerImpl;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {

    private SimpleAccountManager account;
    
    @Before
    public void initAccount() {
        this.account = new SimpleAccountManagerImpl();
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
