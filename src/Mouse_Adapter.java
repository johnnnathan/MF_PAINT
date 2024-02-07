import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse_Adapter extends MouseAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {


        try {
            Board_Component.drawClick(e.getX(),e.getY());
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        Board_Component.drawPixel(e.getX(),e.getY());
    }
}
