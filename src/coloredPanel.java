import javax.swing.*;
import java.awt.*;

public class coloredPanel extends JPanel {
    public void paint(Graphics g){
        Color labelColor = new Color(Main.jsliderRed.getValue(),Main.jsliderGreen.getValue(),Main.jsliderBlue.getValue());
        g.setColor(labelColor);
        g.fillRect(10,5,20,20);
    }

}
