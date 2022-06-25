package controller;

import controller.blackjack.BlackJackLogicImpl;
import view.menu.GeneralGui;
import view.menu.games.blackjack.BlackJackGui;

public class BlackJackControllerImpl2 extends GeneralGameController {

    private final BlackJackGui gameGui;
    private final BlackJackLogicImpl gameLogic;
    
    public BlackJackControllerImpl2(BlackJackGui blackJackGui, final GeneralGui g) {
        super(g);
        this.gameGui = blackJackGui;
    }
    
    public void setBet(final double bet) {
        this.bet = bet;
        this.gameLogic.setBet(bet);
    }
    
    public void setFichesValue(final double fichesvalue) {
        this.gameLogic.setFichesValue(fichesvalue);
    }
    
    public void resetBet() {
        
        this.gameGui.resetBet();
    }
    
    public void confirmBet(){
        this.gameLogic.confirmBet();
    }

    
    public void draw() {
        this.gameLogic.askCard();
        this.gameGui.draw();
        this.gameGui.setDealerPoints(this.gameLogic.getDealerPoints());
        if(this.gameLogic.getPlayerPoints() >= 21) {
            this.gameGui.stand();
        }
    }
    
    public void stand() {
        if (this.gameLogic.checkWin() != -1 
                || this.gameLogic.checkBlackjack(this.gameLogic.getPlayerHand())) {
            showWinMessage(this.gameLogic.getLastWin());
            setBet(this.bet);
        }
        this.gameGui.stand();
    }
    
    public void doubleDown() {
        if (this.gameLogic.askDouble()) {
            updateBalanceValue();
            setBetValue(this.gameLogic.getBet());
            this.gameGui.stand();
        }
    }
    
    public void bet() {
        if(addBetValue(getFichesValue())) {
            this.gameGui.bet();
        }
    }
    
    public void restart() {
        this.gui.setWinMessage(0);
        this.gui.setBetValue(0);
        this.gameGui.restart();
    }
    
    
    
}
