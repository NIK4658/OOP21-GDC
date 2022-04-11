package roulette;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RouletteNumbers extends ArrayList<RouletteNumber> {
    
    public RouletteNumbers() {
        this.setNumbers();
    }
    
    private void setNumbers() {
        this.add(new RouletteNumber(0, Color.GREEN));
        this.setBlackRedNumbers();
    }
    
    private void setBlackRedNumbers() {
        int j = 1;
        while (j < 37) {
            boolean change = false;
            for (int i = j; i < j + 18; i++) {
                if ((j + 10) == i) {
                    change = !change;
                }
                if (change) {
                    this.add(new RouletteNumber(i, Color.BLACK));
                }
                else {
                    this.add(new RouletteNumber(i, Color.RED));
                }
                change = !change;
            }
            j += 18;
        }
    }
    
    public List<RouletteNumber> getNumbers(){
        return this;
    }

}
