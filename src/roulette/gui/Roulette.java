package roulette.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import roulette.table.Table;

public class Roulette extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6291936551503579076L;
//    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    private final int dimX = (int) screenSize.getWidth() / 2;
//    private final int dimY = (int) screenSize.getHeight() / 2;
    private final Random random = new Random();
    private final JPanel panel;
    private final Map<Integer, Color> table;
    
    public Roulette() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        panel = new JPanel(new GridBagLayout());
        table = new Table().getTable();
        JButton button;
        
        
        
        
        int x = 1;
        int y = 2;
        for (final var n : table.keySet()) {
            button = new JButton(n.toString());
            button.setForeground(table.get(n));
            if (n == 0) {
                button.setPreferredSize(new Dimension(40, 40));
                this.addComponent(panel, button, 0, 0, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            }
            else {
                this.addComponent(panel, button, x, y, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
                y--;
                if (y == -1) {
                    y = 2;
                    x++;
                }
            }
        }
        
        for (; y > -1; y--) {
            button = new JButton("2to1");
            addComponent(panel, button, x, y, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        }
        
        x = 1;
        for (int i = 1; i <= 3; i++) {
            button = new JButton(Integer.toString(i) + "st 12");
            addComponent(panel, button, x, 3, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            x += 4;
        }
        
        button = new JButton("1-18");
        addComponent(panel, button, 1, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("PARI");
        addComponent(panel, button, 3, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton();
        button.setBackground(Color.RED);
        button.setOpaque(true);
        button.setBorderPainted(false);
        addComponent(panel, button, 5, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton();
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        addComponent(panel, button, 7, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("DISPARI");
        addComponent(panel, button, 9, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        button = new JButton("19-36");
        addComponent(panel, button, 11, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        
        
        
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addNumbers() {
        
    }
    
    private void addComponent(final Container container, final Component component, final int gridx, final int gridy,
            final int gridwidth, final int gridheight, final int anchor, final int fill) {
          GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
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
