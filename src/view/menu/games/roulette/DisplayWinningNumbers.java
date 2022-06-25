package view.menu.games.roulette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.roulette.number.RouletteNumber;
import view.MyGridBagConstraints;

/**
 * Display the winning numbers of the roulette.
 */
public class DisplayWinningNumbers extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private static final int DISPLAYED_NUMBERS = 12;
    private static final int SCALE_SPACE_HEIGHT_NUMBERS = 8;
    private static final int SCALE_SPACE_WIDTH_NUMBERS = 200;
    private final List<JButton> winningNumbers;
    
    /**
     * Display the winning numbers of the roulette in a panel with spesific dimension.
     * 
     * @param dimension
     */
    public DisplayWinningNumbers(final Dimension dimension) {
        this.setLayout(new GridBagLayout());
        this.setBackground(RouletteGame.BACKGROUND_COLOR);
        this.setPreferredSize(dimension);
        final int heightSpace = dimension.height / SCALE_SPACE_HEIGHT_NUMBERS;
        final int widthSpace = dimension.width / SCALE_SPACE_WIDTH_NUMBERS;
        
        this.winningNumbers = new LinkedList<>();
        for (int i = 0; i < DISPLAYED_NUMBERS; i++) {
            final JButton b = new JButton();
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setForeground(Color.WHITE);
            b.setBackground(this.getBackground());
            this.winningNumbers.add(b);
            this.add(b, new MyGridBagConstraints(i, 0, 1, 1, 
                    new Insets(heightSpace, widthSpace, heightSpace, widthSpace)));
        }
    }
    
    /**
     * Adds the spesific roulette number at the display number. 
     * 
     * @param rouletteNumber
     */
    public void update(final RouletteNumber rouletteNumber) {
        for (int i = DISPLAYED_NUMBERS - 2; i >= 0; i--) {
            winningNumbers.get(i + 1).setText(winningNumbers.get(i).getText());
            winningNumbers.get(i + 1).setBackground(winningNumbers.get(i).getBackground());
        }
        winningNumbers.get(0).setText(rouletteNumber.toString());
        winningNumbers.get(0).setBackground(rouletteNumber.getColor());
    }

}
