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

import view.GridBagConstraintsConstructor;

//pannello CAMBIO PASSWORD, sistemare ripetizioni
public class PasswordPanel extends JPanel implements UpdatePanel {

    public PasswordPanel(final Frame frame, final int DIMX, final int DIMY) {
      
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
            new ConfirmPassword(frame, this, 2);
            final String newPassword = fieldNewPassword.getText();
            //NICO
            System.out.println(newPassword);
        });
        this.add(labelPassword, GridBagConstraintsConstructor.get(0, 0, 0));
        this.add(fieldPassword, GridBagConstraintsConstructor.get(1, 0, 0));
        this.add(labelNewPassword, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldNewPassword, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonPassword, GridBagConstraintsConstructor.get(2, 2, 0));
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

}
