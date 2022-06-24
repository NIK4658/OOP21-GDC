package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.blackjack.BlackJackLogic;
import controller.blackjack.BlackJackLogicImpl;
import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
import model.account.BalanceManager;


public class BlackjackLogicTest {

    private static final String DIRECTORYPATH = "res/json/users/";
    private AccountManager account;
    private BalanceManager balanceaccount;
    private BlackJackLogic game;
    private File file;
    
    @Before
    public void initDeck() {
        this.account = new AdvancedAccountManagerImpl();
        this.account.register("Test", "Password", "20");
        this.account.logger("Test", "Password");
        this.balanceaccount = new AdvancedBalanceManagerImpl(account);
        this.game = new BlackJackLogicImpl(balanceaccount);
        this.file = new File(DIRECTORYPATH + "Test" + ".Json");
    }

    @Test
    public void testStartGame() {
        this.game.startGame(5);
        assertEquals(this.game.getDealerHand().size(), 2);
        assertEquals(this.game.getPlayerHand().size(), 2);
        assertTrue(this.game.getPlayerPoints() <= 21 && this.game.getPlayerPoints() >= 4);
        assertTrue(this.game.getDealerPoints() <= 21 && this.game.getDealerPoints() >= 4);
        assertEquals(this.game.getBet(), 5, 0);
    }
    
    @Test
    public void testAskCard() {
        this.game.startGame(5);
        this.game.askCard();
        assertEquals(this.game.getPlayerHand().size(), 3);
    }
    
    @Test
    public void testAskDouble() {
        this.game.startGame(5);
        assertFalse(this.game.askDouble());
        this.balanceaccount.deposit(5);
        assertTrue(this.game.askDouble());
        assertEquals(this.game.getBet(), 10, 0);
    }
    
    @Test
    public void testCheckWin() {
        //davvero necessario?
    }
    
    @Test
    public void testDealerDraw() {
        this.game.startGame(5);
        this.game.dealerDraw();
        assertEquals(this.game.getDealerHand().size(), 3);
    }
    
    @Test
    public void testNextDealerMove() {
        this.game.startGame(5);
        assertTrue(this.game.getDealerHand().getCard(1).isFaceDown());
        this.game.nextDealerMove();
        assertFalse(this.game.getDealerHand().getCard(1).isFaceDown());
        assertTrue(this.game.getDealerPoints() >= 17);
    }
    
    @Test
    public void testInsurance() {
        //da fare?
    }
    
    @Test
    public void testCheckBlackJack() {
        //da fare?
    }
    
    @After
    public void deleteFiles() {
        if (this.file.exists() && !this.file.isDirectory()) {
            this.file.delete();
        }
    } 
    
}
