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
	 * ����ͼƬ
	 * 
	 * @author xiong
	 * 
	 * @param content ��Ҫ���ɶ�ά�������
	 * 
	 * @param imgPath ������ͼƬ�Ժ󱣴��·��
	 */
	public static void getQrcodeImg(String content, String imgPath) {
		int width = 235;
		int height = 235;

		// ����һ��Qrcode����
		Qrcode qrcode = new Qrcode();
		// ���ö�ά���Ŵ���(���Ƕ�ά�뱻�ڵ��������м��и�ͼƬ�������ܶ�������)
		// L(7%) M(15%) Q(25%) H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		// ���ñ��뷽ʽ
		// N ���� AӢ�� B������ K����
		qrcode.setQrcodeEncodeMode('B');
		// ���ö�ά��İ汾
		// 1--40
		qrcode.setQrcodeVersion(15);

		// ��ά��Ļ���
		// ���û���ͼƬrgb:red greed blue();�ڻ���������һ��ͼƬ
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ��û�ͼ����(����)���ɻ���
		Graphics2D gs = img.createGraphics();
		// ���ñ���Ϊ��ɫ
		gs.setBackground(Color.WHITE);
		// ȷ��������ֽ�����Ķ�ά��-----��ά���ֽ��һ����С
		gs.clearRect(0, 0, width, height);
		// ���û�ͼ����ɫ
		gs.setColor(Color.BLACK);
		try {
			// ��ȡ��Ϣ������ά��content
			byte[] contentBytes = content.getBytes("UTF-8");
			// ͨ������������ת����Boolean���͵Ķ�ά��������Ǻڵ㣬���ǰ׵�
			boolean[][] b1 = qrcode.calQrcode(contentBytes);
			// ����ά�� ��һ��for�Ƕ����� �ڶ����Ƕ�����
			for (int i = 0; i < b1.length; i++) {
				for (int j = 0; j < b1.length; j++) {
					if (b1[j][i]) {
						// ÿ���ڵ㳤Ϊ3 ��Ϊ3 2Ϊƫ����
						gs.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}
			}

			// ���� pngΪ��ʽ imgpathΪ·��
			ImageIO.write(img, "png", new File(imgPath));
			System.out.println("���ɶ�ά��ɹ�");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String content = "����㿴��˵���ɹ���";
		String imgPath = "E:\\SGX.png";
		getQrcodeImg(content, imgPath);

	}
}