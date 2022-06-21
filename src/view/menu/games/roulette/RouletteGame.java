package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import ex.ExTable;
import roulette.AmericanRoulette;
import roulette.BaseRoulette;
import roulette.EuropeanRoulette;
import roulette.Roulette;
import roulette.manageRoulette.ManageRoulette;
import roulette.number.EuropeanRouletteNumber;
import roulette.number.RouletteNumber;
import roulette.number.BaseRouletteNumber;
import roulette.numbers.EuropeanRouletteNumbers;
import utility.Pair;
import view.menu.GeneralGui;
import view.menu.games.Game;
import view.menu.games.roulette.RouletteGame.TypeRoulette;


//RouletteGame
public class RouletteGame extends JPanel implements Game {
    
    public enum TypeRoulette{
        EUROPEAN_ROULETTE, AMERICAN_ROULETTE, BASE_ROULETTE
    }
  
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final ManageRoulette win;
    private final GeneralGui generalInterface;
    
    public RouletteGame(final GeneralGui generalInterface, final Games game) {
        this.setLayout(new BorderLayout());
        Dimension dimension = generalInterface.getMenu().getPreferredSize();
        this.generalInterface = generalInterface;
        this.win = new ManageRoulette();
        
        switch (game) {
            case ROULETTE_BASE: 
                this.roulette = new BaseRoulette();//sistemare
                break;
            case ROULETTE_EUROPEAN: 
                this.roulette = new EuropeanRoulette();
                break;
            case ROULETTE_AMERICAN: 
                this.roulette = new AmericanRoulette();// vedere come metterli in comune
                break;
            default://lanciare un'eccezione?
                this.roulette = null;
        }
        
        this.table = new Table(generalInterface, game);
        this.winningNumbers = new DisplayWinningNumbers(new Dimension(dimension.width, dimension.height / 10));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.add(table);
    }

    @Override
    public void confirmBet() {
        final RouletteNumber rouletteNumber = roulette.spin();
        final List<Pair<Object, Double>> bets = table.endBetting();
        winningNumbers.update(rouletteNumber);
        generalInterface.setWinMessage(this.win.calculateWin(bets, rouletteNumber));
    }

    @Override
    public JPanel getGame() {
        return this;
    }

    @Override
    public void resetBet() {
        // TODO Auto-generated method stub
        
    }

}
