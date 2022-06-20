package view.menu.games;

import javax.swing.JPanel;

public interface Game {
    
    enum Games{
        BLACKJACK, ROULETTE, BACCARAT
    }
    
    void confirmBet();
    
    void resetBet();
    
    JPanel getGame();

}
