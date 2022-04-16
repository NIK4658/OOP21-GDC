package roulette;

import java.util.Random;

import javax.swing.Timer;

import view.roulette.DisplayWinningNumbers;

//da eliminare winningNumbers e metodo spin deve tornare un RouletteNumber
public class Roulette {
    private static final int DELAY = 3000;
    private final Random random;
    private final WinningNumbers winningNumbers;
    private final DisplayWinningNumbers l;
    
    public Roulette(final DisplayWinningNumbers l) {
        this.random = new Random();
        this.winningNumbers = new WinningNumbers();
        this.l = l;
    }
    
    public void start() {
        System.out.println("Start");
        new Timer(DELAY, e -> this.spin()).start();
    }

    private void spin() {
        int a = this.random.nextInt(37);
        System.out.println(a);
        this.winningNumbers.add(a);
        System.out.println(winningNumbers);
        l.display(this.winningNumbers);
    }
}
