package model.account;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Utility class with shared static functions needed by managers.
 */
public class ManagerUtility {
    
    /**
     * Function useful to create JSON files from JSONObject.
     * 
     * @param usr   Username of the user.
     * @param jo    JSONObject previously created.
     * @return      True if the writing was successful, false otherwise
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
     * Useful function to read JSON files to create JSONObject objects.
     * 
     * @param usr   Username of the user.
     * @return      A JSONOBject object with user fiels.
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
     * Function to find the values ​​of the fields of the previously created JSON files.
     * 
     * @param field     Field of which you are interested in knowing the value.
     * @param usr       Username of the user.
     * @return          A String that contains the field value.
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
     * Function to change the field values ​​of a previously created JSON file.
     * 
     * @param field     Field of which you are interested in changing the value.
     * @param newValue  New Value of the field.
     * @param targetUsr Username of the target User.
     * @param usr       Username of the user.
     * @return          True if the writing was successful, false otherwise
     */
    public static boolean changeField(final AccountManager.Fields field, 
            final String newValue, final String targetUsr, final String usr) {
        final JSONObject jo = ManagerUtility.getJsonObject(usr);
        jo.replace(field, newValue);
        return ManagerUtility.writeOnFile(targetUsr, jo);
    }
}
