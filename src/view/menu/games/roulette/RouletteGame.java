package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import roulette.Roulette;


//RouletteGame
public class RouletteGame extends JPanel implements Game {
    
    final DisplayWinningNumbers winningNumbers;
    
    public RouletteGame(final Dimension dimension) {
        this.setLayout(new BorderLayout());
//        System.out.println(dimension);
        Roulette roulette = new Roulette();
        this.winningNumbers = new DisplayWinningNumbers(roulette, new Dimension(dimension.width, dimension.height/8));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.add(new Table());
        
    }

    @Override
    public void endBetting() {
        winningNumbers.display();
    }

    @Override
    public JPanel getGame() {
        return this;
    }

}
