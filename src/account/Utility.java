package account;

import java.io.FileReader;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utility {
    
    public static boolean writeOnFile(final String usr, final JSONObject jo) {
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
    
    public static String getPath(final String usr) {
        return ("res/json/users/" + usr + ".json"); 
    }
    
    public static JSONObject getJsonObject(final String usr) {
        try {
            return (JSONObject) new JSONParser().parse(new FileReader(getPath(usr)));
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isNumeric(final String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static String getField(final String usr, final AdvancedAccountManager.Fields field) {
        final JSONObject jo = getJsonObject(usr);  
        if (!jo.isEmpty()) {
            //BUG TOSTRING DA SISTEMARE
            return jo.get(field.toString()).toString();
        } else {
            return "";
        }
    }
    
    
    
    
    
}
