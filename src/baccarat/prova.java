package baccarat;

public class prova {
	 Double.addActionListener(e -> {   
         if (bet.getBet() * 2 <= account.getBalance()) {
             bet.setBet(bet.getBet() * 2);
             //gameLogic.askCard();
             setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
             playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
             stand.doClick();
         }
     });
	 
	 
	    
     /* draw.addActionListener(e -> {   
          this.gameLogic.askCard();
          setCards(playerCards, gameLogic.getPlayerHand(), center, DIRECTION_PLAYER);
          playerPoints.setText(String.valueOf(gameLogic.getPlayerPoints()));
          Double.setVisible(false);
          if (gameLogic.getPlayerPoints() >= 21) {
              stand.doClick();
          }
      }); */
}
