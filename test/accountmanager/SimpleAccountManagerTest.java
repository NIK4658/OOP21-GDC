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
import model.account.SimpleAccountManagerImpl;

/**
 * Test per manager semplici dell'account.
 */
public class SimpleAccountManagerTest {
    
    private AccountManager simpleAccount;
    private static final String DIRECTORYPATH = "users/";
    private File file;
    private String username; 
    private String usernamenew; 
    private String password; 
    private String passwordnew; 
    private String age;
    
    @Before
    public void initAccount() {
        this.simpleAccount = new SimpleAccountManagerImpl();
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
        this.simpleAccount.register(this.username, this.password, this.age);
        this.simpleAccount.logger(this.username, this.password);
    }

    @Test
    public void testSimpleRegister() {
        this.file.delete();
        assertFalse(this.file.exists() && !file.isDirectory()); //File inizialmente non esiste
        assertTrue(this.simpleAccount.register(this.username, this.password, this.age)); //Eseguo registrazione corretta
        assertTrue(this.file.exists() && !file.isDirectory());  //File ora esiste
    }

    @Test
    public void testSimpleLogin() {
        this.simpleAccount.register(this.username, this.password, this.age);
        assertFalse(this.simpleAccount.logger("Usrnm", "12345"));   //User does not exist, expected false.
        assertTrue(this.simpleAccount.logger(this.username, this.password)); //User exist, expected True.
    }
    
    @Test
    public void testCheckExisting() {
        logIn();
        assertFalse(this.simpleAccount.checkExisting("Test123"));          //Non esiste
        assertTrue(this.simpleAccount.checkExisting("Test"));              //Esiste, creato prima
        assertTrue(this.simpleAccount.checkExisting(this.file.getName()
                .replaceFirst("[.][^.]+$", ""))); //Esiste, creato prima
        assertTrue(this.simpleAccount.checkExisting(this.username));       //Esiste, creato prima
        assertTrue(this.file.exists() && !file.isDirectory());             //Esiste effettivamente il file creato prima
    }
    
    @Test
    public void testGetFields() {
        logIn();
        assertEquals(this.simpleAccount.getUsr(), this.username);
        assertEquals(this.simpleAccount.getPsw(), this.password);
        assertEquals(this.simpleAccount.getAge(), this.age);  
    }
    
    
    @Test
    public void testSimpleChangeUsr() {
        logIn();
        assertTrue(this.file.exists() && !file.isDirectory());
        assertTrue(this.simpleAccount.changeUsr(this.usernamenew));
        assertFalse(this.file.exists() && !file.isDirectory());
        changeFile(this.usernamenew);
        assertTrue(this.file.exists() && !file.isDirectory());
        assertNotEquals(this.simpleAccount.getUsr(), this.username); 
        assertEquals(this.simpleAccount.getUsr(), this.usernamenew);
        changeFile(this.username);
        assertTrue(this.simpleAccount.logger(this.usernamenew, this.password));
        assertFalse(this.simpleAccount.logger(this.username, this.password));
        assertTrue(this.simpleAccount.changeUsr(this.username));
    }
    
    @Test
    public void testSimpleChangePass() {
        logIn();
        assertTrue(this.simpleAccount.changePass(this.passwordnew));
        assertNotEquals(this.simpleAccount.getPsw(), this.password); 
        assertEquals(this.simpleAccount.getPsw(), this.passwordnew); 
    }
    

    @Test
    public void testDeleteAcc() {
        logIn();
        assertTrue(this.simpleAccount.deleteAcc(this.username));
        assertFalse(this.simpleAccount.deleteAcc(this.usernamenew));
    }
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
}

