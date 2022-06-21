package view.menu.account;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Font;
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

public class ConfirmPassword implements PasswordConfirmed {
    
    private static final int SCALE_COMPONENT = 30;
    private static final int CLOSING_DELAY = 2000;
    private boolean isConfirm;
    
    //cambiare var con enumerazione e sistemare ripetizione "Inserisci password per "
    public ConfirmPassword(final Frame frame, final AccountManager account, final String operation, final int minSize) {//da eliminare dim
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
            if (passwordField.getText().equals(account.getPsw())) {
                this.isConfirm = true;
                validLabel.setText("Password confermata");
                new Timer(CLOSING_DELAY, closeDialog).start();
            } else {
                this.isConfirm = false;
                validLabel.setText("Password errata");
            }
        });
        
        final GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 2;
        confirmPanel.add(confirmLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        confirmPanel.add(passwordLabel, c);
        c.gridx = 1;
        confirmPanel.add(passwordField, c);
        c.gridy = 2;
        confirmPanel.add(validLabel, c);
        
        confirmLabel.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        passwordField.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        validLabel.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        
        
        
        confirmDialog.setContentPane(confirmPanel);
        confirmDialog.pack();
        confirmDialog.setLocationRelativeTo(frame);
        confirmDialog.setResizable(false);
        confirmDialog.setVisible(true);
    }
    
    @Override
    public boolean isPasswordConfirmed() {
        return this.isConfirm;
    }
    
}
