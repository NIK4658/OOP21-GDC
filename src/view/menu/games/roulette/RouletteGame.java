package view.menu.games.roulette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

import model.roulette.AmericanRoulette;
import model.roulette.BaseRoulette;
import model.roulette.EuropeanRoulette;
import model.roulette.Roulette;
import model.roulette.checkwin.Wins;
import model.roulette.number.RouletteNumber;
import utility.Pair;
import view.menu.GeneralGui;
import view.menu.games.Game;
import model.roulette.RouletteFactory;
import model.roulette.RouletteFactoryImpl;


public class RouletteGame extends JPanel implements Game {
  
    public static final Color BACKGROUND_COLOR = new Color(0, 118, 58);
    private static final int SCALE_HEIGHT_WINNINGNUMBERS = 10;
    private final GeneralGui generalInterface;
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final Wins win;
    
    public RouletteGame(final GeneralGui generalInterface, final Games game) {//dire nella JavaDoc che può mandare eccezione
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.generalInterface = generalInterface;
        this.win = new Wins();
        final RouletteFactory rouletteFactory = new RouletteFactoryImpl();
        
        switch (game) {
            case ROULETTE_BASE: 
                this.roulette = rouletteFactory.createBaseRoulette();//new BaseRoulette();
                break;
            case ROULETTE_EUROPEAN: 
                this.roulette = rouletteFactory.createEuropeanRoulette();//new EuropeanRoulette();
                break;
            case ROULETTE_AMERICAN: 
                this.roulette = rouletteFactory.createAmericanRoulette();//new AmericanRoulette();
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        final Dimension dimension = generalInterface.getMenu().getPreferredSize();
        this.winningNumbers = new DisplayWinningNumbers(new Dimension(dimension.width, 
                dimension.height / SCALE_HEIGHT_WINNINGNUMBERS));
        this.table = new Table(generalInterface, game);
        this.add(this.winningNumbers, BorderLayout.NORTH);
        this.add(table);
    }

    @Override
    public void confirmBet() {
        final RouletteNumber rouletteNumber = roulette.spin();
        final List<Pair<Object, Double>> bets = table.confirmBet();
        winningNumbers.update(rouletteNumber);
        generalInterface.showWinMessage(this.win.win(bets, rouletteNumber));
    }

    @Override
    public JPanel getGame() {
        return this;
    }

    @Override
    public void resetBet() {
        table.resetBet();
    }

}
