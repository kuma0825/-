import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;


public class QRCode {
	/** 
     * ������ά��(QRCode) 
     *  
     * @param imgPath 
     * @return 
     */  
    public static String decoderQRCode(String imgPath) {  
        // QRCode ��ά��ͼƬ���ļ�  
        File imageFile = new File(imgPath);  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(imageFile);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new QRCodeImageBean(bufImg)), "utf-8");  
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
  
    /** 
     * ���ɶ�ά��(QRCode)ͼƬ 
     *  
     * @param content  �洢���� 
     * @param imgPath ͼƬ·�� 
     * @param imgType  ͼƬ���� 
     */  
    public static void encoderQRCode(String content, String imgPath, String imgType) {  
        encoderQRCode(content, imgPath, imgType, 7);  
    }  
  
    /** 
     * ���ɶ�ά��(QRCode)ͼƬ 
     *  
     * @param content   �洢���� 
     * @param imgPath  ͼƬ·�� 
     * @param imgType ͼƬ���� 
     * @param size ��ά��ߴ� 
     */  
    public static void encoderQRCode(String content, String imgPath, String imgType, int size) {  
        try {  
            BufferedImage bufImg = qRCodeCommon(content, imgType, size);  
            File imgFile = new File(imgPath);  
            // ���ɶ�ά��QRCodeͼƬ  
            ImageIO.write(bufImg, imgType, imgFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ���ɶ�ά��(QRCode)ͼƬ�Ĺ������� 
     *  
     * @param content  �洢���� 
     * @param imgType ͼƬ���� 
     * @param size ��ά��ߴ磨1-40�� 
     * @return 
     */  
    private static BufferedImage qRCodeCommon(String content, String imgType, int size) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            // �������ö�ά��ߴ磬ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��  
            qrcodeHandler.setQrcodeVersion(size);  
            // ������ݵ��ֽ����飬���ñ����ʽ  
            byte[] contentBytes = content.getBytes("utf-8");  
            // ͼƬ�ߴ�  
            int imgSize = 67 + 12 * (size - 1);  
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // ���ñ�����ɫ  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
  
            // �趨ͼ����ɫ> BLACK  
            gs.setColor(Color.BLUE);  
            // ����ƫ�����������ÿ��ܵ��½�������  
            int pixoff = 2;  
            // �������> ��ά��  
            if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }  
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bufImg;  
    }  
  
}  


