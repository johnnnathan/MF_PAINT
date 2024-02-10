import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateImageWithPython {

    public static void MakeImage() throws IOException, InterruptedException {
        String pythonFile = "ReadFromFile.py";
        String line;

        Process process = Runtime.getRuntime().exec(pythonFile);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        int exitCode = process.waitFor();
        System.out.println("Image Created with Python");
    }
}
