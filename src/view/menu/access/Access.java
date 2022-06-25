package view.menu.access;

/**
 * Interface of the login-register GUI.
 *
 */
public interface Access {
    
    /**
     * Enum with two types of operations.
     *
     */
    enum AccessType { 
        LOGIN, REGISTER 
    } 
    
    void successfullyAccessed();

}