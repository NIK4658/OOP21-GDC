package AccountManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;

import account.AccountManager;
import account.SimpleAccountManagerImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test per manager semplici dell'account.
 */
public class SimpleAccountManagerTest {
    
    private AccountManager simpleAccount;
    private File file;
    
    @Before
    public void initAccount() {
        this.simpleAccount = new SimpleAccountManagerImpl();
        this.file = new File("res/json/users/Test.Json");
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
    }

    //test Registrazione
    @Test
    public void testSimpleRegister() {
        assertFalse(this.simpleAccount.checkExisting("Test"));
        assertFalse(this.file.exists() && !file.isDirectory());
        assertTrue(this.simpleAccount.register("Test", "Password", "20"));
        assertTrue(this.simpleAccount.checkExisting("Test"));
        assertTrue(this.file.exists() && !file.isDirectory());
        assertTrue(this.simpleAccount.logger("Test", "Password"));
        assertEquals(this.simpleAccount.getUsr(), "Test");
        assertEquals(this.simpleAccount.getPsw(), "Password");
        assertEquals(this.simpleAccount.getAge(), "20");    
        
        assertTrue(this.simpleAccount.changeUsr("Test2"));
        assertEquals(this.simpleAccount.getUsr(), "Test2");
        
        
        
        
    }

    //test login
    @Test
    public void testSimpleLogin() {
        assertTrue(this.simpleAccount.logger("Username", "Password")); //User exist, expected True.
        assertFalse(this.simpleAccount.logger("Usrnm", "Password"));   //User does not exist, expected false.
    }
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
}

