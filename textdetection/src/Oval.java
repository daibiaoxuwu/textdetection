


import java.awt.Graphics;

public class Oval extends Shape{
    private static final double PI = 3.14;
    private String s;
    private int width;
    private int height;
    @Override
    public void draw(Graphics g) {

        g.setColor(color);

        width= Math.abs(endX-startX);
        height=Math.abs(endY-startY);

        int x1=startX<endX?startX:endX;
        int y1=startY<endY?startY:endY;

        g.drawOval(x1, y1, width, height);
    }

    @Override
    public void calculate(Graphics g) {
        double a=width/2;
        double b=height/2;
        s="此图为椭圆："+"面积为："+PI *a*b+"周长为："+2*PI*b+4*(Math.abs(a-b));
        g.drawString(s, 100, 100);

    }


}