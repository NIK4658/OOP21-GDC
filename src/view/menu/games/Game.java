package view.menu.games;

import javax.swing.JPanel;

/**
 * Interface which define a game based on a panel.
 */
public interface Game {
    
    /**
     * Type of games.
     */
    enum Games {
        BLACKJACK, ROULETTE_BASE, ROULETTE_EUROPEAN, ROULETTE_AMERICAN, BACCARAT
    }
    
    /**
     * Confirm the bet and the game start.
     */
    void confirmBet();
    
    /**
     * Cancel any placed bets.
     */
    void resetBet();
    
    
    /**
     * Returns the panel of the game.
     * 
     * @return returns the panel of the game.
     */
    JPanel getGame();

}
