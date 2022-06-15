package ex;

import java.awt.Dimension;
import java.awt.Image;

public class ExImageModifier {

    //given the image, and the size of the button / label it will scale based on your monitor
    public Image scale(final Image image, final Dimension size) {
        final double scaleFactor = Math.min(1d,
                getScaleFactorToFit(new Dimension(image.getWidth(null), image.getHeight(null)), size));
        
        final int scaleWidth = (int) Math.round(image.getWidth(null) * scaleFactor);
        final int scaleHeight = (int) Math.round(image.getHeight(null) * scaleFactor);
        System.out.println(scaleWidth);
        System.out.println(scaleHeight);
        return image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
    }

    private double getScaleFactor(final int iMasterSize, final int iTargetSize) {
        double dScale = 1;
        if (iMasterSize > iTargetSize) {
            dScale = (double) iTargetSize / (double) iMasterSize;
        } else {
            dScale = (double) iTargetSize / (double) iMasterSize;
        }
        return dScale;
    }
    
    private double getScaleFactorToFit(final Dimension original, final  Dimension toFit) {
        double dScale = 1d;
        if (original != null && toFit != null) {
            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);
            dScale = Math.min(dScaleHeight, dScaleWidth);
        }
        return dScale;
    }
    
    //given an image and the size of the button / label it will fill the 
    public Image scaleFullScreen(final Image img, final Dimension size) {
        return img.getScaledInstance((int) (size.getWidth()), (int) (size.getHeight()), Image.SCALE_DEFAULT);
    }
 
}
