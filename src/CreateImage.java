import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateImage {

    static int imageSize = Board_Component.boardSize;
    static BufferedImage Image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
    public static void CreateImageFromBoard() throws IOException {


        for (int i = 0; i < imageSize; i++) {
            for (int j = 0; j < imageSize; j++) {
                Image.setRGB(i, j, Board_Component.Board[i][j].getRGB());
            }
        }


        File imageFile = new File("PixelArt.png");
        ImageIO.write(Image,"png", imageFile);


    }

}
