

import java.awt.Graphics;

public class Line extends Shape {
    private String s;
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(startX,startY, endX, endY);

    }

    @Override
    public void calculate(Graphics g) {
        s="此图为直线"+"长度为："+Math.sqrt((startX-endX)*(startX-endX)+
                (startY-endY)*(startY-endY));
        g.drawString(s, 100, 100);

    }

}