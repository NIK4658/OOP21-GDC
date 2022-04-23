package view.menu.games.roulette;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import roulette.Roulette;
import roulette.RouletteNumber;
import roulette.WinningNumbers;
import view.MyGridBagConstraints;

public class DisplayWinningNumbers extends JPanel {
    
    private final Roulette roulette;
    private final List<JButton> winningNumbers;
    
    public DisplayWinningNumbers(Roulette roulette) {
        this.setLayout(new GridBagLayout());
//        this.setBackground(new Color(0, 118, 58));    //VERDE COME IL TAVOLO DELLA ROULETTE
        this.winningNumbers = new LinkedList<>();
        this.roulette = roulette;
       
        for (int i = 0; i < WinningNumbers.MAX_NUM_DISPLAYED; i++) {
            final JButton b = new JButton();
            winningNumbers.add(b);
            this.add(b, new MyGridBagConstraints(i, 0));
        }
    }
    
    public void display() {
        int i = 0;
        for (final RouletteNumber n : this.roulette.spin()) {
            final JButton b = this.winningNumbers.get(i);
            b.setText(String.valueOf(n.getValue()));
            b.setForeground(n.getColor());
            i++;
        }
        
    }

}
