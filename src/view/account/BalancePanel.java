package view.account;

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

import account.AccountManager;
import view.GridBagConstraintsConstructor;

//pannello GESTIONE SALDO, sistemare ripetizioni
public class BalancePanel extends JPanel {
    
    private final AccountManager account;
    
    public BalancePanel(final AccountManager account) {
        this.account = account;
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        
        //aggiungere "titolo" BALANCE
        final JLabel labelDeposit = new JLabel(currencySymbol);
        final JLabel labelWithdraw = new JLabel(currencySymbol);
        final JLabel labelAmount = new JLabel("Amount: ");
    
        final NumberFormat format = DecimalFormat.getInstance();
        final NumberFormat formatAmount = DecimalFormat.getCurrencyInstance();
        format.setMaximumIntegerDigits(6);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        final JFormattedTextField fieldDeposit = new JFormattedTextField(format);
        final JFormattedTextField fieldWithdraw = new JFormattedTextField(format);
        final JFormattedTextField fieldAmount = new JFormattedTextField(formatAmount);
        fieldDeposit.setColumns(10);
        fieldDeposit.setValue(0);
        fieldWithdraw.setColumns(10);
        fieldWithdraw.setValue(0);
        fieldAmount.setColumns(10);
        fieldAmount.setEditable(false);
        fieldAmount.setValue(this.getAmount());
        
        //Eliminare rep con altro bottone(es. crea funzione)
        final JButton buttonDeposit = new JButton("Deposit");
        buttonDeposit.addActionListener(e -> {
            final String deposit = fieldDeposit.getText().replace(".", "").replace(",", ".");
            this.setDeposit(Double.parseDouble(deposit));
            fieldAmount.setValue(this.getAmount());
        });
        final JButton buttonWithdraw = new JButton("Withdraw");
        buttonWithdraw.addActionListener(e -> {
            final String withdraw = fieldWithdraw.getText().replace(".", "").replace(",", ".");
            this.setWithdraw(Double.parseDouble(withdraw));
            fieldAmount.setValue(this.getAmount());
        });
    
        this.add(labelDeposit);
        this.add(fieldDeposit);
        this.add(buttonDeposit);
        this.add(labelWithdraw, GridBagConstraintsConstructor.get(0, 1, 0));
        this.add(fieldWithdraw, GridBagConstraintsConstructor.get(1, 1, 0));
        this.add(buttonWithdraw, GridBagConstraintsConstructor.get(2, 1, 0));
        this.add(labelAmount, GridBagConstraintsConstructor.get(0, 2, 0));
        this.add(fieldAmount, GridBagConstraintsConstructor.get(1, 2, 0));
    }

    private double getAmount() {
        return this.account.balanceAmount();
    }
    
    private void setDeposit(final double deposit) {
//      this.account.?          NICO
    }
    
    
    private void setWithdraw(final double withdraw) {
//      this.account.?          NICO
    }

}
