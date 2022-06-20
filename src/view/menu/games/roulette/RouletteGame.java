package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import ex.ExAmericanTable;
import ex.ExBaseTable;
import ex.ExEuropeanTable;
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
import view.menu.GeneralGui2;
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
    private final GeneralGui2 generalInterface;
    
    public RouletteGame(final Dimension dimension, final GeneralGui2 generalInterface, final TypeRoulette typeRoulette) {
        this.setLayout(new BorderLayout());
        
        this.generalInterface = generalInterface;
        this.win = new ManageRoulette();
        
        switch (typeRoulette) {
            case BASE_ROULETTE: 
                this.roulette = new BaseRoulette();//sistemare
                break;
            case EUROPEAN_ROULETTE: 
                this.roulette = new EuropeanRoulette();
                break;
            case AMERICAN_ROULETTE: 
                this.roulette = new AmericanRoulette();// vedere come metterli in comune
                break;
            default://lanciare un'eccezione?
                this.roulette = new EuropeanRoulette();
        }
        
        this.table = new Table(generalInterface, typeRoulette);
        this.winningNumbers = new DisplayWinningNumbers(new Dimension(dimension.width, dimension.height / 10));
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.add(table);

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
