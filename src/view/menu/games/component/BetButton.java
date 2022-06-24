package view.menu.games.component;


/**
 * Interface of a component used to define the amount of money wagered by the user.
 */
public interface BetButton {

    
    /**
     * Get the value of the bet. 
     * 
     * @return the amount of money wagered by the user.
     */
    double getBet();
    
    /**
     * Assign a certain bet value to the component.
     * 
     * @param value Value to set.
     */
    void setBet(double value);
    
    /**
     * Reset the bet values.
     */
    void resetBet();
    
    /**
     * It increases the values ​​of the bet and generates an image relating to the amount of the bet.
     * 
     * @param value Increment value.
     */
    void incrementBet(double value);
    
    /**
     * Confirm the user's bet. He will no longer be able to increase this bet until the start of the next game.
     */
    void confirmBet();
    
    
    
}
