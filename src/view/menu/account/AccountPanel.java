package view.menu.account;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import view.menu.AccountMenu;

public class AccountPanel extends JPanel {
    
    protected static final int N_COLUMNS_FIELD = 10;
    private static final int SCALE_COMPONENT = 30;
    private final Font font;
    
    public AccountPanel(final int minSize) {
        super();
        this.setLayout(new GridBagLayout());
        this.setBackground(AccountMenu.COLOR_BACKGROUND);
        this.font = new Font("Arial", Font.PLAIN, minSize / SCALE_COMPONENT);
    }

    @Override
    public void add(final Component comp, final Object constraints) {
        comp.setFont(this.font);
        super.add(comp, constraints);
    }
    
}