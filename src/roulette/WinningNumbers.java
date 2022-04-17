package roulette;

import java.util.LinkedList;

public class WinningNumbers extends LinkedList<RouletteNumber> {
    
    private static final int MAX_NUM_DISPLAYED = 12;
    
//    public WinningNumbers() {
//     this = new ArrayList<>();
//    }
    
//    public List<RouletteNumber> get() {
//        return this.winningNumbers;
//    }
    
    public void add(final int n) {
        super.add(0, new RouletteNumber(n));
        this.removeIf(e -> this.indexOf(e) >= MAX_NUM_DISPLAYED);
    }

}
