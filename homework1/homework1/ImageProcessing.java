package homework1;

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
		System.out.println("图像处理作业1：你想做什么？");
		System.out.println("1	转为灰度图像");
		System.out.println("2	直方图均衡");
		System.out.println("3	灰度线性变换");
		System.out.println("4	灰度拉伸");
		System.out.println("5	退出");
		System.out.println("请输入数字（1-5）：");
		Scanner in = new Scanner(System.in);
		int opt = in.nextInt();
		while (opt != 5) {
			switch (opt) {
			case 1:
				readFile();
				GrayImage grayImg = new GrayImage(image);
				BufferedImage grayImage = grayImg.getImg();
				outImage(grayImage);
				System.out.println("导出文件成功！");
				break;
			case 2:
				readFile();
				HistogramEqualizationImg hisEqualImg = new HistogramEqualizationImg(image);
				BufferedImage hisEqualImage = hisEqualImg.getImg();
				outImage(hisEqualImage);
				System.out.println("导出文件成功！");
				break;
			case 3:
				readFile();
				System.out.println("input c:");
				int c3 = in.nextInt();
				System.out.println("input d:");
				int d3 = in.nextInt();
				LineTranImg lineTranImg = new LineTranImg(image, c3, d3);
				BufferedImage lineTranImage = lineTranImg.getImg();
				outImage(lineTranImage);
				System.out.println("导出文件成功！");
				break;
			case 4:
				readFile();
				System.out.println("input a:");
				int a = in.nextInt();
				System.out.println("input b:");
				int b = in.nextInt();
				System.out.println("input c:");
				int c = in.nextInt();
				System.out.println("input d:");
				int d = in.nextInt();
				GrayStretchImg graySreImg = new GrayStretchImg(image, a, b, c, d);
				BufferedImage graySreImage = graySreImg.getImg();
				outImage(graySreImage);
				System.out.println("导出文件成功！");
				break;
			default:
				System.out.println("输入无效，请重新输入！");
			}
			System.out.println("图像处理作业1：你想做什么？");
			System.out.println("1	转为灰度图像");
			System.out.println("2	直方图均衡");
			System.out.println("3	灰度线性变换");
			System.out.println("4	灰度拉伸");
			System.out.println("5	退出");
			System.out.println("请输入数字（1-5）：");
			opt = in.nextInt();
		}
	}

}
