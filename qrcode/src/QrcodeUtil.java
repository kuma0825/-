import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeUtil {
	/*
	 * 生成图片
	 * 
	 * @author xiong
	 * 
	 * @param content 需要生成二维码的内容
	 * 
	 * @param imgPath 生成了图片以后保存的路径
	 */
	public static void getQrcodeImg(String content, String imgPath) {
		int width = 235;
		int height = 235;

		// 创建一个Qrcode对象
		Qrcode qrcode = new Qrcode();
		// 设置二维码排错率(就是二维码被遮挡，例如中间有个图片，依旧能读出盖伦)
		// L(7%) M(15%) Q(25%) H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		// 设置编码方式
		// N 数字 A英文 B二进制 K汉字
		qrcode.setQrcodeEncodeMode('B');
		// 设置二维码的版本
		// 1--40
		qrcode.setQrcodeVersion(15);

		// 二维码的绘制
		// 设置缓存图片rgb:red greed blue();在缓存中生成一张图片
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获得画图工具(画笔)生成画笔
		Graphics2D gs = img.createGraphics();
		// 设置背景为白色
		gs.setBackground(Color.WHITE);
		// 确定在这张纸画多大的二维码-----二维码跟纸张一样大小
		gs.clearRect(0, 0, width, height);
		// 设置画图的颜色
		gs.setColor(Color.BLACK);
		try {
			// 获取信息，画二维码content
			byte[] contentBytes = content.getBytes("UTF-8");
			// 通过方法将内容转化成Boolean类型的二维数组真就是黑点，假是白点
			boolean[][] b1 = qrcode.calQrcode(contentBytes);
			// 画二维码 第一个for是多少行 第二个是多少列
			for (int i = 0; i < b1.length; i++) {
				for (int j = 0; j < b1.length; j++) {
					if (b1[j][i]) {
						// 每个黑点长为3 宽为3 2为偏移量
						gs.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}
			}

			// 保存 png为形式 imgpath为路径
			ImageIO.write(img, "png", new File(imgPath));
			System.out.println("生成二维码成功");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String content = "如果你看到说明成功了";
		String imgPath = "E:\\SGX.png";
		getQrcodeImg(content, imgPath);

	}
}