public class TestQRCode {  
  
    public static void main(String[] args) {  
           
        //���ɶ�ά��  
         String imgPath = "d:/qrCode.png";  
         String encoderContent ="http://i9.hexunimg.cn/2014-01-15/161439086.jpg";  
         QRCode.encoderQRCode(encoderContent, imgPath, "png");  
         System.out.println("���ɳɹ�!");  
           
         //������ά��  
         String imgPath2 = "d:/qrCode.png";  
         String qrCon = QRCode.decoderQRCode(imgPath2);  
         System.out.println("�����ɹ�!");  
         System.out.println("��ά������Ϊ:" + qrCon);  
    }  
}  