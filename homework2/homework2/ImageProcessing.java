package homework2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageProcessing {
	static BufferedImage image;

	private static void readFile() throws IOException {
		String filePath;
		System.out.println("请输入文件路径inFile:");
		Scanner in = new Scanner(System.in);
		filePath = in.nextLine();
		File input = new File(filePath);
		image = ImageIO.read(input);
	}

	private static void outImage(BufferedImage output) throws IOException {
		String filePath;
		System.out.println("请输入文件路径outFile:");
		Scanner in = new Scanner(System.in);
		filePath = in.nextLine();
		File file = new File(filePath);
		ImageIO.write(output, "jpg", file);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("图像处理作业2：你想做什么？");
		System.out.println("1	傅里叶变换");
		System.out.println("2	余弦变换");
		System.out.println("3	退出");
		System.out.println("请输入数字（1-3）：");
		Scanner in = new Scanner(System.in);
		int opt = in.nextInt();
		while (opt != 3) {
			switch (opt) {
			case 1:
				readFile();
				FourierTransformer fourierTransImg = new FourierTransformer(image);
				BufferedImage fourierTransImage = fourierTransImg.getImg();
				outImage(fourierTransImage);
				System.out.println("导出文件成功！");
				break;
			case 2:
				readFile();
				DiscreteCosTransformer disCosTransImg = new DiscreteCosTransformer(image);
				BufferedImage disCosTransImage = disCosTransImg.getImg();
				outImage(disCosTransImage);
				System.out.println("导出文件成功！");
				break;
			default:
				System.out.println("输入无效，请重新输入！");
			}
			System.out.println("图像处理作业2：你想做什么？");
			System.out.println("1	傅里叶变换");
			System.out.println("2	余弦变换");
			System.out.println("3	退出");
			System.out.println("请输入数字（1-3）：");
			opt = in.nextInt();
		}
	}

}
