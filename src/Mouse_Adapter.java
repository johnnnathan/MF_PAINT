import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse_Adapter extends MouseAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {

        Board_Component.drawDragged(e.getX(),e.getY());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
