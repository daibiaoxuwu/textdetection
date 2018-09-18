import javax.swing.*;
import java.awt.*;


        import java.awt.Graphics;
                import javax.swing.JFrame;
                import java.awt.Color;

public class Circle2 extends JFrame {

    public void paint() {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Graphics g = getGraphics();
        g.setColor(Color.GREEN);
        g.drawOval(480, 480, 200, 200);
        g.drawOval(180, 480, 200, 200);
    }

    public static void main(String args[]) {
        new Circle2().paint();
    }
}
