package view.menu.games.blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.MyGridBagConstraints;


/**
 * JDialog useful for managing insurance in the "Blackjack" game.
 */
public class InsuranceWindow extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private boolean insuranceValue;

    /**
     * Main Constructor. 
     * 
     * @param dim   Dimension of the main game window.
     * @param canInsurance  True if the user has enough money to carry out the insurance, false otherwhise.
     */
    public InsuranceWindow(final Dimension dim, final boolean canInsurance) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(dim.width / 3, dim.height / 4));
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setModal(true);
        final JPanel insurance = new JPanel(new GridBagLayout());
        insurance.setBackground(new Color(167, 183, 250));
        final JLabel write = new JLabel("Dealer is asking for Insurance, do you accept?", SwingConstants.CENTER);
        write.setFont(new Font("Arial", Font.BOLD, dim.width / 80));
        final JButton yes = new JButton("YES");
        final JButton no = new JButton("NO");
        yes.setEnabled(canInsurance);
        final int ipadButtons = (int) (dim.width / 12.8);
        final int insetsVertical = dim.width / 64;
        final int insetsHorizontal = dim.width / 43;
        insurance.add(write, new MyGridBagConstraints(0, 0, 3, 1, new Insets(0, 0, 0, 0), 0, (int) (dim.width / 8.5)));
        insurance.add(yes, new MyGridBagConstraints(0, 1, 1, 1, 
                new Insets(insetsVertical, insetsHorizontal, insetsVertical, insetsHorizontal),
                ipadButtons, ipadButtons));
        insurance.add(no, new MyGridBagConstraints(1, 1, 1, 1, 
                new Insets(insetsVertical, insetsHorizontal, insetsVertical, insetsHorizontal),
                ipadButtons, ipadButtons));
        yes.addActionListener(e -> {
            this.insuranceValue = true;
            dispose();
        });
        no.addActionListener(e -> {
            this.insuranceValue = false;
            dispose();
        });
        this.add(insurance, BorderLayout.CENTER);
        final int Borderwidth = dim.width / 320;
        this.getRootPane().setBorder(BorderFactory
                .createMatteBorder(Borderwidth, Borderwidth, Borderwidth, Borderwidth, Color.BLACK));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public boolean isInsurance() {
        return this.insuranceValue;
    }
}
