package view.menu;

import account.AccountManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.GridBagConstraintsConstructor;
import view.gui.MenuManager;

/**
 * Menu principale.
 */
public class MainMenu extends JPanel implements Menu {

    private static final long serialVersionUID = 1L;
//    private final MenuManager frame;
//    private final AccountManager account;

    /**
     * Costruttore.
     */
    public MainMenu(final MenuManager frame, final AccountManager account) {
//        this.frame = frame;
//        this.account = account;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        final int width = frame.getWidthMenu();
        final int height = frame.getHeightMenu();
        final JPanel north = new JPanel(new GridBagLayout());
        final JPanel center = new JPanel(new GridBagLayout());
        north.setBackground(new Color(44, 107, 14));
        north.setPreferredSize(new Dimension(width, height / 4));
        center.setBackground(new Color(44, 107, 14));
        
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        
        JButton accountman = new JButton("Impostazioni Account");
        buttonPanel.add(accountman);
        this.add(buttonPanel,BorderLayout.SOUTH);
        
        //DA IMPOSTARE L'IMMAGINE DELLO SFONDO
        new ImageIcon("res/img/backgrounds/tavolo.jpg");
        
        
        final JLabel title = new JLabel("GIOCHI DEL COLOSSO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(width - (width / 3 / 2), height / 10));
        title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, width / 20));
        north.add(title);
        
        
        final JButton blackjack = new JButton();
        final JButton roulette = new JButton();
        final JButton bacarat = new JButton();
        roulette.addActionListener(e -> frame.setGameMenu(account));
        bacarat.addActionListener(e -> frame.setAccountMenu(account));
        
        //meglio creare una funzione
        final Dimension dimButton = new Dimension(width / 5, height / 3);
        Image img, imgScaled;
        
        img = new ImageIcon("res/img/backgrounds/bj.jpg").getImage();
        imgScaled = img.getScaledInstance(dimButton.width, dimButton.height, Image.SCALE_SMOOTH);
        blackjack.setIcon(new ImageIcon((imgScaled)));
        
        img = new ImageIcon("res/img/backgrounds/rou2.jpg").getImage();
        imgScaled = img.getScaledInstance(dimButton.width, dimButton.height, Image.SCALE_SMOOTH);
        roulette.setIcon(new ImageIcon((imgScaled)));
        
        img = new ImageIcon("res/img/backgrounds/bac.jpg").getImage();
        imgScaled = img.getScaledInstance(dimButton.width, dimButton.height, Image.SCALE_SMOOTH);
        bacarat.setIcon(new ImageIcon((imgScaled)));
        

        //la lista non serve, basta usare una funzione
        final ArrayList<JComponent> list = new ArrayList<>();
        list.add(blackjack);
        list.add(roulette);
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