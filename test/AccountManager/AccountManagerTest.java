package AccountManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import account.AdvancedAccountManagerImpl;
import account.AccountManager;
import account.AccountManager.Fields;
import account.SimpleAccountManagerImpl;
import account.Utility;

import org.junit.Before;
import org.junit.Test;

/**
 * Test per manager semplici e avanzati del'account.
 */
public class AccountManagerTest {

    private AccountManager simpleAccount;
    private AccountManager advancedAccount;
    
    @Before
    public void initAccount() {
        this.simpleAccount = new SimpleAccountManagerImpl();
        this.advancedAccount = new AdvancedAccountManagerImpl();
    }

    //test deposito
    @Test
    public void testSimpleRegister() {
        assertTrue(this.simpleAccount.register("Username", "Password", "20"));
        assertTrue(this.simpleAccount.logger("Username", "Password"));
        assertSame(this.simpleAccount.getUsr(), "Username");
        assertSame(this.simpleAccount.getPsw(), "Password");
        assertSame(this.simpleAccount.getAge(), "20");        
    }

    //test ritiro
    @Test
    public void testSimpleLogin() {
        assertTrue(this.simpleAccount.logger("Username", "Password")); //User exist, expected True.
        assertFalse(this.simpleAccount.logger("Usrnm", "Password"));   //User does not exist, expected false.
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
