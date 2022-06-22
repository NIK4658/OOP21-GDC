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
import view.menu.account.AccountPanel;
import view.menu.account.BalancePanel;
import view.menu.account.ConfirmPassword;
import view.menu.account.PasswordPanel;
import view.menu.account.UsernamePanel;
//vedere se possibile sostituire panel con estensione della classe con JPanel
//e se possibile creare un'altra classe per panelAccount.
public class AccountMenu implements Menu {

    private static final int SCALE_TITLE = 7;
    private static final int SPACE_TITLE = 15;
    private static final int SCALE_BUTTON = 15;
    private static final int SPACE_BUTTON = 30;  
    public static final Color COLOR_BACKGROUND = new Color(68, 87, 96);
    private final MenuManager frame;
    private final AccountManager account;
    private final JPanel panel;
    private final JPanel accountPanel;
    private final JButton backButton;
    private final ActionListener backMenuAl;
    private ActionListener alBackPanel;
    
    
    public AccountMenu(final MenuManager frame, final AccountManager account) {
        this.frame = frame;
        this.account = account;
        final int width = frame.getWidthMenu();
        final int height = frame.getHeightMenu();
        final int minSize = Math.min(width, height);
        this.panel = new JPanel(new BorderLayout());
        this.panel.setPreferredSize(this.frame.getSizeMenu());
        this.backButton = new JButton("BACK");
        this.backButton.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
        this.backMenuAl = this.getActionListenerBackMenu();
        this.backButton.addActionListener(backMenuAl);
        this.panel.add(backButton, BorderLayout.SOUTH);
        
//pannello PRINCIPALE
        this.accountPanel = new AccountPanel(minSize);
        this.panel.add(accountPanel);
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        int index = 0;
        this.accountPanel.add(title, this.gridBagConstraints(index++, height / SPACE_TITLE));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, minSize / SCALE_TITLE));
        final JButton balanceButton = new JButton("BALANCE");
        final JButton usernameButton = new JButton("CHANGE USERNAME");
        final JButton passwordButton = new JButton("CHANGE PASSWORD");
        final JButton accountButton = new JButton("DELETE ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(balanceButton);
        list.add(usernameButton);
        list.add(passwordButton);
        list.add(accountButton);
        for (final JButton jb : list) {
            accountPanel.add(jb, this.gridBagConstraints(index++, height / SPACE_BUTTON));//creare costante poi modificare solo l'index
            jb.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
        }
        balanceButton.addActionListener(e -> {
            this.updatePanel(new BalancePanel(this.account, minSize));
        });

        usernameButton.addActionListener(e -> {
            this.updatePanel(new UsernamePanel(this.frame.getFrame(), this.account, minSize));
        });        

        passwordButton.addActionListener(e -> {
            this.updatePanel(new PasswordPanel(this.frame.getFrame(), this.account, minSize));
        });
        
        accountButton.addActionListener(e -> {
            if (new ConfirmPassword(frame.getFrame(), account, minSize).isConfirmed()) {
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
        this.changePanel(panelToAdd, this.accountPanel);
        this.alBackPanel = getActionListenerBackPanel(panelToAdd);
        changeActionListenerButtonBack(this.alBackPanel, this.backMenuAl);
    }
    
    private void changePanel(final JPanel panelToAdd, final JPanel panelToRemove) {
        this.panel.remove(panelToRemove);
        this.panel.add(panelToAdd);
        this.panel.revalidate();
        this.panel.repaint();
    }
    
    private ActionListener getActionListenerBackPanel(final JPanel panelAdded) {
        return e -> {
            this.changePanel(this.accountPanel, panelAdded);
            this.changeActionListenerButtonBack(this.backMenuAl, this.alBackPanel);
        };
    }
    
    private void changeActionListenerButtonBack(final ActionListener alToAdd, final ActionListener alToRemove) {
        this.backButton.addActionListener(alToAdd);
        this.backButton.removeActionListener(alToRemove);
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