package view.menu.games.roulette;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import roulette.RouletteNumbers;
import view.MyGridBagConstraints;

public class Table extends JPanel {
    
    private final Map<Integer, Color> rouletteNumbers;
    private final Image img;
//    private final int rightAlignment;
//    private final int adjustWidthButton;
    private final int adjustHeightButton;
    private int x;
    private int y;
    private GridBagConstraints gbc;
    
    public Table() {
        final ImageIcon imgIcon = new ImageIcon("res/img/backgrounds/Test3.png");
        this.img = imgIcon.getImage();
        this.setPreferredSize(new Dimension(imgIcon.getIconWidth(), imgIcon.getIconHeight()));
        System.out.println(new Dimension(imgIcon.getIconWidth(), imgIcon.getIconHeight()));
        this.setLayout(new GridBagLayout());
//        this.adjustWidthButton = this.getPreferredSize().width / 33;
//        this.rightAlignment = this.getPreferredSize().width / 150;
        this.adjustHeightButton = this.getPreferredSize().height / -2;
        this.rouletteNumbers = new RouletteNumbers(37, 1);//sostituire numeri in modo da avere più tipi di roulette
        
        JButton b;
        final var d = new Dimension(imgIcon.getIconWidth() / 14, (imgIcon.getIconHeight() - 61)/ 3);
        
        final var d2 = new Dimension((imgIcon.getIconWidth() - 122) / 3, 61);
        b = new JButton("1");
        b.setPreferredSize(d2);
        this.add(b, new MyGridBagConstraints(1, 0, 3, 1));
        b = new JButton("2");
        b.setPreferredSize(d2);
        this.add(b, new MyGridBagConstraints(4, 0, 3, 1));
        b = new JButton("3");
        b.setPreferredSize(d2);
        this.add(b, new MyGridBagConstraints(7, 0, 3, 1));
        b = new JButton("4");
        b.setPreferredSize(d2);
        this.add(b, new MyGridBagConstraints(10, 0, 3, 1));
        
        
        b = new JButton("0");
        b.setPreferredSize(new Dimension(imgIcon.getIconWidth() / 14, imgIcon.getIconHeight()));
        this.add(b, new MyGridBagConstraints(0, 1, 1, 3));
        for (int i = 1; i < 14; i++) {
            b = new JButton(String.valueOf(i));
            b.setPreferredSize(d);
            this.add(b, new MyGridBagConstraints(i, 1, 1, 1));
        }
        for (int i = 1; i < 14; i++) {
            b = new JButton(String.valueOf(i));
            b.setPreferredSize(d);
            this.add(b, new MyGridBagConstraints(i, 2, 1, 1));
        }
        for (int i = 1; i < 14; i++) {
            b = new JButton(String.valueOf(i));
            b.setPreferredSize(d);
            this.add(b, new MyGridBagConstraints(i, 3, 1, 1));
        }
        
        
//        this.addSectors();
//        this.addNumbers();
//        this.addRows();
//        this.addColumns();
//        this.addNumbersIncluded();
//        this.addEvenOdd();
//        this.addRedBlack();
    }
    
    private void addSectors() {
        x = 1;
        y = 0;
        gbc = new MyGridBagConstraints(x, y, 3, 1, 0, this.adjustHeightButton);
        this.add(new JButton("TIER"), gbc);
        gbc.gridx += 3;
        this.add(new JButton("ORPHELINS"), gbc);
        gbc.gridx += 3;
        this.add(new JButton("VOISINS"), gbc);
        gbc.gridx += 3;
        this.add(new JButton("ZERO"), gbc);
    }

    //fare in modo da avere più tipi di roulette
    private void addNumbers() {
        y += 3;
        gbc = new MyGridBagConstraints(x, y);
        for (final Integer n : rouletteNumbers.keySet()) {
            final int value = n;
            final JButton button = new JButton(n.toString());
            button.setForeground(rouletteNumbers.get(n));
            if (value == 0) {
//                this.add(button, new MyGridBagConstraints(0, 1, 1, 3, this.adjustWidthButton, 0));
                this.add(button, new MyGridBagConstraints(0,1,1,3));
            } else {
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
//        gbc = new MyGridBagConstraints(x, y, 1, 1, new Insets(0, 0, 0, this.rightAlignment));
        gbc = new MyGridBagConstraints(x, y);
        for (; y > 0; y--) {
            this.add(new JButton("2to1"), gbc);
            gbc.gridy--;
        }
    }
    
    private void addColumns() {
        x -= 12;
        y += 4;
        gbc = new MyGridBagConstraints(x, y, 4, 1, 0, this.adjustHeightButton);
        this.add(new JButton("1st 12"), gbc);
        gbc.gridx += 4;
        this.add(new JButton("2nd 12"), gbc);
        gbc.gridx += 4;
        this.add(new JButton("3rd 12"), gbc);
        
    }

    private void addNumbersIncluded() {
        y += 1;
        gbc = new MyGridBagConstraints(x, y, 2, 1, 0, this.adjustHeightButton);
        this.add(new JButton("1-18"), gbc);
        gbc.gridx += 10;
        this.add(new JButton("19-36"), gbc);
    }
    
    private void addEvenOdd() {
        x += 2;
        gbc.gridx = x;
        this.add(new JButton("EVEN"), gbc);
        gbc.gridx += 6;
        this.add(new JButton("ODD"), gbc);
    }
    
    private void addRedBlack() {
        x += 2;
        gbc.gridx = x;
        this.add(new JButton("RED"), gbc);
        gbc.gridx += 2;
        this.add(new JButton("BLACK"), gbc);
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
