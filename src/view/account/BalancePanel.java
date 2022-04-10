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

//pannello GESTIONE SALDO, sistemare ripetizioni
public class BalancePanel extends JPanel {
    
    public BalancePanel() {
        final String currencySymbol = Currency.getInstance(getLocale()).getSymbol();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
//        this.setPreferredSize(new Dimension(DIMX / 2, DIMY));
        
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
        fieldAmount.setValue(69.69);   //NICO
        
        final JButton buttonDeposit = new JButton("Deposit");//eliminare rep con altro bottone(es. crea funzione)
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
    
        this.add(labelDeposit);
        this.add(fieldDeposit);
        this.add(buttonDeposit);
        this.add(labelWithdraw, setDimensionObj(0, 1, 0));
        this.add(fieldWithdraw, setDimensionObj(1, 1, 0));
        this.add(buttonWithdraw, setDimensionObj(2, 1, 0));
        this.add(labelAmount, setDimensionObj(0, 2, 0));
        this.add(fieldAmount, setDimensionObj(1, 2, 0));
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
    
    

}
