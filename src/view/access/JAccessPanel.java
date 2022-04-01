package view.access;

import account.AccountManagerImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



//DA SISTEMARE I MAGIC NUMBERS
public class JAccessPanel extends JPanel implements AccessPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JButton loginbtn;
    private final JButton registerbtn;

    public JAccessPanel(final AccessType access, final int dimX, final int dimY) {
        
        setLayout(new GridBagLayout());
        setBackground(new Color(68, 87, 96));           // blu bello
        //west.setBackground(new Color(44, 107, 14      //verde tavolo
        setPreferredSize(new Dimension(dimX / 3, dimY));
        final JLabel title = new JLabel(access.toString(), SwingConstants.CENTER); // Titolo Login
        final JTextField usr = new JTextField("Username");                         // Username
        final JTextField psw = new JTextField("Password");                         // Password
        final JTextField eta = new JTextField("Età");                              // Età
        loginbtn = new JButton("Login");                                           // Tasto Login
        registerbtn = new JButton("Registrati");                                   // Tasto Registrati

        final ArrayList<JComponent> list = new ArrayList<>();
        list.add(usr);
        list.add(psw);
        if (access.equals(AccessType.REGISTER)) {
            list.add(eta);
            list.add(registerbtn);
            list.add(loginbtn);
        } else {
            list.add(loginbtn);
            list.add(registerbtn);
        }
        
        // Modifiche solo per titolo, SI PUO' MIGLIORARE
        title.setForeground(Color.WHITE);
        if (access.equals(AccessType.LOGIN)) {
            title.setPreferredSize(new Dimension(dimX / 3 - (dimX / 3 / 2), dimY / 10));
            title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX / 20));
        } else {
            title.setPreferredSize(new Dimension(dimX / 2 - (dimX / 3 / 2), dimY / 10));
            title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, dimX / 25));
        }
        add(title, setDimensionObj(0, 0, 60));
        
        // Modifiche generali
        int i = 1;
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(dimX / 3 - (dimX / 3 / 2), dimY / 20));
            jc.setFont(new Font("Arial", Font.PLAIN, dimX / 50));
            add(jc, setDimensionObj(0, i, 5));
            i++;
        }

        if (access.equals(AccessType.LOGIN)) {
            loginbtn.addActionListener(e -> {
                new AccountManagerImpl().logger(usr.getText(), psw.getText());
            });
        } else if (access.equals(AccessType.REGISTER)) {
            registerbtn.addActionListener(e -> {
                new AccountManagerImpl().register(usr.getText(), psw.getText(), eta.getText());
            });
        }
    }

    public void setActionListenerLoginButton(final ActionListener al) {
        if (loginbtn.getActionListeners().length != 0) {//forse da togliere dopo il testing o da gestire l'eccezione nella GuiTest?
            throw new UnsupportedOperationException();
        }
        loginbtn.addActionListener(al);
    }

    public void setActionListenerRegisterButton(final ActionListener al) {
        if (registerbtn.getActionListeners().length != 0) {//forse da togliere dopo il testing o da gestire l'eccezione nella GuiTest?
            throw new UnsupportedOperationException();
        }
        registerbtn.addActionListener(al);
    }

    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int space) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, space, 0); // terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }

}
