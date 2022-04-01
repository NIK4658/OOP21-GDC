package account;

public interface AccountManager {

	boolean logger(String usr, String psw);
	
	boolean register(String usr, String psw, String eta);
	
	boolean deposit(int amount, String usr);
	
	boolean withdraw(int amount, String usr, String psw);
	
	int balanceAmount(String usr);
	
	boolean changeUsr(String usr, String usrnew);
	
	boolean changePass(String usr, String psw);
	
	boolean deleteAcc(String usr);
	
	boolean checkExisting(String usr);
	
}
