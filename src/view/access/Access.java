package view.access;

import account.AccountManager;

public interface Access {
    
    enum AccessType { 
        LOGIN, REGISTER 
    } 
    
    void successfullyAccessed(AccountManager account);

}