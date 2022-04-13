package roulette;

import java.awt.Color;
import java.util.ArrayList;

//posso decidere quanti numeri totali della roulette avere e quanti di questi essere 0
public class RouletteNumbers extends ArrayList<RouletteNumber> {
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
            this.add(new RouletteNumber(i, Color.GREEN));
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
                    this.add(new RouletteNumber(i, Color.RED));
                }
                else {
                    this.add(new RouletteNumber(i, Color.BLACK));
                }
                isRed = !isRed;
            }
        }
    }
    
}
