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

//pannello CAMBIO PASSWORD, sistemare ripetizioni
public class PasswordPanel extends JPanel implements UpdatePanel {

    public PasswordPanel(final Dialog dialog, final int DIMX, final int DIMY) {
      
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel labelPassword = new JLabel("New Password: ");
        final JLabel labelNewPassword = new JLabel("Confirm Password: ");
        final JTextField fieldPassword = new JTextField(10);
        fieldPassword.setEditable(false);//momentaneamente disabilitato, bisogna implementare una JDialog 
        final JTextField fieldNewPassword = new JTextField(10);
        final JButton buttonPassword = new JButton("Change");
        buttonPassword.addActionListener(e -> {
            new ConfirmPassword(dialog, this, 2, DIMX / 2, DIMY);
            final String newPassword = fieldNewPassword.getText();
            //NICO
            System.out.println(newPassword);
        });
        this.add(labelPassword, setDimensionObj(0, 0, 0));
        this.add(fieldPassword, setDimensionObj(1, 0, 0));
        this.add(labelNewPassword, setDimensionObj(0, 1, 0));
        this.add(fieldNewPassword, setDimensionObj(1, 1, 0));
        this.add(buttonPassword, setDimensionObj(2, 2, 0));
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
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
