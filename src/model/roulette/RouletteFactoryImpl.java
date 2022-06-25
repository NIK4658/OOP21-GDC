package model.roulette;

import java.util.List;
import java.util.Random;
import model.roulette.number.RouletteNumber;
import model.roulette.property.EuropeanSectors;
import model.roulette.property.Property.Sector;
import model.roulette.property.SectorRoulette;
import model.roulette.wheel.WheelFactory;
import model.roulette.wheel.WheelFactoryImpl;

/**
 * Roulette's factory.
 */
public class RouletteFactoryImpl implements RouletteFactory {
    
    private final WheelFactory wheelFactory;
    private final Random random;
    
    /**
     * Create a roulette's factory.
     */
    public RouletteFactoryImpl() {
        this.wheelFactory = new WheelFactoryImpl();
        this.random = new Random();
    }

    @Override
    public Roulette createBaseRoulette() {
        final List<RouletteNumber> baseNumbers = this.wheelFactory.createBaseWheel().getList();
        return () -> baseNumbers.get(this.random.nextInt(baseNumbers.size()));
    }

    @Override
    public Roulette createEuropeanRoulette() {
        return () -> new SectorRoulette(this.createBaseRoulette().spin()) {
            @Override
            protected Sector getSector(final RouletteNumber rouletteNumber) {
                return new EuropeanSectors().getList().get(rouletteNumber.getValue());
            }
        };
    }

    @Override
    public Roulette createAmericanRoulette() {
        final List<RouletteNumber> americanNumbers = this.wheelFactory.createAmericanWheel().getList();
        return () -> americanNumbers.get(this.random.nextInt(americanNumbers.size()));
    }
    
}
