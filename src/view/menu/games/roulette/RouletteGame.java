package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import roulette.Roulette;
import roulette.RouletteNumbers;
import roulette.manageRoulette.ManageRoulette;
import utility.Pair;
import view.menu.games.Game;


//RouletteGame
public class RouletteGame extends JPanel implements Game {
    
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final ManageRoulette win;
    
    public RouletteGame(final Dimension dimension) {
        this.setLayout(new BorderLayout());
        
        this.roulette = new Roulette();
        this.winningNumbers = new DisplayWinningNumbers(roulette, new Dimension(dimension.width, dimension.height/8));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.table = new Table();
        this.add(table);
        this.win = new ManageRoulette();
    }

    @Override
    public void endBetting() {
        final List<Pair<Object, Double>> bets = table.endBetting();
        final double win = this.win.calculateWin(bets, new RouletteNumbers().get(0));
        System.out.println("La vincita è di: " + win);
        winningNumbers.display();
    }

    @Override
    public JPanel getGame() {
        return this;
    }

}
