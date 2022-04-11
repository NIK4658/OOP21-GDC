package view.account;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import account.AccountManager;
import view.GridBagConstraintsConstructor;

public class ConfirmPassword implements PasswordConfirmed {
    
    private static final int CLOSING_DELAY = 2000;
    private boolean isTrue;
    
    //cambiare var con enumerazione e sistemare ripetizione "Inserisci password per "
    public ConfirmPassword(final Frame frame, final AccountManager account, final String operation) {//da eliminare dim
        final JDialog confirmDialog = new JDialog(frame, true);
        final JPanel confirmPanel = new JPanel(new GridBagLayout());//creare un qualche metodo che ritorna un pannello già settato
        confirmPanel.setBackground(new Color(68, 87, 96));//
        confirmPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight() / 2));//
        final JLabel confirmLabel = new JLabel("Enter password" + operation);
        final JLabel passwordLabel = new JLabel("Password: ");
        final JTextField passwordField = new JTextField(10);
        final JLabel validLabel = new JLabel();
        final ActionListener closeDialog = e -> confirmDialog.dispose();
        
        passwordField.addActionListener(e -> {
            if (true) {//passwordField.getText() == account.?        NICO
                this.isTrue = true;
                validLabel.setText("Password confermata");
                new Timer(CLOSING_DELAY, closeDialog).start();
            } else {
                this.isTrue = false;
                validLabel.setText("Password errata");
            }
        });
        
        confirmPanel.add(confirmLabel, GridBagConstraintsConstructor.get(0, 0, 0));
        confirmPanel.add(passwordLabel, GridBagConstraintsConstructor.get(0, 1, 0));
        confirmPanel.add(passwordField, GridBagConstraintsConstructor.get(1, 1, 0));
        confirmPanel.add(validLabel, GridBagConstraintsConstructor.get(1, 2, 0));
        confirmDialog.setContentPane(confirmPanel);
        confirmDialog.pack();
        confirmDialog.setLocationRelativeTo(frame);
        confirmDialog.setResizable(false);
        confirmDialog.setVisible(true);
    }
    
    @Override
    public boolean isPasswordConfirmed() {
        return this.isTrue;
    }
    
}
