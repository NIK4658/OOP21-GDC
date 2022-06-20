package roulette.number;

import java.awt.Color;

public class EuropeanRouletteNumber extends BaseRouletteNumber implements RouletteNumber{ //devo mettere esplicitamente implements RouletteNumber o non serve? Controllare anche altre classi con caso simile 
    
    private final Sector sector;
    
    public enum Sector{
        TIER, ORPHELINS, VOISINS, ZERO
    }

    public EuropeanRouletteNumber(final int value, final Color color, final Sector sector) {
        super(value, color);
        this.sector = sector;
    }
    
    public EuropeanRouletteNumber(final BaseRouletteNumber rouletteNumber, final Sector sector) {
        super(rouletteNumber.getValue(), rouletteNumber.getColor());
        this.sector = sector;
    }
    
    public Sector getSector() {
        return this.sector;
    }
    
    @Override
    public <P> boolean isProperty(final P property) {
        if (property.equals(this.getSector())) {
            return true;
        } else if (property == Sector.VOISINS && this.getSector() == Sector.ZERO) {
            return true;
        }
        return super.isProperty(property);
    }

    //sistemare l'equals e hashcode
}
