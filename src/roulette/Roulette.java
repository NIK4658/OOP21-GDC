package roulette;

import java.util.Random;

import javax.swing.Timer;

public class Roulette {
    private static final int DELAY = 3000;
    private final Random random;
    private final WinningNumbers winningNumbers;
    public Roulette() {
        random = new Random();
        winningNumbers = new WinningNumbers();
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
    }
}
