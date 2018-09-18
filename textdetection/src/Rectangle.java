
import java.awt.Graphics;

public class Rectangle extends Shape {
    private int width;
    private int height;
    private String s;
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        width= Math.abs(endX-startX);
        height=Math.abs(endY-startY);

        int x1=startX<endX?startX:endX;
        int y1=startY<endY?startY:endY;

        g.drawRect(x1, y1, width, height);

    }

    @Override
    public void calculate(Graphics g) {
        s="此图为矩形"+"矩形周长为："+2*(width+height)+"面积为："+width*height;

        g.drawString(s, 100,100 );

    }

}
