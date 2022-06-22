package view.menu.account;

import account.AccountManager;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

//pannello CAMBIO USERNAME, sistemare ripetizioni
public class UsernamePanel extends AccountPanel {
    
    private final AccountManager account;
    private final JTextField usernameField;
    private final JTextField newUsernameField;
    
    public UsernamePanel(final Frame frame, final AccountManager account, final int minSize) {
        super(minSize);
        this.account = account;
        
        final JLabel usernameLabel = new JLabel("Username: ");
        final JLabel newUsernameLabel = new JLabel("New Username: ");
        usernameField = new JTextField(this.getUsername(), N_COLUMNS_FIELD);
        newUsernameField = new JTextField(N_COLUMNS_FIELD);
        usernameField.setEditable(false);
        final JLabel alertLabel = new JLabel();
        
        final JButton changeButton = new JButton("Change");
        changeButton.addActionListener(e -> {
            if (new ConfirmPassword(frame, account, minSize).isConfirmed()) {
                if (this.setUsername(newUsernameField.getText())) {
                    this.updateUsernameFields();
                    alertLabel.setText("Username changed");
                } else {
                    alertLabel.setText("Username not valid");
                }
            }
        });
        
        final var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(usernameLabel, c);
        c.gridx++;
        this.add(usernameField, c);
        c.gridx = 0;
        c.gridy++;
        this.add(newUsernameLabel, c);
        c.gridx++;
        this.add(newUsernameField, c);
        c.gridy++;
        this.add(changeButton, c);
        c.gridy++;
        c.gridwidth = 3;
        this.add(alertLabel, c);
    }
    
    private void updateUsernameFields() {
        this.usernameField.setText(this.getUsername());
        this.newUsernameField.setText("");
    }

    private String getUsername() {
        return account.getUsr();
    }
    
    private boolean setUsername(final String username) {
        return account.changeUsr(username);
    }
}
