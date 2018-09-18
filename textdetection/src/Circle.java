
        import java.awt.Graphics;
        import javax.swing.JFrame;
        import java.awt.Color;

public class Circle extends JFrame {
    public Circle() {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawOval(480, 480, 200, 200);
        g.drawOval(180, 480, 200, 200);
    }

    public static void main(String args[]) {
        Circle t = new Circle();
        t.paint(null);
    }
}