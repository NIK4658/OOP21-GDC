package view.menu;

import javax.swing.JPanel;
import account.AccountManager;
import roulette.Roulette;
import view.gui.MenuManager;
import view.roulette.Table;

import java.awt.BorderLayout;
import java.awt.Color;

public class RouletteMenu extends JPanel implements Menu {
    
    public RouletteMenu(final MenuManager frame, final AccountManager account){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(frame.getSizeMenu());
        final Roulette roulette = new Roulette();
        this.add(new Table());
        roulette.start();
    }

    @Override
    public JPanel getMenu() {
        return this;
    }

}
