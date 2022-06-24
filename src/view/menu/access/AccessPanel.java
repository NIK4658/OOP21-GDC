package view.menu.access;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.SimpleAccountManagerImpl;
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
        final JTextField age = new JTextField("Age"); 
        final JButton loginButton;
        final JButton registerButton;
        final ArrayList<JComponent> list = new ArrayList<>();
        
        
        list.add(warning);
        list.add(username);
        list.add(password);
        
        if (accessType.equals(AccessType.LOGIN)) {
            loginButton = new JButton("Login");
            registerButton = new JButton("<html>\nDon't have an Account?<br/>Sign in!<html>");
            list.add(loginButton);
            list.add(registerButton);
        } else {
            registerButton = new JButton("Sign In");
            loginButton = new JButton("<html>Already got an account?<br/>Log in!</html>");
            list.add(age);
            list.add(registerButton);
            list.add(loginButton);
        }
        
        
        final int dimX = dim.width;
        final int dimY = dim.height;
        
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(dimX, dimY / RATIOTITLEAREAY));
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX / RATIOTITLEFONT));
        final GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, SPACINGTITLE, 0);
        this.add(title, c);
        
        // Modifiche generali
        int i = 1;
        c.insets = new Insets(0, 0, SPACINGBTN, 0);
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(dimX / RATIOBTNAREAX, dimY / RATIOBTNAREAY));
            jc.setFont(new Font("Arial", Font.PLAIN, dimX / RATIOBTNFONT));
            jc.setForeground(new Color(150, 150, 150));
            c.gridy = i;
            this.add(jc, c);
            i++;
        }
        
        loginButton.setForeground(Color.BLACK);
        registerButton.setForeground(Color.BLACK);
        
        warning.setFont(new Font("Arial", Font.PLAIN, dimX/((RATIOBTNFONT))));
        
       
        username.addFocusListener(new FocusListener() {  
            @Override  
            public void focusGained(final FocusEvent e) {  
                if (username.getText().equals("Username")) {  
                    username.setText("");  
                    username.setForeground(new Color(50, 50, 50));   
                }  
            }  
            
            @Override
            public void focusLost(final FocusEvent e) { 

                if (username.getText().length() == 0) {  
                    username.setText("Username");  
                    username.setForeground(new Color(150, 150, 150));  
                }
            }
        });
        
        password.addFocusListener(new FocusListener() {  
            @Override  
            public void focusGained(final FocusEvent e) {  
                if (password.getText().equals("Password")) {  
                    password.setText("");  
                    password.setForeground(new Color(50, 50, 50));  
                }  
            }  
            
            @Override
            public void focusLost(final FocusEvent e) { 
                if (password.getText().length() == 0) {  
                    password.setText("Password");  
                    password.setForeground(new Color(150, 150, 150));  
                }
            }
        });
        
        
        age.addFocusListener(new FocusListener() {  
            @Override  
            public void focusGained(final FocusEvent e) {  
                if (age.getText().equals("Age")) {  
                    age.setText("");  
                    age.setForeground(new Color(50, 50, 50));  
                }  
            }  
            
            @Override
            public void focusLost(final FocusEvent e) { 
                if (age.getText().length() == 0) {  
                    age.setText("Age");  
                    age.setForeground(new Color(150, 150, 150));  
                }
            }
        });
        
        
        if (accessType.equals(AccessType.LOGIN)) {
            registerButton.addActionListener(al);
            registerButton.setPreferredSize(new Dimension(dimX / RATIOBTNACCESSAREAX, dimY / RATIOBTNACCESSAREAY));
            
            loginButton.addActionListener(e -> {
                final AccountManager account = new AdvancedAccountManagerImpl();
                if (account.logger(username.getText(), password.getText())) {
                    access.successfullyAccessed(account);
                } else {
                    warning.setText("Wrong Credentials");
                }
            });
            
        } else {
            loginButton.addActionListener(al);
            loginButton.setPreferredSize(new Dimension(dimX / RATIOBTNACCESSAREAX, dimY / RATIOBTNACCESSAREAY));
            registerButton.addActionListener(e -> {
                if (new AdvancedAccountManagerImpl().register(username.getText(), password.getText(), age.getText())) {
                    warning.setText("Signed up");
                } else {
                    warning.setText("Unable to sign up");
                }
            });
            
        }
    }

}
