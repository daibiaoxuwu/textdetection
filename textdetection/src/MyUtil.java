
import java.awt.Color;
/**
 * 工具类：第一：加上final，让这个类不能被继承
 * 　　　　　第二：构造器私有，让别人只能以对象加点调用方法
 * @author Administrator
 *
 */
public final class MyUtil {
    private MyUtil(){

    }

    /**
     * 产生指定范围的随机整数
     * @param min　最小值（闭区间）
     * @param max　最大值（闭区间）
     * @return　指定范围的随机整数
     */
    public static int random(int min,int max){

        return (int) (Math.random()*(max-min+1)+min);
    }

    public static Color randomColor(){
        int r=random(0,255);
        int g=random(0,255);
        int b=random(0,255);
        return new Color(r,g,b);
    }

}