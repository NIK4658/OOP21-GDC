package view.menu.games.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.Utilities;


/**
 * Useful component to define the amount of money wagered by the user.
 */
public class BetButtonImpl extends JButton implements BetButton {
    
    private static final long serialVersionUID = 1L;
    private double value;
    private final Dimension dim;
    
    /**
     * Main Constructor of this class.
     * 
     * @param dim   Dimension of the main Window.
     */
    public BetButtonImpl(final Dimension dim) {
        super();
        this.dim = dim;
        this.setVisible(true);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
    
    /**
     * Get the value of the bet. 
     * 
     * @return the amount of money wagered by the user.
     */
    public double getBet() {
        return this.value;
    }
    
    /**
     * Assign a certain bet value to the component.
     * 
     * @param value Value to set.
     */
    public void setBet(final double value) {
        resetBet();
        incrementBet(value);
        this.value = value;
    }
    
    /**
     * Reset the bet values.
     */
    public void resetBet() {
        this.value = 0;
        this.removeAll();
        this.setIcon(null);
        this.setEnabled(true);
    }
    
    
    /**
     * It increases the values ​​of the bet and generates an image relating to the amount of the bet.
     * 
     * @param value Increment value.
     */
    public void incrementBet(final double value) {
        this.value += value;
        this.removeAll();
        this.setIcon(chooseChip(this.value));
        final JPanel jp = new JPanel(new BorderLayout());
        final String stringValue = this.value * 100 % 100 == 0 
                ? String.valueOf((int) this.value) : String.valueOf(this.value);
        final JLabel punt = new JLabel(stringValue, SwingConstants.CENTER);
        punt.setFont(new Font("Arial", Font.BOLD, dim.width / 100));
        punt.setForeground(Color.WHITE);
        jp.setOpaque(false);
        jp.add(punt, BorderLayout.CENTER);
        this.add(jp, BorderLayout.CENTER);
        validate();
    }  
    
    /**
     * Confirm the user's bet. He will no longer be able to increase this bet until the start of the next game.
     */
    public void confirmBet() {
        this.setEnabled(false);
        this.setDisabledIcon(chooseChip(this.value));
        validate();
    } 
     
    private ImageIcon chooseChip(final double puntata) {
        final String path = "img/fiches/empty/";
        final int widthfiches = this.dim.width / 16;
        final int heightfiches = this.dim.height / 9;
        String fichesname = "500";
        if (puntata <  5) {
            fichesname = "1";
        } else if (puntata < 25) {
            fichesname = "5";
        } else if (puntata < 100) {
            fichesname = "25";
        } else if (puntata < 500) {
            fichesname = "100";
        }
        return new ImageIcon((Utilities.getImage(path + fichesname + "HD2.png"))
                .getScaledInstance(widthfiches, heightfiches, Image.SCALE_SMOOTH));
    }
}
