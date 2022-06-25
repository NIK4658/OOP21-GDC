package view.menu.games;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import view.gui.MenuManager;
import view.menu.GeneralGui;
import view.menu.Menu;

/**
 * Class that generate a game merged with the general interface.
 */
public class MergedGame extends JPanel implements Menu {

    private static final long serialVersionUID = 1L;

    /**
     * Main constructor.
     * 
     * @param frame Frame of the window.
     * @param g     General Interface.
     * @param game  JPanel of the game. 
     */
    public MergedGame(final MenuManager frame,  final GeneralGui g, final JPanel game){
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
