package view.menu.games;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamesPanel extends JPanel {
    
    public GamesPanel(){
        this.setLayout(new GridBagLayout());
        
        final JLabel balanceLabel = new JLabel("Balance");
        final NumberFormat format = DecimalFormat.getCurrencyInstance();
        final JFormattedTextField balanceField = new JFormattedTextField(format);
        balanceField.setEditable(false);
        balanceField.setValue(this.getBalance());
        final JLabel betLabel = new JLabel("Bet");
        final JFormattedTextField betField = new JFormattedTextField(format);
        betField.setEditable(false);
        betField.setValue(this.getBet());
        
        this.add(balanceLabel);
        this.add(balanceField);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 50, 0, 0);
        this.add(betLabel, c);
        this.add(betField);
    }

    private double getBalance() {
        return 69.69;
    }

    private double getBet() {
        return 666;
    }
}
