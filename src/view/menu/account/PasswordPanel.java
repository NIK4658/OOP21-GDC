package view.menu.account;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.SimpleAccountManager;
import view.GridBagConstraintsConstructor;

//pannello CAMBIO PASSWORD, sistemare ripetizioni
public class PasswordPanel extends JPanel {
    
    private final SimpleAccountManager account;

    public PasswordPanel(final Frame frame, final SimpleAccountManager account) {
        this.account = account;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));     
        
        final JLabel labelPassword = new JLabel("Insert new Password: ");
        final JLabel labelNewPassword = new JLabel("Confirm new Password: ");
        final JTextField fieldPassword = new JTextField(10);
//        fieldPassword.setEditable(false);//momentaneamente disabilitato, bisogna implementare una JDialog 
        final JTextField fieldNewPassword = new JTextField(10);
        final JButton buttonPassword = new JButton("Change");
        final JLabel labelAlert = new JLabel();
        
        buttonPassword.addActionListener(e -> {//aggiungere password non valida
            if (fieldPassword.getText().equals(fieldNewPassword.getText())) {
                if (new ConfirmPassword(frame, account, " to change Password").isPasswordConfirmed()) {
                    if (this.setPassword(fieldNewPassword.getText())) {
                        labelAlert.setText("Password changed");
                        fieldPassword.setText("");
                        fieldNewPassword.setText("");
                    }
                }
            } else {
                labelAlert.setText("Different passwords entered in the fields");
            }
            
        });
        this.add(labelPassword, GridBagConstraintsConstructor.get(0, 0, 0));
        this.add(fieldPassword, GridBagConstraintsConstructor.get(1, 0, 0));
        this.add(labelNewPassword, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldNewPassword, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonPassword, GridBagConstraintsConstructor.get(2, 2, 0));
        this.add(labelAlert, GridBagConstraintsConstructor.get(1, 3, 0));
    }

    private boolean setPassword(final String password) {
        return this.account.changePass(password);
    }
    
}
