package view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import account.AccountManager;
import view.gui.MenuManager;
import view.menu.games.roulette.RoulettePanel;
import view.menu.games.roulette.Table;

public class GameMenu extends JPanel implements Menu {
    
//    private final JFormattedTextField balanceField;
//    private final JFormattedTextField betField;
    
    public GameMenu(final MenuManager frame, final AccountManager account) {
        this.setLayout(new BorderLayout());
//        this.setPreferredSize(frame.getSizeMenu());
//        this.setBackground(new Color(0, 118, 58)); //verde come il tavolo della roulette
        
        final JPanel panel = new JPanel(new GridBagLayout());
//        final JLabel balanceLabel = new JLabel("Balance");
//        final JLabel betLabel = new JLabel("Bet");
//        final NumberFormat format = DecimalFormat.getCurrencyInstance();
//        balanceField = new JFormattedTextField(format);
//        betField = new JFormattedTextField(format);
//        balanceField.setEditable(false);
//        betField.setEditable(false);
//        this.updateField();
//        final JButton b = new JButton("Round ended");
//        panel.add(balanceLabel);
//        panel.add(balanceField);
//        panel.add(betLabel);
//        panel.add(betField);
//        panel.add(b);
        this.add(panel, BorderLayout.SOUTH);
        this.add(new RoulettePanel());
    }

//    private void updateBalance() {
//        this.balanceField.setValue(69.69);
//    }
//
//    private void updateBet() {
//        this.betField.setValue(69.69);
//    }
//    
//    private void updateField() {
//        this.updateBalance();
//        this.updateBet();
//    }

    @Override
    public JPanel getMenu() {
        return this;
    }
}
