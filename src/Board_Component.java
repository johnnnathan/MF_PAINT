import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.*;

public class Board_Component extends JComponent {

    public static int frameSize = 1080;
    public static int boardSize = 128;
    public static int pixelDimension = 5;
    public static int boardPixelDimension = 640;

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

    public static int lastX;
    public static int lastY;

    public static Color_Node individual_color_node_for_painting = new Color_Node();

    public static Color_Node current_color_node = new Color_Node("BLACK");


    public Board_Component() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int x = e.getX() / pixelDimension;
                int y = e.getY() / pixelDimension;

                if (e.isShiftDown()){drawFill(x,y);}

                Board[x][y] = current_color_node;

                repaint();

            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = (e.getX() / pixelDimension);
                int y = (e.getY() / pixelDimension);
                Board[x][y] = current_color_node;


                repaint();

            }
        });


    }


    public static void populateBoard(){
        Color_Node backgroundColor = new Color_Node(255,255,255);
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                Board[i][j] = backgroundColor;
            }
        }
        Main.frame.repaint();


    }

    @Override
    public void paintComponent(Graphics g) {



        int x,y;

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
                g.setColor(Color.BLACK);
//                g.drawRect(x,y,pixelDimension,pixelDimension);
            }
        }

    }
    public static void drawFill(int x, int y) {

        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || Board[x][y].compareColors(current_color_node) ) {
            return;
        }
        Board[x][y] = current_color_node;
        drawFill(x - 1, y);
        drawFill(x + 1, y);
        drawFill(x, y+1);
        drawFill(x, y-1);

    }





}