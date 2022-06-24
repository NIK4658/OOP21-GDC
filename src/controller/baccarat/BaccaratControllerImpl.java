package controller.baccarat;

import controller.BalanceController;
import model.blackjack.Hand;

public class BaccaratControllerImpl implements BaccaratController {
	
    private BaccaratLogic game;
    
    public BaccaratControllerImpl(final BalanceController BController) {
        this.game = new BaccaratLogicImpl(BController);
    }

	@Override
	public void startGame(final double bet) {
		this.game.startGame(bet);

	}

	@Override
	public void NextMove() {
		this.game.nextMove();

	}

	@Override
	public int checkWin() {
		return this.game.checkWin();
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
	public boolean checkBaccarat(final Hand h) {
		return this.game.checkBaccarat(h);
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
