package account;

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
	public boolean logger(final String usr, final String psw) {
		try {
			final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"));
			final String name = jo.get("Username").toString();
			final String password = jo.get("Password").toString();
			final String saldo = jo.get("Saldo").toString();
			final String eta = jo.get("Eta").toString();

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
	public boolean register(final String usr, final String psw, final String eta) {
		if (!(usr.length() == 0 || psw.length() == 0 || eta.length() == 0)) {

			if (checkExisting(usr)) {
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

			final Map<String, String> m = new HashMap<>();
			m.put("Username", usr);
			m.put("Password", psw);
			m.put("Saldo", "0");
			m.put("Eta", eta);
			final JSONObject jo = new JSONObject(m);

			try {
				final PrintWriter pw = new PrintWriter("res/json/users/" + usr + ".json");
				pw.write(jo.toJSONString());
				pw.flush();
				pw.close();
			} catch (Exception e1) {
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
	public boolean checkExisting(final String usr) {
		final File f = new File("res/json/users/" + usr + ".json");
		return(f.exists() && !f.isDirectory());
	}

	private static boolean isNumeric(final String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public boolean deposit(final int amount, final String usr) {
		try {
			final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"));
			jo.replace("Saldo", amount);
			writeOnFile(usr,jo);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean withdraw(final int amount, final String usr, final String psw) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int balanceAmount(final String usr) {
		
			try {
				return (int) (((JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"))).get("Saldo"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return -1;
	}

	@Override
	public boolean changeUsr(final String usr, final String usrnew) {
		try {
			final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"));
			jo.replace("Username", usrnew);
			writeOnFile(usr,jo);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean changePass(final String usr, final String psw) {
		try {
			final JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("res/json/users/" + usr + ".json"));
			jo.replace("Password", psw);
			writeOnFile(usr,jo);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean deleteAcc(final String usr) {
		final File f=new File("res/json/users/" + usr + ".json");
		return f.delete();
	}
	
	private static boolean writeOnFile(final String usr, final JSONObject jo) {
		try {
			final PrintWriter pw = new PrintWriter("res/json/users/" + usr + ".json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
