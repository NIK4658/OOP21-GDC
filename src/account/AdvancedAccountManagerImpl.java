package account;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import account.SimpleAccountManager.Fields;


public class AdvancedAccountManagerImpl extends SimpleAccountManagerImpl implements AdvancedAccountManager {

    private String username;
    
    @Override
    public boolean register(final String usr, final String psw, final String age) {
        if (!(usr.length() == 0 || psw.length() == 0 || age.length() == 0)) {
            if (checkExisting(usr)) {
                System.out.println("Account already existing");
                return false;
            }
            if (!Utility.isNumeric(age)) {
                System.out.println("Unable to complete registration, invalid age");
                return false;
            }
            if (Integer.parseInt(age) < 18) { 
                System.out.println("Unable to complete registration, "
                        + "you must be at least 18 years old to register on this site");
                return false;
            }
            if (super.register(usr, psw, age)) {
                System.out.println("Registration confirmed, file writing successful");
                return true;
            } else {
                System.out.println("Unable to complete registration, there was a problem writing the file");
                return false;
            }
        } else {
            System.out.println("Unable to complete registration, not all fields have been filled in");
            return false;
        }
    }
    
    @Override
    public boolean logger(final String usr, final String psw) {
        if (super.logger(usr, psw)) {
            this.username = Utility.getField(usr, AdvancedAccountManager.Fields.USERNAME);
            final String password = Utility.getField(usr, AdvancedAccountManager.Fields.PASSWORD);
            final String balance = Utility.getField(usr, AdvancedAccountManager.Fields.BALANCE);
            final String age = Utility.getField(usr, AdvancedAccountManager.Fields.AGE);
            if (usr.equals(this.username) && psw.equals(password)) {
                System.out.println(("Welcome back: " + this.username + ". Your password is: " + password
                        + ", your balance is: " + balance + " and you're " + age + " years old"));
                return true;
            } else {
                System.out.println("Wrong password");
                this.username = null;
                return false;
            }
        } else {
            System.out.println("The account could not be found");
            return false;
        }    
    }
    
    @Override
    public String getUsr() {
        return this.username;
    }
}
