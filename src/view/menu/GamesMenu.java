package view.menu;

import account.AccountManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.GridBagConstraintsConstructor;
import view.ImageModifier;
import view.gui.MenuManager;

/**
 * Menu principale.
 */
public class GamesMenu extends JPanel implements Menu {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore.
     */
    public GamesMenu(final MenuManager frame, final AccountManager account) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        final int width = frame.getWidthMenu();
        final int height = frame.getHeightMenu();
        final JPanel north = new JPanel(new GridBagLayout());
        final JPanel center = new JPanel(new GridBagLayout());
        north.setBackground(new Color(44, 107, 14));
        north.setPreferredSize(new Dimension(width, height / 4));
        center.setBackground(new Color(44, 107, 14));
        
        //DA IMPOSTARE L'IMMAGINE DELLO SFONDO
        final ImageModifier imgMod = new ImageModifier();
        Image imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/backgrounds/tavolo.jpg").getImage()), frame.getSizeMenu());
        final JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(imgi));
        //
        
        final JLabel title = new JLabel("GIOCHI DEL COLOSSO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(width - (width / 3 / 2), height / 10));
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, width / 20));
        north.add(title);
        
        final JButton roulette = new JButton();
        final JButton blackjack = new JButton();
        final JButton bacarat = new JButton();
        final Dimension dimButton = new Dimension(width / 5, height / 3);

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/roulette.jpeg").getImage()), dimButton);
        roulette.setIcon(new ImageIcon((imgi)));

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/blackjack.PNG").getImage()), dimButton);
        blackjack.setIcon(new ImageIcon((imgi)));

        imgi = imgMod.scaleFullScreen(
                (new ImageIcon("res/img/buttons/baccarat.jpg").getImage()), dimButton);
        bacarat.setIcon(new ImageIcon((imgi)));
        //roulette.setBorder(BorderFactory.createEmptyBorder());
        //roulette.setBorder(null);
        //roulette.setBorderPainted(false);


        final ArrayList<JComponent> list = new ArrayList<>();
        list.add(roulette);
        list.add(blackjack);
        list.add(bacarat);
        int i = 0;
        for (final JComponent jc : list) {
            jc.setPreferredSize(new Dimension(width / 5, height / 3));
            jc.setFont(new Font("Arial", Font.PLAIN, width / 50));
            center.add(jc, GridBagConstraintsConstructor.get(i, 0, 0, 50, 50));
            i++;
        }
        
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    @Override
    public JPanel getMenu() {
        return this;
    }
    
}