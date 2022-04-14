package roulette;

import java.util.ArrayList;

public class WinningNumbers extends ArrayList<RouletteNumber> {
    
//    public WinningNumbers() {
//     this = new ArrayList<>();
//    }
    
//    public List<RouletteNumber> get() {
//        return this.winningNumbers;
//    }
    
    public void add(final int n) {
        super.add(0, new RouletteNumber(n));
        this.removeIf(e -> this.indexOf(e) > 11);
    }

}
