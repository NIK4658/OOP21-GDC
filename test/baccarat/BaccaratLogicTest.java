package baccarat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.account.AccountManager;
import model.account.AdvancedAccountManagerImpl;
import model.account.AdvancedBalanceManagerImpl;
import model.account.BalanceManager;

public class BaccaratLogicTest {
	 private static final String DIRECTORYPATH = "res/json/users/";
	    private AccountManager account;
	    private BalanceManager balanceaccount;
	    private BaccaratLogic game;
	    private File file;
	    
	    @Before
	    public void initDeck() {
	        this.account = new AdvancedAccountManagerImpl();
	        this.account.register("Test", "Password", "20");
	        this.account.logger("Test", "Password");
	        this.balanceaccount = new AdvancedBalanceManagerImpl(account);
	        this.game = new BaccaratLogicImpl(balanceaccount);
	        this.file = new File(DIRECTORYPATH + "Test" + ".Json");
	    }

	    @Test
	    public void testStartGame() {
	        this.game.startGame(5);
	        assertEquals(this.game.getDealerHand().size(), 2);
	        assertEquals(this.game.getPlayerHand().size(), 2);
	        assertTrue(this.game.getPlayerPoints() >= 0 && this.game.getPlayerPoints() <= 9);
	        assertTrue(this.game.getDealerPoints() >= 0 && this.game.getDealerPoints() <= 9);
	        assertEquals(this.game.getBet(), 5, 0);
	    }
	    
	    @Test
	    public void testNextMove() {
	        this.game.startGame(5);
	        this.game.nextMove();
	        assertTrue(this.game.getPlayerHand().size() >= 2);
	        assertTrue(this.game.getDealerHand().size() >= 2);
	        
	    }
	    
	    @Test
	    public void testPlayerDraw() {
	        this.game.startGame(5);
	        this.game.playerDraw();
	        assertEquals(this.game.getPlayerHand().size(), 3);
	        assertFalse(this.game.getPlayerHand().size() > 3);
	    }
	    
	    @Test
	    public void testDealerDraw() {
	    	this.game.startGame(5);
	        this.game.dealerDraw();
	        assertEquals(this.game.getDealerHand().size(), 3);
	        assertFalse(this.game.getDealerHand().size() > 3);
	    }
	    
	    @Test
	    public void testCheckBaccarat() {
	    	this.game.startGame(5);
	        if(this.game.getPlayerPoints() == 8 || this.game.getPlayerPoints() == 9) {
	        	assertTrue(this.game.checkBaccarat(this.game.getPlayerHand()));
	        }
	        if(this.game.getDealerPoints() == 8 || this.game.getDealerPoints() == 9) {
	        	assertTrue(this.game.checkBaccarat(this.game.getDealerHand()));
	        }
	        
	    }
	    
	    @After
	    public void deleteFiles() {
	        if (this.file.exists() && !this.file.isDirectory()) {
	            this.file.delete();
	        }
	    } 
	    
	}


