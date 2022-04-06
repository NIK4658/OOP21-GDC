package view.account;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//pannello CAMBIO USERNAME, sistemare ripetizioni
public class UsernamePanel extends JPanel implements UpdatePanel {
    private final JTextField fieldNewUsername;
    
    public UsernamePanel(final Dialog dialog, final int DIMX, final int DIMY) {
      
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel labelUsername = new JLabel("Username: ");
        final JLabel labelNewUsername = new JLabel("New Username: ");
        final JTextField fieldUsername = new JTextField("Massimo Bossetti", 10);//NICO
        fieldUsername.setEditable(false);
        fieldNewUsername = new JTextField(10);
        final JButton buttonUsername = new JButton("Change");
        buttonUsername.addActionListener(e -> {//aggiungere richiesta psw
            new ConfirmPassword(dialog, this, 1, DIMX / 2, DIMY);
            
        });
        this.add(labelUsername, setDimensionObj(0, 0, 0));
        this.add(fieldUsername, setDimensionObj(1, 0, 0));
        this.add(labelNewUsername, setDimensionObj(0, 1, 0));
        this.add(fieldNewUsername, setDimensionObj(1, 1, 0));
        this.add(buttonUsername, setDimensionObj(2, 2, 0));
    }
    
    @Override
    public void update() {
        fieldNewUsername.getText();//NICO
    }
    
    /* Da sostituire con la mia versione */
    private static GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int verticalSpace) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, verticalSpace, 0);
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }




}
