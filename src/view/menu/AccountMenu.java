package view.menu;

import account.AccountManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.GridBagConstraintsConstructor;
import view.gui.MenuManager;
import view.menu.account.BalancePanel;
import view.menu.account.ConfirmPassword;
import view.menu.account.PasswordPanel;
import view.menu.account.UsernamePanel;
//vedere se possibile sostituire panel con estensione della classe con JPanel
//e se possibile creare un'altra classe per panelAccount.
public class AccountMenu implements Menu {

    /**
     * 
     **/
    private static final long serialVersionUID = 1L;
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
        this.panel = new JPanel(new BorderLayout());
        this.panel.setPreferredSize(this.frame.getSizeMenu());
        this.buttonBack = new JButton("BACK");
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
        title.setFont(new Font("Arial", Font.BOLD, frame.getWidthMenu() / 20));
        int index = 0;
        this.panelAccount.add(title, GridBagConstraintsConstructor.get(0, index++, 40));
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
            jb.setPreferredSize(new Dimension(frame.getWidthMenu() / 4, frame.getHeightMenu() / 20));
            jb.setFont(new Font("Arial", Font.PLAIN, frame.getWidthMenu() / 50));
            panelAccount.add(jb, GridBagConstraintsConstructor.get(0, index++, 5));
        }
        
        final BalancePanel panelBalance = new BalancePanel(this.account);
        final UsernamePanel panelUsername = new UsernamePanel(this.frame.getFrame(), this.account);
        final PasswordPanel panelPassword = new PasswordPanel(this.frame.getFrame(), this.account);

        buttonBalance.addActionListener(e -> {
            this.updatePanel(panelBalance);
        });

        buttonUsername.addActionListener(e -> {
            this.updatePanel(panelUsername);
        });        

        buttonPassword.addActionListener(e -> {
            this.updatePanel(panelPassword);
        });
        
        buttonAccount.addActionListener(e -> {
            if (new ConfirmPassword(frame.getFrame(), account, " to delete Account").isPasswordConfirmed()) {
                this.frame.setAccessMenu();
            }
        });
        
    }
    
    private void updatePanel(final JPanel panelToAdd) {
        this.changePanel(panelToAdd, this.panelAccount);
        this.alBackPanel = getActionListenerBackPanel(panelToAdd);
        changeActionListenerButtonBack(this.alBackPanel, this.alBackMenu);
    }
    
    private void changePanel(final JPanel panelToAdd, final JPanel panelToRemove) {
        this.panel.remove(panelToRemove);
        this.panel.add(panelToAdd);
        this.panel.revalidate();//controllare se serve
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
        return e -> frame.setGamesMenu(account);
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