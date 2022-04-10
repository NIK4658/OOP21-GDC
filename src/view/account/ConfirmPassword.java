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

import view.GridBagConstraintsConstructor;

public class ConfirmPassword {
    
    //cambiare var con enumerazione e sistemare ripetizione "Inserisci password per "
    public ConfirmPassword(final Frame frame, final UpdatePanel updatePanel, final int var) {//da eliminare dim
        final JDialog confirmDialog = new JDialog(frame, true);
        final JPanel confirmPanel = new JPanel(new GridBagLayout());//creare un qualche metodo che ritorna un pannello già settato
        confirmPanel.setBackground(new Color(68, 87, 96));//
        confirmPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight() / 2));//
        final JLabel confirmLabel = typeLabel(var);
        final JLabel passwordLabel = new JLabel("Password: ");
        final JTextField passwordField = new JTextField(10);
        final JLabel validLabel = new JLabel();
        
        final ActionListener closeDialog = e -> confirmDialog.dispose();
        passwordField.addActionListener(e -> {
            if (true) {//NICO passwordField.getText()==
                System.out.println("Password confermata");
                validLabel.setText("Password confermata");
                updatePanel.update();
                int delay = 2000;
                new Timer(delay, closeDialog).start();
            } else {
                System.out.println("Password non valida");
                validLabel.setText("Password non valida");
            }
        });
        
        confirmPanel.add(confirmLabel, GridBagConstraintsConstructor.get(0, 0, 0));
        confirmPanel.add(passwordLabel, GridBagConstraintsConstructor.get(0, 1, 0));
        confirmPanel.add(passwordField, GridBagConstraintsConstructor.get(1, 1, 0));
        confirmPanel.add(validLabel, GridBagConstraintsConstructor.get(1, 2, 0));
        confirmDialog.setContentPane(confirmPanel);
        confirmDialog.pack();
        confirmDialog.setLocationRelativeTo(null);
        confirmDialog.setResizable(false);
        confirmDialog.setVisible(true);
    }
    
    private JLabel typeLabel(final int var) {
        switch (var) {
            case 1: return new JLabel("Inserisci password per cambiare Username");
            case 2: return new JLabel("Inserisci password per cambiare Password");
            case 3: return new JLabel("Inserisci password per eliminare Account");
            default: return new JLabel("Error");
        }
    }
    
}
