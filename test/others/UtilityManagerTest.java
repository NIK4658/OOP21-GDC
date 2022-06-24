package others;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.ManagerUtility;

public class UtilityManagerTest {
    
    private AccountManager advancedAccount;
    private static final String LONGSTRING = "abcdefghilmnopqrstuvz";
    private static final String DIRECTORYPATH = "res/json/users/";
    private File file;
    private String username; 
    private String usernamenew; 
    private String password; 
    private String passwordnew; 
    private String age;
    
    
    //VALUTARE SE FARE QUESTI TESTING O MENO
    
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
    
    @Test
    public void testWriteOnFile() {
        //assertTrue(ManagerUtility.writeOnFile());
    }
    
    @Test
    public void testGetPath() {

    }
    
    @Test
    public void testGetJsonObject() {

    }
    
    @Test
    public void testGetField() {

    }
    
    @Test
    public void testChangeField() {

    }
}
