import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Board_Component extends JComponent {
    public static int size = 500;
    Random random = new Random();
    static Color_Node[][] Board = new Color_Node[size][size];

    public static int brushSize = 20;

    public static Color_Node current_color_node = new Color_Node("Default");



    public void populateBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Board[i][j] = new Color_Node();
            }
        }


    }

    @Override
    public void paint(Graphics g) {
        int red;
        int blue;
        int green;
        int x;
        int y;
        Color_Node individual_color_node_for_painting = new Color_Node();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                individual_color_node_for_painting = Board[i][j];
                red = individual_color_node_for_painting.getRed();
                green = individual_color_node_for_painting.getGreen();
                blue = individual_color_node_for_painting.getBlue();
                Color color = new Color(red,green,blue);

                g.setColor(color);
//                x =  i* Component_Listener.pixelSizeWidth; this variable can be used to fit the board to the screen, BUT it needs to be in the same ratio
//                y = j * Component_Listener.pixelSizeHeight;
                g.fillRect(i,j,1,1);
            }
        }
        super.paint(g);
    }
    public static void drawClick(int mouseX, int mouseY) throws ArrayIndexOutOfBoundsException, InterruptedException {

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;


        startX = mouseX - (brushSize/2) - 17;
        startY = mouseY - (brushSize/2) - 40;
        endX = mouseX + (brushSize/2) - 17;
        endY = mouseY + (brushSize/2) - 40;


        for (int i = startX; i<endX; i++){
            for (int j = startY; j < endY; j++){
                Board[i][j] = current_color_node;
            }
        }

        Main.frame.repaint();
        Thread.sleep(100);


    }
    public static void drawDragged(int mouseX, int mouseY){
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        startX = (mouseX - (brushSize / 2) - 17);
        startY = (mouseY - (brushSize / 2) - 40);
        endX = (mouseX + (brushSize / 2) - 17);
        endY = (mouseY + (brushSize / 2) - 40);


        for (int i = startX; i< endX; i++){
            for (int j = startY; j < endY; j++){
                Board[i][j] = current_color_node;
            }
        }

        Main.frame.repaint();


    }
}
