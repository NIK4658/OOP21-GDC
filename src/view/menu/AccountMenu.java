package view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.account.AccountManager;
import view.gui.MenuManager;
import view.menu.account.AccountPanel;
import view.menu.account.BalancePanel;
import view.menu.account.ConfirmPassword;
import view.menu.account.PasswordPanel;
import view.menu.account.UsernamePanel;

/**
 * Account menu manages account-related iterations such as withdrawals, deposits, 
 * username change, password and account deletion.
 */
public class AccountMenu implements Menu {

    private static final int SCALE_TITLE = 7;
    private static final int SPACE_TITLE = 15;
    private static final int SCALE_BUTTON = 15;
    private static final int SPACE_BUTTON = 30;
    private final MenuManager menuManager;
    private final AccountManager account;
    private final JPanel panel;
    private final JPanel accountPanel;
    private final JButton backButton;
    private final ActionListener backMenuAl;
    private ActionListener backPanelAl;
    
    /**
     * Create an account menu which manages operation about the specif account passed as an argument.
     * 
     * @param menuManager 
     * 
     * @param account
     * 
     */
    public AccountMenu(final MenuManager menuManager, final AccountManager account) {
        final int width = menuManager.getWidthMenu();
        final int height = menuManager.getHeightMenu();
        final int minSize = Math.min(width, height);
        this.account = account;
        this.menuManager = menuManager;
        this.panel = new JPanel(new BorderLayout());
        this.panel.setPreferredSize(menuManager.getSizeMenu());
        this.backButton = new JButton("BACK");
        this.backButton.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
        this.backMenuAl = this.getActionListenerBackMenu();
        this.backButton.addActionListener(backMenuAl);
        this.panel.add(backButton, BorderLayout.SOUTH);
        
        //Account panel
        this.accountPanel = new AccountPanel(minSize);
        this.panel.add(this.accountPanel);
        final JLabel title = new JLabel("ACCOUNT", SwingConstants.CENTER);
        final JButton balanceButton = new JButton("BALANCE");
        final JButton usernameButton = new JButton("CHANGE USERNAME");
        final JButton passwordButton = new JButton("CHANGE PASSWORD");
        final JButton accountButton = new JButton("DELETE ACCOUNT");
        final List<JButton> list = new LinkedList<>();
        list.add(balanceButton);
        list.add(usernameButton);
        list.add(passwordButton);
        list.add(accountButton);
        int index = 0;
        this.accountPanel.add(title, this.gridBagConstraints(index, height / SPACE_TITLE));
        index++;
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, minSize / SCALE_TITLE));
        for (final JButton b : list) {
            accountPanel.add(b, this.gridBagConstraints(index, height / SPACE_BUTTON));
            b.setFont(new Font("Arial", Font.PLAIN, minSize / SCALE_BUTTON));
            index++;
        }
        
        balanceButton.addActionListener(e -> {
            this.updatePanel(new BalancePanel(this.account, minSize));
        });

        usernameButton.addActionListener(e -> {
            this.updatePanel(new UsernamePanel(menuManager.getFrame(), this.account, minSize));
        });        

        passwordButton.addActionListener(e -> {
            this.updatePanel(new PasswordPanel(menuManager.getFrame(), this.account, minSize));
        });
        
        accountButton.addActionListener(e -> {
            if (new ConfirmPassword(menuManager.getFrame(), this.account, minSize).isConfirmed()) {
                this.account.deleteAcc(this.account.getUsr());
                this.menuManager.setAccessMenu(this.account);
            }
        });
        
    }
   
    private void updatePanel(final JPanel addPanel) {
        this.changePanel(addPanel, this.accountPanel);
        this.backPanelAl = getActionListenerBackPanel(addPanel);
        changeActionListenerButtonBack(this.backPanelAl, this.backMenuAl);
    }
    
    private void changePanel(final JPanel addPanel, final JPanel removePanel) {
        this.panel.remove(removePanel);
        this.panel.add(addPanel);
        this.panel.revalidate();
        this.panel.repaint();
    }
    
    private void changeActionListenerButtonBack(final ActionListener addAl, final ActionListener removeAl) {
        this.backButton.addActionListener(addAl);
        this.backButton.removeActionListener(removeAl);
    }
    
    private ActionListener getActionListenerBackPanel(final JPanel addedPanel) {
        return e -> {
            this.changePanel(this.accountPanel, addedPanel);
            this.changeActionListenerButtonBack(this.backMenuAl, this.backPanelAl);
        };
    }

    private ActionListener getActionListenerBackMenu() {
        return e -> this.menuManager.setMainMenu(this.account);
    }
    
    private GridBagConstraints gridBagConstraints(final int gridy, final int spacedown) {
        final GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.insets = new Insets(0, 0, spacedown, 0);
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    @Override
    public JPanel getMenu() {
        return this.panel;
    }
    
}