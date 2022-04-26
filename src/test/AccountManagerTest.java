package test;

import static org.junit.Assert.assertTrue;

import account.AdvancedAccountManager;
import account.AdvancedAccountManagerImpl;
import account.SimpleAccountManager;
import account.SimpleAccountManagerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test per manager semplici e avanzati del'account.
 */
public class AccountManagerTest {

    private SimpleAccountManager simpleAccount;
    private AdvancedAccountManager advancedAccount;
    
    @Before
    public void initAccount() {
        this.simpleAccount = new SimpleAccountManagerImpl();
        this.advancedAccount = new AdvancedAccountManagerImpl();
    }

    //test deposito
    @Test
    public void testSimpleRegister() {
        assertTrue(true);
    }

    //test ritiro
    @Test
    public void testSimpleLogin() {
        assertTrue(true);
    }
    
    //test deposito
    @Test
    public void testAdvancedRegister() {
        assertTrue(true);
    }

    //test ritiro
    @Test
    public void testAdvancedLogin() {
        assertTrue(true);
    }
}
