package view.menu.account;

import account.AccountManager;
import account.AdvancedBalanceManagerImpl;
import account.BalanceManager;
import java.awt.GridBagConstraints;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class BalancePanel extends AccountPanel {
    
    private static final Locale LOCALE = Locale.ITALY; //vedere se metterla in comune tra altre classi o se togliere la constante e usare locale.italy direttamente
    private static final int N_FRACTION_DIGITS = 2;
    private static final int N_MAX_INTEGER_DIGITS = 6;
    
    private final BalanceManager account;
    private final JFormattedTextField importField;
    
    public BalancePanel(final AccountManager account, final int minSize) {
        super(minSize);
        this.account = new AdvancedBalanceManagerImpl(account);
        
        final JLabel importLabel = new JLabel(Currency.getInstance(LOCALE).getSymbol());
        final JLabel balanceLabel = new JLabel("Balance: ");
        final JLabel alertLabel = new JLabel();
        final NumberFormat format = DecimalFormat.getInstance(LOCALE);
        final NumberFormat balanceFormat = DecimalFormat.getCurrencyInstance(LOCALE);
        format.setMinimumFractionDigits(N_FRACTION_DIGITS);
        format.setMaximumFractionDigits(N_FRACTION_DIGITS);
        format.setMaximumIntegerDigits(N_MAX_INTEGER_DIGITS);
        format.setRoundingMode(RoundingMode.HALF_UP);
        importField = new JFormattedTextField(format);
        final JFormattedTextField balanceField = new JFormattedTextField(balanceFormat);
        importField.setColumns(N_COLUMNS_FIELD);
        importField.setValue(0);
        balanceField.setColumns(N_COLUMNS_FIELD);
        balanceField.setEditable(false);
        balanceField.setValue(this.getBalance());
        
        final JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> {
            final double amount = this.getImport();
            if (this.setDeposit(amount)) {
                balanceField.setValue(this.getBalance());
                alertLabel.setText("Successful deposit of " + balanceFormat.format(amount));
            } else {
                alertLabel.setText("Unsuccessful deposit");
            }
        });
        
        final JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> {
            final double amount = this.getImport();
            if (this.setWithdraw(amount)) {
                balanceField.setValue(this.getBalance());
                alertLabel.setText("Successful withdraw of " + balanceFormat.format(amount));
            } else {
                alertLabel.setText("Unsuccessful withdraw");
            }
        });
        
        final var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(importLabel, c);
        c.gridx++;
        this.add(importField, c);
        c.gridx++;
        this.add(depositButton, c);
        c.gridx = 0;
        c.gridy++;
        this.add(balanceLabel, c);
        c.gridx++;
        this.add(balanceField, c);
        c.gridx++;
        this.add(withdrawButton, c);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 3;
        this.add(alertLabel, c);
    }
    
    private double getImport() {
        return Double.parseDouble(importField.getText().replace(".", "").replace(",", "."));
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
