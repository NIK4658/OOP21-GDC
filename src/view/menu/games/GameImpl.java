package view.menu.games;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import account.AdvancedAccountManager;
import account.AdvancedBalanceManagerImpl;
import blackjack.Gui;
import java.awt.Color;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.Menu;

public class GameImpl extends JPanel implements Menu {

    
    
    public GameImpl(final MenuManager frame,  GeneralGui g, final JPanel game){
        final int width = frame.getWidthMenu();
        final int height = frame.getHeightMenu();
        this.setPreferredSize(frame.getSizeMenu());
        this.setLayout(null);
        
        final JPanel usrInterface = g;
        usrInterface.setBounds(0, 0, width, height);
        game.setBounds(0, 0, width, height);
       
        final JLayeredPane containerPanel = new JLayeredPane();
        containerPanel.setBounds(0, 0, width, height);
        containerPanel.setOpaque(true);
        
        containerPanel.add(usrInterface, Integer.valueOf(1));
        containerPanel.add(game, Integer.valueOf(0));
        
        this.add(containerPanel);
    }

    @Override
    public JPanel getMenu() {
        return this;
    }

}
