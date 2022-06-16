package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import roulette.Roulette;
import roulette.RouletteNumber;
import roulette.RouletteNumbers;
import roulette.manageRoulette.ManageRoulette;
import utility.Pair;
import view.menu.GeneralGui2;
import view.menu.games.Game;


//RouletteGame
public class RouletteGame extends JPanel implements Game {
    
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final ManageRoulette win;
    private final GeneralGui2 generalInterface;
    
    public RouletteGame(final Dimension dimension, final GeneralGui2 generalInterface) {
        this.setLayout(new BorderLayout());
        
        this.generalInterface = generalInterface;
        this.roulette = new Roulette();
        this.winningNumbers = new DisplayWinningNumbers(new Dimension(dimension.width, dimension.height / 10));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.table = new Table(generalInterface);
        this.add(table);
        this.win = new ManageRoulette();
    }

    @Override
    public void endBetting() {
        final RouletteNumber rouletteNumber= roulette.spin();
        final List<Pair<Object, Double>> bets = table.endBetting();
        final double win = this.win.calculateWin(bets, rouletteNumber);
        winningNumbers.update(rouletteNumber);
        generalInterface.setWinMessage(win);
        
    }

    @Override
    public JPanel getGame() {
        return this;
    }

}
