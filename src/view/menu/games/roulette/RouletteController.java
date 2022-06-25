//package view.menu.games.roulette;
//
//import java.util.List;
//
//import javax.swing.JPanel;
//
//import model.roulette.Roulette;
//import model.roulette.RouletteFactory;
//import model.roulette.RouletteFactoryImpl;
//import model.roulette.number.RouletteNumber;
//import model.roulette.win.Wins;
//import utility.Pair;
//import view.menu.GeneralGui;
//import view.menu.games.Game;
//import view.menu.games.Game.Games;
//
//public class RouletteController implements Game {
//    
//    private final Roulette roulette;
//    private final Wins win;
//    private final GeneralGui generalInterface;
//    private final Game rouletteGame;
//    
//    public RouletteController(final GeneralGui generalInterface, final Games game) {//dire nella JavaDoc che può mandare eccezione
//        final RouletteFactory rouletteFactory = new RouletteFactoryImpl();
//        this.generalInterface = generalInterface;
//        this.win = new Wins();
//        this.rouletteGame = new RouletteGame(this.generalInterface, game);
//                
//        switch (game) {
//            case ROULETTE_BASE: 
//                this.roulette = rouletteFactory.createBaseRoulette();
//                break;
//            case ROULETTE_EUROPEAN: 
//                this.roulette = rouletteFactory.createEuropeanRoulette();
//                break;
//            case ROULETTE_AMERICAN: 
//                this.roulette = rouletteFactory.createAmericanRoulette();
//                break;
//            default:
//                throw new IllegalArgumentException();
//        }
//        
//    }
//    
//    @Override
//    public void confirmBet() {
//        final RouletteNumber rouletteNumber = roulette.spin();
//        final List<Pair<Object, Double>> bets = table.confirmBet();
//        winningNumbers.update(rouletteNumber);
//        generalInterface.showWinMessage(this.win.win(bets, rouletteNumber));
//    }
//
//    @Override
//    public void resetBet() {
//        table.resetBet();
//    }
//
//    @Override//DA TOGLIERE DALL'INTERFACCIA
//    public JPanel getGame() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//}
