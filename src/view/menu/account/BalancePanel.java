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
    
    private static final int SCALE_TITLE = 7;
    private static final int SCALE_COMPONENT = 30;
    private static final double MAX_IMPORT = 10000;
    private static final double MIN_IMPORT = 15;
    private final BalanceManager account;
    
    public BalancePanel(final AccountManager account, final int minSize) {
        
        this.account = new AdvancedBalanceManagerImpl(account);
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96)); //risolvere ripetizione        



        final JLabel labelImport = new JLabel(currencySymbol);
        final JLabel labelBalance = new JLabel("Balance: ");
        final JLabel labelAlert = new JLabel();

    
        final NumberFormat format = DecimalFormat.getInstance();
        final NumberFormat formatBalance = DecimalFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        final JFormattedTextField fieldImport = new JFormattedTextField(format);
        final JFormattedTextField fieldBalance = new JFormattedTextField(formatBalance);
        fieldImport.setColumns(10);
        fieldImport.setValue(0);
        fieldBalance.setColumns(10);
        fieldBalance.setEditable(false);
        fieldBalance.setValue(this.getBalance());
        
        
        
        
        //Eliminare rep con altro bottone(es. crea funzione)
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            final String deposit = fieldImport.getText().replace(".", "").replace(",", ".");
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
            final String withdraw = fieldImport.getText().replace(".", "").replace(",", ".");
            final double amount = Double.parseDouble(withdraw);
            if (this.setWithdraw(amount)) {
                fieldBalance.setValue(this.getBalance());
                labelAlert.setText("Successful withdraw of " + formatBalance.format(amount));
            } else {
                labelAlert.setText("Unsuccessful withdraw");
            }
        });
        
        final JLabel title = new JLabel("BALANCE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, minSize / SCALE_TITLE));
        labelImport.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        labelBalance.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        labelAlert.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        fieldImport.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        fieldBalance.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        buttonDeposit.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        buttonWithdraw.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT));
        
        
        
        final var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelImport, c);
        c.gridx++;
        this.add(fieldImport, c);
        c.gridx++;
        this.add(buttonDeposit, c);
        c.gridx = 0;
        c.gridy++;
        this.add(labelBalance, c);
        c.gridx++;
        this.add(fieldBalance, c);
        c.gridx++;
        this.add(buttonWithdraw, c);
        c.gridx = 0;
        c.gridy++;
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
