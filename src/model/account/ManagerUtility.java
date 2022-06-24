package model.account;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Classe di utility per funzioni statiche in comune necessarie ai manager.
 */
public class ManagerUtility {
    
    /**
     * Funzione utile a creare File JSON da JSONObject.
     */
    public static boolean writeOnFile(final String usr, final JSONObject jo) {
        final File theDir = new File("users");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        try {
            final PrintWriter pw = new PrintWriter(getPath(usr));
            pw.write(jo.toJSONString());
            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String getPath(final String usr) {
        return ("users/" + usr + ".json"); 
    }
    
    /**
     * Funzione utile a creare JSONObject da File JSON precedentemente creati.
     */
    public static JSONObject getJsonObject(final String usr) {
        try {
            final FileReader fr = new FileReader(getPath(usr));
            final JSONObject jo = (JSONObject) new JSONParser().parse(fr);
            fr.close();
            final Map<AccountManager.Fields, String> m = new HashMap<>();
            for (final Object o : jo.keySet()) {
                for (final AccountManager.Fields f : AccountManager.Fields.values()) {
                    if (o.equals(f.name())) {
                        m.put(f, jo.get(o).toString());
                    }
                }
            }
            return new JSONObject(m);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Funzione utile a trovare i valodi dei campi dei File JSON precedentemente creati.
     */
    public static String getField(final AccountManager.Fields field, final String usr) {
        final JSONObject jo = getJsonObject(usr);  
        if (jo != null) {
            return jo.get(field).toString();
        } else {
            return "";
        }
    }
    
    /**
     * Funzione utile a cambiare i valodi dei campi dei File JSON precedentemente creati.
     */
    public static boolean changeField(final AccountManager.Fields field, 
            final String newValue, final String targetUsr, final String usr) {
        final JSONObject jo = ManagerUtility.getJsonObject(usr);
        jo.replace(field, newValue);
        return ManagerUtility.writeOnFile(targetUsr, jo);
    }
}
