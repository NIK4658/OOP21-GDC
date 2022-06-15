package roulette;

import java.util.LinkedList;

public class WinningNumbers extends LinkedList<RouletteNumber> {
    
    public static final int MAX_NUM_DISPLAYED = 12;
    
    public WinningNumbers() {
        for (int i = 0; i < MAX_NUM_DISPLAYED; i++) {
            this.add(new RouletteNumbers().get(0));
        }
    }
    
//    public List<RouletteNumber> get() {
//        return this.winningNumbers;
//    }
    
    public void add(final int n) {
        super.add(0, new RouletteNumbers().get(n));
        this.removeIf(e -> this.indexOf(e) >= MAX_NUM_DISPLAYED);
    }

}
