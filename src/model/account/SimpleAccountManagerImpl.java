package model.account;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * SIMPLE class for account management.
 */
public class SimpleAccountManagerImpl implements AccountManager {

    private String username;
    
    @Override
    public boolean logger(final String usr, final String psw) {
        final JSONObject jo = ManagerUtility.getJsonObject(usr);
        if (jo != null) {
            this.username = jo.get(Fields.USERNAME).toString();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean register(final String usr, final String psw, final String age) {
        final Map<AccountManager.Fields, String> m = new HashMap<>();
        m.put(Fields.USERNAME, usr);
        m.put(Fields.PASSWORD, psw);
        m.put(Fields.BALANCE, "0.0");
        m.put(Fields.AGE, age);
        final JSONObject jo = new JSONObject(m);
        return ManagerUtility.writeOnFile(usr, jo);
    }

    @Override
    public boolean checkExisting(final String usr) {
        final File f = new File(ManagerUtility.getPath(usr));
        return (f.exists() && !f.isDirectory());
    }

    @Override
    public boolean changeUsr(final String usrnew) {
        if (ManagerUtility.changeField(Fields.USERNAME, usrnew, usrnew, this.username) && deleteAcc(this.username)) {
            this.username = usrnew;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changePass(final String psw) {
        return ManagerUtility.changeField(Fields.PASSWORD, psw, this.username, this.username);
    }

    @Override
    public boolean deleteAcc(final String usr) {
        return (new File(ManagerUtility.getPath(usr))).delete();
    }

    @Override
    public String getUsr() {
        return ManagerUtility.getField(Fields.USERNAME, this.username);
    }

    @Override
    public String getPsw() {
        return ManagerUtility.getField(Fields.PASSWORD, this.username);
    }

    @Override
    public String getAge() {
        return ManagerUtility.getField(Fields.AGE, this.username);
    }    
    
    protected String getUsername() {
        return this.username;
    }
    
    protected void resetUsername() {
        this.username = null;
    }
}
