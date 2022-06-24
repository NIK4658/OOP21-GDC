package controller.blackjack;

import controller.BalanceController;
import model.blackjack.Hand;

public class BlackJackControllerImpl implements BlackJackController {

    private BlackJackLogic game;
    
    public BlackJackControllerImpl(final BalanceController BController) {
        this.game = new BlackJackLogicImpl(BController);
    }
    
    @Override
    public void startGame(final double bet) {
        this.game.startGame(bet);
    }

    @Override
    public void askCard() {
        this.game.askCard();
    }

    @Override
    public void stand() {
        this.game.stand();
    }

    @Override
    public boolean askDouble() {
        return this.game.askDouble();
    }

    @Override
    public int checkWin() {
        return this.game.checkWin();
    }

    @Override
    public boolean canInsurance() {
        return this.game.canInsurance();
    }
    
    @Override
    public boolean checkInsurance() {
        return this.game.checkInsurance();
    }

    @Override
    public boolean calculateInsurance(final boolean insurance) {
        return this.game.calculateInsurance(insurance);
    }

    @Override
    public Hand getPlayerHand() {
        return this.game.getPlayerHand();
    }

    @Override
    public Hand getDealerHand() {
        return this.game.getDealerHand();
    }

    @Override
    public double getLastWin() {
        return this.game.getLastWin();
    }

    @Override
    public boolean checkBlackjack(final Hand h) {
        return this.game.checkBlackjack(h);
    }

    @Override
    public int getPlayerPoints() {
        return this.game.getPlayerPoints();
    }

    @Override
    public int getDealerPoints() {
        return this.game.getDealerPoints();
    }

}
