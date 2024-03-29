import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends JFrame implements ChangeListener {


    public static Board_Component Board = new Board_Component();
    static JFrame frame = new JFrame();
    static int mouseX = 0;
    static int mouseY = 0;
    public static boolean pressed = false;
    static JButton bRed = new JButton("RED");
    static JButton bGreen = new JButton("GREEN");
    static JButton bBlue = new JButton("BLUE");
    static JButton resetButton = new JButton("RESET");
    static JButton exportButton = new JButton("EXPORT");
    static JButton importButton = new JButton("IMPORT");
    static JButton dimensionButton = new JButton("CHANGE DIMENSION");
    static JButton undoButton = new JButton("UNDO");
    static JButton saveButton = new JButton("SAVE CURRENT STATE");
    static JButton redoButton = new JButton("REDO");
    static JSlider jsliderRed = new JSlider(JSlider.VERTICAL,0,255, 0);

    static JSlider jsliderGreen = new JSlider(JSlider.VERTICAL,0,255, 0);

    static JSlider jsliderBlue = new JSlider(JSlider.VERTICAL,0,255, 0);
    static JPanel jpanel = new JPanel();
    static JPanel sliderPanel = new JPanel();
    static JPanel valuePanel = new JPanel();


    static JPanel valuesAndSlidersPanel = new JPanel();
    static JTextArea valuesText = new JTextArea("Red:" + jsliderRed.getValue() + "\n"+
            "Green:"+ jsliderGreen.getValue() +"\n"+
            "Blue:" + jsliderBlue.getValue() + "\n");




    public static void main(String[] args) throws InterruptedException {


        coloredPanel jpanelColorBox = new coloredPanel();

        jsliderRed.addChangeListener(new Main());
        jsliderGreen.addChangeListener(new Main());
        jsliderBlue.addChangeListener(new Main());
        valuesText.setOpaque(false);

        BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.X_AXIS);
        valuesAndSlidersPanel.setLayout(new BoxLayout(valuesAndSlidersPanel, BoxLayout.Y_AXIS));
        jpanel.add(bRed);
        jpanel.add(bBlue);
        jpanel.add(bGreen);
        jpanel.add(resetButton);
        jpanel.add(dimensionButton);
        jpanel.add(exportButton);
        jpanel.add(importButton);
        jpanel.add(undoButton);
        jpanel.add(saveButton);
        jpanel.add(redoButton);

        jpanel.add(jpanelColorBox);

        InitializeButtons();
        jpanel.setLayout(boxLayout);
        Board.setDoubleBuffered(true);
        sliderPanel.add(jsliderRed, BorderLayout.EAST);
        sliderPanel.add(jsliderGreen, BorderLayout.CENTER);
        sliderPanel.add(jsliderBlue, BorderLayout.WEST);
        valuesAndSlidersPanel.add(sliderPanel);
        valuesAndSlidersPanel.add(valuesText);
        frame.add(valuesAndSlidersPanel, BorderLayout.EAST);


        frame.setBackground(Color.DARK_GRAY);



        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("MF_PAINT");
        frame.setSize(Board_Component.frameSize, (int) ( Board_Component.frameSize*0.92));
        frame.setVisible(true);
        frame.add(Board, BorderLayout.CENTER);
        frame.add(jpanel, BorderLayout.NORTH);
        frame.setResizable(true);



        Board.populateBoard();



    }

    public static void InitializeButtons(){
        bRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.current_color_node = new Color_Node("Red");
                System.out.println(Board_Component.current_color_node.getBlue());
            }
        });
        bGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.current_color_node = new Color_Node("Green");
            }
        });
        bBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.current_color_node = new Color_Node("Blue");
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.populateBoard();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateImage.CreateImageFromBoard();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        dimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension_Frame.createDimensionFrame();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileManager.WriteBoardToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileManager.ImportBoard();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.undoState();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.saveState();
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board_Component.redoState();
            }
        });


    }
    public void stateChanged(ChangeEvent e){
        frame.repaint();
        valuesText.setText("Red:" + jsliderRed.getValue() + "\n" +
                "Green:" + jsliderGreen.getValue() + "\n" +
                "Blue:" + jsliderBlue.getValue() + "\n");

        Board_Component.current_color_node = new Color_Node(jsliderRed.getValue(),jsliderGreen.getValue(),jsliderBlue.getValue());



    }

}