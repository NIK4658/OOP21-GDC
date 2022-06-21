package blackjack;

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
import view.ImageLoader;


/**
 * javadoc comment.
 */
public class BetButton extends JButton {
    
    private static final long serialVersionUID = 1L;
    private double value;
    private final Dimension dim;
    
    /**
     * javadoc comment.
     */
    public BetButton(final Dimension dim) {
        super();
        this.dim = dim;
        this.setVisible(true);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
    
    public double getBet() {
        return this.value;
    }
    
    public double setBet(final double value) {
        return this.value = value;
    }
    
    /**
     * javadoc comment.
     */
    public void resetBet() {
        this.value = 0;
        this.removeAll();
        this.setIcon(null);
        this.setEnabled(true);
    }
    
    /**
     * javadoc comment.
     */
    public void incrementBet(final double fvalue) {
        this.value += fvalue;
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
     * javadoc comment.
     */
    public void confirmBet() {
        this.setEnabled(false);
        this.setDisabledIcon(chooseChip(this.value));
        validate();
    } 
     
    private ImageIcon chooseChip(final double puntata) {
        if (puntata <  5) {
            return new ImageIcon((ImageLoader.getImage("res/img/fiches/empty/1HD2.png"))
                    .getScaledInstance(this.dim.width / 16, this.dim.height / 9, Image.SCALE_SMOOTH));
        }
        if (puntata < 25) {
            return new ImageIcon((ImageLoader.getImage("res/img/fiches/empty/5HD2.png"))
                    .getScaledInstance(this.dim.width / 16, this.dim.height / 9, Image.SCALE_SMOOTH));
        }
        if (puntata < 100) {
            return new ImageIcon((ImageLoader.getImage("res/img/fiches/empty/25HD2.png"))
                    .getScaledInstance(this.dim.width / 16, this.dim.height / 9, Image.SCALE_SMOOTH));
        }
        if (puntata < 500) {
            return new ImageIcon((ImageLoader.getImage("res/img/fiches/empty/100HD2.png"))
                    .getScaledInstance(this.dim.width / 16, this.dim.height / 9, Image.SCALE_SMOOTH));
        }
        return new ImageIcon((ImageLoader.getImage("res/img/fiches/empty/500HD2.png"))
                .getScaledInstance(this.dim.width / 16, this.dim.height / 9, Image.SCALE_SMOOTH));
    }
}
