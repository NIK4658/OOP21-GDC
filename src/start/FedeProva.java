package start;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FedeProva {
    
    public FedeProva() {
        var frame = new JFrame();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width * 2 / 9, screenSize.height * 2 / 3);
        
        var panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 87, 96));
        panel.setPreferredSize(frame.getSize());
        
        var label = new JLabel("Login");
//        addComponent(panel, label, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        panel.add(label, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        
        
        
//        var button = new JButton("Button");
//        button.setPreferredSize(new Dimension(40, 40));
//        addComponent(panel, button, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void addComponent(final Container container, final Component component, final int gridx, final int gridy,
            final int gridwidth, final int gridheight, final int anchor, final int fill) {
        final GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets(0, 0, 0, 0), 0, 0);
        container.add(component, gbc);
    }
}
