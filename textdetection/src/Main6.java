import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//做误差分析曲线图
//main6 一个点的定位

public class Main6 extends JFrame {


    @Override
    public void paint(Graphics g) {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color[] colors = new Color[4];
        colors[0]=Color.blue;
        colors[1]=Color.red;
        colors[2]=Color.orange;
        colors[3]=Color.black;
        final Double[] truth = {4.0, 1.0};
                for (int i = 0; i < 1; i++) {
                    final List<Double[]> corrdinatelist = new ArrayList<>();
                    final List<Double[]> truecorrdinatelist = new ArrayList<>();
                    final List<Integer> direction = new ArrayList<>();

                    Double[] location7 = {-3.0, -3.0};
                    truecorrdinatelist.add(location7);//MISHA
                    Double[] location5 = {0.0, 6.5};
                    truecorrdinatelist.add(location5);//CITY
                    Double[] location1 = {7.5, 6.5};
                    truecorrdinatelist.add(location1);//VERO
                    Double[] location4 = {11.5, 6.5};
                    truecorrdinatelist.add(location4);//VERO
                    for (Double[] location : truecorrdinatelist) {
                        TextDetection2.drawCircle(location[0], location[1], 0.3, g);
                    }
                    TextDetection2.drawCircle(truth[0], truth[1], 0.3, g);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);

                    final ArrayList<Double> difflist = new ArrayList<>();
                    difflist.add(Math.random()*30.0);
                    difflist.add(Math.random()*30.0);
                    difflist.add(Math.random()*30.0);
                    difflist.add(Math.random()*30.0);


                    double turnangle = Math.PI / 100 * i * 2;
                    for (int j = 0; j < truecorrdinatelist.size(); j++) {




                        final ArrayList<Double> anglelist = new ArrayList<>();
                        double firstangle=0.0;
                        for(int j0=0;j0<truecorrdinatelist.size();++j0){
                            Double[] point = truecorrdinatelist.get(j0);
                            double diffx = point[0] - truth[0];
                            double diffy = point[1] - truth[1];
                            double angle = Math.toDegrees(Math.atan(diffy / diffx))+difflist.get(j0);
                            if (diffx < 0) angle += 180;
                            if (diffy < 0 && diffx > 0) angle += 360;
                            if(j0==j){
                                continue;
                            }
                            if (firstangle!=0.0) {
                                double diffangle = -angle + firstangle;
                                if (diffangle < 0.0) diffangle += 360.0;
                                anglelist.add(diffangle);
                            }
                            firstangle = angle;
                        }





                        Double[] point2 = truecorrdinatelist.get(j);
                        truecorrdinatelist.remove(j);





                        work(truecorrdinatelist, g, direction, turnangle, truth, 0.0,anglelist,colors[j]);
                        truecorrdinatelist.add(j,point2);
                    }

                g.setColor(Color.BLUE);
                TextDetection2.drawCircle(truth[0], truth[1], 0.3, g);
        }
    }

    public static void main(String args[]) {
            new Main6().paint(null);
    }


    public double work(List<Double[]> truecorrdinatelist, Graphics g, List<Integer> direction, double turnangle, Double[] truth, double randomField, ArrayList<Double> anglelist, Color color){

        ArrayList<Double[]> corrdinatelist = new ArrayList<>();
        for(int i=0;i<truecorrdinatelist.size();++i) {
            Double[] point =truecorrdinatelist.get(i);
            Double[] randomPoint;
            do {
                randomPoint = new Double[]{point[0] + (-0.5 + Math.random()) * randomField, point[1] + (Math.random() - 0.5) * randomField};
            }while (Math.abs(randomPoint[1]-point[1]) < randomField * 0.4 && Math.abs(randomPoint[0]-point[0]) < randomField * 0.4);
            corrdinatelist.add(randomPoint);
            TextDetection2.drawCircle(randomPoint[0],randomPoint[1],0.1,g);
        }

//表示方向 数量是和coordinatelist一样的
//如果是1 代表(y-y1)*(x2-x1)>(x-x1)*(y2-y1)
//也就是当y2>y1 在y+的方向 当y2<y1 在y-的方向
//否则是-1
//当y2=y1时 x2>x1 在y上面
//当x2=x1时 y2>y1 在x左边
//注意 最后一个点的顺时针逆时针方向是按"360-其他"那个角度定的.如果那个(或者别的任何一个)角度大于180,
//后续会用360减它,并且把direction反向.


        g.setColor(color);
        Double[] answer = TextDetection2.cal_corrdinate(anglelist, corrdinatelist, direction, g);
        double difflength = TextDetection2.dis(truth,answer);
//            System.out.println("turnangle"+turnangle+ "difflength" + difflength + "turnangle" + Math.sin(turnangle) + " " + Math.cos(turnangle));
//            TextDetection2.drawCircle(turnangle, difflength, 0.1, g);
//        g.setColor(Color.BLUE);
//        TextDetection2.drawCircle(answer[0], answer[1], 0.05, g);
//        try {
//            Thread.sleep(1000);
//        }catch (Exception e){}
//        g.setColor(Color.GRAY);
//        TextDetection2.drawCircle(answer[0], answer[1], 0.05, g);
        System.out.println(answer[0] + " " + answer[1]);
        return difflength;
    }
}
