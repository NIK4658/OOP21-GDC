package view.menu.access;

import account.AdvancedAccountManager;
import account.AdvancedAccountManagerImpl;
import account.SimpleAccountManager;
import account.SimpleAccountManagerImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import view.GridBagConstraintsConstructor;
import view.menu.access.Access.AccessType;


/**
 * //DA SISTEMARE I MAGIC NUMBERS.
 */
public class AccessPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int RATIOTITLEFONT = 6;
    private static final int RATIOTITLEAREAY = 10;
    private static final int RATIOBTNACCESSAREAX = 2;
    private static final int RATIOBTNACCESSAREAY = 7;
    private static final int RATIOBTNAREAX = 2;
    private static final int RATIOBTNAREAY = 20;
    private static final int RATIOBTNFONT = 17;
    private static final int SPACINGTITLE = 60;
    private static final int SPACINGBTN = 5;
    
    
    /**
     * Main function.
     */
    public AccessPanel(final Access access, final Dimension dim, final AccessType accessType, final ActionListener al) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(dim);
        final JLabel title = new JLabel(accessType.toString(), SwingConstants.CENTER);
        final JLabel warning = new JLabel("", SwingConstants.CENTER);
        final JTextField username = new JTextField("Username");
        final JTextField password = new JTextField("Password");                        
        final JTextField age = new JTextField("Età"); 
        final JButton loginButton;
        final JButton registerButton;
        final ArrayList<JComponent> list = new ArrayList<>();
        //warning.setText("ciao");
        
        //da eliminare
        username.setForeground(new Color(150, 150, 150));
        password.setForeground(new Color(150, 150, 150));
        
        list.add(warning);
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
        
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(dimX, dimY / RATIOTITLEAREAY));
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX / RATIOTITLEFONT));

        this.add(title, GridBagConstraintsConstructor.get(0, 0, SPACINGTITLE));
        
        // Modifiche generali
        int i = 1;
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(dimX / RATIOBTNAREAX, dimY / RATIOBTNAREAY));
            jc.setFont(new Font("Arial", Font.PLAIN, dimX / RATIOBTNFONT));
            this.add(jc, GridBagConstraintsConstructor.get(0, i, SPACINGBTN));
            i++;
        }
        
        //warning.setFont(new Font("Arial", Font.PLAIN, dimX/(RATIOBTNFONT-2)));
        
       
        username.addFocusListener(new FocusListener() {  
            @Override  
            public void focusGained(FocusEvent e) {  
                username.setText("");  
                username.setForeground(new Color(50, 50, 50));  
            }  
            
            @Override
            public void focusLost(FocusEvent e) { 

                if (username.getText().length() == 0) {  
                    username.setText("Username");  
                    username.setForeground(new Color(150, 150, 150));  
                }
            }
        });
        
        password.addFocusListener(new FocusListener() {  
            @Override  
            public void focusGained(FocusEvent e) {  
                password.setText("");  
                password.setForeground(new Color(50, 50, 50));  
            }  
            
            @Override
            public void focusLost(FocusEvent e) { 

                if (password.getText().length() == 0) {  
                    password.setText("Password");  
                    password.setForeground(new Color(150, 150, 150));  
                }
            }
        });
        
        
        if (accessType.equals(AccessType.LOGIN)) {
            registerButton.addActionListener(al);
            registerButton.setPreferredSize(new Dimension(dimX / RATIOBTNACCESSAREAX, dimY / RATIOBTNACCESSAREAY));
            
            loginButton.addActionListener(e -> {
                final AdvancedAccountManager account = new AdvancedAccountManagerImpl();
                if (account.logger(username.getText(), password.getText())) {
                    access.successfullyAccessed(account);
                } else {
                    warning.setText("Login errato");
                }
                //aggiungere else: login errato, magari aggiungere JLabel("Credenziali errate")
                //o Username non trovato, Password non trovata: sono da cambiare i metodi dela classe Account
            });
            
        } else {
            loginButton.addActionListener(al);
            loginButton.setPreferredSize(new Dimension(dimX / RATIOBTNACCESSAREAX, dimY / RATIOBTNACCESSAREAY));
            
            registerButton.addActionListener(e -> {
                if (new AdvancedAccountManagerImpl().register(username.getText(), password.getText(), age.getText())) {
                    //JLabel "registrazione completata esegui il login"
                } else {
                    warning.setText("Impossibile registrarsi");
                }
                //else: JLabel "...."
            });
            
        }
    }

}
