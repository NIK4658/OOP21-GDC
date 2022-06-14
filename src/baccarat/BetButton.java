package baccarat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BetButton extends JButton{
    
    private int value;
    
    public int getBet() {
        return this.value;
    }
    
    public void resetBet() {
        this.value = 0;
        this.removeAll();
        this.setIcon(null);
    }
    
    public void incrementBet(final int fvalue) {
        this.value += fvalue;
        this.removeAll();
        this.setIcon(chooseChip(this.value));
        final JPanel jp = new JPanel(new BorderLayout());
        final JLabel punt = new JLabel(String.valueOf(this.value), SwingConstants.CENTER);
        punt.setForeground(Color.WHITE);
        jp.setOpaque(false);
        jp.add(punt, BorderLayout.CENTER);
        this.add(jp, BorderLayout.CENTER);
        validate();
    }  
     
    private ImageIcon chooseChip(final int puntata) {
        if (puntata <  5) {
            final Image img = new ImageIcon("res/img/fiches/empty/1HD2.png").getImage();
            return new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        }
        if (puntata < 25) {
            final Image img = new ImageIcon("res/img/fiches/empty/5.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 100) {
            final Image img = new ImageIcon("res/img/fiches/empty/25.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 500) {
            final Image img = new ImageIcon("res/img/fiches/empty/100.png").getImage();
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        final Image img = new ImageIcon("res/img/fiches/empty/500.png").getImage();
        return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH)); 
    }
}
