import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//做误差分析曲线图
//main13 实地测试

public class Main13 extends JFrame {

    //TODO:加权反了.去版本14吧.

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
        final Double[] truth = {1450d, 236d};
        for (int i = 0; i < 1; i++) {

            final List<Double[]> truecorrdinatelist = new ArrayList<>();
            final List<Integer> direction = new ArrayList<>();

            //构建truecorrdinatelist 真实点表 招牌的实际位置
//            Double[] location7 = {790d, 146d};
//            truecorrdinatelist.add(location7);//JNBY
//            Double[] location5 = {863d, 140d};
//            truecorrdinatelist.add(location5);//Donoratico
//            Double[] location1 = {926d, 137d};
//            truecorrdinatelist.add(location1);//MarisFrolg
//            Double[] location4 = {1012d, 138d};
//            truecorrdinatelist.add(location4);//Ochrily
//            Double[] locatione = {1087d, 143d};
//            truecorrdinatelist.add(locatione);//阿芙
//            Double[] locationf = {1171d, 155d};
//            truecorrdinatelist.add(locationf);//blue erdos
            Double[] location1 = {1357d, 188d};
            truecorrdinatelist.add(location1);//MsFlora
            Double[] location4 = {1426d, 196d};
            truecorrdinatelist.add(location4);//Ochrily
            Double[] locatione = {1484d, 196d};
            truecorrdinatelist.add(locatione);//阿芙
            Double[] locationf = {1548d, 288d};
            truecorrdinatelist.add(locationf);//blue erdos
//            输出真实点表:黑色
            g.setColor(Color.black);
            for (Double[] location : truecorrdinatelist) {
                TextDetection3.drawCircle(location[0], location[1], 0.3, g);
            }
            //输出真实自己位置:黑色
            TextDetection3.drawCircle(truth[0], truth[1], 0.3, g);
            direction.add(-1);
            direction.add(-1);
            direction.add(-1);
            direction.add(-1);
            //构建真实夹角表
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
            //构建虚拟坐标点表 店中心的位置
            ArrayList<Double[]> corrdinatelist=new ArrayList<>();
//            Double[] locationa = {790d, 91d};
//            corrdinatelist.add(locationa);//JNBY
//            Double[] locationb = {857d, 88d};
//            corrdinatelist.add(locationb);//Donoratico
//            Double[] locationc = {921d, 87d};
//            corrdinatelist.add(locationc);//MarisFrolg
//            Double[] locationd = {1011d, 88d};
//            corrdinatelist.add(locationd);//Ochrily
//            Double[] location2 = {1088d, 132d};
//            corrdinatelist.add(location2);//阿芙
//            Double[] location3 = {1174d, 103d};
//            corrdinatelist.add(location3);//blue erdos
            Double[] locationc = {1351d, 143d};
            corrdinatelist.add(locationc);//MsFlora
            Double[] locationd = {1418d, 146d};
            corrdinatelist.add(locationd);//STACCATO
            Double[] location2 = {1473d, 174d};
            corrdinatelist.add(location2);//木九十
            Double[] location3 = {1552d, 273d};
            corrdinatelist.add(location3);//OVER SEA

            //四个坐标点的情况:依次去除一个坐标点, 重复四次
            for (int j = 0; j < corrdinatelist.size(); j++) {
                Double[] point2 = corrdinatelist.get(j);
                //输出虚拟坐标点表 颜色:粉色
                g.setColor(Color.MAGENTA);
                TextDetection3.drawCircle(corrdinatelist.get(j)[0], corrdinatelist.get(j)[1], 0.3, g);

                //去除坐标点
                corrdinatelist.remove(j);
                //构建夹角表:复制并合并一个夹角
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

                //对(三个点中)每个点旋转一周,计算误差
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
                        double difflength = TextDetection3.dis(truth,answer);
                        maxdiff=Math.max(maxdiff,difflength);
                    }
                    diffp[j][(j1<j ? j1 : j1+1)]=maxdiff;
                }


                //计算三点定位的节点(一共四个)
                answers[j] = work(corrdinatelist, g, direction, turnangle, truth, 0.0,anglelist,colors[j]);
                //绘制三点定位的节点 颜色:蓝色
                g.setColor(Color.blue);
                TextDetection3.drawCircle(answers[j][0],answers[j][1],0.1,g);

                //复原虚拟坐标点表
                corrdinatelist.add(j,point2);
            }

            //综合四个点: 显示一个普通平均值 颜色:粉色
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
            TextDetection3.drawCircle(dx0,dy0, 0.1, g);

            //显示加权平均值 颜色:红色
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
            TextDetection3.drawCircle(dx,dy, 0.1, g);
//不知道要不要加:相当于将基准点从"加权平均值"移动到"普通平均值"
            //哦 天哪 加权平均值好像加权反了 咱们去版本14吧.
//                    dx=2*dx0-dx;
//                    dy=2*dy0-dy;

            //两两比较三点定位的结果,计算偏转向量
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


//大蓝圈:重新绘制真实坐标groundtruth
            g.setColor(Color.BLUE);
            TextDetection3.drawCircle(truth[0], truth[1], 0.3, g);

            //大黑圈:最后的定位结果
            g.setColor(Color.BLACK);
            TextDetection3.drawCircle(dx,dy, 0.2, g);
            System.out.println(dx + " " + dy + " " +
                    TextDetection3.dis(new Double[]{dx,dy},truth));

        }
    }

    public static void main(String args[]) {
        new Main13().paint(null);
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
            TextDetection3.drawCircle(randomPoint[0],randomPoint[1],0.1,g);
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
        Double[] answer = TextDetection3.cal_corrdinate(anglelist, corrdinatelist, direction, g);
//            System.out.println("turnangle"+turnangle+ "difflength" + difflength + "turnangle" + Math.sin(turnangle) + " " + Math.cos(turnangle));
//            TextDetection3.drawCircle(turnangle, difflength, 0.1, g);
//        g.setColor(Color.BLUE);
//        TextDetection3.drawCircle(answer[0], answer[1], 0.05, g);
//        try {
//            Thread.sleep(1000);
//        }catch (Exception e){}
//        g.setColor(Color.GRAY);
//        TextDetection3.drawCircle(answer[0], answer[1], 0.05, g);
        return answer;
    }
}
