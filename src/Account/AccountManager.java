package Account;

public interface AccountManager {

	public boolean Logger(String usr, String psw);
	
	public boolean Register(String usr, String psw, String eta);
	
	public boolean CheckExisting(String usr);
	
}
