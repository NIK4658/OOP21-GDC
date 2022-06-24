package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

//renderla statica?
public class MyGridBagConstraints extends GridBagConstraints {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MyGridBagConstraints(final int gridx, final int gridy, final int gridwidth, 
            final int gridheight, final Insets insets, final int ipadx, final int ipady) {
        super(gridx, gridy, gridwidth, gridheight, 1, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, ipadx, ipady);
    }
    
    public MyGridBagConstraints(final int gridx, final int gridy, final Insets insets, final int fill) {
        super(gridx, gridy, 1, 1, 1, 1, GridBagConstraints.CENTER, fill, insets, 0, 0);
    }
    
    public MyGridBagConstraints(final int gridx, final int gridy, final int gridwidth,
            final int gridheight, final int ipadx, final int ipady) {
        this(gridx, gridy, gridwidth, gridheight, new Insets(0, 0, 0, 0), ipadx, ipady);
    }
    
    public MyGridBagConstraints(final int gridx, final int gridy, final int gridwidth,
            final int gridheight, final Insets insets) {
        this(gridx, gridy, gridwidth, gridheight, insets, 0, 0);
    }
    
    public MyGridBagConstraints(final int gridx, final int gridy, final int gridwidth,
            final int gridheight) {
        this(gridx, gridy, gridwidth, gridheight, 0, 0);
    }
    
    public MyGridBagConstraints(final int gridx, final int gridy) {
        this(gridx, gridy, 1, 1, 0, 0);
    }
}
