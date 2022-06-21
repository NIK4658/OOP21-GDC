package view.menu.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileReader;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import view.MyGridBagConstraints;
import view.menu.games.Game.Games;

/**
 * Javadoc Comment.
 */
public class GuideGui extends JDialog {
    
    private static final long serialVersionUID = 1L;

    /**
     * Javadoc Comment.
     */
    public GuideGui(final Dimension dim, final Games game) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(dim.width / 2, (int) (dim.height / 1.3)));
        this.setLayout(new BorderLayout());
        final JPanel container = new JPanel(new BorderLayout());
        final JPanel textareaPanel = new JPanel(new GridBagLayout());
        container.setBackground(new Color(167, 183, 250));
        textareaPanel.setBackground(new Color(167, 183, 250));
        final JLabel title = new JLabel(game.name() + " GUIDE", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(dim.width / 15, dim.width / 15));
        title.setFont(new Font("Arial", Font.BOLD, dim.width / 25));
        final JTextArea text = new JTextArea();
        text.setFont(new Font("Arial", Font.PLAIN, dim.width / 95));
        FileReader reader;
        try {
            reader = new FileReader("res/txt/" + game + ".txt");
            text.read(reader, game + ".txt"); //Object of JTextArea
            reader.close();
        } catch (Exception e) {
            System.out.println("File non trovato");
            //e.printStackTrace();
        }
        text.setEditable(false); // set textArea non-editable
        final JScrollPane scrollArea = new JScrollPane(text);
        scrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        final int insetsVertical = dim.width / 64;
        final int insetsHorizontal = dim.width / 43;
        textareaPanel.add(scrollArea, new MyGridBagConstraints(1, 1, 1, 1, 
                        new Insets(insetsVertical, insetsHorizontal, insetsVertical, insetsHorizontal),
                        0, 0));
        container.add(title, BorderLayout.NORTH);
        container.add(textareaPanel, BorderLayout.CENTER);        
        this.add(container, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
