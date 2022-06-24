package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * 
 *
 */
public class Utilities {

    /**
     * 
     * 
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
        return new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
    }
    
    public static String getFileText(final String path) {
        final InputStream in = ClassLoader.getSystemResourceAsStream(path);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String st = "";
        String total = "";
        try {
            while ((st = br.readLine()) != null) {
                total = total + st + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return total;
    }

    
            

        
        
}

