package start;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.access.AccessPanel.AccessType;

public class AccountMenu extends JPanel {

    /**
     * 
     **/
    private static final long serialVersionUID = 1L;
    /* Togliere poi */
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int dimX = (int) screenSize.getWidth() / 2;
    private final int dimY = (int) screenSize.getHeight() / 2;
    private final JDialog dialog;
    
    
    /* TESTING, togliere poi */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        final AccountMenu accountPanel = new AccountMenu(frame);
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 87, 96));
        panel.setPreferredSize(new Dimension(accountPanel.dimX, accountPanel.dimY));
        final JButton btDialog = new JButton("JDialog");
        panel.add(btDialog, accountPanel.setDimensionObj(0, 0, 40));
        btDialog.addActionListener(e -> {
            accountPanel.getDialog().setVisible(true);
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public AccountMenu(final JFrame frame) {
        
        //pannello MENU
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(68, 87, 96));
        this.setPreferredSize(new Dimension(dimX / 2, dimY));
        
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, dimX / 20));
        int index = 0;
        this.add(title, setDimensionObj(0, index++, 40));
      
        final JButton ricarica = new JButton("RICARICA");
        final JButton preleva = new JButton("PRELEVA");
        final JButton dettaglioSaldo = new JButton("DETTAGLIO SALDO");
        final JButton cambiaUsername = new JButton("CAMBIA USERNAME");
        final JButton cambiaPassword = new JButton("CAMBIA PASSWORD");
        final JButton eliminaAccount = new JButton("ELIMINA ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(ricarica);
        list.add(preleva);
        list.add(dettaglioSaldo);
        list.add(cambiaUsername);
        list.add(cambiaPassword);
        list.add(eliminaAccount);
        for (final JButton jb : list) {
            jb.setPreferredSize(new Dimension(dimX / 4, dimY / 20));
            jb.setFont(new Font("Arial", Font.PLAIN, dimX / 50));
            this.add(jb, setDimensionObj(0, index++, 5));
        }
        
        //pannello RICARICA
        final JPanel panelRicarica = new JPanel(new GridBagLayout());
        panelRicarica.setBackground(new Color(68, 87, 96));
        panelRicarica.setPreferredSize(new Dimension(dimX / 2, dimY));
        JLabel labelRicarica = new JLabel("Ricarica:");
        panelRicarica.add(labelRicarica);

        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        JFormattedTextField myTwoDecimalTextfield = new JFormattedTextField(format);
        panelRicarica.add(myTwoDecimalTextfield);
//        paymentField.setColumns(10);
//        paymentField.setEditable(false);
        
        
        
        
        final CardLayout cl = new CardLayout();
        final JPanel panel = new JPanel(cl);
        
        panel.add(this, "1");
        panel.add(panelRicarica, "2");
//        panel.add(panelPreleva, "3");
//        panel.add(panelDettaglioSaldo, "4");
//        panel.add(panelCambiaUsername, "5");
//        panel.add(panelCambiaPassword, "6");
//        panel.add(panelEliminaAccount, "7");
        
        cl.show(panel, "1");
        
        ricarica.addActionListener(e -> {
            System.out.println("ricarica");
            cl.show(panel, "2");
        });
        
        preleva.addActionListener(e -> {
            System.out.println("preleva");
        });
        
        dettaglioSaldo.addActionListener(e -> {
            System.out.println("dettaglioSaldo");
        });

        cambiaUsername.addActionListener(e -> {
            System.out.println("cambiaUsername");
        });        

        cambiaPassword.addActionListener(e -> {
            System.out.println("cambiaPassword");
        });
        
        eliminaAccount.addActionListener(e -> {
            System.out.println("eliminaAccount");
        });
        
        this.dialog = new JDialog(frame, true);
        this.dialog.setContentPane(panel);
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setResizable(false);
    }
    
    public JDialog getDialog() {
        return this.dialog;
    }
   
    /* Da sostituire con la mia versione */
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int verticalSpace) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, verticalSpace, 0);
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }

}
