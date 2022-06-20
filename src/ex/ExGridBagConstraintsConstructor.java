package ex;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ExGridBagConstraintsConstructor {
    
    public static GridBagConstraints get(final int gridx, final int gridy, final int space) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, space, 0);
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }

    public static GridBagConstraints get(final int gridx, final int gridy, final int spacedown,
            final int spaceleft, final int spaceright) {
        final GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0, spaceleft, spacedown, spaceright); //terzo parametro definisce la distanza verticale
        //(verso il basso) tra i vari oggetti della gui
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
    
}
