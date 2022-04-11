package view.account;

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

import account.AccountManager;
import view.GridBagConstraintsConstructor;

//pannello CAMBIO USERNAME, sistemare ripetizioni
public class UsernamePanel extends JPanel {
    
    private final AccountManager account;
    
    public UsernamePanel(final Frame frame, final AccountManager account) {
        this.account = account;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        
        final JLabel labelUsername = new JLabel("Username: ");
        final JLabel labelNewUsername = new JLabel("New Username: ");
        final JTextField fieldUsername = new JTextField(this.getUsername(), 10);
        final JTextField fieldNewUsername = new JTextField(10);
        fieldUsername.setEditable(false);
        
        final JButton buttonUsername = new JButton("Change");
        buttonUsername.addActionListener(e -> {//aggiungere username non valido/già presente
            if (new ConfirmPassword(frame, account, " to change Username").isPasswordConfirmed()) {
                this.setUsername(fieldNewUsername.getText());
            }
        });
        
        this.add(labelUsername, GridBagConstraintsConstructor.get(0, 0, 0));
        this.add(fieldUsername, GridBagConstraintsConstructor.get(1, 0, 0));
        this.add(labelNewUsername, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldNewUsername, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonUsername, GridBagConstraintsConstructor.get(2, 2, 0));
    }
    
    private String getUsername() {
        return "Massimo Bossetti";
//      return account.?        NICO
    }
    
    private void setUsername(final String username) {
//      account.?
    }



}
