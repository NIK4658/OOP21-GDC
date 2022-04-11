package notify.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.Resizer;

/**
 * Classe per debug.
 */
public class NotifyGui extends JDialog implements ActionListener {

    private final Dimension dim = new Resizer().resize(5);
    private static final long serialVersionUID = 1L;

    /**
    * Costruttore.
    */
    public NotifyGui(final String text) {
        this.setTitle("Attenzione"); //Titolo finestra
        this.setModal(true); //Disabilita altre finestre fino a che non viene chiusa questa
        setResizable(false); //non può essere allargata

        setAlwaysOnTop(true);
        setSize((int) dim.getWidth(), (int) dim.getHeight());
        setLocation((int) dim.getWidth(), (int) dim.getHeight());
        final JPanel content = new JPanel(new GridBagLayout());
        final JLabel txt = new JLabel(text);
        final JButton btn = new JButton("OK");
        txt.setFont(new Font("Arial", Font.PLAIN, (int) dim.getWidth() / 30));
        btn.setFont(new Font("Arial", Font.BOLD, (int) dim.getHeight() / 30));
        content.add(txt, setDimensionObj(0, 0, 10));
        content.add(btn, setDimensionObj(0, 1, 5));
        add(content);
        btn.addActionListener(this);
        setVisible(true);
    }

    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int space) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10, 0, space, 0); //3o parametro definisce la dist verticale tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        dispose();
    }
}
