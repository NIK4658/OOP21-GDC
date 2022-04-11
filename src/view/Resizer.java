package view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Class useful to resize all windows with a factor scale.
 */
public class Resizer {

    private int width;
    private int height;

    /**
     * Main function.
     */
    public Dimension resize(final float factor) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        
        if (checkVertical()) {
            System.out.println("verticale");
            return new Dimension(Math.round(width / factor), Math.round(width * 16 / 9 / factor));
        }
        System.out.println("orizzontale");
        if (checkRatio()) {
            System.out.println("RatioCorretto 16/9");
            return new Dimension(Math.round(width / factor), Math.round(height / factor));
        } else {
            System.out.println("RatioSbagliato != 16/9");  
            return new Dimension(Math.round(height * 16 / 9 / factor), Math.round(height / factor));
        }
    }

    private boolean checkRatio() {
        return (height * 16 / 9) == width;
    }

    private boolean checkVertical() {
        return width < height;
    }
}
