import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//做误差分析曲线图
//main12 将人工误差变成点的误差 一个勉强能用的东西
//debug main121 TODO:把加权平均反过来

public class Main121 extends JFrame {


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
                    g.setColor(Color.black);
                    for (Double[] location : truecorrdinatelist) {
                        TextDetection2.drawCircle(location[0], location[1], 0.3, g);
                    }
                    TextDetection2.drawCircle(truth[0], truth[1], 0.3, g);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);
                    direction.add(-1);
                    final ArrayList<Double> anglelist0 = new ArrayList<>();
                    double firstangle=0.0;
                    for(int j0=0;j0<truecorrdinatelist.size();++j0){
                        Double[] point = truecorrdinatelist.get(j0);
                        double diffx = point[0] - truth[0];
                        double diffy = point[1] - truth[1];
                        double angle = Math.toDegrees(Math.atan(diffy / diffx));
                        if (diffx < 0) angle += 180;
                        if (diffy < 0 && diffx > 0) angle += 360;
                        if (firstangle!=0.0) {
                            double diffangle = -angle + firstangle;
                            if (diffangle < 0.0) diffangle += 360.0;
                            anglelist0.add(diffangle);
                        }
                        firstangle = angle;
                    }


                    double turnangle = Math.PI / 100 * i * 2;
                    double diffp[][]=new double[4][4];
                    Double answers[][]=new Double[4][2];
                    g.setColor(Color.MAGENTA);
                    ArrayList<Double[]> corrdinatelist=new ArrayList<>();
                    for (int j = 0; j < truecorrdinatelist.size(); j++) {
                        corrdinatelist.add(new Double[]{
                                truecorrdinatelist.get(j)[0]+(Math.random()-0.5)*4,
                                truecorrdinatelist.get(j)[1]+(Math.random()-0.5)*4 });
                        TextDetection.drawCircle(corrdinatelist.get(j)[0],corrdinatelist.get(j)[1],0.3,g);
                    }


                    for (int j = 0; j < corrdinatelist.size(); j++) {



                        Double[] point2 = corrdinatelist.get(j);
                        corrdinatelist.remove(j);
                        final ArrayList<Double> anglelist = new ArrayList<>();
                        double oldangle = 0.0;
                        if(j==0){
                            for (int k = 1; k < anglelist0.size(); k++) {
                                anglelist.add(anglelist0.get(k));
                            }
                        } else {
                            for (int k = 0; k < anglelist0.size(); k++) {
                                oldangle += anglelist0.get(k);
                                if (k != j - 1) {
                                    anglelist.add(oldangle);
                                    oldangle = 0.0;
                                }
                            }
                        }
                        System.out.println(anglelist);
                        System.out.println(anglelist0);

                        for (int j1 = 0; j1 < corrdinatelist.size(); j1++) {
                            double maxdiff = 0;
                            for (int j2 = 0; j2 < 100; ++j2) {
                                ArrayList<Double[]> corrdinatelist2 = new ArrayList<>();
                                for (Double[] point : corrdinatelist) {
                                    corrdinatelist2.add(new Double[]{point[0], point[1]});
                                }
                                double angle = Math.PI * j2 / 50;
                                corrdinatelist2.get(j1)[0] += Math.cos(angle);
                                corrdinatelist2.get(j1)[1] += Math.sin(angle);
                                Double[] answer = work(corrdinatelist2, g, direction, turnangle, truth, 0.0, anglelist, Color.white);
                                double difflength = TextDetection2.dis(truth,answer);
                                maxdiff=Math.max(maxdiff,difflength);
                            }
                            diffp[j][(j1<j ? j1 : j1+1)]=maxdiff;
                        }



                        answers[j] = work(corrdinatelist, g, direction, turnangle, truth, 0.0,anglelist,colors[j]);
                        g.setColor(Color.blue);
                        TextDetection.drawCircle(answers[j][0],answers[j][1],0.1,g);

                        corrdinatelist.add(j,point2);
                    }
                    //去掉j. 旋转j1
                    //显示一个普通平均值
                    double dx0=0.0, dy0=0.0, da0=0.0;
                    for(int j=0;j<4;++j){
                        double ds = 1;
                        dx0+=answers[j][0]*ds;
                        dy0+=answers[j][1]*ds;
                        da0+=ds;
                    }
                    dx0/=da0;
                    dy0/=da0;
                    g.setColor(Color.MAGENTA);
                    TextDetection2.drawCircle(dx0,dy0, 0.1, g);
                    //显示加权平均值

                    double dx=0.0, dy=0.0, da=0.0;
                    for(int j=0;j<4;++j){
                        double ds = 1/(diffp[j][0]+diffp[j][1]+diffp[j][2]+diffp[j][3]);
                        dx+=answers[j][0]*ds;
                        dy+=answers[j][1]*ds;
                        da+=ds;
                    }
                    dx/=da;
                    dy/=da;
                    g.setColor(Color.RED);
                    TextDetection2.drawCircle(dx,dy, 0.1, g);

//                    dx=2*dx0-dx;
//                    dy=2*dy0-dy;

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
            new Main121().paint(null);
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
//            TextDetection2.drawCircle(randomPoint[0],randomPoint[1],0.1,g);
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
        return answer;
    }
}
