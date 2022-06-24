package model.roulette.property;

import java.util.Objects;

import model.roulette.number.RouletteNumber;
import model.roulette.property.Property.Sector;

public abstract class SectorRoulette extends PropertyDecorator { //da camb nome
    
    private final Sector sector;
    
    public SectorRoulette(final RouletteNumber rouletteNumber) {
        super(rouletteNumber);
        this.sector = this.getSector(rouletteNumber);
    }
    
    protected abstract Sector getSector(final RouletteNumber rouletteNumber);
    
    private Sector getSector() {
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
        final SectorRoulette other = (SectorRoulette) obj;
        return sector == other.sector;
    }

}