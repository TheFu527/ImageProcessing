package homework1;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class GrayStretchImg {
	BufferedImage image;
	BufferedImage grayStrImg;
	int imgWidth;
	int imgHeight;
	int a, b, c, d;

	GrayStretchImg(BufferedImage img, int a, int b, int c, int d) throws InterruptedException {
		image = img;
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		grayStretch();
	}

	public BufferedImage getImg() {
		return grayStrImg;
	}

	private void grayStretch() throws InterruptedException {
		int[] pixels = new int[imgWidth * imgHeight];
		grayStrImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
		PixelGrabber pg = new PixelGrabber(image, 0, 0, imgWidth, imgHeight, pixels, 0, imgWidth);
		pg.grabPixels();
		for (int i = 0; i < imgHeight; i++) {
			for (int j = 0; j < imgWidth; j++) {
				int gray = pixels[i * imgWidth + j] & 0xFF;
				int rgb;
				double k1, k2, k3;
				k1 = (double) c / a;
				k2 = (double) (d - c) / (b - a);
				k3 = (double) (255 - d) / (255 - b);
				if (gray < a) {
					gray = (int) (k1 * gray);
				} else if (gray >= a && gray < b) {
					gray = (int) (k2 * (gray - a) + c);
				} else if (gray >= b) {
					gray = (int) (k3 * (gray - b) + d);
				}
				rgb = (255 << 24) | (gray << 16) | (gray << 8) | (gray);
				grayStrImg.setRGB(j, i, rgb);
			}
		}
	}
}
