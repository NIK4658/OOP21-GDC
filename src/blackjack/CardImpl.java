package blackjack;

import java.awt.Image;
import java.util.Objects;
import java.util.Random;
import utility.Pair;
import view.ImageLoader;

/**
 * Classe principale gestione Carte.
 */
public class CardImpl implements Card {

    private final Pair<Suits, Integer> card;
    private boolean facedown;
    private Image img;
    
    /**
    * Genera una carta precisa.
    */
    public CardImpl(final Suits s, final int value) {
        this.card = new Pair<>(s, value);
        this.img = ImageLoader.getImage("res/img/cards/" + this.card.getX() + "/" + this.card.getY() + ".png");
        this.facedown = false;
    }
    
    //Genera carta random
    public CardImpl() {
        this(getRandomSuit(), getRandomValue());
        this.facedown = false;
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
    

    @Override
    public Suits getSuit() {
        return this.card.getX(); 
    }
    
    @Override
    public int getValue() {     
        if (this.card.getY() >= 10) {
            return 10;
        } else {
            return this.card.getY();
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
        return (this.card.getX() == Suits.DIAMONDS || this.card.getX() == Suits.HEARTS);
    }
    
    @Override
    public void turnOver() {
        if (this.facedown) {
            this.img = ImageLoader.getImage("res/img/cards/" + this.card.getX() + "/" + this.card.getY() + ".png");
            this.facedown = false;
        } else {
            if (isRedColored()) {
                this.img = ImageLoader.getImage("res/img/cards/back/red.png");
            } else {
                this.img = ImageLoader.getImage("res/img/cards/back/black.png");
            } 
            this.facedown = true;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(card);
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
        return Objects.equals(this.card.getX(), other.getSuit()) && Objects.equals(this.card.getY(), other.getValue());
    }

}


