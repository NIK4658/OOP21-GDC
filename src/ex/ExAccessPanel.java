package ex;

import java.awt.event.ActionListener;

//Serve l'interfaccia?? Eventualmente spostare enum in JAccessPanel
/**
 * Interfaccia.
 */
public interface ExAccessPanel {
    
    /**
     * Enum.
     */
    enum AccessType {
    LOGIN, REGISTER
    }

    void setActionListenerLoginButton(ActionListener al);

    void setActionListenerRegisterButton(ActionListener al);
}
