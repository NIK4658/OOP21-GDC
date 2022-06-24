package start;

import controller.ViewControllerImpl;
import view.gui.MainGui;

/**
 * Starts the application.
 */
public final class ApplicationStart {
    public static void main(final String[] args) {
        //new ViewControllerImpl().StartGame();
        new MainGui();
    }
}
