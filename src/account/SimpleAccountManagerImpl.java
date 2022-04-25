package account;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import notify.gui.NotifyGui;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Classe principale gestione account.
 */
public class SimpleAccountManagerImpl implements SimpleAccountManager {

    private String username;
    
    
    @Override
    public boolean logger(final String usr, final String psw) {
        try {
            final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(Utility.getPath(usr)));
            this.username = jo.get(Fields.USERNAME.toString()).toString();
            System.out.println(this.username);
            final String password = jo.get(Fields.PASSWORD.toString()).toString();
            final String saldo = jo.get(Fields.BALANCE.toString()).toString();
            final String eta = jo.get(Fields.AGE.toString()).toString();

            if (usr.equals(this.username) && psw.equals(password)) {
                new NotifyGui(("<html>Bentornato: " + this.username + ". La tua password è: " + password
                        + "<br/>Il tuo saldo è: " + saldo + ". Hai: " + eta + " anni</html>"));
                return true;
            } else {
                new NotifyGui("Password sbagliata");
                return false;
            }
        } catch (Exception e1) {
            new NotifyGui("Impossibile trovare account");
            return false;
        }
    }

  
    @Override
    public boolean register(final String usr, final String psw, final String age) {
        if (!(usr.length() == 0 || psw.length() == 0 || age.length() == 0)) {

            if (checkExisting(usr)) {
                new NotifyGui("Account già presente");
                return false;
            }

            if (Utility.isNumeric(age)) {
                if (Integer.parseInt(age) < 18) { 
                    new NotifyGui("Impossibile registrarsi, devi essere maggiorenne per registrarti a questo sito");
                    return false;
                }
            } else {
                new NotifyGui("Impossibile registrarsi, età non valida");
                return false;
            }

            final Map<SimpleAccountManager.Fields, String> m = new HashMap<>();
            m.put(Fields.USERNAME, usr);
            m.put(Fields.PASSWORD, psw);
            m.put(Fields.BALANCE, "0.0");
            m.put(Fields.AGE, age);
            final JSONObject jo = new JSONObject(m);
            
            Utility.writeOnFile(usr, jo);
            
            new NotifyGui(("Registrazione confermata, scrittura su file avvenuta"));
            return true;
        } else {
            new NotifyGui("Impossibile registrarsi, non sono stati compilati tutti i campi");
            return false;
        }
    }

    @Override
    public boolean checkExisting(final String usr) {
        final File f = new File(Utility.getPath(usr));
        return (f.exists() && !f.isDirectory());
    }

    @Override
    public boolean changeUsr(final String usrnew) {
        if (changeField(Fields.USERNAME, usrnew, usrnew) && deleteAcc()) {
            this.username = usrnew;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changePass(final String psw) {
        return changeField(Fields.PASSWORD, psw, this.username);
    }
    
    private boolean changeField(final SimpleAccountManager.Fields field, final String newValue, final String usr) {
        final JSONObject jo = Utility.getJsonObject(this.username);
        jo.replace(field, newValue);
        Utility.writeOnFile(usr, jo);
        return true;
    }

    @Override
    public boolean deleteAcc() {
        final File f = new File(Utility.getPath(this.username));
        return f.delete();
    }

    @Override
    public String getUsr() {
        return String.valueOf((Utility.getJsonObject(this.username)).get(Fields.USERNAME));
    }

    @Override
    public String getPsw() {
        return String.valueOf((Utility.getJsonObject(this.username)).get(Fields.PASSWORD));
    }

    @Override
    public String getAge() {
        return String.valueOf((Utility.getJsonObject(this.username)).get(Fields.AGE));
    }    
}
