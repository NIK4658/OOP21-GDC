package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Class.
 */
public class Utilities {

    /**
     * Class.
     */
    public static Dimension resize(final float factor) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = screenSize.width;
        final int height = screenSize.height;
        if (checkVertical(height, width)) {
            return new Dimension(Math.round(width / factor), Math.round(width * 16 / 9 / factor));
        }
        if (checkRatio(height, width)) {
            return new Dimension(Math.round(width / factor), Math.round(height / factor));
        } else {
            return new Dimension(Math.round(height * 16 / 9 / factor), Math.round(height / factor));
        }
    }
    
    private static boolean checkRatio(final int height, final int width) {
        return (height * 16 / 9) == width;
    }

    private static boolean checkVertical(final int height, final int width) {
        return width < height;
    }

    public static Image getImage(final String path) {
        return new ImageIcon(path).getImage();
    }
}
