package controller;

import model.account.AdvancedAccountManagerImpl;

public class AccountControllerImpl implements AccountController {

    
    
    @Override
    public boolean login(final String username, final String password) {
        account = new AdvancedAccountManagerImpl();
        return account.logger(username, password);
    }
    
    @Override
    public boolean signup(final String username, final String password, final String age) {
        return new AdvancedAccountManagerImpl().register(username, password, age);
    }
    
    @Override
    public String getUsername() {
        return this.account.getUsr();
    }
    
    @Override
    public boolean changeUsername(final String username) {
        return this.account.changeUsr(username);
    }
    
    @Override
    public boolean isPassword(final String password) {
        return this.account.isPsw(password);
    }
    
    @Override
    public boolean setPassword(final String password) {
        return this.account.changePass(password);//cambiare nome metodo NICO in setPsw
    }
    
    @Override
    public boolean deleteAccount() {
        return this.account.deleteAcc(this.getUsername());
    }
}
