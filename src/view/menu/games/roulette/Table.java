package view.menu.games.roulette;
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
import java.util.List;
import java.util.Map;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import roulette.RouletteNumber;
import roulette.RouletteNumbers;
import view.MyGridBagConstraints;

public class Table extends JPanel {
    
    private final List<RouletteNumber> rouletteNumbers;
    private final Image img;
    private int x;
    private int y;
    private GridBagConstraints gbc;
    private JButton b;
    private Dimension d;
    private final int width;
    private final int height;
    
    public Table() {
        final ImageIcon imgIcon = new ImageIcon("res/img/backgrounds/RouletteTable.png");
        this.img = imgIcon.getImage();
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        this.setLayout(new GridBagLayout());
        
        this.rouletteNumbers = new RouletteNumbers().getList();
        this.addSectors();
        this.addNumbers();
        this.addRows();
        this.addColumns();
        this.addNumbersIncluded();
        this.addParity();
        this.addColors();
    }
    
    private void addSectors() {
        x = 1;
        y = 0;
        d = new Dimension(this.width / 14 * 3, this.height / 6);
        gbc = new MyGridBagConstraints(x, y, 3, 1);
        b = new JButton("TIER");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 3;
        b = new JButton("ORPHELINS");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 3;
        b = new JButton("VOISINS");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 3;
        b = new JButton("ZERO");
        b.setPreferredSize(d);
        this.add(b, gbc);
    }

    //fare in modo da avere più tipi di roulette
    private void addNumbers() {
        y += 3;
        gbc = new MyGridBagConstraints(x, y);
        for (final RouletteNumber n : rouletteNumbers) {
            final Integer value = n.getValue();
            final JButton button = new JButton(value.toString());
            button.setForeground(n.getColor());
            if (value == 0) {
                button.setPreferredSize(new Dimension(width / 14, height / 2));
                this.add(button, new MyGridBagConstraints(0, 1, 1, 3));
            } else {
                button.setPreferredSize(new Dimension(width / 14, height / 6));
                this.add(button, gbc);
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
            b = new JButton("2to1");
            b.setPreferredSize(new Dimension(width / 14, height / 6));
            this.add(b, gbc);
            gbc.gridy--;
        }
    }
    
    private void addColumns() {
        x -= 12;
        y += 4;
        gbc = new MyGridBagConstraints(x, y, 4, 1);
        d = new Dimension(width / 14 * 4, height / 6);
        b = new JButton("1st 12");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 4;
        b = new JButton("2nd 12");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 4;
        b = new JButton("3rd 12");
        b.setPreferredSize(d);
        this.add(b, gbc);
        
    }

    private void addNumbersIncluded() {
        y += 1;
        gbc = new MyGridBagConstraints(x, y, 2, 1);
        d = new Dimension(width / 7, height / 6);
        b = new JButton("1-18");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 10;
        b = new JButton("19-36");
        b.setPreferredSize(d);
        this.add(b, gbc);
    }
    
    private void addParity() {
        x += 2;
        gbc.gridx = x;
        b = new JButton("EVEN");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 6;
        b = new JButton("ODD");
        b.setPreferredSize(d);
        this.add(b, gbc);
    }
    
    private void addColors() {
        x += 2;
        gbc.gridx = x;
        b = new JButton("RED");
        b.setPreferredSize(d);
        this.add(b, gbc);
        gbc.gridx += 2;
        b = new JButton("BLACK");
        b.setPreferredSize(d);
        this.add(b, gbc);
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, getWidth(), getHeight(), null);
    }
    
}
//APPUNTI//
/*
 * 
//        button = new JButton();
//        button.setBackground(Color.BLACK);
//        button.setOpaque(true);
//        button.setBorderPainted(false);
*
*
*/
