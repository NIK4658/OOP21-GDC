package view.menu.games.roulette;

import account.BalanceManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import model.roulette.AmericanRoulette;
import model.roulette.BaseRoulette;
import model.roulette.EuropeanRoulette;
import model.roulette.Roulette;
import model.roulette.manageRoulette.ManageRoulette;
import model.roulette.number.RouletteNumber;
import utility.Pair;
import view.menu.GeneralGui;
import view.menu.games.Game;


public class RouletteGame extends JPanel implements Game {
  
    private static final int SCALE_HEIGHT_WINNINGNUMBERS = 10;
    private final GeneralGui generalInterface;
    private final Roulette roulette;
    private final DisplayWinningNumbers winningNumbers;
    private final Table table;
    private final ManageRoulette win;
    
    public RouletteGame(final GeneralGui generalInterface, final Games game) {
        this.setLayout(new BorderLayout());
        final Dimension dimension = generalInterface.getMenu().getPreferredSize();
        this.generalInterface = generalInterface;
        this.win = new ManageRoulette();
        
        switch (game) {
            case ROULETTE_BASE: 
                this.roulette = new BaseRoulette();
                break;
            case ROULETTE_EUROPEAN: 
                this.roulette = new EuropeanRoulette();
                break;
            case ROULETTE_AMERICAN: 
                this.roulette = new AmericanRoulette();
                break;
            default://lanciare un'eccezione?
                this.roulette = null;
        }
        
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
        generalInterface.showWinMessage(this.win.calculateWin(bets, rouletteNumber));
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
