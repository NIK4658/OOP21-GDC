package view.menu.account;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.account.AccountManager;

/**
 * Panel which manages account password.
 */
public class PasswordPanel extends AccountPanel {
    
    private static final long serialVersionUID = 1L;
    private final AccountManager account;
    private final JTextField passwordField;
    private final JTextField newPasswordField;

    /**
     * Create a panel which manages account password.
     */
    public PasswordPanel(final Frame frame, final AccountManager account, final int minSize) {
        super(minSize);
        this.account = account;
        final JLabel passwordLabel = new JLabel("Insert new Password: ");
        final JLabel newPasswordLabel = new JLabel("Confirm new Password: ");
        passwordField = new JTextField(10);
        newPasswordField = new JTextField(10);
        final JButton buttonPassword = new JButton("Change");
        final JLabel labelAlert = new JLabel();
        
        buttonPassword.addActionListener(e -> {
            if (passwordField.getText().equals(newPasswordField.getText())) {
                if (new ConfirmPassword(frame, account, minSize).isConfirmed()) {
                    if (this.setPassword(newPasswordField.getText())) {
                        labelAlert.setText("Password changed");
                        this.updatePasswordFields();
                    } else {
                        labelAlert.setText("Password not valid");
                    }
                }
            } else {
                labelAlert.setText("Different passwords entered in the fields");
            }
        });
        
        final var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(passwordLabel, c);
        c.gridx++;
        this.add(passwordField, c);
        c.gridx = 0;
        c.gridy++;
        this.add(newPasswordLabel, c);
        c.gridx++;
        this.add(newPasswordField, c);
        c.gridy++;
        this.add(buttonPassword, c);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 3;
        this.add(labelAlert, c);
    }
    
    private void updatePasswordFields() {
        this.passwordField.setText("");
        this.newPasswordField.setText("");
    }

    private boolean setPassword(final String password) {
        return this.account.changePass(password);
    }
    
}
