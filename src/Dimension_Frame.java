import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dimension_Frame {
    static JFrame dimensionFrame = new JFrame();
    static JButton buttonx28 = new JButton("28x28px");

    static JButton buttonx56 = new JButton("56x56px");
    static JButton buttonx112 = new JButton("112x112px");

    static JButton buttonx250 = new JButton("250x250px");
    static JTextField dimensionXInput = new JTextField();
    static JTextField dimensionYInput = new JTextField();


    public static void createDimensionFrame() {

        dimensionFrame.setResizable(false);
        dimensionFrame.setTitle("Change Canvas Dimension");
        dimensionFrame.setLayout(null);
        dimensionFrame.setSize(400,400);
        dimensionFrame.setBackground(Color.darkGray);


        dimensionFrame.add(buttonx28);
        dimensionFrame.add(buttonx56);
        dimensionFrame.add(buttonx112);
        dimensionFrame.add(buttonx250);

        dimensionFrame.add(dimensionXInput);
        dimensionFrame.add(dimensionYInput);

        buttonx28.setBounds(30,10,150,75);
        buttonx56.setBounds(205,10,150,75);
        buttonx112.setBounds(30,100,150,75);
        buttonx250.setBounds(205,100,150,75);

        buttonx28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSymmetricBoard(28);
            }
        });
        buttonx56.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSymmetricBoard(56);
            }
        });
        buttonx112.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSymmetricBoard(112);
            }
        });
        buttonx250.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSymmetricBoard(250);
            }
        });

        dimensionFrame.setVisible(true);


    }
    public static void createNewSymmetricBoard(int newSize){ //a symmetric board is in the shape of a square, so x = y
        Color_Node[][] newBoard = new Color_Node[newSize][newSize];
        int choice = JOptionPane.YES_OPTION;
        boolean lossyFlag = Board_Component.boardSize> newSize;
        if (lossyFlag){
            choice = JOptionPane.showConfirmDialog(null, "This action will cause a lossy conversion.", "Do you want to continue?", JOptionPane.YES_NO_OPTION);
        }

        if (choice == JOptionPane.YES_OPTION && lossyFlag){
            for (int i = 0; i < newSize; i++){
                for (int j = 0; j < newSize; j++){
                    newBoard[i][j] = Board_Component.Board[i][j];
                }
            }


            Board_Component.pixelDimension = (Board_Component.boardPixelDimension / newSize);
            Board_Component.Board = newBoard;
            Board_Component.boardSize = newSize;
            Board_Component.boardPixelDimension = newSize * Board_Component.pixelDimension;
            Main.frame.repaint();


        } else if (choice == JOptionPane.YES_OPTION && !lossyFlag) {
            Color_Node emptyNode = new Color_Node(255,255,255);
            for (int i = 0; i < newSize; i++){
                for (int j = 0; j < newSize; j++){
                    if (i< Board_Component.boardSize && j < Board_Component.boardSize){
                        newBoard[i][j] = Board_Component.Board[i][j];
                    }
                    else {newBoard[i][j] = emptyNode;}

                }
            }


            Board_Component.pixelDimension = (int)((Board_Component.boardPixelDimension / newSize)*1.60);
            Board_Component.Board = newBoard;
            Board_Component.boardSize = newSize;
            Main.frame.repaint();
        } else if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Operation cancelled");

        }

    }





}
