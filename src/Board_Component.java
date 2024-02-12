import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.*;

public class Board_Component extends JComponent{

    public static int frameSize = 1080;
    public static int boardSize = 128;
    public static int pixelDimension = 5;
    public static int boardPixelDimension = 640;
    static boolean first = true;


    Random random = new Random();
    static Color_Node[][] Board = new Color_Node[boardSize][boardSize];


    public static int red;
    public static  int blue;
    public static  int green;
    static int lastX,lastY,currentX,currentY;



    public static Color_Node individual_color_node_for_painting = new Color_Node();

    public static Color_Node current_color_node = new Color_Node("BLACK");

    public static long startTimer = 0;

    public static long endTimer = 0;

    public Board_Component() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                startTimer = System.nanoTime();
                if (0 == endTimer || startTimer-endTimer>100000000){first = true;}
                lastX = currentX;
                lastY = currentY;


                int x = (e.getX() / pixelDimension);
                int y = (e.getY() / pixelDimension);

                currentX = x;
                currentY = y;

                Point currentPoint = new Point(currentX,currentY);
                Point lastPoint = new Point(lastX, lastY);

                Board[x][y] = current_color_node;
                if (!first) {
                    if (Math.abs(lastX - currentX) > 1 || Math.abs(lastY - currentY) > 1) {
                        new Thread(new Runnable() {
                            public void run() {
                                interpolateBoard(lastPoint, currentPoint);
                            }
                        }).start();
                    }
                }
                repaint();
                first = false;

                endTimer = System.nanoTime();


            }});











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

    public static void interpolateBoard(Point lastCoords, Point currentCoords) {
        int startX = (int) lastCoords.getX();
        int startY = (int) lastCoords.getY();
        int endX = (int) currentCoords.getX();
        int endY = (int) currentCoords.getY();

        int dx = endX - startX;
        int dy = endY - startY;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = startX;
        float y = startY;

        for (int i = 0; i <= steps; i++) {
            Board[Math.round(x)][Math.round(y)] = current_color_node;
            x += xIncrement;
            y += yIncrement;
        }

    }



}