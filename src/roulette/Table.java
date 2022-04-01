package roulette;


import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Table extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6291936551503579076L;
//    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    private final int dimX = (int) screenSize.getWidth() / 2;
//    private final int dimY = (int) screenSize.getHeight() / 2;
    private final Random random = new Random();
    private final JPanel table;
    private final List<RouletteNumber> rouletteNumbers;
    
    public Table() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.table = new JPanel(new GridBagLayout());
        this.rouletteNumbers = new RouletteNumbers().getNumbers();
        
        this.addNumbers();
        this.addRows();
        this.addColumns();
        this.addNumbersIncluded();
        this.addEvenOdd();
        this.addRedBlack();
        
        
        this.add(table);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void addNumbers() {
        int x = 1;
        int y = 2;
        for (final RouletteNumber n : rouletteNumbers) {
            final int value = n.getNumber();
            final JButton button = new JButton(Integer.toString(value));
            button.setForeground(n.getColor());
            if (value == 0) {
                button.setPreferredSize(new Dimension(40, 40));
                this.addComponent(table, button, 0, 0, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            }
            else {
                this.addComponent(table, button, x, y, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
                y--;
                if (y == -1) {
                    y = 2;
                    x++;
                }
            }
        }
        
    }
    
    private void addRows() {
        for (int y = 2; y > -1; y--) {
            addComponent(table, new JButton("2to1"), 13, y, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(table, new JButton("19-36"), 11, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        }
    }
    
    private void addColumns() {
        addComponent(table, new JButton("1st 12"), 1, 3, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(table, new JButton("2nd 12"), 5, 3, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(table, new JButton("3rd 12"), 9, 3, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void addNumbersIncluded() {
        addComponent(table, new JButton("1-18"), 1, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void addEvenOdd() {
        addComponent(table, new JButton("PARI"), 3, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(table, new JButton("DISPARI"), 9, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void addRedBlack() {
        JButton button = new JButton();
        button.setBackground(Color.RED);
        button.setOpaque(true);
        button.setBorderPainted(false);
        addComponent(table, button, 5, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        button = new JButton();
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        addComponent(table, button, 7, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    private void addComponent(final Container container, final Component component, final int gridx, final int gridy,
            final int gridwidth, final int gridheight, final int anchor, final int fill) {
        final GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets(0, 0, 0, 0), 0, 0);
        container.add(component, gbc);
    }
    
    public int numberPlayed() {
        return random.nextInt(38);
    }
    

    public int numberCameOut() {
        return random.nextInt(37);
    }

}
