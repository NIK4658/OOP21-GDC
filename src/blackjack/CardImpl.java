package blackjack;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import utility.Pair;

/**
 * Classe principale gestione Carte.
 */
public class CardImpl implements Card {

    
    private final Pair<Suits, Integer> card;
    private Image img;
    
    
    //Genera carta random
    public CardImpl() {
        this(getRandomSuit(), getRandomValue());
    }
    
    //Genera carta random con seme preciso
    public CardImpl(final Suits s) {
        this(s, getRandomValue());
    }
    
    //Genera carta random con valore preciso
    public CardImpl(final int value) {
        this(getRandomSuit(), value);
    }
    
    //Genera carta precisa
    public CardImpl(final Suits s, final int value) {
        this.card = new Pair<>(s, value);
        this.img = new ImageIcon("res/img/cards/" + this.card.get1() + "/" + this.card.get2() + ".png").getImage();
    }
    
    @Override
    public Suits getSuit() {
        return this.card.get1(); 
    }
    
    @Override
    public int getValue() {
        return this.card.get2();
    }
    
    @Override
    public Image getImg() {
        return this.img;
    }
    
    private static Suits getRandomSuit() {
        return Suits.values()[new Random().nextInt(Suits.values().length)];
    }
    
    private static int getRandomValue() {
        return new Random().nextInt(13) + 1;
    }
    
    /**
     * Testing. Da eliminare in seguito.
     */
    public static void main(final String[] args) {      
        //new DeckImpl().showPreciseValue(5); 
        //new DeckImpl().showPreciseSuit(Suits.DIAMONDS);
        //new DeckImpl().showPreciseCard(new CardImpl());
        new DeckImpl().showAllCards();
    }

}


