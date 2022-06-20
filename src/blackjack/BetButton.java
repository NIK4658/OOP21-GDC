package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.ImageLoader;

public class BetButton extends JButton{
    
    private double value;
    
    public BetButton(){
        super();
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
        return this.value=value;
    }
    
    public void resetBet() {
        this.value = 0;
        this.removeAll();
        this.setIcon(null);
        this.setEnabled(true);
    }
    
    public void incrementBet(final double fvalue) {
        this.value += fvalue;
        this.removeAll();
        this.setIcon(chooseChip(this.value));
        final JPanel jp = new JPanel(new BorderLayout());
        final String stringValue = this.value * 100 % 100 == 0 
                ? String.valueOf((int) this.value) : String.valueOf(this.value);
        final JLabel punt = new JLabel(stringValue, SwingConstants.CENTER);
        punt.setForeground(Color.WHITE);
        jp.setOpaque(false);
        jp.add(punt, BorderLayout.CENTER);
        this.add(jp, BorderLayout.CENTER);
        validate();
    }  
    
    public void confirmBet() {
        this.setEnabled(false);
        this.setDisabledIcon(chooseChip(this.value));
        validate();
    } 
     
    private ImageIcon chooseChip(final double puntata) {
        if (puntata <  5) {
            final Image img = ImageLoader.getImage("res/img/fiches/empty/1HD2.png");
            return new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        }
        if (puntata < 25) {
            final Image img = ImageLoader.getImage("res/img/fiches/empty/5.png");
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 100) {
            final Image img = ImageLoader.getImage("res/img/fiches/empty/25.png");
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        if (puntata < 500) {
            final Image img = ImageLoader.getImage("res/img/fiches/empty/100.png");
            return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        final Image img = ImageLoader.getImage("res/img/fiches/empty/500.png");
        return new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH)); 
    }
}
