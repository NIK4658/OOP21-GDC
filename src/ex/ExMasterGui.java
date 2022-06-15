package ex;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.Resizer;

/**
 * Menu principale.
 */
public class ExMasterGui extends JFrame {

    private static final Dimension DIM = new Resizer().resize(2);
    private static final int DIMX = (int) DIM.getWidth();
    private static final int DIMY = (int) DIM.getHeight();

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore.
     */
    public ExMasterGui() {
        // Default
        setDefaultCloseOperation(EXIT_ON_CLOSE); // chiude processo finestra
        setResizable(false); // non può essere allargata
        setLocation((DIMX / 2) - (getSize().width / 2), (DIMY / 2) - (getSize().height / 2)); // spawn al centro
        //setUndecorated(true); //toglie la barra in alto
        //device.setFullScreenWindow(this); //set full screen

        // Zona di destra
        //final JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        final JPanel south = new JPanel(new GridBagLayout());
        final JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        final ExImageModifier imgMod = new ExImageModifier();
        Image imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/backgrounds/tavolo.jpg").getImage()),
                new Dimension(DIMX, DIMY));
        final JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(imgi));

        // Zona di sinistra

        //setContentPane(new JLabel(new ImageIcon(imgi)));

        north.setBackground(new Color(44, 107, 14));
        south.setBackground(new Color(44, 107, 14)); // blu bello

        //west.setBackground(new Color(44, 107, 14)); //verde tavolo
        north.setLayout(new GridBagLayout());
        north.setPreferredSize(new Dimension(DIMX, DIMY / 4));
        south.setPreferredSize(new Dimension(DIMX, 3 * DIMY / 4));

        // Componenti JPanel west (login)
        final JLabel title = new JLabel("GIOCHI DEL COLOSSO", SwingConstants.CENTER); // Titolo Login

        
        final JButton roulette = new JButton(); // Tasto Login
        final JButton bj = new JButton(); // Tasto Registrati
        final JButton bacarat = new JButton();
        

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/roulette.jpeg").getImage()),
                new Dimension(DIMX / 5, DIMY / 3));
        roulette.setIcon(new ImageIcon((imgi)));

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/blackjack.PNG").getImage()),
                new Dimension(DIMX / 5, DIMY / 3));
        bj.setIcon(new ImageIcon((imgi)));

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/baccarat.jpg").getImage()),
                new Dimension(DIMX / 5, DIMY / 3));
        bacarat.setIcon(new ImageIcon((imgi)));
        
        
        //roulette.setBorder(BorderFactory.createEmptyBorder());
        //roulette.setBorder(null);
        //roulette.setBorderPainted(false);


        final ArrayList<JComponent> list = new ArrayList<>();

        list.add(roulette);
        list.add(bj);
        list.add(bacarat);

        // Modifiche solo per titolo
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(DIMX - (DIMX / 3 / 2), DIMY / 10));
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, DIMX / 20));
        north.add(title, setDimensionObj(0, 0, 0, 0, 0));

        // Modifiche generali
        int i = 0;
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(DIMX / 5, DIMY / 3));
            jc.setFont(new Font("Arial", Font.PLAIN, DIMX / 50));
            south.add(jc, setDimensionObj(i, 0, 0, 50, 50));
            i++;
        }

        
        //south.add(new JLabel("ciao "), setDimensionObj(0, 1, 0) );
        // Add JPanel to JFrame
        
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);

        
        setSize(DIMX, DIMY);
        setVisible(true);
    }

    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int spacedown,
            final int spaceleft, final int spaceright) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, spaceleft, spacedown, spaceright); //terzo parametro definisce la distanza verticale
        //(verso il basso) tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
}
