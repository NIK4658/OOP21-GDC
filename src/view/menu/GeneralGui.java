package view.menu;

import account.AccountManager;
import baccarat.BaccaratGui;
import view.menu.games.Game.Games;
import view.menu.games.GuideGui;
import view.menu.games.roulette.RouletteGame;
import account.AdvancedBalanceManagerImpl;
import account.BalanceManager;
import blackjack.BlackJackGui;

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
import view.menu.games.Game;


public class GeneralGui extends JPanel implements Menu {
    
    private final AccountManager account;
    private final BalanceManager Balanceaccount;
    private double bet;
    private int fichesvalue = 1;
    private final Game g;
    private final JButton reset;
    private final JButton confirm;
    private final JButton fiches1;
    private final JButton fiches5;
    private final JButton fiches25;
    private final JButton fiches100;
    private final JButton fiches500;
    private final int width;
    private final int height;
    private final JLabel betValue = new JLabel("0€");
    private final JLabel winValue = new JLabel("0€");
    private final JLabel balanceValue = new JLabel("0€");
    private final JLayeredPane winmsg = new JLayeredPane();
    private final JLabel winMessageText = new JLabel("");
    
    public GeneralGui(final MenuManager frame, final AccountManager account, final Games game ){

        this.account = account;
        this.Balanceaccount = new AdvancedBalanceManagerImpl(this.account);
        setLayout(new BorderLayout());
        this.setPreferredSize(frame.getSizeMenu());
        this.width = frame.getWidthMenu();
        this.height = frame.getHeightMenu();
        final JPanel north = new JPanel(new GridBagLayout());
        final JPanel south = new JPanel(new BorderLayout());
        final JPanel southleft = new JPanel(new GridBagLayout());
        final JPanel southright = new JPanel(new GridBagLayout());
        JPanel center = new JPanel();
        this.reset = new JButton();
        this.confirm = new JButton();
        southleft.setOpaque(false);
        north.setOpaque(false);
        center.setOpaque(false);
        south.setOpaque(false);
        southright.setOpaque(false);
        this.setOpaque(false);
        switch (game) {
            case BLACKJACK: 
                this.g = new BlackJackGui(frame, new AdvancedBalanceManagerImpl(account), this);
                showButtons(false);
                break;
            case BACCARAT: this.g = new BaccaratGui(frame, new AdvancedBalanceManagerImpl(account), this);
                break;
            default: 
                this.g = new RouletteGame(this, game);
                center = (JPanel) this.g;
                this.setOpaque(true);
                this.setBackground(new Color(0, 118, 58));
        }
        north.setPreferredSize(new Dimension((int) (width / 12.8), (int) (height / 7.2)));
        final JButton backToMenu = new JButton("Back to Menu");
        final JButton help = new JButton("?");
        this.winMessageText.setOpaque(false);
        this.winMessageText.setForeground(Color.WHITE);
        this.winMessageText.setHorizontalAlignment(SwingConstants.CENTER);
        this.winMessageText.setFont(new Font("Arial", Font.PLAIN | Font.ITALIC, height / 25));
        winmsg.setPreferredSize(new Dimension(width / 4, height / 10));
        final JLabel backgrnd = new JLabel(new ImageIcon(ImageLoader.getImage("res/img/gui/ProvaSfondoLabel4.png")
                .getScaledInstance(width / 5, height / 12, Image.SCALE_SMOOTH)));
        backgrnd.setBounds(width / 50, height / 120, width / 5, height / 12);
        this.winMessageText.setBounds(0, 0, width / 4, height / 10);
        winmsg.add(backgrnd, 1);
        winmsg.add(this.winMessageText, 0);
        north.add(backToMenu, new MyGridBagConstraints(0, 0, 1, 1,
                new Insets(height / 30, width / 15, height / 30, 0)));
        north.add(winmsg, new MyGridBagConstraints(1, 0, 1, 1,
                new Insets(0, width / 5, 0, width / 5)));
        north.add(help, new MyGridBagConstraints(2, 0, 1, 1,
                new Insets(height / 30, 0, height / 30, width / 15)));
        final List<JButton> listbutton1 = new ArrayList<JButton>();
        listbutton1.add(help);
        listbutton1.add(backToMenu);
        for (final JButton jb : listbutton1) {
            jb.setContentAreaFilled(false);
            jb.setOpaque(true);
            jb.setBackground(new Color(204, 208, 222));
            jb.setBorderPainted(true); 
            jb.setFocusPainted(false);
            final int BordSize = width / 640;
            jb.setBorder(BorderFactory
                    .createMatteBorder(BordSize, BordSize, BordSize, BordSize, new Color(90, 106, 173)));
            jb.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, height / 40));
        }
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
        this.fiches1 = new JButton();
        this.fiches5 = new JButton();
        this.fiches25 = new JButton();
        this.fiches100 = new JButton();
        this.fiches500 = new JButton();
        this.reset.setName("cancel");
        this.confirm.setName("confirm");
        fiches1.setName("1new");
        fiches5.setName("5new");
        fiches25.setName("25new");
        fiches100.setName("100new");
        fiches500.setName("500new");  
        final List<JButton> list = new ArrayList<>();
        list.add(this.reset);
        list.add(this.confirm);
        list.add(fiches1);
        list.add(fiches5);
        list.add(fiches25);
        list.add(fiches100);
        list.add(fiches500);
        i = 0;
        for (final JButton jb : list) { 
            jb.setOpaque(false);
            jb.setFocusPainted(false);
            jb.setBorderPainted(false);
            jb.setContentAreaFilled(false);
            jb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            if (i <= 1) {
                jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/buttons/"
                        + jb.getName() + ".png"))
                        .getScaledInstance(width / 20, width / 20, Image.SCALE_SMOOTH)));
                if (i == 0) {
                    southright.add(jb, 
                            new MyGridBagConstraints(i, 0, 1, 2, new Insets(0, 0, height / 200, height / 100)));
                } else {
                    southright.add(jb, 
                            new MyGridBagConstraints(i, 0, 1, 2, new Insets(0, 0, height / 200, height / 30)));
                }
            } else {
                jb.setIcon(new ImageIcon((ImageLoader.getImage("res/img/fiches/numbers/new/"
                        + jb.getName() + ".png"))
                        .getScaledInstance(width / 15, width / 15, Image.SCALE_SMOOTH)));
                southright.add(jb, new MyGridBagConstraints(i, 0, 1, 2, new Insets(0, 0, height / 100, 0)));
            }
            i++;
        }
        this.reset.addActionListener(e -> {
            Balanceaccount.deposit(this.bet);
            this.g.resetBet();
            this.bet = 0;
            updateBalanceValue();
            this.setBetValue(this.bet);
        });
        this.confirm.addActionListener(e -> this.g.confirmBet());
        backToMenu.addActionListener(e -> frame.setMainMenu(account));
        help.addActionListener(e -> new GuideGui(frame.getSizeMenu(), game));
        fiches1.addActionListener(e -> {
            setSelectedFiches(0);
            fichesvalue = 1;
        });
        fiches5.addActionListener(e -> {
            setSelectedFiches(1);
            fichesvalue = 5;
        });
        fiches25.addActionListener(e -> {
            setSelectedFiches(2);
            fichesvalue = 25;
        });
        fiches100.addActionListener(e -> {
            setSelectedFiches(3);
            fichesvalue = 100;
        });
        fiches500.addActionListener(e -> {
            setSelectedFiches(4);
            fichesvalue = 500;
        });
        final JLayeredPane southtotal = new JLayeredPane();
        southtotal.setPreferredSize(new Dimension((int) (width / 3.5) + width / 60, height / 10));
        southleft.setBounds(width / 60, 0, (int) (width / 3.5), height / 10);
        final JLabel bckgnd = new JLabel(new ImageIcon(ImageLoader.getImage("res/img/gui/ProvaSfondoLabel4.png")
                .getScaledInstance((int) (width / 3.5), height / 10, Image.SCALE_SMOOTH)));
        bckgnd.setBounds(width / 60, 0, (int) (width / 3.5), height / 10);
        southtotal.add(bckgnd, 1);
        southtotal.add(southleft, 0);
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        south.add(southtotal, BorderLayout.WEST);
        south.add(southright, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);
        setSelectedFiches(0);
        updateBalanceValue();
        this.setMinimumSize(this.getPreferredSize());
        setVisible(true);
    }

    public final int getFichesValue() {
        return this.fichesvalue;
    }
    
    public boolean addBetValue(final double value) {
        if (Balanceaccount.withdraw(value)) {
            this.bet += value;
            setBetValue(this.bet);
            updateBalanceValue();
            return true;
        }
        return false;
    }
    
    public void setBetValue(final double value) {
        betValue.setText(valueToText(value) + "€");
    }
        
    public void setSelectedFiches(final int fichesvalue) {
        final ArrayList<JButton> list = new ArrayList<>();
        list.add(fiches1);
        list.add(fiches5);
        list.add(fiches25);
        list.add(fiches100);
        list.add(fiches500);
        int i = 0;
        String s;
        for (final JButton jb : list) {
            if (fichesvalue == i) {
                s = jb.getName() + "high";
            } else {
                s = jb.getName();
            }
            jb.setIcon(new ImageIcon((ImageLoader
                    .getImage("res/img/fiches/numbers/new/" + s + ".png"))
                    .getScaledInstance(width / 15, width / 15, Image.SCALE_SMOOTH)));
            i++;
        }
    }
    
    public void showButtons(final boolean val) {
        this.reset.setVisible(val);
        this.confirm.setVisible(val);
    }

    public void updateBalanceValue() {
        balanceValue.setText(valueToText(Balanceaccount.getBalance()) + "€");
    }
    
    public void showWinMessage(final double value) {
        if (value > 0) {
            setWinMessage(value);
            Balanceaccount.deposit(value);
            this.updateBalanceValue();
        } else {
            winMessageText.setText("");
        }
        setWinValue(value);
        this.bet = 0;
        this.setBetValue(this.bet);
    }
    
    public void setWinValue(final double value) {
        winValue.setText(valueToText(value) + "€");
    }
    
    private void setWinMessage(final double value) {
        winMessageText.setText("YOU WON " + valueToText(value) + "€!");
    }
    
    private String valueToText(final double value) {
        return (value * 100 % 100 == 0 ? String.valueOf((int) value) : String.valueOf(value));
    }

    @Override
    public JPanel getMenu() {
        return this;
    }
    
    public JPanel getGame() {
        return (JPanel) this.g;
    }
}