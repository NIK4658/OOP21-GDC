package view.menu.games.roulette;

import blackjack.BetButton;

public class RouletteBetButton extends BetButton {
    
    private final Object property;
    
    public RouletteBetButton(final Object property) {
        super();
        this.property = property;
        this.setOpaque(false);
        this.setBorderPainted(false);
    }

    public Object getProperty() {
        return this.property;
    }
    
    public void incrementBet(final double fvalue) {
        super.incrementBet(fvalue);
    }

}
