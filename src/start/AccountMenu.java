package start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AccountMenu extends JFrame {

    /**
     * 
     **/
    private static final long serialVersionUID = 1L;
    /* Ripetizione da sistemare poi */
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int dimX = (int) screenSize.getWidth() / 2;
    private final int dimY = (int) screenSize.getHeight() / 2;
    
    public AccountMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        final JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(new Color(68, 87, 96));
        panel2.setPreferredSize(new Dimension(dimX, dimY));
        final JButton btDialog = new JButton("JDialog");
        panel2.add(btDialog, setDimensionObj(0, 0, 40));
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 87, 96));
        panel.setPreferredSize(new Dimension(dimX / 2, dimY));
        
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, dimX / 20));
        int index = 0;
        panel.add(title, setDimensionObj(0, index++, 40));
      
        final JButton ricarica = new JButton("RICARICA");
        final JButton preleva = new JButton("PRELEVA");
        final JButton dettaglioSaldo = new JButton("DETTAGLIO SALDO");
        final JButton cambiaPassword = new JButton("CAMBIA PASSWORD");
        final JButton eliminaAccount = new JButton("ELIMINA ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(ricarica);
        list.add(preleva);
        list.add(dettaglioSaldo);
        list.add(cambiaPassword);
        list.add(eliminaAccount);
        for (final JButton jb : list) {
            jb.setPreferredSize(new Dimension(dimX / 4, dimY / 20));
            jb.setFont(new Font("Arial", Font.PLAIN, dimX / 50));
            panel.add(jb, setDimensionObj(0, index++, 5));
        }
        
        ricarica.addActionListener(e -> {
            System.out.println("Ciao");
        });
        
        preleva.addActionListener(e -> {
            System.out.println("Ciao");
        });
        
        dettaglioSaldo.addActionListener(e -> {
            System.out.println("Ciao");
        });
        
        cambiaPassword.addActionListener(e -> {
            System.out.println("Ciao");
        });
        
        eliminaAccount.addActionListener(e -> {
            System.out.println("Ciao");
        });
        
        btDialog.addActionListener(e -> {
            System.out.println("va");
            final JDialog dialog = new JDialog(this, true);
            dialog.setContentPane(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
        
        add(panel2, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); //setta al centro dello schermo --> al centro del menu principale
        setVisible(true);
    }
   
    /* Ripetizione da sistemare poi */
    private GridBagConstraints setDimensionObj(final int gridx, final int gridy, final int space) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, 0, space, 0); // terzo parametro definisce la distanza verticale (verso il basso) tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
    
    /* Testing */
    public static void main(String[] args) {
        new AccountMenu();
    }
}
