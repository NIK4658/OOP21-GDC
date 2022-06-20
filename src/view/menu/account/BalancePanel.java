package view.menu.account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import account.AccountManager;
import account.AdvancedBalanceManagerImpl;
import account.BalanceManager;
import account.SimpleBalanceManagerImpl;

//pannello GESTIONE SALDO, sistemare ripetizioni e creare funzioni per check amount
public class BalancePanel extends JPanel {
    
//    private static final int SCALE_TITLE = 7;
//    private static final int SCALE_COMPONENT = 20;
    private static final double MAX_IMPORT = 10000;
    private static final double MIN_IMPORT = 15;
    private final BalanceManager account;
    
    public BalancePanel(final AccountManager account, final Dimension dimension) { //togliere dimension
        
//        final int width = dimension.width;
//        final int height = dimension.height;
//        final int minSize = Math.min(width, height);
        
        
        this.account = new AdvancedBalanceManagerImpl(account);
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));//da cambiare is opaque
        

        //inserire title???
//        final JLabel title = new JLabel("BALANCE", SwingConstants.CENTER);
//        title.setForeground(Color.WHITE);
//        title.setFont(new Font("Arial", Font.BOLD, minSize / SCALE_TITLE));
        final JLabel labelDeposit = new JLabel(currencySymbol);
        final JLabel labelWithdraw = new JLabel(currencySymbol);
        final JLabel labelBalance = new JLabel("Balance: ");
        final JLabel labelAlert = new JLabel();
//        labelDeposit.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
//        labelWithdraw.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
//        labelBalance.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
//        labelAlert.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
    
        final NumberFormat format = DecimalFormat.getInstance();
        final NumberFormat formatBalance = DecimalFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        final JFormattedTextField fieldDeposit = new JFormattedTextField(format);
        final JFormattedTextField fieldWithdraw = new JFormattedTextField(format);
        final JFormattedTextField fieldBalance = new JFormattedTextField(formatBalance);
        fieldDeposit.setColumns(10);
        fieldDeposit.setValue(0);
        fieldWithdraw.setColumns(10);
        fieldWithdraw.setValue(0);
        fieldBalance.setColumns(10);
        fieldBalance.setEditable(false);
        fieldBalance.setValue(this.getBalance());
        
//        fieldDeposit.setPreferredSize(new Dimension(width / SCALE_COMPONENT, height / SCALE_COMPONENT));
        
        
        
        //Eliminare rep con altro bottone(es. crea funzione)
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            final String deposit = fieldDeposit.getText().replace(".", "").replace(",", ".");
            final double amount = Double.parseDouble(deposit);
            if (this.setDeposit(amount)) {
                fieldBalance.setValue(this.getBalance());
                labelAlert.setText("Successful deposit of " + formatBalance.format(amount));
            } else {
                labelAlert.setText("Unsuccessful deposit");
            }
        });
        
        final JButton buttonWithdraw = new JButton("Withdraw");
        buttonWithdraw.addActionListener(e -> {
            final String withdraw = fieldWithdraw.getText().replace(".", "").replace(",", ".");
            final double amount = Double.parseDouble(withdraw);
            if (this.setWithdraw(amount)) {
                fieldBalance.setValue(this.getBalance());
                labelAlert.setText("Successful withdraw of " + formatBalance.format(amount));
            } else {
                labelAlert.setText("Unsuccessful withdraw");
            }
        });
        final var c = new GridBagConstraints();
        this.add(labelDeposit);
        this.add(fieldDeposit);
        this.add(buttonDeposit);
        c.gridy = 1;
        this.add(labelWithdraw, c);
        c.gridx = 1;
        this.add(fieldWithdraw, c);
        c.gridx = 2;
        this.add(buttonWithdraw, c);
        c.gridx = 0;
        c.gridy = 2;
        this.add(labelBalance, c);
        c.gridx = 1;
        this.add(fieldBalance, c);
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 3;
        this.add(labelAlert, c);
    }

    private double getBalance() {
        return this.account.getBalance();
    }
    
    private boolean setDeposit(final double deposit) {
        return this.account.deposit(deposit);
    }
    
    
    private boolean setWithdraw(final double withdraw) {
        return this.account.withdraw(withdraw);
    }

}
