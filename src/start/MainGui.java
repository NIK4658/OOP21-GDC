package start;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.access.AccessMenu;


public class MainGui extends JFrame {
    
//    private static final int SCALE = 2 / 3;

    public MainGui() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        
        new AccessMenu(this);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void updateContentPanel(final JPanel panel) {
        this.setContentPane(panel);
        this.pack();
        this.revalidate();
    }
    
//    public void goToAccessMenu(){}
//    public void goToAccountMenu(){}
}


































