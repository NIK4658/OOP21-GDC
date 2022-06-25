package accountmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;


public class AdvancedAccountManagerTest {

    private AccountManager advancedAccount;
    private static final String LONGSTRING = "abcdefghilmnopqrstuvz";
    private static final String DIRECTORYPATH = "users/";
    private File file;
    private String username; 
    private String usernamenew; 
    private String password; 
    private String passwordnew; 
    private String age;
    
    @Before
    public void initAccount() {
        this.advancedAccount = new AdvancedAccountManagerImpl();
        this.username = "Test";
        this.password = "Password";
        this.usernamenew = "Test2";
        this.passwordnew = "Password2";
        this.age = "21";
        this.file = new File(DIRECTORYPATH + this.username + ".Json");
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
    }
    
    private void changeFile(final String name) {
        this.file = new File(DIRECTORYPATH + name + ".Json");
    }
    
    private void logIn() {
        this.advancedAccount.register(this.username, this.password, this.age);
        this.advancedAccount.logger(this.username, this.password);
    }

    @Test
    public void testAdvancedRegister() {
        this.file.delete();
        assertFalse(this.file.exists() && !file.isDirectory()); //File inizialmente non esiste
        assertFalse(this.advancedAccount.register(this.username + LONGSTRING, this.password, this.age));
        assertFalse(this.advancedAccount.register(this.username, this.password + LONGSTRING, this.age));
        assertFalse(this.advancedAccount.register(this.username, this.password, "100"));
        assertFalse(this.advancedAccount.register(this.username, this.password, "17"));
        assertFalse(this.advancedAccount.register(this.username, this.password, "-20"));
        assertFalse(this.advancedAccount.register(this.username, this.password, "ab"));
        assertFalse(this.advancedAccount.register("", this.password, this.age));
        assertFalse(this.advancedAccount.register(this.username, "", this.age));
        assertFalse(this.advancedAccount.register(this.username, this.password, ""));
        assertTrue(this.advancedAccount.register(this.username, this.password, this.age)); //Eseguo regist corretta
        assertFalse(this.advancedAccount.register(this.username, this.password, this.age));
        assertTrue(this.file.exists() && !file.isDirectory());  //File ora esiste
    }

    @Test
    public void testAdvancedLogin() {
        this.advancedAccount.register(this.username, this.password, this.age);
        assertFalse(this.advancedAccount.logger("Usrnm", "12345"));   //User does not exist, expected false.
        assertFalse(this.advancedAccount.logger(this.username, this.password + "a"));
        assertTrue(this.advancedAccount.logger(this.username, this.password)); //User exist, expected True.
    }
    
    @Test
    public void testAdvancedChangeUsr() {
        logIn();
        assertTrue(this.file.exists() && !file.isDirectory());
        assertTrue(this.advancedAccount.changeUsr(this.usernamenew));
        assertFalse(this.file.exists() && !file.isDirectory());
        changeFile(this.usernamenew);
        assertTrue(this.file.exists() && !file.isDirectory());
        assertNotEquals(this.advancedAccount.getUsr(), this.username); 
        assertEquals(this.advancedAccount.getUsr(), this.usernamenew);
        //changeFile(this.username);
        assertFalse(this.advancedAccount.logger(this.username, this.password));
        assertTrue(this.advancedAccount.logger(this.usernamenew, this.password));
        assertTrue(this.advancedAccount.changeUsr(this.username));
        changeFile(this.usernamenew);
        assertFalse(this.file.exists() && !file.isDirectory());
        assertFalse(this.advancedAccount.changeUsr(this.username + LONGSTRING));
        assertFalse(this.file.exists() && !file.isDirectory());
        assertFalse(this.advancedAccount.logger(this.username + LONGSTRING, this.password));
        changeFile(this.username);
    }
    
    @Test
    public void testAdvancedChangePass() {
        logIn();
        assertFalse(this.advancedAccount.changePass(this.passwordnew + LONGSTRING));
        assertEquals(this.advancedAccount.getPsw(), this.password); 
        assertNotEquals(this.advancedAccount.getPsw(), this.passwordnew);
        assertTrue(this.advancedAccount.changePass(this.passwordnew));
        assertNotEquals(this.advancedAccount.getPsw(), this.password); 
        assertEquals(this.advancedAccount.getPsw(), this.passwordnew); 
    }
    
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
}