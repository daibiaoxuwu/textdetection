import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
//做可视化

public class Main3 extends JFrame {


    @Override
    public void paint(Graphics g) {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        final List<Double[]> corrdinatelist = new ArrayList<>();
        final List<Double[]> truecorrdinatelist = new ArrayList<>();

        Double[] location7 = {-2.0, -3.0};
        truecorrdinatelist.add(location7);//MISHA
        Double[] location5 = {0.0, 6.5};
        truecorrdinatelist.add(location5);//CITY
        Double[] location8 = {2.5, 6.5};
        truecorrdinatelist.add(location8);//VERO
        Double[] location4 = {7.5, 6.5};
        truecorrdinatelist.add(location4);//VERO
        g.setColor(Color.ORANGE);
        for(Double[] point: truecorrdinatelist){
            TextDetection.drawCircle(point[0],point[1],0.3,g);
        }

        final double randomField = 3.0;

        for(Double[] location : truecorrdinatelist) {
            corrdinatelist.add(new Double[]
                    {location[0]+Math.random() * randomField,location[1]+Math.random() * randomField});
        }


//        corrdinatelist.clear();
//        Double[] location71 = {-3.0, -2.0};
//        corrdinatelist.add(location71);//MISHA
//        Double[] location51 = {-2.0, 6.5};
//        corrdinatelist.add(location51);//CITY
//        Double[] location41 = {9.5, 6.5};
//        corrdinatelist.add(location41);//VERO
        g.setColor(Color.RED);
        for(Double[] point: corrdinatelist) {
            TextDetection.drawCircle(point[0], point[1], 0.3, g);
        }
        final List<Integer> direction = new ArrayList<>();
//表示方向 数量是和coordinatelist一样的
//如果是1 代表(y-y1)*(x2-x1)>(x-x1)*(y2-y1)
//也就是当y2>y1 在y+的方向 当y2<y1 在y-的方向
//否则是-1
//当y2=y1时 x2>x1 在y上面
//当x2=x1时 y2>y1 在x左边
//注意 最后一个点的顺时针逆时针方向是按"360-其他"那个角度定的.如果那个(或者别的任何一个)角度大于180,
//后续会用360减它,并且把direction反向.
        direction.add(-1);
        direction.add(-1);
        direction.add(-1);
        direction.add(-1);




        final List<Double> anglelist = new ArrayList<>();
//        anglelist.add(90.54159);
//        anglelist.add(71.08507749999999);

        g.setColor(Color.BLUE);
        final Double[] truth = {4.0,-4.0};
        TextDetection.drawCircle(truth[0],truth[1],0.3,g);


        g.setColor(Color.GREEN);
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
                System.out.println("trueangle: " + diffangle);
                anglelist.add(diffangle);
            }
            firstangle = angle;
        }


        Double[] answer = TextDetection.cal_corrdinate(anglelist, corrdinatelist, direction, g);
        System.out.println(Arrays.toString(answer) + " " + TextDetection.dis(answer, truth));
        firstangle = 0.0;
        for (Double[] point : corrdinatelist) {
            double diffx = point[0] - answer[0];
            double diffy = point[1] - answer[1];
            double angle = Math.toDegrees(Math.atan(diffy / diffx));
            if (diffx < 0) angle += 180;
            if (diffy < 0 && diffx > 0) angle += 360;
//System.out.println(angle);
            if (firstangle != 0.0) {
                double diffangle = -angle + firstangle;
                if (diffangle < 0.0) diffangle += 360.0;
                System.out.println("answerangle: " + diffangle);
            }
            firstangle = angle;
        }
    }

    public static void main(String args[]) {
        new Main3().paint(null);
    }


}
