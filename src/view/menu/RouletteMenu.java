package view.menu;

import javax.swing.JPanel;

import account.AccountManager;
import roulette.Table;
import view.gui.MenuManager;
import java.awt.BorderLayout;
import java.awt.Color;

public class RouletteMenu extends JPanel implements Menu {
    
    public RouletteMenu(final MenuManager frame, final AccountManager account){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(frame.getSizeMenu());
        this.add(new Table());
    }

    @Override
    public JPanel getMenu() {
        return this;
    }

}
