package view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import account.AccountManager;
import view.gui.MenuManager;
import view.menu.games.roulette.RoulettePanel;
import view.menu.games.roulette.Table;

public class GameMenu extends JPanel implements Menu {
    
    private static final int CLOCK_TIME = 1000;
    private static final int TIME_BETTING = 10000;
    private int time;
    private final JFormattedTextField balanceField;
    private final JFormattedTextField betField;
    
    
    public GameMenu(final MenuManager frame, final AccountManager account) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
//        this.setBackground(new Color(0, 118, 58)); //verde come il tavolo della roulette
        
        final JPanel south = new JPanel(new GridBagLayout());
        final JLabel balanceLabel = new JLabel("Balance");
        final JLabel betLabel = new JLabel("Bet");
        final NumberFormat format = DecimalFormat.getCurrencyInstance();
        balanceField = new JFormattedTextField(format);
        betField = new JFormattedTextField(format);
        balanceField.setEditable(false);
        betField.setEditable(false);
        this.updateField();
        
        final JLabel timer = new JLabel();
        this.time = TIME_BETTING;
        final ActionListener clock = e -> {
            if (time <= 0) {
                time = TIME_BETTING;
            }
            timer.setText(String.valueOf(time / CLOCK_TIME));
            time -= 1000;
        };
        final ActionListener endBetting = e -> System.out.println("End betting");
        new Timer(CLOCK_TIME, clock).start();
        new Timer(TIME_BETTING, endBetting).start();
        
        south.add(balanceLabel);
        south.add(balanceField);
        south.add(betLabel);
        south.add(betField);
        south.add(timer);
        this.add(south, BorderLayout.SOUTH);
        this.add(new RoulettePanel());
    }

    private void updateBalance() {
        this.balanceField.setValue(69.69);
    }

    private void updateBet() {
        this.betField.setValue(69.69);
    }
    
    private void updateField() {
        this.updateBalance();
        this.updateBet();
    }

    @Override
    public JPanel getMenu() {
        return this;
    }
}
