import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Segmenter {

    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\User\\Desktop\\1321343\\input.jpg");
            BufferedImage img = ImageIO.read(inputFile);
            int width = img.getWidth();
            int height = img.getHeight();

            BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int minBrightness = 90;  
            int maxBrightness = 240; 

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color c = new Color(img.getRGB(x, y));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    int brightness = (r + g + b) / 3;

                    if (r > (g + 15) && r > 40 && brightness > 60) { 
    
                        if (brightness > 180) {
                            output.setRGB(x, y, Color.GREEN.getRGB()); 
                        } else {
                            output.setRGB(x, y, Color.RED.getRGB()); 
                        }
                        
                    } else {
                        
                        output.setRGB(x, y, Color.BLUE.getRGB());
                    }
                }
            }


            ImageIO.write(output, "jpg", new File("C:\\Users\\User\\Desktop\\1321343\\output.jpg"));
            System.out.println("主體抓取完成！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}