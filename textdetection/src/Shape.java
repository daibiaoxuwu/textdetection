
        import java.awt.Color;
        import java.awt.Graphics;
/**
 * 图形(抽象类，不能实例化)
 * @author Administrator
 *
 */
public abstract class Shape {
    protected int startX,startY;//起点横纵坐标
    protected int endX,endY;//终点横纵坐标

    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 绘图(抽象方法留给子类重写)
     * @param g　画笔
     */
    public abstract void draw(Graphics g);

    public abstract void calculate(Graphics g);

    public int getStartX() {
        return startX;
    }
    public void setStartX(int startX) {
        this.startX = startX;
    }
    public int getStartY() {
        return startY;
    }
    public void setStartY(int startY) {
        this.startY = startY;
    }
    public int getEndX() {
        return endX;
    }
    public void setEndX(int endX) {
        this.endX = endX;
    }
    public int getEndY() {
        return endY;
    }
    public void setEndY(int endY) {
        this.endY = endY;
    }

}
