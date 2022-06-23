package model.roulette;

import model.roulette.number.RouletteNumber;
import model.roulette.property.EuropeanSectors;
import model.roulette.property.Property.Sector;
import model.roulette.property.SectorRoulette;

public class EuropeanRoulette extends BaseRoulette {
    
    public EuropeanRoulette() {
        super();
    }
    
    @Override
    public RouletteNumber spin() {
        return new SectorRoulette(super.spin()) {
            @Override
            protected Sector getSector(final RouletteNumber rouletteNumber) {
                return new EuropeanSectors().getList().get(rouletteNumber.getValue());
            }
        };
    }
  
}
