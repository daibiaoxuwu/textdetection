import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//做误差分析曲线图

public class Main4 extends JFrame {


    @Override
    public void paint(Graphics g) {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 100; i++) {
            for (double randomField = 0.0; randomField < 3.0; randomField += 0.2) {
                final List<Double[]> corrdinatelist = new ArrayList<>();
                final List<Double[]> truecorrdinatelist = new ArrayList<>();
                final List<Integer> direction = new ArrayList<>();

                Double[] location7 = {-2.0, -3.0};
                truecorrdinatelist.add(location7);//MISHA
                Double[] location5 = {0.0, 6.5};
                truecorrdinatelist.add(location5);//CITY
                Double[] location4 = {7.5, 6.5};
                truecorrdinatelist.add(location4);//VERO
                for (Double[] location : truecorrdinatelist) {
                    TextDetection.drawCircle(location[0], location[1], 0.3, g);
                }
                final Double[] truth = {4.0, -4.0};
                TextDetection.drawCircle(truth[0], truth[1], 0.3, g);
                direction.add(-1);
                direction.add(-1);
                direction.add(-1);
                direction.add(-1);

                double turnangle = Math.PI / 100 * i * 2;
                work(truecorrdinatelist, g, direction, turnangle, truth, randomField);
            }
        }

    }

    public static void main(String args[]) {
        new Main4().paint(null);
    }


    public void work(List<Double[]> truecorrdinatelist, Graphics g, List<Integer> direction, double turnangle, Double[] truth, double randomField){

        ArrayList<Double[]> corrdinatelist = new ArrayList<>();
        for(int i=0;i<truecorrdinatelist.size()-1;++i) {
            corrdinatelist.add(truecorrdinatelist.get(i));
        }
        g.setColor(Color.RED);
        for(Double[] point:truecorrdinatelist){
            TextDetection.drawCircle(point[0], point[1], 0.05, g);
        }
        Double[] location = truecorrdinatelist.get(truecorrdinatelist.size()-1);

        corrdinatelist.add(new Double[]
                {location[0]+Math.sin(turnangle)*randomField, location[1]+Math.cos(turnangle)*randomField});
        location=corrdinatelist.get(corrdinatelist.size()-1);
        TextDetection.drawCircle(location[0], location[1], 0.05, g);

//表示方向 数量是和coordinatelist一样的
//如果是1 代表(y-y1)*(x2-x1)>(x-x1)*(y2-y1)
//也就是当y2>y1 在y+的方向 当y2<y1 在y-的方向
//否则是-1
//当y2=y1时 x2>x1 在y上面
//当x2=x1时 y2>y1 在x左边
//注意 最后一个点的顺时针逆时针方向是按"360-其他"那个角度定的.如果那个(或者别的任何一个)角度大于180,
//后续会用360减它,并且把direction反向.

        final List<Double> anglelist = new ArrayList<>();
        double firstangle = 0.0;
        for (Double[] point : truecorrdinatelist) {
            double diffx = point[0] - truth[0];
            double diffy = point[1] - truth[1];
            double angle = Math.toDegrees(Math.atan(diffy / diffx));
            if (diffx < 0) angle += 180;
            if (diffy < 0 && diffx > 0) angle += 360;
//System.out.println(angle);
            if (firstangle != 0.0) {
                double diffangle = -angle + firstangle;
                if (diffangle < 0.0) diffangle += 360.0;
                anglelist.add(diffangle);
            }
            firstangle = angle;
        }

        Double[] answer = TextDetection.cal_corrdinate(anglelist, corrdinatelist, direction, g);
        double difflength = TextDetection.dis(truth,answer);
//            System.out.println("turnangle"+turnangle+ "difflength" + difflength + "turnangle" + Math.sin(turnangle) + " " + Math.cos(turnangle));
//            TextDetection.drawCircle(turnangle, difflength, 0.1, g);
        g.setColor(Color.BLUE);
        TextDetection.drawCircle(answer[0], answer[1], 0.05, g);
//        try {
//            Thread.sleep(1);
//        }catch (Exception e){}
        g.setColor(Color.GRAY);
        TextDetection.drawCircle(answer[0], answer[1], 0.05, g);
    }
}
