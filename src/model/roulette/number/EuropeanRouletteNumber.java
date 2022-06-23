package model.roulette.number;

import java.awt.Color;
import java.util.Objects;

public class EuropeanRouletteNumber extends BaseRouletteNumber { 
    
    private final Sector sector;
    
    public enum Sector{
        TIER, ORPHELINS, VOISINS, ZERO
    }

    public EuropeanRouletteNumber(final int value, final Color color, final Sector sector) {
        super(value, color);
        this.sector = sector;
    }
    
    public EuropeanRouletteNumber(final RouletteNumber rouletteNumber, final Sector sector) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(sector);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EuropeanRouletteNumber other = (EuropeanRouletteNumber) obj;
        return sector == other.sector;
    }

}
