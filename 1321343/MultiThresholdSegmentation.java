import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MultiThresholdSegmentation {

    public static void main(String[] args) {
        try {
           
            File inputFile = new File("C:\\Users\\User\\Desktop\\1321343\\input.jpg"); // 請確保路徑正確
            BufferedImage originalImage = ImageIO.read(inputFile);

            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            
            int lowerThreshold = 100;
            int upperThreshold = 200;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = originalImage.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb) & 0xFF;

                    int gray = (r + g + b) / 3;

                    if (gray < 80) {
                        resultImage.setRGB(x, y, 0xFF0000); // 暗部染成紅色
                    } else if (gray < 160) {
                        resultImage.setRGB(x, y, 0x00FF00); // 中間亮度染成綠色
                    } else {
                        resultImage.setRGB(x, y, 0x0000FF); // 亮部染成藍色
                    }
                }
            }

            // 5. 輸出結果影像
            File outputFile = new File("C:\\Users\\User\\Desktop\\1321343\\output.jpg");
            ImageIO.write(resultImage, "jpg", outputFile);
            System.out.println("影像分割完成，結果已儲存。");

        } catch (IOException e) {
            System.err.println("讀取或儲存影像時發生錯誤: " + e.getMessage());
        }
    }
}
