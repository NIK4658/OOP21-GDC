package start;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Roulette extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6291936551503579076L;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int dimX = (int) screenSize.getWidth() / 2;
    private final int dimY = (int) screenSize.getHeight() / 2;
    
    public Roulette() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
    }
    
    
    public static void main(final String arg[]) {
        new Roulette();
    }

}
