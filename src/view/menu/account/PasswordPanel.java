package view.menu.account;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.AccountManager;

//pannello CAMBIO PASSWORD, sistemare ripetizioni
public class PasswordPanel extends JPanel {
    
    private static final int SCALE_COMPONENT = 30;
    private final AccountManager account;

    public PasswordPanel(final Frame frame, final AccountManager account, final int minSize) {
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
                if (new ConfirmPassword(frame, account, " to change Password", minSize).isPasswordConfirmed()) {
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
        
        
        labelPassword.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        labelNewPassword.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        fieldPassword.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        fieldNewPassword.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        buttonPassword.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        labelAlert.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        
        
        
        
        final var c = new GridBagConstraints();
        this.add(labelPassword);
        c.gridx = 1;
        this.add(fieldPassword, c);
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelNewPassword, c);
        c.gridx = 1;
        this.add(fieldNewPassword, c);
        c.gridx = 1;
        c.gridy = 2;
        this.add(buttonPassword, c);
        c.gridy = 3;
        this.add(labelAlert, c);
    }

    private boolean setPassword(final String password) {
        return this.account.changePass(password);
    }
    
}
