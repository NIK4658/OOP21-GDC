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
        this.card = new Pair<>(getRandomSuit(), getRandomValue());
        setImg();
    }
    
    //Genera carta random con seme preciso
    public CardImpl(final Suits s) {
        this.card = new Pair<>(s, getRandomValue());
        setImg();
    }
    
    //Genera carta random con valore preciso
    public CardImpl(final int value) {
        this.card = new Pair<>(getRandomSuit(), value);
        setImg();
    }
    
    //Genera carta precisa
    public CardImpl(final Suits s, final int value) {
        this.card = new Pair<>(s, value);
        setImg();
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
    public void setImg() {
        this.img = new ImageIcon("res/img/cards/" + getSuit() + "/" + getValue() + ".png").getImage();
    }
    
    @Override
    public Image getImg() {
        return this.img;
    }
    
    
    private Suits getRandomSuit() {
        return Suits.values()[new Random().nextInt(Suits.values().length)];
    }
    
    private int getRandomValue() {
        return new Random().nextInt(13) + 1;
    }
    
    /**
     * Testing. Da eliminare in seguito.
     */
    public static void main(final String[] args) {      
        new DeckImpl().showAllCards(); 
    }
}


