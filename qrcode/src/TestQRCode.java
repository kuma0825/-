public class TestQRCode {  
  
    public static void main(String[] args) {  
           
        //生成二维码  
         String imgPath = "d:/qrCode.png";  
         String encoderContent ="http://i9.hexunimg.cn/2014-01-15/161439086.jpg";  
         QRCode.encoderQRCode(encoderContent, imgPath, "png");  
         System.out.println("生成成功!");  
           
         //解析二维码  
         String imgPath2 = "d:/qrCode.png";  
         String qrCon = QRCode.decoderQRCode(imgPath2);  
         System.out.println("解析成功!");  
         System.out.println("二维码内容为:" + qrCon);  
    }  
}  