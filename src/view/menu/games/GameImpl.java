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

    
    public GameImpl(final MenuManager frame,  final AdvancedAccountManager account){
        

        final JPanel interfacee = new GeneralGui(frame, account);
        final JPanel game = new Gui(frame, new AdvancedBalanceManagerImpl(account));
        this.setPreferredSize(frame.getSizeMenu());
        
        final JLayeredPane jll = new JLayeredPane();
        jll.setOpaque(true);
        jll.setBackground(Color.GREEN);
        final JButton ciao = new JButton("ciao");
        ciao.setBounds(0, 0, 100, 100);
        jll.add(ciao, 0);
        
        jll.setBounds(1, 1, 1000, 500);
        
        //jl.add(interfacee, 0);
        //jl.add(game, 0);
        this.add(game);
    }

    @Override
    public JPanel getMenu() {
        return this;
    }

}
