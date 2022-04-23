package view.menu.games.roulette;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import account.AccountManager;
import roulette.Roulette;
import view.gui.MenuManager;
import view.menu.GameMenu;

import java.awt.BorderLayout;
import java.awt.Color;

public class RoulettePanel extends JPanel {
    
    public RoulettePanel(){
        this.setLayout(new BorderLayout());
        
        final DisplayWinningNumbers l = new DisplayWinningNumbers();
        final Roulette roulette = new Roulette(l);
        this.add(new Table());
        this.add(l, BorderLayout.NORTH);
        
        roulette.start();
    }

}
