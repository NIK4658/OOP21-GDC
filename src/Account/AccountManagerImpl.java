package Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import NotifyGUI.NotifyGUI;

public class AccountManagerImpl implements AccountManager {

	@Override
	public boolean Logger(String usr, String psw) {
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"));
			String name = jo.get("Username").toString();
			String password = jo.get("Password").toString();
			String saldo = jo.get("Saldo").toString();
			String eta = jo.get("Eta").toString();

			if (usr.equals(name) && psw.equals(password)) {
				new NotifyGUI(("<html>Bentornato: " + name + ". La tua password è: " + password
						+ "<br/>Il tuo saldo è: " + saldo + ". Hai: " + eta + " anni</html>"));
				return true;
			} else {
				new NotifyGUI("Password sbagliata");
				return false;
			}
		} catch (Exception e1) {
			new NotifyGUI("Impossibile trovare account");
			return false;
		}
	}

	@Override
	public boolean Register(String usr, String psw, String eta) {
		if (!(usr.length() == 0 || psw.length() == 0 || eta.length() == 0)) {

			if (CheckExisting(usr)) {
				new NotifyGUI("Account già presente");
				return false;
			}

			if(isNumeric(eta)) {
				if (Integer.parseInt(eta) < 18) { 
					new NotifyGUI("Impossibile registrarsi, devi essere maggiorenne per registrarti a questo sito");
					return false;
				}
			}else {
				new NotifyGUI("Impossibile registrarsi, età non valida");
				return false;
			}

			Map<String, String> m = new HashMap<>();
			m.put("Username", usr);
			m.put("Password", psw);
			m.put("Saldo", "0");
			m.put("Eta", eta);
			JSONObject jo = new JSONObject(m);

			try {
				PrintWriter pw = new PrintWriter("res/json/users/" + usr + ".json");
				pw.write(jo.toJSONString());
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				// File non trovato (Impossibile che capiti)
			}
			new NotifyGUI(("Registrazione confermata, scrittura su file avvenuta"));
			return true;
		} else {
			new NotifyGUI("Impossibile registrarsi, non sono stati compilati tutti i campi");
			return false;
		}
	}

	@Override
	public boolean CheckExisting(String usr) {
		File f = new File("res/json/users/" + usr + ".json");
		if (f.exists() && !f.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
