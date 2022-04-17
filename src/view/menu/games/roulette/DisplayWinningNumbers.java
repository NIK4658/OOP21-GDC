package view.menu.games.roulette;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import roulette.RouletteNumber;

public class DisplayWinningNumbers extends JPanel {
    
    private static final int MAX_NUM_DISPLAYED = 12;
    private final List<JButton> list;
    
    public DisplayWinningNumbers() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(35654));
        this.list = new LinkedList<>();
        for (int i = 0; i < MAX_NUM_DISPLAYED; i++) {
            final JButton b = new JButton();
            list.add(b);
            this.addComponent(this, b, i, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        }
    }
    
    public void display(List<RouletteNumber> list) {
        int i = 0;
        for (RouletteNumber n : list) {
            JButton b = this.list.get(i);
            b.setText(String.valueOf(n.getValue()));
            b.setForeground(n.getColor());
            i++;
        }
        
    }
    
    private void addComponent(final Container container, final Component component, final int gridx, final int gridy,
            final int gridwidth, final int gridheight, final int anchor, final int fill) {
        final GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets(0, 0, 0, 0), 0, 0);
        container.add(component, gbc);
    }

}
