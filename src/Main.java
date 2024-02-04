import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Main extends JFrame {


    public static Board_Component Board = new Board_Component();
    static Component_Listener componentListener = new Component_Listener();
    public static JFrame frame = new JFrame();
    static Mouse_Adapter mouseAdapter = new Mouse_Adapter();
    public static int mouseX = 0;
    public static int mouseY = 0;
    public static boolean pressed = false;
    static JButton bRed = new JButton("RED");
    static JButton bGreen = new JButton("GREEN");
    static JButton bBlue = new JButton("BLUE");

    static JPanel jpanel = new JPanel();


    public static void main(String[] args) throws InterruptedException {


        BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.X_AXIS);
        jpanel.add(bRed);
        jpanel.add(bBlue);
        jpanel.add(bGreen);
        InitializeButtons();
        jpanel.setLayout(boxLayout);
        frame.addMouseMotionListener(mouseAdapter);

        bRed.addComponentListener(componentListener);
        bBlue.addComponentListener(componentListener);
        bGreen.addComponentListener(componentListener);

        frame.addComponentListener(componentListener);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("MF_PAINT");
        frame.setSize(Board_Component.size, Board_Component.size);
        frame.setVisible(true);
        frame.add(Board, BorderLayout.CENTER);
        frame.add(jpanel, BorderLayout.SOUTH);
        frame.setResizable(false);
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
    }

}
