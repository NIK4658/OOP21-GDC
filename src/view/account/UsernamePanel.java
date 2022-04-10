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

//pannello CAMBIO USERNAME, sistemare ripetizioni
public class UsernamePanel extends JPanel implements UpdatePanel {
    private final JTextField fieldNewUsername;
    
    public UsernamePanel(final Frame frame) {
      
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        
        final JLabel labelUsername = new JLabel("Username: ");
        final JLabel labelNewUsername = new JLabel("New Username: ");
        final JTextField fieldUsername = new JTextField("Massimo Bossetti", 10);//NICO
        fieldUsername.setEditable(false);
        fieldNewUsername = new JTextField(10);
        final JButton buttonUsername = new JButton("Change");
        buttonUsername.addActionListener(e -> {//aggiungere richiesta psw
            new ConfirmPassword(frame, this, 1);
            
        });
        this.add(labelUsername, GridBagConstraintsConstructor.get(0, 0, 0));
        this.add(fieldUsername, GridBagConstraintsConstructor.get(1, 0, 0));
        this.add(labelNewUsername, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldNewUsername, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonUsername, GridBagConstraintsConstructor.get(2, 2, 0));
    }
    
    @Override
    public void update() {
        fieldNewUsername.getText();//NICO
    }




}
