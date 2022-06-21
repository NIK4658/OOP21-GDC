package baccarat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InsuranceWindow extends JDialog {
    
    private static final long serialVersionUID = 1L;

    public InsuranceWindow() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.add(new JLabel("A dialog"));
        this.pack();
        this.setVisible(true);
    }

}
