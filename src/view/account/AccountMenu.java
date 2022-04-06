package view.account;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AccountMenu extends JPanel {

    /**
     * 
     **/
    private static final long serialVersionUID = 1L;
    /* Togliere poi */
    private final static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private final static int DIMX = (int) SCREENSIZE.getWidth() / 2;
    private final static int DIMY = (int) SCREENSIZE.getHeight() / 2;
    private final JDialog dialog;
    private final CardLayout cl;
    private final JPanel panel;
    
    /* TESTING, togliere poi */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 87, 96));
        panel.setPreferredSize(new Dimension(DIMX, DIMY));
        final JButton btDialog = new JButton("JDialog");
        panel.add(btDialog, setDimensionObj(0, 0, 40));
        btDialog.addActionListener(e -> {
            new AccountMenu(frame).getDialog().setVisible(true);
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public AccountMenu(final JFrame frame) {
        //sistemare
        this.dialog = new JDialog(frame, true);
        
        
//pannello MENU
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, DIMX / 20));
        int index = 0;
        this.add(title, setDimensionObj(0, index++, 40));
      
        final JButton balanceManagement = new JButton("BALANCE");
        final JButton changeUsername = new JButton("CHANGE USERNAME");
        final JButton changePassword = new JButton("CHANGE PASSWORD");
        final JButton deleteAccount = new JButton("DELETE ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(balanceManagement);
        list.add(changeUsername);
        list.add(changePassword);
        list.add(deleteAccount);
        for (final JButton jb : list) {
            jb.setPreferredSize(new Dimension(DIMX / 4, DIMY / 20));
            jb.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
            this.add(jb, setDimensionObj(0, index++, 5));
        }
        
        
        final BalancePanel panelBalance = new BalancePanel(DIMX, DIMY);
        final UsernamePanel panelUsername = new UsernamePanel(dialog, DIMX, DIMY);
        final PasswordPanel panelPassword = new PasswordPanel(dialog, DIMX, DIMY);
        

        cl = new CardLayout();
        panel = new JPanel(cl);
        
        panel.add(this, "1");
        panel.add(panelBalance, "2");
        panel.add(panelUsername, "3");
        panel.add(panelPassword, "4");
        
        cl.show(panel, "1");
        
        balanceManagement.addActionListener(e -> {
            cl.show(panel, "2");
        });

        changeUsername.addActionListener(e -> {
            cl.show(panel, "3");
        });        

        changePassword.addActionListener(e -> {
            cl.show(panel, "4");
        });
        
        deleteAccount.addActionListener(e -> {
            //aggiungere una volta eliminato account tornare menu d'accesso.
            
            final UpdatePanel p = new UpdatePanel() {
                @Override
                public void update() {
                    System.out.println("AccountDeleted");
                  //NICO 
                }
            };
            new ConfirmPassword(dialog, p, 3, DIMX / 2, DIMY);
        });
        
        
        
        this.dialog.setContentPane(panel);
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setResizable(false);
    }
    
    public JDialog getDialog() {
        return this.dialog;
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
    
/*Appunti*/
//  final NumberFormat format = NumberFormat.getNumberInstance();
//  final NumberFormat format = NumberFormat.getCurrencyInstance();
//  final NumberFormat format = DecimalFormat.getCurrencyInstance();
//  fieldRicarica.setName("Ricarica");
//  fieldRicarica.setEditable(false); 
//  labelRicarica.setLabelFor(fieldRicarica);

}