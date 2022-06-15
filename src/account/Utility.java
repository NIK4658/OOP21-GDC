package account;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Classe di utility per funzioni statiche in comune necessarie ai manager.
 */
public class Utility {
    
    /**
     * Funzione utile a creare File JSON da JSONObject.
     */
    public static boolean writeOnFile(final String usr, final JSONObject jo) {
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
        return ("res/json/users/" + usr + ".json"); 
    }
    
    /**
     * Funzione utile a creare JSONObject da File JSON precedentemente creati.
     */
    public static JSONObject getJsonObject(final String usr) {
        try {
            final FileReader fr = new FileReader(getPath(usr));
            final JSONObject jo = (JSONObject) new JSONParser().parse(fr);
            fr.close();
            final Map<SimpleAccountManager.Fields, String> m = new HashMap<>();
            for (final Object o : jo.keySet()) {
                for (final SimpleAccountManager.Fields f : SimpleAccountManager.Fields.values()) {
                    if (o.equals(f.toString())) {
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
    public static String getField(final SimpleAccountManager.Fields field, final String usr) {
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
    public static boolean changeField(final SimpleAccountManager.Fields field, 
            final String newValue, final String targetUsr, final String usr) {
        final JSONObject jo = Utility.getJsonObject(usr);
        jo.replace(field, newValue);
        return Utility.writeOnFile(targetUsr, jo);
    }
}
