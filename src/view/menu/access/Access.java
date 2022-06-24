package view.menu.access;

import model.account.AccountManager;

public interface Access {
    
    enum AccessType { 
        LOGIN, REGISTER 
    } 
    
    void successfullyAccessed(AccountManager account);

}