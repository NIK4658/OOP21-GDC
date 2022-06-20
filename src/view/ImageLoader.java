package view;

import javax.swing.ImageIcon;
import java.awt.Image;

public class ImageLoader {
    public static Image getImage(final String path) {
        return new ImageIcon(path).getImage();
    }
}
