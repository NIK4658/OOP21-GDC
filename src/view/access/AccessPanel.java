package view.access;

import java.awt.event.ActionListener;

//Serve l'interfaccia?? Eventualmente spostare enum in JAccessPanel
public interface AccessPanel {
    enum AccessType {
    LOGIN, REGISTER
    }

    void setActionListenerLoginButton(ActionListener al);

    void setActionListenerRegisterButton(ActionListener al);
}
