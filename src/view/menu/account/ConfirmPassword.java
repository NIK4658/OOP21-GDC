package view.menu.account;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import model.account.AccountManager;

/**
 * A window asking for password confirmation.
 */
public class ConfirmPassword {
    
    private static final int CLOSING_DELAY = 2000;
    private boolean isValid;
    
    /**
     * Creates a new window asking for password confirmation.
     * 
     * @param frame
     * 
     * @param account
     * 
     * @param minSize
     */
    public ConfirmPassword(final Frame frame, final AccountManager account, final int minSize) {
        final JDialog confirmDialog = new JDialog(frame, true);
        final JPanel confirmPanel = new AccountPanel(minSize);
        confirmPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight() / 2));
        final JLabel confirmLabel = new JLabel("Enter password");
        final JTextField passwordField = new JTextField(10);
        final JLabel validLabel = new JLabel();
        final ActionListener closeDialog = e -> confirmDialog.dispose();
        
        passwordField.addActionListener(e -> {
            if (account.isPsw(passwordField.getText())) {
                this.isValid = true;
                validLabel.setText("Password confirmed");
                new Timer(CLOSING_DELAY, closeDialog).start();
            } else {
                this.isValid = false;
                validLabel.setText("Password not confirmed");
            }
        });
        
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        confirmPanel.add(confirmLabel, c);
        c.gridy++;
        confirmPanel.add(passwordField, c);
        c.gridy++;
        confirmPanel.add(validLabel, c);
        
        confirmDialog.setContentPane(confirmPanel);
        confirmDialog.pack();
        confirmDialog.setLocationRelativeTo(frame);
        confirmDialog.setResizable(false);
        confirmDialog.setVisible(true);
    }
    
    /**
     * Returns if password is confirmed.
     * 
     * @return  returns if password is confirmed.
     */
    public boolean isConfirmed() {
        return this.isValid;
    }
    
}
