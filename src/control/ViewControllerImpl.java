package control;

import view.gui.MainGui;
import view.gui.MenuManager;

public class ViewControllerImpl implements ViewController {
    
    private final MenuManager gui;
    
    public ViewControllerImpl() {
        this.gui = new MainGui(this);
    }
    
    
    

}
