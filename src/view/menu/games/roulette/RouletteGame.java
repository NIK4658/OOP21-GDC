package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import roulette.AmericanRoulette;
import roulette.EuropeanRoulette;
import roulette.Roulette;
import roulette.manageRoulette.ManageRoulette;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.RouletteNumber;
import roulette.number.BaseRouletteNumber;
import roulette.numbers.EuropeanRouletteNumbers;
import utility.Pair;
import view.menu.GeneralGui2;
import view.menu.games.Game;
import view.menu.games.roulette.table.AmericanTable;
import view.menu.games.roulette.table.EuropeanTable;
import view.menu.games.roulette.table.Table;


//RouletteGame
public class RouletteGame extends JPanel implements Game {
    
    public enum TypeRoulette{
        EUROPEAN_ROULETTE, AMERICAN_ROULETTE
    }
    
    private final TypeRoulette typeRoulette = TypeRoulette.AMERICAN_ROULETTE;
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final ManageRoulette win;
    private final GeneralGui2 generalInterface;
    
    public RouletteGame(final Dimension dimension, final GeneralGui2 generalInterface) {
        this.setLayout(new BorderLayout());
        
        this.generalInterface = generalInterface;
        this.win = new ManageRoulette();
        
        if (typeRoulette == TypeRoulette.EUROPEAN_ROULETTE) {
            this.roulette = new EuropeanRoulette();
            this.table = new EuropeanTable(generalInterface);
        } else {
            this.roulette = new AmericanRoulette();
            this.table = new AmericanTable(generalInterface);// vedere come metterli in comune
        }

        this.winningNumbers = new DisplayWinningNumbers(new Dimension(dimension.width, dimension.height / 10));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.add((Component) table);//da risolvere

    }

    @Override
    public void endBetting() {
        final RouletteNumber rouletteNumber = roulette.spin();
        final List<Pair<Object, Double>> bets = table.endBetting();
        winningNumbers.update(rouletteNumber);
        generalInterface.setWinMessage(this.win.calculateWin(bets, rouletteNumber));
    }

    @Override
    public JPanel getGame() {
        return this;
    }

}
