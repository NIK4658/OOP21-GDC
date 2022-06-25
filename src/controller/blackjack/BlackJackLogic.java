package controller.blackjack;

import model.blackjack.Hand;

/**
 * Interface that defines the main methods of the game "Blackjack".
 */
public interface BlackJackLogic {
    
    
    /**
     * Start the blackjack game. 
     * 
     * @param bet amount of money wagered.
     */
    void startGame(final double bet);
    
    /**
     * Manage player asking a card.
     */
    void askCard();
    
    /**
     * Manage Player asking for no more cards.
     */
    void stand();
    
    /**
     * Manage Player for asking doubledown. 
     * 
     * @return true if has enough money in the balance, else otherwise.
     */
    boolean askDouble();

    /**
     * Calculate the winner of the game. 
     * @return 1 if the winner is the player, -1 if the winner is the dealer, 0 if there is a tie. 
     */
    int checkWin();
    
    /**
     * Calculate the next move of the dealer in his turn. 
     */
    void nextDealerMove();
    
    /**
     * Manage the dealer drawing a card. 
     */
    void dealerDraw();
    
    /**
     * Check if the player has enough money to ask for insurance.
     * 
     * @return true if the player balance is enough, else otherwise. 
     */
    boolean canInsurance();
    
    /**
     * Check if the bet insurance should pop in. 
     * 
     * @return true if the dealer has an ace as the first card, else otherwise.
     */
    boolean checkInsurance();
    
    /**
     * Calculate the new balance of the player if he choosed to use insurance. 
     * 
     * @param insurance true if the player has choose insurance. 
     * @return true if the game can continue or false otherwise.
     */
    boolean calculateInsurance(final boolean insurance);
    
    
    /**
     * Check if a hand has BlackJack.
     * @param h hand to analyze. 
     * @return true if has blackjack, false otherwise.
     */
    boolean checkBlackjack(Hand h);
    
    /*
     * End the game, check if the deck should be shuffled and set the last win of the player. 
     */
    void endGame();
    
    /**
     * Getter of the hand of the player.
     * 
     * @return the hand of the player. 
     */
    Hand getPlayerHand();
    
    /**
     * Getter of the hand of the dealer.
     * 
     * @return the hand of the dealer. 
     */
    Hand getDealerHand();
    
    /**
     * Getter of the player points.
     * 
     * @return the player points. 
     */
    int getPlayerPoints();
    
    /**
     * Getter of the dealer points.
     * 
     * @return the dealer points. 
     */
    int getDealerPoints();
    
    /**
     * Getter of the player bet.
     * 
     * @return the player bet. 
     */
    double getBet();
    
    /**
     * Getter of the player last win.
     * 
     * @return the player last win. 
     */
    double getLastWin();
    
}
