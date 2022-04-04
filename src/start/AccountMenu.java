package start;

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
        
//pannello GESTIONE SALDO, sistemare ripetizioni
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        
        final JPanel panelBalance = new JPanel(new GridBagLayout());
        panelBalance.setBackground(new Color(68, 87, 96));
        panelBalance.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel labelDeposit = new JLabel(currencySymbol);
        final JLabel labelWithdraw = new JLabel(currencySymbol);
        final JLabel labelAmount = new JLabel("Amount: ");

        final NumberFormat format = DecimalFormat.getInstance();
        format.setMaximumIntegerDigits(6);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        final JFormattedTextField fieldDeposit = new JFormattedTextField(format);
        final JFormattedTextField fieldWithdraw = new JFormattedTextField(format);
        final NumberFormat formatAmount = DecimalFormat.getCurrencyInstance();
        format.setMaximumIntegerDigits(6);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        final JFormattedTextField fieldAmount = new JFormattedTextField(formatAmount);
        fieldDeposit.setColumns(10);
        fieldDeposit.setValue(0);
        fieldWithdraw.setColumns(10);
        fieldWithdraw.setValue(0);
        fieldAmount.setColumns(10);
        fieldAmount.setEditable(false);
        fieldAmount.setValue(69.69);   //NICO
        
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            String s = fieldDeposit.getText();
            s = s.replace(".", "").replace(",", ".");
            final double d = Double.parseDouble(s);
            //NICO
            System.out.println(d);
            fieldAmount.setValue(1234); //da impostare saldo
        });
        final JButton buttonWithdraw = new JButton("Withdraw");
        buttonWithdraw.addActionListener(e -> {
            String s = fieldWithdraw.getText();
            s = s.replace(".", "").replace(",", ".");
            final double d = Double.parseDouble(s);
            //NICO
            System.out.println(d);
            fieldAmount.setValue(1234); //da impostare saldo
        });

        panelBalance.add(labelDeposit);
        panelBalance.add(fieldDeposit);
        panelBalance.add(buttonDeposit);
        panelBalance.add(labelWithdraw, setDimensionObj(0, 1, 0));
        panelBalance.add(fieldWithdraw, setDimensionObj(1, 1, 0));
        panelBalance.add(buttonWithdraw, setDimensionObj(2, 1, 0));
        panelBalance.add(labelAmount, setDimensionObj(0, 2, 0));
        panelBalance.add(fieldAmount, setDimensionObj(1, 2, 0));
        
        
//pannello CAMBIO USERNAME, sistemare ripetizioni
        final JPanel panelUsername = new JPanel(new GridBagLayout());
        panelUsername.setBackground(new Color(68, 87, 96));
        panelUsername.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel labelUsername = new JLabel("Username: ");
        final JLabel labelNewUsername = new JLabel("New Username: ");
        final JTextField fieldUsername = new JTextField("Massimo Bossetti", 10);        //NICO
        fieldUsername.setEditable(false);
        final JTextField fieldNewUsername = new JTextField(10);
        final JButton buttonUsername = new JButton("Change");
        buttonUsername.addActionListener(e -> {//aggiungere richiesta psw
            final String newUsername = fieldNewUsername.getText();
            fieldUsername.setText(newUsername);
            //NICO
            System.out.println(newUsername);
        });
        panelUsername.add(labelUsername, setDimensionObj(0, 0, 0));
        panelUsername.add(fieldUsername, setDimensionObj(1, 0, 0));
        panelUsername.add(labelNewUsername, setDimensionObj(0, 1, 0));
        panelUsername.add(fieldNewUsername, setDimensionObj(1, 1, 0));
        panelUsername.add(buttonUsername, setDimensionObj(2, 2, 0));
        
        
//pannello CAMBIO PASSWORD, sistemare ripetizioni
        final JPanel panelPassword = new JPanel(new GridBagLayout());
        panelPassword.setBackground(new Color(68, 87, 96));
        panelPassword.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
        final JLabel labelPassword = new JLabel("New Password: ");
        final JLabel labelNewPassword = new JLabel("Confirm Password: ");
        final JTextField fieldPassword = new JTextField(10);        //NICO
        fieldPassword.setEditable(false);//momentaneamente disabilitato, bisogna implementare una JLabel 
        final JTextField fieldNewPassword = new JTextField(10);
        final JButton buttonPassword = new JButton("Change");
        buttonPassword.addActionListener(e -> {//aggiungere richiesta psw
            final String newPassword = fieldNewPassword.getText();
            //NICO
            System.out.println(newPassword);
        });
        panelPassword.add(labelPassword, setDimensionObj(0, 0, 0));
        panelPassword.add(fieldPassword, setDimensionObj(1, 0, 0));
        panelPassword.add(labelNewPassword, setDimensionObj(0, 1, 0));
        panelPassword.add(fieldNewPassword, setDimensionObj(1, 1, 0));
        panelPassword.add(buttonPassword, setDimensionObj(2, 2, 0));
        

        final CardLayout cl = new CardLayout();
        final JPanel panel = new JPanel(cl);
        
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
            //aggiungere JLabel "sicuro di elimiare account? Inserisci la psw:"
            //aggiungere una volta eliminato account tornare menu d'accesso.
            //NICO
            System.out.println("Account deleted");
        });
        
        this.dialog = new JDialog(frame, true);
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
