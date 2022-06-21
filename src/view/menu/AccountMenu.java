package view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import account.AccountManager;
import view.MyGridBagConstraints;
import view.gui.MenuManager;
import view.menu.account.BalancePanel;
import view.menu.account.ConfirmPassword;
import view.menu.account.PasswordPanel;
import view.menu.account.UsernamePanel;
//vedere se possibile sostituire panel con estensione della classe con JPanel
//e se possibile creare un'altra classe per panelAccount.
public class AccountMenu implements Menu {

    
    private static final int SCALE_TITLE = 7;
    private static final int SCALE_BUTTON = 15;
    private static final int SPACE_TITLE = 15;
    private static final int SPACE_BUTTON = 30;
//    private static final Font font = new Font("Arial", Font.BOLD, 1);
    
    
    private final MenuManager frame;
    private final AccountManager account;
    private final JPanel panel;
    private final JPanel panelAccount;
    private final JButton buttonBack;
    private final ActionListener alBackMenu;
    private ActionListener alBackPanel;
    

    
    public AccountMenu(final MenuManager frame, final AccountManager account) {
        this.frame = frame;
        this.account = account;
        final Dimension dimension = frame.getSizeMenu();
        final int width = dimension.width;
        final int height = dimension.height;
        final int minSize = Math.min(width, height);
        this.panel = new JPanel(new BorderLayout());
        this.panel.setPreferredSize(this.frame.getSizeMenu());
        this.buttonBack = new JButton("BACK");
        this.buttonBack.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
        this.alBackMenu = this.getActionListenerBackMenu();
        this.buttonBack.addActionListener(alBackMenu);
        this.panel.add(buttonBack, BorderLayout.SOUTH);
        
//pannello PRINCIPALE
        this.panelAccount = new JPanel(new GridBagLayout());
        this.panelAccount.setBackground(new Color(68, 87, 96));
        this.panelAccount.setPreferredSize(frame.getSizeMenu());
        this.panel.add(panelAccount);

        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, minSize / SCALE_TITLE));
        int index = 0;
        this.panelAccount.add(title, this.gridBagConstraints(index++, height / SPACE_TITLE));
        final JButton buttonBalance = new JButton("BALANCE");
        final JButton buttonUsername = new JButton("CHANGE USERNAME");
        final JButton buttonPassword = new JButton("CHANGE PASSWORD");
        final JButton buttonAccount = new JButton("DELETE ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(buttonBalance);
        list.add(buttonUsername);
        list.add(buttonPassword);
        list.add(buttonAccount);
        for (final JButton jb : list) {
//            jb.setPreferredSize(new Dimension(frame.getWidthMenu() / 2, frame.getHeightMenu() / 12));
            jb.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
            panelAccount.add(jb, this.gridBagConstraints(index++, height / SPACE_BUTTON));//creare costante poi modificare solo l'index
        }

        buttonBalance.addActionListener(e -> {
            this.updatePanel(new BalancePanel(this.account, minSize));
        });

        buttonUsername.addActionListener(e -> {
            this.updatePanel(new UsernamePanel(this.frame.getFrame(), this.account, minSize));
        });        

        buttonPassword.addActionListener(e -> {
            this.updatePanel(new PasswordPanel(this.frame.getFrame(), this.account, minSize));
        });
        
        buttonAccount.addActionListener(e -> {
            if (new ConfirmPassword(frame.getFrame(), account, " to delete Account", minSize).isPasswordConfirmed()) {
                this.account.deleteAcc(this.account.getUsr());
                this.frame.setAccessMenu();
            }
        });
        
    }
    
    private GridBagConstraints gridBagConstraints(final int gridy, final int spacedown) {
        final GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.insets = new Insets(0, 0, spacedown, 0);
        c.fill = GridBagConstraints.BOTH;
        return c;
    }
    
    private void updatePanel(final JPanel panelToAdd) {
        this.changePanel(panelToAdd, this.panelAccount);
        this.alBackPanel = getActionListenerBackPanel(panelToAdd);
        changeActionListenerButtonBack(this.alBackPanel, this.alBackMenu);
    }
    
    private void changePanel(final JPanel panelToAdd, final JPanel panelToRemove) {
        this.panel.remove(panelToRemove);
        this.panel.add(panelToAdd);
        this.panel.revalidate();
        this.panel.repaint();
    }
    
    private ActionListener getActionListenerBackPanel(final JPanel panelAdded) {
        return e -> {
            this.changePanel(this.panelAccount, panelAdded);
            this.changeActionListenerButtonBack(this.alBackMenu, this.alBackPanel);
        };
    }
    
    private void changeActionListenerButtonBack(final ActionListener alToAdd, final ActionListener alToRemove) {
        this.buttonBack.addActionListener(alToAdd);
        this.buttonBack.removeActionListener(alToRemove);
    }
    
    private ActionListener getActionListenerBackMenu() {
        return e -> frame.setMainMenu(account);
    }

    @Override
    public JPanel getMenu() {
        return this.panel;
    }
    
}
/*Appunti*/
//final NumberFormat format = NumberFormat.getNumberInstance();
//final NumberFormat format = NumberFormat.getCurrencyInstance();
//final NumberFormat format = DecimalFormat.getCurrencyInstance();
//fieldRicarica.setName("Ricarica");
//fieldRicarica.setEditable(false); 
//labelRicarica.setLabelFor(fieldRicarica);