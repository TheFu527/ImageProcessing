package homework1;

import java.awt.image.BufferedImage;

public class GrayImage {
	BufferedImage image;
	BufferedImage grayImg;
	int imgWidth;
	int imgHeight;

	GrayImage(BufferedImage img) {
		image = img;
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		changeToGray();
	}

	public BufferedImage getImg() {
		return grayImg;
	}

	private void changeToGray() {
		grayImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < imgWidth; i++) {
			for (int j = 0; j < imgHeight; j++) {
				int rgb = image.getRGB(i, j);
				int blue = (rgb & 0x000000FF) >> 0;
				int green = (rgb & 0x0000FF00) >> 8;
				int red = (rgb & 0x00FF0000) >> 16;
				int gray = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
				rgb = (255 << 24) | (gray << 16) | (gray << 8) | (gray);
				grayImg.setRGB(i, j, rgb);
			}
		}
	}

}
