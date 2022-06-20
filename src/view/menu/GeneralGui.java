package view.menu;

import account.AccountManager;
import account.AdvancedBalanceManagerImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.ImageLoader;
import view.MyGridBagConstraints;
import view.gui.MenuManager;


public class GeneralGui extends JPanel implements Menu {

    
    private final AccountManager account;
    private int fichesvalue = 1;
    private final JButton reset;
    private final JButton confirm;
    private final JLabel betValue = new JLabel("0€");
    private final JLabel winValue = new JLabel("0€");
    private final JLabel balanceValue = new JLabel("0€");
    final JLayeredPane win2 = new JLayeredPane();
    private final JLabel winmessage = new JLabel("");
    
    public GeneralGui(final MenuManager frame, final AccountManager account){
        this.account = account;
        setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        final int width = frame.getWidthMenu();
        final int height = frame.getHeightMenu();
        
        final JPanel north = new JPanel(new GridBagLayout());
        final JPanel south = new JPanel(new BorderLayout());
        final JPanel center = new JPanel();

        final JPanel southleft = new JPanel(new GridBagLayout());
        final JPanel southright = new JPanel(new GridBagLayout());
        
        //south.setPreferredSize(new Dimension((int) (dimx / 12.8), (int) (dimy / 7.2)));
        north.setPreferredSize(new Dimension((int) (width / 12.8), (int) (height / 7.2)));

        //North
        final JButton backToMenu = new JButton("Back to Menu");
        final JButton help = new JButton("?");
         
        //this.win2.setVisible(false);
        this.winmessage.setOpaque(false);
        this.winmessage.setForeground(Color.WHITE);
        this.winmessage.setHorizontalAlignment(SwingConstants.CENTER);
        this.winmessage.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, height / 25));
        
        
        win2.setPreferredSize(new Dimension(width / 4, height / 10));
        
        final JLabel backgrnd = new JLabel(new ImageIcon(ImageLoader.getImage("res/img/gui/ProvaSfondoLabel4.png")
                .getScaledInstance(width / 5, height / 12, Image.SCALE_SMOOTH)));
        backgrnd.setBounds(width / 50, height / 120, width / 5, height / 12);
        this.winmessage.setBounds(0, 0, width / 4, height / 10);
        
        win2.add(backgrnd, 1);
        win2.add(this.winmessage, 0);


        north.add(backToMenu, new MyGridBagConstraints(0, 0, 1, 1,
                new Insets(height / 30, width / 15, height / 30, 0)));
        
        north.add(win2, new MyGridBagConstraints(1, 0, 1, 1,
                new Insets(0, width / 5, 0, width / 5)));
        
        north.add(help, new MyGridBagConstraints(2, 0, 1, 1,
                new Insets(height / 30, 0, height / 30, width / 15)));
        
        final List<JButton> listbutton1 = new ArrayList<JButton>();
        listbutton1.add(help);
        listbutton1.add(backToMenu);
        
         
        //South
        final JLabel bet = new JLabel("BET");
        final JLabel balance = new JLabel("BALANCE");
        final JLabel win = new JLabel("WIN");

        final List<JLabel> listlabel = new ArrayList<>();
        
        listlabel.add(balance);
        listlabel.add(bet);
        listlabel.add(win);
        listlabel.add(this.balanceValue);
        listlabel.add(this.betValue);
        listlabel.add(this.winValue);
        
        int i = 0;
        int j = 0;
        for (final JLabel jb : listlabel) {
            jb.setForeground(Color.WHITE);
            jb.setHorizontalAlignment(SwingConstants.CENTER);
            jb.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, height / 40));
            southleft.add(jb, new MyGridBagConstraints(i, j, 1, 1, new Insets(0, height / 30, 0, height / 30)));
            if (i == 2) {
                i = 0;
                j++;
            } else {
                i++;
            }
        }
        
        this.reset = new JButton();
        this.confirm = new JButton();
        final JButton fiches1 = new JButton();
        final JButton fiches5 = new JButton();
        final JButton fiches25 = new JButton();
        final JButton fiches100 = new JButton();
        final JButton fiches500 = new JButton();
            
        final List<JButton> list = new ArrayList<>();
        list.add(this.reset);
        list.add(this.confirm);
        list.add(fiches1);
        list.add(fiches5);
        list.add(fiches25);
        list.add(fiches100);
        list.add(fiches500);
        
        final List<String> fichesList = new ArrayList<>();
        fichesList.add("cancel"); //da cambiare in pulsante reset
        fichesList.add("confirm"); //da cambiare in pulsante conferma
        fichesList.add("1");
        fichesList.add("5");
        fichesList.add("25");
        fichesList.add("100");
        fichesList.add("500");
        
        //Adding fiches
        i = 0;
        for (final JButton jb : list) { 
            jb.setOpaque(false);
            jb.setFocusPainted(false);
            jb.setBorderPainted(false);
            jb.setContentAreaFilled(false);
            jb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            
            //da fare bene
            if (i == 0 || i == 1) {
                jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/buttons/"
                        + fichesList.get(i) + ".png"))
                        .getScaledInstance(width / 20, width / 20, Image.SCALE_SMOOTH)));
            } else {
                jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/fiches/numbers/"
                        + fichesList.get(i) + ".png"))
                        .getScaledInstance(width / 20, width / 20, Image.SCALE_SMOOTH)));
            }

            if (i == 1) {
                southright.add(jb, new MyGridBagConstraints(i, 0, 1, 2, new Insets(0, 0, height / 100, height / 30)));
            } else {
                southright.add(jb, new MyGridBagConstraints(i, 0, 1, 2, new Insets(0, 0, height / 100, height / 100)));
            }
            i++;
        }
        
        //Adding Listener to set fiches value
        fiches1.addActionListener(e -> fichesvalue = 1);
        fiches5.addActionListener(e -> fichesvalue = 5);
        fiches25.addActionListener(e -> fichesvalue = 25);
        fiches100.addActionListener(e -> fichesvalue = 100);
        fiches500.addActionListener(e -> fichesvalue = 500);
        
        backToMenu.addActionListener(e -> frame.setMainMenu(account));

        //da vedere come migliorare
        north.setOpaque(false);
        center.setOpaque(false);
        south.setOpaque(false);
        southleft.setOpaque(false);
        southright.setOpaque(false);
        this.setOpaque(false);
        

        final JLayeredPane southtotal = new JLayeredPane();
        southtotal.setPreferredSize(new Dimension(width / 4, height / 10));
        southleft.setBounds(0, 0, width / 4, height / 10);
        
        final JLabel jl = new JLabel(new ImageIcon(ImageLoader.getImage("res/img/gui/ProvaSfondoLabel4.png")
                .getScaledInstance(width / 4, height / 10, Image.SCALE_SMOOTH)));
        jl.setBounds(0, 0, width / 4, height / 10);
        
        southtotal.add(southleft, 0);
        southtotal.add(jl, 1);

        //adding the panel to the Container 
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        south.add(southtotal, BorderLayout.WEST);
        south.add(southright, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        setBalanceValue();
        showButtons(false);
        setVisible(true);
    }
        
    public final JButton getResetButton() {
        return this.reset;
    }
    
    public final JButton getConfirmButton() {
        return this.confirm;
    }
    
    public final int getFichesValue() {
        return this.fichesvalue;
    }
        
    public void setBetValue(final double value) {
        betValue.setText(value + "€");
    }
        
    public void setWinValue(final double value) {
        winValue.setText(value + "€");
    }
        
    public void setBalanceValue() {
        balanceValue.setText(new AdvancedBalanceManagerImpl(this.account).getBalance() + "€");
    }
    
    public void showWinMessage(final boolean val, final double value) {
        //win2.setVisible(val);
        if (val) {
            setWinMessage(value);
        } else {
            winmessage.setText("");
        }
        
    }
    
    public void setWinMessage(final double value) {
        winmessage.setText("YOU WON " + value + "€!");
    }
    
    public void showButtons(final boolean val) {
        this.reset.setVisible(val);
        this.confirm.setVisible(val);
    }

    @Override
    public JPanel getMenu() {
        return this;
    }
}