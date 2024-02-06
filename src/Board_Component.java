import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.*;

public class Board_Component extends JComponent {
    public static int frameSize = 390;
    public static int boardSize = 32;
    public static int pixelDimension = 10;
    public static byte offsetX = -1;
    public static byte offsetY = -6;
    Random random = new Random();
    static Color_Node[][] Board = new Color_Node[boardSize][boardSize];

    public static int brushSize = 1;

    public static int startX = 0;
    public static int startY = 0;
    public static int endX = 0;
    public static int endY = 0;
    public static int red;
    public static  int blue;
    public static  int green;
    public static  int x;
    public static  int y;

    public static Color_Node individual_color_node_for_painting = new Color_Node();

    public static Color_Node current_color_node = new Color_Node("Default");



    public void populateBoard(){
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                Board[i][j] = new Color_Node();
            }
        }


    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);


        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                individual_color_node_for_painting = Board[i][j];
                red = individual_color_node_for_painting.red;
                green = individual_color_node_for_painting.green;
                blue = individual_color_node_for_painting.blue;
                Color color = new Color(red,green,blue);
//                Color color1 = new Color()

                g.setColor(color);
                x =  i* pixelDimension; //this variable can be used to fit the board to the screen, BUT it needs to be in the same ratio
                y = j * pixelDimension;
                g.fillRect(x,y,pixelDimension,pixelDimension);
            }
        }

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

        Main.frame.repaint(startX + 20, startY + 20, brushSize *2, brushSize*2);
        Thread.sleep(100);


    }
    public static void drawPixel(int mouseX, int mouseY){


        int x = mouseX/pixelDimension;
        int y = mouseY/pixelDimension;

//        for (int i = startX; i< endX; i++){
//            for (int j = startY; j < endY; j++){
//                Board[i][j] = current_color_node;
//            }
//        }
        Board[x+offsetX][y+offsetY] = current_color_node;

//        Main.frame.repaint(mouseX-40,mouseY-40,100,100);
        Main.frame.repaint();

    }
}
