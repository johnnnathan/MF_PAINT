import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileManager {
    public static void WriteBoardToFile() throws IOException {
        File file = new File("ASCIIFile.txt");
        FileWriter writer = new FileWriter(file);

        short offset = 160;
        char red, green, blue;
        String ASCIInode;

        writer.write(Board_Component.boardSize + "x" + Board_Component.boardSize + "\n");
        for (int i = 0; i< Board_Component.boardSize; i++){
            for (int j = 0; j< Board_Component.boardSize; j++){
                red = (char) ((char) Board_Component.Board[j][i].getRed() + offset);
                blue = (char) ((char) Board_Component.Board[j][i].getBlue() + offset);
                green = (char) ((char) Board_Component.Board[j][i].getGreen() + offset);
                ASCIInode = red + "" + green + "" + blue + " ";

                writer.write(ASCIInode);
            }
            writer.write("\n");
        }
        writer.flush();
        writer.close();



        
    }

    public static void ImportBoard() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("For the Time only .txt files, in the future different types of text and possibly even images", "txt");
        chooser.setFileFilter(filter);
        int value = chooser.showOpenDialog(null);
        if (value == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            Scanner scanner = new Scanner(file);
            String[] sizeLine = scanner.nextLine().split("x");
            int size = Integer.parseInt(sizeLine[0]);

            Color_Node[][] newArray = new Color_Node[size][size];

            while(scanner.hasNext()){
                for (int i = 0; i < size; i++){
                    String line = scanner.nextLine();
                    String[] codes = line.split(" ");
                    for (int j = 0; j < size; j++){
                        newArray[i][j] = new Color_Node((int) codes[j].charAt(0) - 160, (int) codes[j].charAt(1) - 160, (int) codes[j].charAt(2) - 160);

                    }
                }
            }
            Board_Component.pixelDimension = (Board_Component.boardPixelDimension / size);
            Board_Component.Board = newArray;
            Board_Component.boardSize = size;
            Board_Component.boardPixelDimension = size * Board_Component.pixelDimension;
            scanner.close();
            Main.frame.repaint();


        }
        else {
            JOptionPane.showConfirmDialog(null, "The file could not be accepted");
        }








    }
    
}
