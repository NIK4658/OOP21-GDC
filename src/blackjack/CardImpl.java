package blackjack;

import java.awt.Image;
import java.util.Objects;
import java.util.Random;
import javax.swing.ImageIcon;
import utility.Pair;

/**
 * Classe principale gestione Carte.
 */
public class CardImpl implements Card {

    
    private final Pair<Suits, Integer> card;
    private Image img;
    private boolean facedown;
    
    
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
    
    //Genera carta girata random
    public CardImpl(final boolean isFacedown) {
        this(getRandomSuit(), getRandomValue());      
        this.facedown = isFacedown;
    }
    
    /**
     * Costruttore che genera una carta precisa.
     */
    public CardImpl(final Suits s, final int value) {
        this.card = new Pair<>(s, value);
        this.img = new ImageIcon("res/img/cards/" + this.card.get1() + "/" + this.card.get2() + ".png").getImage();
        this.facedown = false;
    }
    
    @Override
    public Suits getSuit() {
        return this.card.get1(); 
    }
    
    @Override
    public int getValue() {     
        if (this.card.get2() >= 10) {
            return 10;
        } else {
            return this.card.get2();
        }   
    }
    
    @Override
    public Image getImg() {
        return this.img;
    }
    
    @Override
    public boolean isFaceDown() {
        return this.facedown;
    }
    
    private static Suits getRandomSuit() {
        return Suits.values()[new Random().nextInt(Suits.values().length)];
    }
    
    private static int getRandomValue() {
        return new Random().ints(1, 14).findFirst().getAsInt();
    }
    
    private boolean isRedColored() {
        return (this.card.get1() == Suits.DIAMONDS || this.card.get1() == Suits.HEARTS);
    }
    
    
    @Override
    public void turnOver() {
        if (this.facedown) {
            new ImageIcon("res/img/cards/" + this.card.get1() + "/" + this.card.get2() + ".png").getImage();
            this.facedown = false;
        } else {
            if (isRedColored()) {
                this.img = new ImageIcon("res/img/cards/back/red.png").getImage();
            } else {
                this.img = new ImageIcon("res/img/cards/back/black.png").getImage();
            } 
            this.facedown = true;
        }
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return Objects.equals(this.card.get1(), other.getSuit()) && Objects.equals(this.card.get2(), other.getValue());
    }
    
    @Override
    public int hashCode() {
        return 0;
    }   

}


