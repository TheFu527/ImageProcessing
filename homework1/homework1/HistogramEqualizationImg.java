package homework1;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class HistogramEqualizationImg {
	BufferedImage image;
	BufferedImage hisEqualImg;
	int imgWidth;
	int imgHeight;

	HistogramEqualizationImg(BufferedImage img) throws InterruptedException {
		image = img;
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		histogramEqualization();
	}

	public BufferedImage getImg() {
		return hisEqualImg;
	}

	private void histogramEqualization() throws InterruptedException {
		int[] histogram = new int[256];
		int[] pixels = new int[imgWidth * imgHeight];
		hisEqualImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
		PixelGrabber pg = new PixelGrabber(image, 0, 0, imgWidth, imgHeight, pixels, 0, imgWidth);
		pg.grabPixels();
		for (int i = 0; i < imgHeight - 1; i++) {
			for (int j = 0; j < imgWidth - 1; j++) {
				int gray = pixels[i * imgWidth + j] & 0xFF;
				histogram[gray]++;
			}
		}
		double divPiNum = (double) 255 / (imgWidth * imgHeight);
		double[] Sk = new double[256];
		Sk[0] = (double) histogram[0] * divPiNum;
		for (int i = 1; i < 256; i++) {
			Sk[i] = Sk[i - 1] + histogram[i] * divPiNum;
		}
		for (int i = 0; i < imgHeight; i++) {
			for (int j = 0; j < imgWidth; j++) {
				int gray = pixels[i * imgWidth + j] & 0xFF;
				int newGray = ((int) Sk[gray]);
				int rgb = (255 << 24) | (newGray << 16) | (newGray << 8) | (newGray);
				hisEqualImg.setRGB(j, i, rgb);
			}
		}
	}
}
