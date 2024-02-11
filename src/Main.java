import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main extends JFrame implements ChangeListener {


    public static Board_Component Board = new Board_Component();
    static Component_Listener componentListener = new Component_Listener();
    public static JFrame frame = new JFrame();
    public static int mouseX = 0;
    public static int mouseY = 0;
    public static boolean pressed = false;
    static JButton bRed = new JButton("RED");
    static JButton bGreen = new JButton("GREEN");
    static JButton bBlue = new JButton("BLUE");
    static JButton resetButton = new JButton("RESET");
    static JButton saveButton = new JButton("SAVE");
    static JButton dimensionButton = new JButton("Change Dimension");
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
        jpanel.add(saveButton);
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

        bRed.addComponentListener(componentListener);
        bBlue.addComponentListener(componentListener);
        bGreen.addComponentListener(componentListener);
        frame.setBackground(Color.DARK_GRAY);


        frame.addComponentListener(componentListener);
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
        saveButton.addActionListener(new ActionListener() {
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


    }
    public void stateChanged(ChangeEvent e){
        frame.repaint();
        valuesText.setText("Red:" + jsliderRed.getValue() + "\n" +
                "Green:" + jsliderGreen.getValue() + "\n" +
                "Blue:" + jsliderBlue.getValue() + "\n");

        Board_Component.current_color_node = new Color_Node(jsliderRed.getValue(),jsliderGreen.getValue(),jsliderBlue.getValue());



    }

}