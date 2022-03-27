package view;

import java.awt.*;

public class ImageModifier {

	//given the image, and the size of the button / label it will scale based on your monitor
	public Image scale(Image image, Dimension size) {
		double scaleFactor = Math.min(1d,
				getScaleFactorToFit(new Dimension(image.getWidth(null), image.getHeight(null)), size));

		int scaleWidth = (int) Math.round(image.getWidth(null) * scaleFactor);
		int scaleHeight = (int) Math.round(image.getHeight(null) * scaleFactor);

		return image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
	}

	private double getScaleFactor(int iMasterSize, int iTargetSize) {
		double dScale = 1;
		if (iMasterSize > iTargetSize) {
			dScale = (double) iTargetSize / (double) iMasterSize;
		} else {
			dScale = (double) iTargetSize / (double) iMasterSize;
		}
		return dScale;
	}

	private double getScaleFactorToFit(Dimension original, Dimension toFit) {
		double dScale = 1d;
		if (original != null && toFit != null) {
			double dScaleWidth = getScaleFactor(original.width, toFit.width);
			double dScaleHeight = getScaleFactor(original.height, toFit.height);
			dScale = Math.min(dScaleHeight, dScaleWidth);
		}
		return dScale;
	}

	//given an image and the size of the button / label it will fill the 
	public Image scaleFullScreen(Image img, Dimension size) {
		return img.getScaledInstance((int) (size.getWidth()), (int) (size.getHeight()), Image.SCALE_DEFAULT);
	}

}
