import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//做误差分析曲线图
//main7 尝试预估店面厚度
//findbug: set things in textdetection
public class Main8 extends JFrame {


    @Override
    public void paint(Graphics g) {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final Double[] truth = {4.0, 1.0};
                for (int i = 0; i < 1; i++) {
                    final List<Double[]> truecorrdinatelist = new ArrayList<>();
                    final List<Integer> direction = new ArrayList<>();

                    Double[] location7 = {3.0, -3.0};
                    truecorrdinatelist.add(location7);//MISHA
                    Double[] location5 = {0.0, 6.5};
                    truecorrdinatelist.add(location5);//CITY
                    Double[] location4 = {7.5, 6.5};
                    truecorrdinatelist.add(location4);//VERO
                    for (Double[] location : truecorrdinatelist) {
                        TextDetection.drawCircle(location[0], location[1], 0.3, g);
                    }
                    TextDetection.drawCircle(truth[0], truth[1], 0.3, g);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);

                    double turnangle = Math.PI / 100 * i * 2;
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
                    System.out.println(anglelist);
                    Double[] answer0 = work(truecorrdinatelist, g, direction, turnangle, truth, 0.0, anglelist);
                    double shopWidth = 3.0;
                    //预估一个点 然后缩小距离
                    ArrayList<Double[]> truecorrdinatelist2 = new ArrayList<>();
                    for(Double[] point:truecorrdinatelist){
                        truecorrdinatelist2.add(new Double[]{point[0],point[1]});
                    }
                    double angle = 0;
                    for(int j=0;j<truecorrdinatelist2.size();++j) {
                        Double[] point = truecorrdinatelist2.get(j);
                        angle += Math.atan((point[1] - truth[1]) / (point[0] - truth[0]));
                    }

                    angle/=truecorrdinatelist2.size();
                    for(int j=0;j<truecorrdinatelist2.size();++j) {
                        double rand = .5;
                        Double[] point = truecorrdinatelist2.get(j);
                        if(point[0]-truth[0]>0) {
                            point[1] -= Math.sin(angle) * shopWidth * rand;
                            point[0] -= Math.cos(angle) * shopWidth * rand;
                        } else {
                            point[1] += Math.sin(angle) * shopWidth * rand;
                            point[0] += Math.cos(angle) * shopWidth * rand;
                        }
                        g.setColor(Color.ORANGE);
                        TextDetection.drawCircle(point[0], point[1], 0.3, g);
                        System.out.println(point[0]+" "+point[1]);
                    }

//                    Double[] answer1 = work(truecorrdinatelist2, g, direction, turnangle, truth, 0.0, anglelist);


                    for(int rand1=0;rand1<64;++rand1) {
//                        if(rand1 != 2 && rand1 != 3 && rand1 != 6 && rand1 != 7 && rand1 != 18 && rand1 != 19 && rand1 != 22 && rand1 != 23)continue;
                        if(rand1 != 18 && rand1 != 19)continue;
                        truecorrdinatelist2 = new ArrayList<>();
                        for (Double[] point : truecorrdinatelist) {
                            truecorrdinatelist2.add(new Double[]{point[0], point[1]});
                        }
                        for (int j = 0; j < truecorrdinatelist2.size(); ++j) {
                            int rand=rand1 / (int)Math.pow(4,j);
                            Double[] point = truecorrdinatelist2.get(j);
                            if (rand % 4 == 2 || rand % 4 == 3)
                                point[1] -= shopWidth / 2.0;
                            else
                                point[1] += shopWidth / 2.0;
                            if (rand % 2 == 0)
                                point[0] -= shopWidth / 2.0;
                            else
                                point[0] += shopWidth / 2.0;
                            g.setColor(Color.RED);
                            TextDetection.drawCircle(point[0], point[1], 0.3, g);
                            System.out.println(point[0] + " " + point[1]);
                        }
                        Double[] answer2 = work(truecorrdinatelist2, g, direction, turnangle, truth, 0.0, anglelist);
                        try {
                            Thread.sleep(1000);
                        }catch(Exception e){}
                    }

                g.setColor(Color.BLUE);
                TextDetection.drawCircle(truth[0], truth[1], 0.3, g);
        }
    }

    public static void main(String args[]) {
        new Main8().paint(null);
    }


    public Double[] work(List<Double[]> truecorrdinatelist, Graphics g, List<Integer> direction, double turnangle, Double[] truth, double randomField, List<Double> anglelist){

        ArrayList<Double[]> corrdinatelist = new ArrayList<>();
        g.setColor(Color.CYAN);
        for(int i=0;i<truecorrdinatelist.size();++i) {
            Double[] point =truecorrdinatelist.get(i);
            Double[] randomPoint;
            do {
                randomPoint = new Double[]{point[0] + (-0.5 + Math.random()) * randomField, point[1] + (Math.random() - 0.5) * randomField};
            }while (Math.abs(randomPoint[1]-point[1]) < randomField * 0.4 && Math.abs(randomPoint[0]-point[0]) < randomField * 0.4);
            corrdinatelist.add(randomPoint);
            TextDetection.drawCircle(randomPoint[0],randomPoint[1],0.1,g);
        }

//表示方向 数量是和coordinatelist一样的
//如果是1 代表(y-y1)*(x2-x1)>(x-x1)*(y2-y1)
//也就是当y2>y1 在y+的方向 当y2<y1 在y-的方向
//否则是-1
//当y2=y1时 x2>x1 在y上面
//当x2=x1时 y2>y1 在x左边
//注意 最后一个点的顺时针逆时针方向是按"360-其他"那个角度定的.如果那个(或者别的任何一个)角度大于180,
//后续会用360减它,并且把direction反向.


        Double[] answer = TextDetection.cal_corrdinate(anglelist, corrdinatelist, direction, g);
//            System.out.println("turnangle"+turnangle+ "difflength" + difflength + "turnangle" + Math.sin(turnangle) + " " + Math.cos(turnangle));
//            TextDetection.drawCircle(turnangle, difflength, 0.1, g);
//        g.setColor(Color.BLUE);
//        TextDetection.drawCircle(answer[0], answer[1], 0.05, g);
//        try {
//            Thread.sleep(1);
//        }catch (Exception e){}
//        g.setColor(Color.GRAY);
//        TextDetection.drawCircle(answer[0], answer[1], 0.05, g);
        return answer;
    }
}
