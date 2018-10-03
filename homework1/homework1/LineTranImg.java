package homework1;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class LineTranImg {
	BufferedImage image;
	BufferedImage lineTranImg;
	int imgWidth;
	int imgHeight;
	int c, d;

	LineTranImg(BufferedImage img, int c, int d) throws InterruptedException {
		image = img;
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		this.c = c;
		this.d = d;
		lineTrans();
	}

	public BufferedImage getImg() {
		return lineTranImg;
	}

	private void lineTrans() throws InterruptedException {
		int[] pixels = new int[imgWidth * imgHeight];
		int[] histogram = new int[256];
		lineTranImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
		PixelGrabber pg = new PixelGrabber(image, 0, 0, imgWidth, imgHeight, pixels, 0, imgWidth);
		pg.grabPixels();
		for (int i = 0; i < imgHeight - 1; i++) {
			for (int j = 0; j < imgWidth - 1; j++) {
				int gray = pixels[i * imgWidth + j] & 0xFF;
				histogram[gray]++;
			}
		}
		int a = 255;
		int b = 0;
		for (int i = 0; i < 256; i++) {
			if (histogram[i] > 0) {
				if (i < a) {
					a = i;
				}
				if (i > b) {
					b = i;
				}
			}
		}
		double k = (double) (d - c) / (b - a);
		for (int i = 0; i < imgHeight; i++) {
			for (int j = 0; j < imgWidth; j++) {
				int gray = pixels[i * imgWidth + j] & 0xFF;
				int rgb;
				gray = (int) (k * (gray - a) + c);
				rgb = (255 << 24) | (gray << 16) | (gray << 8) | (gray);
				lineTranImg.setRGB(j, i, rgb);
			}
		}

	}
}
