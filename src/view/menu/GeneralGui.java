package view.menu;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import view.MyGridBagConstraints;


public class GeneralGui extends JPanel {
	
    private final int x = 1280;
    private final int y = 720;
    private int fichesvalue;
	
    private final int getFichesValue() {
        return fichesvalue;
    }
	

	
    public GeneralGui(){

        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(x, y)); 
        
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel center = new JPanel();

        south.setLayout(new GridBagLayout());
        south.setPreferredSize(new Dimension((int) (x / 12.8), (int) (x / 12.8)));


        north.setLayout(new GridBagLayout());
        north.setPreferredSize(new Dimension((int) (x / 12.8), (int) (x / 12.8)));
        //north.setBackground(Color.RED);

        //JLabel definition

        //North jpanel label

        final JLabel labelvincita = new JLabel("HAI VINTO...");
        labelvincita.setForeground(Color.BLACK);
        labelvincita.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 25));
        north.add(labelvincita);

        final JButton backToMenu = new JButton("BACK MENU");
        backToMenu.setPreferredSize(new Dimension((int) (x / 12.8), (int) (x / 25.6)));
        north.add(backToMenu, setDimensionObj(0, 0, 0, (int) (x / 1.3), 0));
            
        final JButton help = new JButton("?");
        help.setPreferredSize(new Dimension(100, 50));
        north.add(help, setDimensionObj(1, 0, 0, 0, 0));
            
        //South Jpanel label

        final JLabel vincita = new JLabel("VINCITA");
        vincita.setForeground(Color.BLACK);
        //vincita.setBounds(350, 20, 150, 150);
        vincita.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(vincita, new MyGridBagConstraints(1, 2, 30, 30));

        //(0,0,0,(int)(x/3),150));

        final JLabel saldo = new JLabel("SALDO");
        saldo.setForeground(Color.BLACK);
        //saldo.setBounds(350, 20, 150, 150);
        saldo.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(saldo, new MyGridBagConstraints(2, 2, 30, 30));

        //(int)(x/2),20))

        final JLabel puntata = new JLabel("PUNTATA");
        puntata.setForeground(Color.BLACK);
        //puntata.setBounds(350, 20, 150, 150);
        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(puntata, new MyGridBagConstraints(0, 2, 30, 30));

        //south.add(puntata, setDimensionObj(0,0,0,(int)(x/3),0));

        //Jlabel for puntata,vincita,saldo value

        final JLabel vsaldo = new JLabel("1");
        puntata.setForeground(Color.BLACK);
        //puntata.setBounds(350, 20, 150, 150);
        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(vsaldo, new MyGridBagConstraints(1, 1, 30, 30));
        // south.add(vsaldo, setDimensionObj(0,1,0,(int)(x/3),150));
            
        final JLabel vpuntata = new JLabel("2");
        puntata.setForeground(Color.BLACK);
        //puntata.setBounds(350, 20, 150, 150);
        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(vpuntata, new MyGridBagConstraints(2, 1, 30, 30));
            
        //south.add(vpuntata, setDimensionObj(0,1,0,(int)(x/2),20));
            
        final JLabel vvincita = new JLabel("3");
        puntata.setForeground(Color.BLACK);
        //puntata.setBounds(350, 20, 150, 150);
        puntata.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, 15));
        south.add(vvincita, new MyGridBagConstraints(0, 1, 30, 30));
        //south.add(vvincita, setDimensionObj(0,1,0,(int)(x/3),0));
            
            
        //Button for confirm or cancel your bet in the south panel
            
        final JButton reset = new JButton(" ");
        final JButton confirm = new JButton("  ");
            
        //Button with fish image for your bet
            
        final JButton fish1 = new JButton(" ");
        final JButton fish2 = new JButton(" ");
        final JButton fish3 = new JButton(" ");
        final JButton fish4 = new JButton(" ");
        final JButton fish5 = new JButton(" ");
            
        //Adding Listener to set fiches value
            
        fish1.addActionListener(e -> fichesvalue = 1);
        fish2.addActionListener(e -> fichesvalue = 5);
        fish3.addActionListener(e -> fichesvalue = 25);
        fish4.addActionListener(e -> fichesvalue = 100);
        fish5.addActionListener(e -> fichesvalue = 500);
            

            
        final List<JButton> list = new ArrayList<>();
        list.add(reset);
        list.add(confirm);
        list.add(fish1);
        list.add(fish2);
        list.add(fish3);
        list.add(fish4);
        list.add(fish5);
            
        //Setting all the button transparent 
        for (final JButton jb : list) { 
            jb.setOpaque(false);
            jb.setFocusPainted(false);
            jb.setBorderPainted(false);
            jb.setContentAreaFilled(false);
            jb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        }

        //Setting images to the buttons

        final Image img = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
        reset.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img1 = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
        confirm.setIcon(new ImageIcon(img1.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img2 = new ImageIcon("res/img/fiches/numbers/1.png").getImage();
        fish1.setIcon(new ImageIcon(img2.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img3 = new ImageIcon("res/img/fiches/numbers/5.png").getImage();
        fish2.setIcon(new ImageIcon(img3.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img4 = new ImageIcon("res/img/fiches/numbers/25.png").getImage();
        fish3.setIcon(new ImageIcon(img4.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img5 = new ImageIcon("res/img/fiches/numbers/100.png").getImage();
        fish4.setIcon(new ImageIcon(img5.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        final Image img6 = new ImageIcon("res/img/fiches/numbers/500.png").getImage();
        fish5.setIcon(new ImageIcon(img6.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

        //Adding fish and confirm/cancel button on the south panel

        int c1 = 4;
        for (final JButton jb : list) {
            south.add(jb, new MyGridBagConstraints(0, c1, 30, 30));
            c1++;
        }


        //adding the panel to the Container 
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        setVisible(true);
    }


        public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Gui giochi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.add(new GeneralGui());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
        
        private GridBagConstraints setDimensionObj(final int gridx, final int gridy,    
                final int spacedown, final int spaceright, final int spaceleft) {       
            final GridBagConstraints c = new GridBagConstraints();        
            c.anchor = GridBagConstraints.PAGE_END;        
            c.insets = new Insets(0, spaceleft, spacedown, spaceright); 
            // terzo parametro definisce la distanza verticale     
            //(verso il basso) tra i vari oggetti della gui      
            c.gridx = gridx;       
            c.gridy = gridy;       
            return c;    
            }
}
	
	