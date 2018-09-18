import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//做误差分析曲线图
//main11 四个点的定位加上暴力求误差 加权平均和指向加权偏转值

public class Main11 extends JFrame {


    @Override
    public void paint(Graphics g) {
        setTitle("Tutorial");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color[] colors = new Color[4];
        colors[0]=Color.blue;
        colors[1]=Color.blue;
        colors[2]=Color.blue;
        colors[3]=Color.blue;
//        colors[1]=Color.red;
//        colors[2]=Color.orange;
//        colors[3]=Color.black;
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
                    difflist.add(Math.random()*20.0);
                    difflist.add(Math.random()*20.0);
                    difflist.add(Math.random()*20.0);
                    difflist.add(Math.random()*20.0);


                    double turnangle = Math.PI / 100 * i * 2;
                    double diffp[][]=new double[4][4];
                    Double answers[][]=new Double[4][2];
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

                        for (int j1 = 0; j1 < truecorrdinatelist.size(); j1++) {
                            double maxdiff = 0;
                            for (int j2 = 0; j2 < 100; ++j2) {
                                ArrayList<Double[]> truecorrdinatelist2 = new ArrayList<>();
                                for (Double[] point : truecorrdinatelist) {
                                    truecorrdinatelist2.add(new Double[]{point[0], point[1]});
                                }
                                double angle = Math.PI * j2 / 50;
                                truecorrdinatelist2.get(j1)[0] += Math.cos(angle);
                                truecorrdinatelist2.get(j1)[1] += Math.sin(angle);
                                Double[] answer = work(truecorrdinatelist2, g, direction, turnangle, truth, 0.0, anglelist, Color.white);
                                double difflength = TextDetection2.dis(truth,answer);
                                maxdiff=Math.max(maxdiff,difflength);
                            }
                            System.out.println(j+" "+j1+" "+maxdiff);
                            diffp[j][(j1<j ? j1 : j1+1)]=maxdiff;
                        }



                        answers[j] = work(truecorrdinatelist, g, direction, turnangle, truth, 0.0,anglelist,colors[j]);
                        g.setColor(Color.blue);
                        TextDetection.drawCircle(answers[j][0],answers[j][1],0.1,g);

                        truecorrdinatelist.add(j,point2);
                    }
                    //去掉j. 旋转j1
                    double dx=0.0, dy=0.0, da=0.0;
                    for(int j=0;j<4;++j){
                        double ds = (diffp[j][0]+diffp[j][1]+diffp[j][2]+diffp[j][3]);
                        dx+=answers[j][0]*ds;
                        dy+=answers[j][1]*ds;
                        da+=ds;
                    }
                    dx/=da;
                    dy/=da;
                    g.setColor(Color.RED);
                    TextDetection2.drawCircle(dx,dy, 0.1, g);
                    for(int j=0;j<4;++j){
                        for(int j1=0;j1<4;++j1){//双方被去掉的点既是对方的弱点
                            if(j==j1)continue;
                            double diff1=diffp[j1][j];//去掉j1后 j的不确定度 也就是第j1组中第j个点
                            double diff2=diffp[j][j1];
//                            double mid = -(1/diff1-1/diff2)/(1/diff1+1/diff2)/8;
                            double mid = (diff1-diff2)/(diff1+diff2)/8;
                            dx+=(answers[j][0]-answers[j1][0])*mid;
                            dy+=(answers[j][1]-answers[j1][1])*mid;
                        }
                    }



                g.setColor(Color.BLUE);
                TextDetection2.drawCircle(truth[0], truth[1], 0.3, g);
                g.setColor(Color.BLACK);
                TextDetection2.drawCircle(dx,dy, 0.2, g);
                    System.out.println(dx + " " + dy);

                }
    }

    public static void main(String args[]) {
            new Main11().paint(null);
    }


    public Double[] work(List<Double[]> truecorrdinatelist, Graphics g, List<Integer> direction, double turnangle, Double[] truth, double randomField, ArrayList<Double> anglelist, Color color){

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
        return answer;
    }
}
