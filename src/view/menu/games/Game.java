package view.menu.games;

import javax.swing.JPanel;

public interface Game {
    
    enum Games{
        BLACKJACK, ROULETTE_BASE, ROULETTE_EUROPEAN, ROULETTE_AMERICAN, BACCARAT
    }
    
    void confirmBet();
    
    void resetBet();
    
    JPanel getGame();

}
