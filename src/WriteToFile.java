import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteToFile {
    public static void WriteBoardToFile() throws IOException {
        File file = new File("ASCIIFile.txt");
        FileWriter writer = new FileWriter(file);

        short offset = 160;
        char red, green, blue;
        String ASCIInode;

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
    
}
