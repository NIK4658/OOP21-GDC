package view.menu.access;

import account.AdvancedAccountManager;
import account.AccountManager;

public interface Access {
    
    enum AccessType { 
        LOGIN, REGISTER 
    } 
    
    void successfullyAccessed(AdvancedAccountManager account);

}