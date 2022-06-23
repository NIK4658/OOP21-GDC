package model.roulette;

public interface RouletteFactory {
    
    Roulette createBaseRoulette();
    
    Roulette createEuropeanRoulette();
    
    Roulette createAmericanRoulette();
    
}
