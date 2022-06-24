package start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import view.Utilities;

public class Warning extends JDialog {

    private static final long serialVersionUID = 1L;

    public Warning() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("WARNING");
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setModal(true);
        this.setResizable(false);
        //Definition of center and south JPanel
        this.setModal(true);
        final JPanel center = new JPanel();
        final JPanel south = new JPanel();
        center.setBorder(new TitledBorder(new EtchedBorder(), "WARNING!!"));
        // Definition of a Text area with terms and condiction
        final JTextArea textArea = new JTextArea(15, 30);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(new Color(247, 99, 79));
        //file lecture
        textArea.append(Utilities.getFileText("txt/WarningTest.txt"));
        // set textArea non-editable
        textArea.setEditable(false); 
        //Putting the Text area on a scroll panel
        final JScrollPane scrollText = new JScrollPane(textArea);
        //Setting scroll option only vertical
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //Definition of a ceckbox and button for confirm terms and conditions
        final JCheckBox ceckbox = new JCheckBox("Sono consapevole"); // accepts upto 10 characters
        final JButton accept = new JButton("Avanti");
        accept.setEnabled(false);
        //Adding action listener for the checkbox the button "avanti" is unavaiable 
        //the button "avanti" is not enabled until the checkbox is selected
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                accept.setEnabled(abstractButton.getModel().isSelected()); 
            }
        };
        accept.addActionListener(e -> this.dispose());
        //Adding the components to the panels
        center.add(scrollText);
        south.add(ceckbox);
        south.add(accept);
        // Setting Frame
        ceckbox.addActionListener(actionListener);
        this.add(center, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}      

