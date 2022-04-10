package view.access;

import account.AccountManager;
import account.AccountManagerImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import view.GridBagConstraintsConstructor;
import view.access.Access.AccessType;


/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class AccessPanel extends JPanel {

    private static final long serialVersionUID = 1L;
          

    /**
     * Main function.
     */
    public AccessPanel(final Access access, final Dimension dim, final AccessType accessType, final ActionListener al) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(dim);
        final JLabel title = new JLabel(accessType.toString(), SwingConstants.CENTER);
        final JTextField username = new JTextField("Username");
        final JTextField password = new JTextField("Password");                        
        final JTextField age = new JTextField("Età"); 
        final JButton loginButton;
        final JButton registerButton;
        final ArrayList<JComponent> list = new ArrayList<>();
        list.add(username);
        list.add(password);
        
        if (accessType.equals(AccessType.LOGIN)) {
            loginButton = new JButton("Login");
            registerButton = new JButton("<html>Non hai un Account? <br/>Registrati!<html>");
            list.add(loginButton);
            list.add(registerButton);
        } else {
            registerButton = new JButton("Registrati");
            loginButton = new JButton("<html>Hai già un account? <br/>Esegui il Login</html>");
            list.add(age);
            list.add(registerButton);
            list.add(loginButton);
        }
        
        final int dimX = dim.width;
        final int dimY = dim.height;
        // Modifiche solo per titolo, SI PUO' MIGLIORARE
        title.setForeground(Color.WHITE);
        if (accessType.equals(AccessType.LOGIN)) {
            title.setPreferredSize(new Dimension(dimX - (dimX / 2), dimY / 10));
            title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX * 3 / 20));
        } else {
            title.setPreferredSize(new Dimension(dimX * 3 / 2 - (dimX / 2), dimY / 10));
            title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX * 3 / 25));
        }
        this.add(title, GridBagConstraintsConstructor.get(0, 0, 60));
        
        // Modifiche generali
        int i = 1;
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(dimX - (dimX / 2), dimY / 20));
            jc.setFont(new Font("Arial", Font.PLAIN, dimX * 3 / 50));
            this.add(jc, GridBagConstraintsConstructor.get(0, i, 5));
            i++;
        }
        
        if (accessType.equals(AccessType.LOGIN)) {
            registerButton.addActionListener(al);
            registerButton.setPreferredSize(new Dimension(dimX - (dimX / 2), dimY / 7));
            
            loginButton.addActionListener(e -> {
                final AccountManager account = new AccountManagerImpl();
                if (account.logger(username.getText(), password.getText())) {
                    access.successfullyAccessed(account);
                }
                //aggiungere else: login errato, magari aggiungere JLabel("Credenziali errate")
                //o Username non trovato, Password non trovata: sono da cambiare i metodi dela classe Account
            });
            
        } else {
            loginButton.addActionListener(al);
            loginButton.setPreferredSize(new Dimension(dimX - (dimX / 2), dimY / 7));
            
            registerButton.addActionListener(e -> {
                if (new AccountManagerImpl().register(username.getText(), password.getText(), age.getText())) {
                    //JLabel "registrazione completata esegui il login"
                }
                //else: JLabel "...."
            });
            
        }
    }

}
