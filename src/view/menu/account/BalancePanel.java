package view.menu.account;

import java.awt.Color;
import java.awt.Dimension;
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

import account.SimpleAccountManager;
import account.SimpleBalanceManager;
import account.SimpleBalanceManagerImpl;
import view.GridBagConstraintsConstructor;

//pannello GESTIONE SALDO, sistemare ripetizioni e creare funzioni per check amount
public class BalancePanel extends JPanel {
    
    private static final double MAX_IMPORT = 10000;
    private static final double MIN_IMPORT = 15;
    private final SimpleBalanceManager account;
    
    public BalancePanel(final SimpleAccountManager account) {
        this.account = new SimpleBalanceManagerImpl(account);
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        
        //aggiungere "titolo" BALANCE
        final JLabel labelDeposit = new JLabel(currencySymbol);
        final JLabel labelWithdraw = new JLabel(currencySymbol);
        final JLabel labelBalance = new JLabel("Balance: ");
        final JLabel labelAlert = new JLabel();
    
        final NumberFormat format = DecimalFormat.getInstance();
        final NumberFormat formatBalance = DecimalFormat.getCurrencyInstance();
//        format.setMaximumIntegerDigits(4);
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
        
        //Eliminare rep con altro bottone(es. crea funzione)
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            final String deposit = fieldDeposit.getText().replace(".", "").replace(",", ".");
            final double amount = Double.parseDouble(deposit);
            if (amount < MIN_IMPORT) {
                labelAlert.setText("The minimum amount must be at least " + formatBalance.format(MIN_IMPORT));
            } else if (amount > MAX_IMPORT) {
                labelAlert.setText("The maximum amount must be amaximum " + formatBalance.format(MAX_IMPORT));
            } else {
                if (this.setDeposit(amount)) {
                    fieldBalance.setValue(this.getBalance());
                    labelAlert.setText("Successful deposit of " + formatBalance.format(amount));
                } else {
                    labelAlert.setText("Unsuccessful deposit");
                }
            }
        });
        final JButton buttonWithdraw = new JButton("Withdraw");
        buttonWithdraw.addActionListener(e -> {
            final String withdraw = fieldWithdraw.getText().replace(".", "").replace(",", ".");
            final double amount = Double.parseDouble(withdraw);
            if (amount < MIN_IMPORT) {
                labelAlert.setText("The minimum amount must be at least " + formatBalance.format(MIN_IMPORT));
            } else if (amount > MAX_IMPORT) {
                labelAlert.setText("The maximum amount must be a maximum " + formatBalance.format(MAX_IMPORT));
            } else {
                if (this.setWithdraw(amount)) {
                    fieldBalance.setValue(this.getBalance());
                    labelAlert.setText("Successful withdraw of " + formatBalance.format(amount));
                } else {
                    labelAlert.setText("Unsuccessful withdraw");
                }
            }
        });
    
        this.add(labelDeposit);
        this.add(fieldDeposit);
        this.add(buttonDeposit);
        this.add(labelWithdraw, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldWithdraw, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonWithdraw, GridBagConstraintsConstructor.get(2, 1, 0));
        this.add(labelBalance, GridBagConstraintsConstructor.get(0, 2, 0));
        this.add(fieldBalance, GridBagConstraintsConstructor.get(1, 2, 0));
        this.add(labelAlert, GridBagConstraintsConstructor.get(1, 3, 0));
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
