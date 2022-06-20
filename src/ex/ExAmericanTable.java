package ex;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import blackjack.BetButton;
import roulette.number.AmericanRouletteNumber;
import roulette.number.BaseRouletteNumber;
import roulette.number.BaseRouletteNumber.Column;
import roulette.number.BaseRouletteNumber.Included;
import roulette.number.BaseRouletteNumber.Parity;
import roulette.number.BaseRouletteNumber.Row;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.EuropeanRouletteNumber.Sector;
import roulette.numbers.AmericanRouletteNumbers;
import roulette.numbers.EuropeanRouletteNumbers;
import utility.Pair;
import view.ImageLoader;
import view.MyGridBagConstraints;
import view.menu.GeneralGui2;
import view.menu.games.roulette.RouletteBetButton;

public class ExAmericanTable extends JPanel implements ExTable {
    
    private final Image img;
    private int x;
    private int y;
    private GridBagConstraints gbc;
    private RouletteBetButton b;
    private Dimension d;
    private final int width;
    private final int height;
    private final List<RouletteBetButton> buttons;
    private final GeneralGui2 generalInterface;
//    private final ActionListener al;
    
    public ExAmericanTable(final GeneralGui2 generalInterface) {
//        final ImageIcon imgIcon = new ImageIcon("res/img/backgrounds/AmericanRouletteTable.png");
        this.img = ImageLoader.getImage("res/img/backgrounds/AmericanRouletteTable.png");
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        this.setLayout(new GridBagLayout());
        
        this.generalInterface = generalInterface;
        this.buttons = new LinkedList<>();
        
        this.addNumbers();
        this.addRows();
        this.addColumns();
        this.addNumbersIncluded();
        this.addParity();
        this.addColors();
        this.addActionListener();
        
    }

    
    @Override
    public List<Pair<Object, Double>> endBetting() { //usare una mappa?
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        for (final var b : buttons) {
            bets.add(new Pair<>(b.getProperty(), b.getBet()));
            b.resetBet();
        }
        return bets;
    }


    private void addNumbers() {
        x = 1;
        y += 3;
        gbc = new MyGridBagConstraints(x, y);
        for (final AmericanRouletteNumber n : new AmericanRouletteNumbers().getAmericanList()) {
            final Integer value = n.getValue();
            b = new RouletteBetButton(value);
            b.setForeground(n.getColor());
            buttons.add(b);
            if (value == 0) {
                b.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(b, new MyGridBagConstraints(0, 1, 1, 1));
            } else if (value == 37) {
                b.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(b, new MyGridBagConstraints(0, 3, 1, 1));
            } else {
                b.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(b, gbc);
                y--;
                if (y == 0) {
                    y = 3;
                    x++;
                    gbc.gridx = x;
                }
                gbc.gridy = y;
            }
        }
    }
    
    private void addRows() {
        gbc = new MyGridBagConstraints(x, y, 1, 1);
        for (; y > 0; y--) {
            final Row row;
            switch (y) {
                case 1: row = Row.FIRST;
                    break;
                case 2: row = Row.SECOND;
                    break;
                default: row = Row.THIRD;
            }
            b = new RouletteBetButton(row);
            b.setPreferredSize(new Dimension(width / 14, height / 6));
            buttons.add(b);
            this.add(b, gbc);
            gbc.gridy--;
        }
    }
    
    private void addColumns() {
        x -= 12;
        y += 4;
        gbc = new MyGridBagConstraints(x, y, 4, 1);
        d = new Dimension(width / 14 * 4, height / 6);
        b = new RouletteBetButton(Column.FIRST);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        gbc.gridx += 4;
        b = new RouletteBetButton(Column.SECOND);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        gbc.gridx += 4;
        b = new RouletteBetButton(Column.THIRD);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        
    }

    private void addNumbersIncluded() {
        y += 1;
        gbc = new MyGridBagConstraints(x, y, 2, 1);
        d = new Dimension(width / 7, height / 6);
        b = new RouletteBetButton(Included._1_18_);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        gbc.gridx += 10;
        b = new RouletteBetButton(Included._19_36_);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
    }
    
    private void addParity() {
        x += 2;
        gbc.gridx = x;
        b = new RouletteBetButton(Parity.EVEN);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        gbc.gridx += 6;
        b = new RouletteBetButton(Parity.ODD);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
    }
    
    private void addColors() {
        x += 2;
        gbc.gridx = x;
        b = new RouletteBetButton(Color.RED);
        b.setPreferredSize(d);
        buttons.add(b);
        this.add(b, gbc);
        gbc.gridx += 2;
        final RouletteBetButton ba = new RouletteBetButton(Color.BLACK);
        ba.setPreferredSize(d);
        buttons.add(ba);
        this.add(ba, gbc);
    }
    
    private void addActionListener() {
        for (final var button : buttons) {
            button.addActionListener(e -> {
                final double fichesValue = this.generalInterface.getFichesValue();
                button.incrementBet(fichesValue);
                //settare bet in GeneralGui2
            });
        }
    }
    
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }
    
}
