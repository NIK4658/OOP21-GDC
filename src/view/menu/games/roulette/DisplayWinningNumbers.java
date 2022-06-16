package view.menu.games.roulette;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import roulette.Roulette;
import roulette.RouletteNumber;
import view.MyGridBagConstraints;

public class DisplayWinningNumbers extends JPanel {
    
    public static final int MAX_NUM_DISPLAYED = 12;
    private final List<JButton> winningNumbers;
    
    public DisplayWinningNumbers(final Dimension dimension) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 118, 58));
        this.setPreferredSize(dimension);
        int height = dimension.height / 8;
        int width = dimension.width / 200;
        
        
        this.winningNumbers = new LinkedList<>();
//        dimension.width /= (MAX_NUM_DISPLAYED * 2);
        for (int i = 0; i < MAX_NUM_DISPLAYED; i++) {
            final JButton b = new JButton();
//            b.setPreferredSize(dimension);
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setForeground(Color.WHITE);
            b.setBackground(getBackground());
            this.winningNumbers.add(b);
            this.add(b, new MyGridBagConstraints(i, 0, 1, 1, 
                    new Insets(height, width, height, width)));//da controllare
        }
    }
    
    public void update(RouletteNumber rouletteNumber) {
        
        for (int i = MAX_NUM_DISPLAYED - 2; i >= 0; i--) {
            winningNumbers.get(i + 1).setText(winningNumbers.get(i).getText());
            winningNumbers.get(i + 1).setBackground(winningNumbers.get(i).getBackground());
            
        }
        winningNumbers.get(0).setText(rouletteNumber.getValue().toString());
        winningNumbers.get(0).setBackground(rouletteNumber.getColor());
    }
    
    
//    public void display() {
//        int i = 0;
//        for (final RouletteNumber n : this.roulette.spin()) {
//            final JButton b = this.winningNumbers.get(i);
//            b.setText(String.valueOf(n.getValue()));
//            b.setForeground(n.getColor());
//            i++;
//        }
        
//    }

}
