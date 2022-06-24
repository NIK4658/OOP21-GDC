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

import model.roulette.EuropeanRoulette;
import model.roulette.number.BaseRouletteNumber;
import model.roulette.number.EuropeanRouletteNumber;
import model.roulette.number.RouletteNumber;
import view.MyGridBagConstraints;

public class DisplayWinningNumbers extends JPanel {
    
    public static final int MAX_NUM_DISPLAYED = 12;
    private final List<JButton> winningNumbers;
    
    public DisplayWinningNumbers(final Dimension dimension) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 118, 58));//creare una costante in comune del colore, è possibile settare il pannello come opaque false così che prende il colore del pannello sotto
        this.setPreferredSize(dimension);
        final int height = dimension.height / 8;
        final int width = dimension.width / 200;
//      dimension.width /= (MAX_NUM_DISPLAYED * 2);
        
        this.winningNumbers = new LinkedList<>();
        
        for (int i = 0; i < MAX_NUM_DISPLAYED; i++) {
            final JButton b = new JButton();
//            b.setPreferredSize(dimension);
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setForeground(Color.WHITE);
            b.setBackground(this.getBackground());
            this.winningNumbers.add(b);
            this.add(b, new MyGridBagConstraints(i, 0, 1, 1, 
                    new Insets(height, width, height, width)));//da controllare
        }
    }
    
    public void update(final RouletteNumber rouletteNumber) {
        
        for (int i = MAX_NUM_DISPLAYED - 2; i >= 0; i--) {
            winningNumbers.get(i + 1).setText(winningNumbers.get(i).getText());
            winningNumbers.get(i + 1).setBackground(winningNumbers.get(i).getBackground());
            
        }
        winningNumbers.get(0).setText(rouletteNumber.getNumber());
        winningNumbers.get(0).setBackground(rouletteNumber.getColor());
    }

}
