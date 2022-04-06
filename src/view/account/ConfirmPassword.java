package view.account;

import java.awt.Color;
import java.awt.Dialog;
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

public class ConfirmPassword {
    
    //cambiare var con enumerazione e sistemare ripetizione "Inserisci password per "
    public ConfirmPassword(final Dialog dialog, final UpdatePanel updatePanel, final int var, final int DIMX, final int DIMY) {//da eliminare dim
        final JDialog confirmDialog = new JDialog(dialog, true);
        final JPanel confirmPanel = new JPanel(new GridBagLayout());//creare un qualche metodo che ritorna un pannello già settato
        confirmPanel.setBackground(new Color(68, 87, 96));//
        confirmPanel.setPreferredSize(new Dimension(DIMX, DIMY));//
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
        
        confirmPanel.add(confirmLabel, setDimensionObj(0, 0, 0));
        confirmPanel.add(passwordLabel, setDimensionObj(0, 1, 0));
        confirmPanel.add(passwordField, setDimensionObj(1, 1, 0));
        confirmPanel.add(validLabel, setDimensionObj(1, 2, 0));
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
        }
        return new JLabel("Error");
    }
    
    /* Da sostituire con la mia versione e rimuovere */
    private static GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int verticalSpace) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, verticalSpace, 0);
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
}
