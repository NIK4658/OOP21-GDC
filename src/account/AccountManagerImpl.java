package account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import notify.gui.NotifyGui;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe principale gestione account.
 */
public class AccountManagerImpl implements AccountManager {

    private String Username;
    
    
    @Override
    public boolean logger(final String usr, final String psw) {
        try {
            final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(getPath(usr)));
            final String name = jo.get("Username").toString();
            final String password = jo.get("Password").toString();
            final String saldo = jo.get("Saldo").toString();
            final String eta = jo.get("Eta").toString();
            this.Username=name;

            if (usr.equals(name) && psw.equals(password)) {
                new NotifyGui(("<html>Bentornato: " + name + ". La tua password è: " + password
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
    public boolean register(final String usr, final String psw, final String eta) {
        if (!(usr.length() == 0 || psw.length() == 0 || eta.length() == 0)) {

            if (checkExisting(usr)) {
                new NotifyGui("Account già presente");
                return false;
            }

            if (isNumeric(eta)) {
                if (Integer.parseInt(eta) < 18) { 
                    new NotifyGui("Impossibile registrarsi, devi essere maggiorenne per registrarti a questo sito");
                    return false;
                }
            } else {
                new NotifyGui("Impossibile registrarsi, età non valida");
                return false;
            }

            final Map<String, String> m = new HashMap<>();
            m.put("Username", usr);
            m.put("Password", psw);
            m.put("Saldo", "0");
            m.put("Eta", eta);
            final JSONObject jo = new JSONObject(m);

            writeOnFile(usr, jo);
            
            new NotifyGui(("Registrazione confermata, scrittura su file avvenuta"));
            return true;
        } else {
            new NotifyGui("Impossibile registrarsi, non sono stati compilati tutti i campi");
            return false;
        }
    }

    @Override
    public boolean checkExisting(final String usr) {
        final File f = new File(getPath(usr));
        return (f.exists() && !f.isDirectory());
    }

    private static boolean isNumeric(final String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getPath(final String usr) {
        return ("res/json/users/" + usr + ".json"); 
    }
    
    private static JSONObject getJsonObject(final String usr) {
        try {
            return (JSONObject) new JSONParser().parse(new FileReader(getPath(usr)));
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public boolean deposit(final int amount, final String usr) {
        final JSONObject jo = getJsonObject(usr);
        jo.replace("Saldo", amount);
        writeOnFile(usr, jo);
        return true;
    }

    @Override
    public boolean withdraw(final int amount, final String usr, final String psw) {
        final int newbalance = balanceAmount(usr) - amount;
        if (newbalance >= 0) {
            changeBalance(usr, newbalance);
            return true;
        } else {
            //Impossibile ritirare "amount", non si dispone di tale cifra;
            return false;
        }
    }

    @Override
    public int balanceAmount(final String usr) {   
        return (int) (getJsonObject(usr)).get("Saldo");
    }

    @Override
    public boolean changeUsr(final String usr, final String usrnew) {
        final JSONObject jo = getJsonObject(usr);
        jo.replace("Username", usrnew);
        writeOnFile(usr, jo);
        return true;
    }

    @Override
    public boolean changePass(final String usr, final String psw) {
        final JSONObject jo = getJsonObject(usr);
        jo.replace("Password", psw);
        
        writeOnFile(usr, jo);
        return true;
    }
    
    @Override
    public boolean changeBalance(final String usr, final int balancenew) {
        final JSONObject jo = getJsonObject(usr);
        jo.replace("Saldo", balancenew);
        writeOnFile(usr, jo);
        return true;
    }

    @Override
    public boolean deleteAcc(final String usr) {
        final File f = new File(getPath(usr));
        f.delete();
        return true;
    }

    private static boolean writeOnFile(final String usr, final JSONObject jo) {
        try {
            final PrintWriter pw = new PrintWriter(getPath(usr));
            pw.write(jo.toJSONString());
            pw.flush();
            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    
}
