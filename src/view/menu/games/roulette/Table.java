package view.menu.games.roulette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import model.roulette.number.BaseRouletteNumber.Column;
import model.roulette.number.BaseRouletteNumber.Included;
import model.roulette.number.BaseRouletteNumber.Parity;
import model.roulette.number.BaseRouletteNumber.Row;
import model.roulette.number.EuropeanRouletteNumber.Sector;
import model.roulette.number.RouletteNumber;
import model.roulette.numbers.AmericanRouletteNumbers;
import model.roulette.numbers.BaseRouletteNumbers;
import utility.Pair;
import view.ImageLoader;
import view.MyGridBagConstraints;
import view.menu.GeneralGui;
import view.menu.games.Game.Games;

public class Table extends JPanel {
    
    private final GeneralGui generalInterface;
    private final Games game;
    private final Image img;
    private int gridx;
    private int gridy;
    private GridBagConstraints gbc;
    private RouletteBetButton button;
    private Dimension dim;
    private final int width;
    private final int height;
    private final List<RouletteBetButton> buttons;
    
    public Table(final GeneralGui generalInterface, final Games game) {
        this.generalInterface = generalInterface;
        this.game = game;
        this.setPreferredSize(generalInterface.getMenu().getPreferredSize());
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        this.setLayout(new GridBagLayout());
        this.buttons = new LinkedList<>();
        
        this.gridx = 1;
        this.gridy = 0;
        switch (game) {
            case ROULETTE_BASE: 
                this.img = ImageLoader.getImage("res/img/backgrounds/BaseRouletteTable.png");
                break;
            case ROULETTE_AMERICAN: 
                this.img = ImageLoader.getImage("res/img/backgrounds/AmericanRouletteTable.png");
                break;
            case ROULETTE_EUROPEAN: 
                this.img = ImageLoader.getImage("res/img/backgrounds/EuropeanRouletteTable.png");
                this.addSectors();
                break;
            default: 
                this.img = null; //lanciare eccezione?
        }
        this.addNumbers();
        this.addRows();
        this.addColumns();
        this.addNumbersIncluded();
        this.addParity();
        this.addColors();
        this.addActionListener();
        
    }

    public List<Pair<Object, Double>> confirmBet() {
        final List<Pair<Object, Double>> bets = new LinkedList<>();
        for (final var b : buttons) {
            bets.add(new Pair<>(b.getProperty(), b.getBet()));
            b.resetBet();
        }
        return bets;
    }
    
    public void resetBet() {
        buttons.forEach(b -> {
            b.resetBet();
        });
        generalInterface.updateBalanceValue();
    }
    
    private void addSectors() {
        dim = new Dimension(this.width / 14 * 3, this.height / 6);
        gbc = new MyGridBagConstraints(gridx, gridy, 3, 1);
        button = new RouletteBetButton(this.getPreferredSize(), Sector.TIER);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.getPreferredSize(), Sector.ORPHELINS);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.getPreferredSize(), Sector.VOISINS);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.getPreferredSize(), Sector.ZERO);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }

    private void addNumbers() {
        gridy += 3;
        gbc = new MyGridBagConstraints(gridx, gridy);
        
        final List<RouletteNumber> list;
        if (this.game == Games.ROULETTE_AMERICAN) {
            list = new AmericanRouletteNumbers().getList();
        } else {
            list = new BaseRouletteNumbers().getList();
        }
        for (final RouletteNumber n : list) {
            final Integer value = n.getValue();
            button = new RouletteBetButton(this.getPreferredSize(), value);
            button.setForeground(n.getColor());
            buttons.add(button);
            if (value == 0 && game == Games.ROULETTE_AMERICAN) {
                button.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(button, new MyGridBagConstraints(0, 1, 1, 1));
            } else if (value == AmericanRouletteNumbers._00_) {
                button.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(button, new MyGridBagConstraints(0, 3, 1, 1));
            } else if (value == 0) { 
                button.setPreferredSize(new Dimension(width / 14, height / 2));
                this.add(button, new MyGridBagConstraints(0, 1, 1, 3));
            } else {
                button.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(button, gbc);
                gridy--;
                if (gridy == 0) {
                    gridy = 3;
                    gridx++;
                    gbc.gridx = gridx;
                }
                gbc.gridy = gridy;
            }
        }
    }
    
    private void addRows() {
        gbc = new MyGridBagConstraints(gridx, gridy, 1, 1);
        for (; gridy > 0; gridy--) {
            final Row row;
            switch (gridy) {
                case 1: row = Row.FIRST;
                    break;
                case 2: row = Row.SECOND;
                    break;
                default: row = Row.THIRD;
            }
            button = new RouletteBetButton(this.getPreferredSize(), row);
            button.setPreferredSize(new Dimension(width / 14, height / 6));
            buttons.add(button);
            this.add(button, gbc);
            gbc.gridy--;
        }
    }
    
    private void addColumns() {
        gridx -= 12;
        gridy += 4;
        gbc = new MyGridBagConstraints(gridx, gridy, 4, 1);
        dim = new Dimension(width / 14 * 4, height / 6);
        button = new RouletteBetButton(this.getPreferredSize(), Column.FIRST);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 4;
        button = new RouletteBetButton(this.getPreferredSize(), Column.SECOND);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 4;
        button = new RouletteBetButton(this.getPreferredSize(), Column.THIRD);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        
    }

    private void addNumbersIncluded() {
        gridy += 1;
        gbc = new MyGridBagConstraints(gridx, gridy, 2, 1);
        dim = new Dimension(width / 7, height / 6);
        button = new RouletteBetButton(this.getPreferredSize(), Included._1_18_);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 10;
        button = new RouletteBetButton(this.getPreferredSize(), Included._19_36_);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }
    
    private void addParity() {
        gridx += 2;
        gbc.gridx = gridx;
        button = new RouletteBetButton(this.getPreferredSize(), Parity.EVEN);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 6;
        button = new RouletteBetButton(this.getPreferredSize(), Parity.ODD);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }
    
    private void addColors() {
        gridx += 2;
        gbc.gridx = gridx;
        button = new RouletteBetButton(this.getPreferredSize(), Color.RED);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 2;
        final RouletteBetButton ba = new RouletteBetButton(this.getPreferredSize(), Color.BLACK);
        ba.setPreferredSize(dim);
        buttons.add(ba);
        this.add(ba, gbc);
    }
    
    private void addActionListener() {
        for (final var button : buttons) {
            button.addActionListener(e -> {
                final double fichesValue = this.generalInterface.getFichesValue();
                if (this.generalInterface.addBetValue(fichesValue)) {
                    button.incrementBet(fichesValue);
                    this.generalInterface.updateBalanceValue();
                }
            });
        }
    }
    
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }

}