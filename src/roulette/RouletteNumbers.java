package roulette;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

//posso decidere quanti numeri totali della roulette avere e quanti di questi essere 0
//creare la classe statica?
public class RouletteNumbers extends TreeMap<Integer, Color> {
    private final int rouletteNumbers;
    private final int greenNumbers;
    
    public RouletteNumbers(final int rouletteNumbers, final int greenNumbers) {
        this.rouletteNumbers = rouletteNumbers;
        this.greenNumbers = greenNumbers;
        this.addGreenNumbers();
        this.addBlackRedNumbers();
    }
    
    private void addGreenNumbers() {
        for (int i = 0; i > -this.greenNumbers; i--) {
            this.put(i, Color.GREEN);
        }
    }
    
    private void addBlackRedNumbers() {
        int i = this.greenNumbers;
        while (i < this.rouletteNumbers) {
            boolean isRed = true;
            for (final int j = i; i < j + 18; i++) {
                if ((j + 10) == i) {
                    isRed = !isRed;
                }
                if (isRed) {
                    this.put(i, Color.RED);
                }
                else {
                    this.put(i, Color.BLACK);
                }
                isRed = !isRed;
            }
        }
    }
    
}
