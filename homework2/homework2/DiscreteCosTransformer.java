package homework2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;

public class DiscreteCosTransformer {
	Image im;
	BufferedImage imageAuth = null;
	int iw;
	int ih;
	int[] pixels;

	public DiscreteCosTransformer(BufferedImage img) throws InterruptedException {
		this.im = img;
		this.iw = im.getWidth(null);
		this.ih = im.getHeight(null);
		pixels = new int[iw * ih];
		DCT();
	}

	public BufferedImage getImg() {
		return imageAuth;
	}

	private void DCT() throws InterruptedException {
		int n = iw;
		PixelGrabber pg = new PixelGrabber(im, 0, 0, iw, ih, pixels, 0, iw);
		pg.grabPixels();

		double[][] iMatrix = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				iMatrix[i][j] = (double) (pixels[i * n + j]);
			}
		}
		double[][] quotient = coefficient(n); // 求系数矩阵
		double[][] quotientT = transposingMatrix(quotient, n); // 转置系数矩阵

		double[][] temp = new double[n][n];
		temp = matrixMultiply(quotient, iMatrix, n);
		iMatrix = matrixMultiply(temp, quotientT, n);
		

		int newpix[] = new int[n * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newpix[i * n + j] = (int) iMatrix[i][j];
			}
		}
		imageAuth = new BufferedImage(n, n, BufferedImage.TYPE_BYTE_GRAY);
		ColorModel colorModel = imageAuth.getColorModel();
		WritableRaster raster = colorModel.createCompatibleWritableRaster(n, n);
		raster.setPixels(0, 0, n, n, newpix);
		imageAuth.setData(raster);
	}

	private double[][] transposingMatrix(double[][] matrix, int n) {
		double nMatrix[][] = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nMatrix[i][j] = matrix[j][i];
			}
		}
		return nMatrix;
	}

	private double[][] coefficient(int n) {
		double[][] coeff = new double[n][n];
		double sqrt = 1.0 / Math.sqrt(n);
		for (int i = 0; i < n; i++) {
			coeff[0][i] = sqrt;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				coeff[i][j] = Math.sqrt(2.0 / n) * Math.cos(i * Math.PI * (j + 0.5) / (double) n);
			}
		}
		return coeff;
	}

	private double[][] matrixMultiply(double[][] A, double[][] B, int n) {
		double nMatrix[][] = new double[n][n];
		double t = 0.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				t = 0;
				for (int k = 0; k < n; k++) {
					t += A[i][k] * B[k][j];
				}
				nMatrix[i][j] = t;
			}
		}
		return nMatrix;
	}

}
