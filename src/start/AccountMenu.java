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
import javax.swing.SwingConstants;


public class AccountMenu extends JPanel {

    /**
     * 
     **/
    private static final long serialVersionUID = 1L;
    /* Togliere poi */
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int dimX = (int) screenSize.getWidth() / 2;
    private final int dimY = (int) screenSize.getHeight() / 2;
    private final JDialog dialog;
    //da sistemare
    private final JFormattedTextField fieldAmount;
    
    
    /* TESTING, togliere poi */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final AccountMenu accountPanel = new AccountMenu(frame);
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 87, 96));
        panel.setPreferredSize(new Dimension(accountPanel.dimX, accountPanel.dimY));
        final JButton btDialog = new JButton("JDialog");
        panel.add(btDialog, accountPanel.setDimensionObj(0, 0, 40));
        btDialog.addActionListener(e -> {
            accountPanel.getDialog().setVisible(true);
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
        this.setPreferredSize(new Dimension(dimX / 2, dimY));
        
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, dimX / 20));
        int index = 0;
        this.add(title, setDimensionObj(0, index++, 40));
      
        final JButton balanceManagement = new JButton("BALANCE");
        final JButton cambiaUsername = new JButton("CAMBIA USERNAME");
        final JButton cambiaPassword = new JButton("CAMBIA PASSWORD");
        final JButton eliminaAccount = new JButton("ELIMINA ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(balanceManagement);
        list.add(cambiaUsername);
        list.add(cambiaPassword);
        list.add(eliminaAccount);
        for (final JButton jb : list) {
            jb.setPreferredSize(new Dimension(dimX / 4, dimY / 20));
            jb.setFont(new Font("Arial", Font.PLAIN, dimX / 50));
            this.add(jb, setDimensionObj(0, index++, 5));
        }
        
//pannello GESTIONE SALDO, sistemare ripetizioni
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        
        final JPanel panelBalance = new JPanel(new GridBagLayout());
        panelBalance.setBackground(new Color(68, 87, 96));
        panelBalance.setPreferredSize(new Dimension(dimX / 2, dimY));
        
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
        fieldAmount = new JFormattedTextField(formatAmount);
        fieldDeposit.setColumns(10);
        fieldDeposit.setValue(0);
        fieldWithdraw.setColumns(10);
        fieldWithdraw.setValue(0);
        fieldAmount.setColumns(10);
        //NICO
        fieldAmount.setValue(100.89);

        
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            String s = fieldDeposit.getText();
            s = s.replace(".", "").replace(",", ".");
            final double d = Double.parseDouble(s);
            System.out.println(d);
            //NICO
        });
        final JButton buttonWithdraw = new JButton("Withdraw");
        buttonWithdraw.addActionListener(e -> {
            String s = fieldWithdraw.getText();
            s = s.replace(".", "").replace(",", ".");
            final double d = Double.parseDouble(s);
            System.out.println(d);
            //NICO
        });


        panelBalance.add(labelDeposit);
        panelBalance.add(fieldDeposit);
        panelBalance.add(buttonDeposit);
        panelBalance.add(labelWithdraw, setDimensionObj(0, 1, 0));
        panelBalance.add(fieldWithdraw, setDimensionObj(1, 1, 0));
        panelBalance.add(buttonWithdraw, setDimensionObj(2, 1, 0));
        panelBalance.add(labelAmount, setDimensionObj(0, 2, 0));
        panelBalance.add(fieldAmount, setDimensionObj(1, 2, 0));
        
        
        
        
        
        
        
        final CardLayout cl = new CardLayout();
        final JPanel panel = new JPanel(cl);
        
        panel.add(this, "1");
        panel.add(panelBalance, "2");
//        panel.add(panelPreleva, "3");
//        panel.add(panelDettaglioSaldo, "4");
//        panel.add(panelCambiaUsername, "5");
//        panel.add(panelCambiaPassword, "6");
//        panel.add(panelEliminaAccount, "7");
        
        cl.show(panel, "1");
        
        balanceManagement.addActionListener(e -> {
            cl.show(panel, "2");
        });

        cambiaUsername.addActionListener(e -> {
            System.out.println("cambiaUsername");
        });        

        cambiaPassword.addActionListener(e -> {
            System.out.println("cambiaPassword");
        });
        
        eliminaAccount.addActionListener(e -> {
            System.out.println("eliminaAccount");
        });
        
        this.dialog = new JDialog(frame, true);
        this.dialog.setContentPane(panel);
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setResizable(false);
    }
    
    //NICO
    public void setAmount(final double amount) {
        this.fieldAmount.setValue(amount);
    }
    
    public JDialog getDialog() {
        return this.dialog;
    }
   
    /* Da sostituire con la mia versione */
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int verticalSpace) {
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
