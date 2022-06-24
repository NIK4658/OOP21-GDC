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
import model.roulette.property.Property.Column;
import model.roulette.property.Property.Included;
import model.roulette.property.Property.Parity;
import model.roulette.property.Property.Row;
import model.roulette.property.Property.Sector;
import model.roulette.wheel.BaseWheel;
import model.roulette.wheel.WheelFactoryImpl;
import model.roulette.number.AmericanRouletteNumber;
import model.roulette.number.RouletteNumber;
import utility.Pair;
import view.MyGridBagConstraints;
import view.Utilities;
import view.menu.GeneralGui;
import view.menu.games.Game.Games;

public class Table extends JPanel {
    
    private final GeneralGui generalInterface;
    private final Games game;
    private final Image img;
    private final List<RouletteBetButton> buttons;
    private final int width;
    private final int height;
    private final Dimension dimBut;
    private int gridx;
    private int gridy;
    private GridBagConstraints gbc;
    private RouletteBetButton button;
    private Dimension dim;
    
    
    public Table(final GeneralGui generalInterface, final Games game) {
        this.dimBut = generalInterface.getMenu().getPreferredSize();
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        this.setLayout(new GridBagLayout());
        this.generalInterface = generalInterface;
        this.game = game;
        this.buttons = new LinkedList<>();
        
        this.gridx = 1;
        this.gridy = 0;
        switch (game) {
            case ROULETTE_BASE: 
                this.img = Utilities.getImage("img/backgrounds/BaseRouletteTable.png");
                break;
            case ROULETTE_AMERICAN: 
                this.img = Utilities.getImage("img/backgrounds/AmericanRouletteTable.png");
                break;
            case ROULETTE_EUROPEAN: 
                this.img = Utilities.getImage("img/backgrounds/EuropeanRouletteTable.png");
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
        button = new RouletteBetButton(this.dimBut, Sector.TIER);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.dimBut, Sector.ORPHELINS);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.dimBut, Sector.VOISINS);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 3;
        button = new RouletteBetButton(this.dimBut, Sector.ZERO);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }

    private void addNumbers() {
        gridy += 3;
        gbc = new MyGridBagConstraints(gridx, gridy);
        
        final List<RouletteNumber> list;
        if (this.game == Games.ROULETTE_AMERICAN) {
            list = new WheelFactoryImpl().createAmericanWheel().getList();//
        } else {
            list = new BaseWheel().getList();//
        }
        for (final RouletteNumber n : list) {
            final Integer value = n.getValue();
            button = new RouletteBetButton(this.dimBut, value);
            button.setForeground(n.getColor());
            buttons.add(button);
            if (value == 0 && game == Games.ROULETTE_AMERICAN) {
                button.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(button, new MyGridBagConstraints(0, 1, 1, 1));
            } else if (value == AmericanRouletteNumber._00_) {
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
            button = new RouletteBetButton(this.dimBut, row);
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
        button = new RouletteBetButton(this.dimBut, Column.FIRST);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 4;
        button = new RouletteBetButton(this.dimBut, Column.SECOND);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 4;
        button = new RouletteBetButton(this.dimBut, Column.THIRD);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        
    }

    private void addNumbersIncluded() {
        gridy += 1;
        gbc = new MyGridBagConstraints(gridx, gridy, 2, 1);
        dim = new Dimension(width / 7, height / 6);
        button = new RouletteBetButton(this.dimBut, Included._1_18_);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 10;
        button = new RouletteBetButton(this.dimBut, Included._19_36_);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }
    
    private void addParity() {
        gridx += 2;
        gbc.gridx = gridx;
        button = new RouletteBetButton(this.dimBut, Parity.EVEN);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 6;
        button = new RouletteBetButton(this.dimBut, Parity.ODD);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
    }
    
    private void addColors() {
        gridx += 2;
        gbc.gridx = gridx;
        button = new RouletteBetButton(this.dimBut, Color.RED);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
        gbc.gridx += 2;
        button = new RouletteBetButton(this.dimBut, Color.BLACK);
        button.setPreferredSize(dim);
        buttons.add(button);
        this.add(button, gbc);
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