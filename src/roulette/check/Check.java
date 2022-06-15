package roulette.check;

import roulette.RouletteNumber;

//cambiare package
//fare una classe nested?
//creare un metodo check all'interno di RouletteNumber?
public class Check {
    
    public static boolean win(int number, RouletteNumber rouletteNumber) {
        return number == rouletteNumber.getValue();
    }
    
}
