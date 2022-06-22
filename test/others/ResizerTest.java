package others;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.junit.Test;
import view.Utilities;

public class ResizerTest {
    
    @Test
    public void testResize() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        for (float factor = 0.1f; factor <= 2; factor += 0.1) {
            final Dimension dim = Utilities.resize(factor);
            assertEquals(dim.width, Math.round(screen.width / factor), 0);
            assertEquals(dim.height, Math.round(screen.height / factor), 0);
        }
        final Dimension dimFull = Utilities.resize(1f);
        assertEquals(dimFull, screen);   
    }

}
