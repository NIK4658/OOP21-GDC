package view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Class useful to resize all windows with a factor scale.
 */
public class Resizer {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Main function.
     */
    public Dimension resize(final double factor) {
        int width = 0;
        int height = 0;

        if (checkVertical()) {
            System.out.println("verticale");
            width = (int) (screenSize.getWidth() / factor);
            height = (int) ((screenSize.getWidth() * 16 / 9) / factor);
        } else {
            System.out.println("orizzontale");
            if (checkRatio()) {
                System.out.println("RatioCorretto 16/9");
                width = (int) (screenSize.getWidth() / factor);
                height = (int) (screenSize.getHeight() / factor);
            } else {
                System.out.println("RatioSbagliato != 16/9");  
                width = (int) ((screenSize.getHeight() * 16 / 9) / factor);
                height = (int) (screenSize.getHeight() / factor);
            }
        }
        return new Dimension(width, height);
    }

    private boolean checkRatio() {
        return (((screenSize.getHeight() * 16) / 9) == screenSize.getWidth());
    }

    private boolean checkVertical() {
        return (screenSize.getWidth() < screenSize.getHeight());
    }
}
