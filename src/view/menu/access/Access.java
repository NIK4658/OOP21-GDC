package view.menu.access;

import account.SimpleAccountManager;

public interface Access {
    
    enum AccessType { 
        LOGIN, REGISTER 
    } 
    
    void successfullyAccessed(SimpleAccountManager account);

}