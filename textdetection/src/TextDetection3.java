import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//加上绘制圆
public class TextDetection3 {


    static void drawCircle(double x, double y, double r, Graphics g) {

        final double comp = 1;
        int xc = (int) (x * comp) - 500;
//        if(xc>900) System.out.println("xcbig");
        int yc = (int) (y * comp) + 500;
//        if(yc>900) System.out.println("ycbig");
        int rc = (int) (r * 20);
        g.drawOval(xc - rc, yc - rc, rc * 2, rc * 2);
        System.out.println((xc - rc) + " " + (yc - rc) + " " + ( rc * 2));

    }
    //两点距离
    public static Double dis(Double[] a, Double[] b) {
        return Math.sqrt(dis_s(a, b));
    }

    //两点距离的平方
    public static Double dis_s(Double[] a, Double[] b) {
        return ((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
    }

    public static boolean lineCross(Double[] a, Double[] b, Double[] c, Double[] d) {
        if (!(Math.min(a[0], b[0]) <= Math.max(c[0], d[0]) && Math.min(c[1], d[1]) <= Math.max(a[1], b[1]) && Math.min(c[0], d[0]) <= Math.max(a[0], b[0]) && Math.min(a[1], b[1]) <= Math.max(c[1], d[1])))//这里的确如此，这一步是判定两矩形是否相交
            //1.线段ab的低点低于cd的最高点（可能重合） 2.cd的最左端小于ab的最右端（可能重合）
            //3.cd的最低点低于ab的最高点（加上条件1，两线段在竖直方向上重合） 4.ab的最左端小于cd的最右端（加上条件2，两直线在水平方向上重合）
            //综上4个条件，两条线段组成的矩形是重合的
            /*特别要注意一个矩形含于另一个矩形之内的情况*/
            return false;
    	       /*
    	       跨立实验：
    	       如果两条线段相交，那么必须跨立，就是以一条线段为标准，另一条线段的两端点一定在这条线段的两段
    	       也就是说a b两点在线段cd的两端，c d两点在线段ab的两端
    	       */
        double u, v, w, z;//分别记录两个向量
        u = (c[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (c[1] - a[1]);
        v = (d[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (d[1] - a[1]);
        w = (a[0] - c[0]) * (d[1] - c[1]) - (d[0] - c[0]) * (a[1] - c[1]);
        z = (b[0] - c[0]) * (d[1] - c[1]) - (d[0] - c[0]) * (b[1] - c[1]);
        return (u * v <= 0.00000001 && w * z <= 0.00000001);
    }

    public static Double[] grad_descent(List<Double> anglelist, List<Double[]> corrdinatelist, Double x, Double y) {
        Double a = 0.0000001;
        Double end = 0.00000001;
        int flag = 1;
        while (true) {
            Double dx = 0.0;
            Double dy = 0.0;
            Double[] co = {x, y};
            for (int i = 0; i < anglelist.size(); i++) {
                Double a2 = dis_s(corrdinatelist.get(i), corrdinatelist.get(i + 1));
                Double b2 = dis_s(corrdinatelist.get(i + 1), co);
                Double c2 = dis_s(corrdinatelist.get(i), co);
                Double d = 2 * ((b2 + c2 - a2) / (2 * Math.sqrt(b2) * Math.sqrt(c2) + 0.001) - Math.cos(3.1415926 * anglelist.get(i) / 180));
                dx = dx + d * (-4 * (corrdinatelist.get(i)[0] + corrdinatelist.get(i + 1)[0] - x * 2) * Math.sqrt(b2) * Math.sqrt(c2) - 2 * (b2 + c2 - a2) * (-1 * ((Math.sqrt(c2) / (Math.sqrt(b2) * (corrdinatelist.get(i + 1)[0] - x) + 0.001)) + (Math.sqrt(b2) / (Math.sqrt(c2) * (corrdinatelist.get(i)[0] - x) + 0.001)))));
                dy = dy + d * (-4 * (corrdinatelist.get(i)[1] + corrdinatelist.get(i + 1)[1] - y * 2) * Math.sqrt(b2) * Math.sqrt(c2) - 2 * (b2 + c2 - a2) * (-1 * ((Math.sqrt(c2) / (Math.sqrt(b2) * (corrdinatelist.get(i + 1)[1] - y) + 0.001)) + (Math.sqrt(b2) / (Math.sqrt(c2) * (corrdinatelist.get(i)[1] - y) + 0.001)))));
            }
            if (Math.abs(a * dx) < end && Math.abs(a * dy) < end) {
                break;
            }
            flag++;
            x = x - a * dx;
            y = y - a * dy;
        }
        Double[] ans = {x, y};
        return ans;
    }


    public static Double[] cal_corrdinate(List<Double> anglelist0, List<Double[]> corrdinatelist, List<Integer> direction0, Graphics g) {
        ArrayList<Integer> direction = new ArrayList<>(direction0);
        ArrayList<Double> anglelist = new ArrayList<>(anglelist0);
//        System.out.println(anglelist);
//        for(Double[] point:corrdinatelist)
//            System.out.println(point[0]+ " "+point[1]);
//        System.out.println(direction);
        //计算scale，估计误差可接受范围
        double smin = 0.0, smax = 0.0;
        for ( Double[] i : corrdinatelist)
            for ( double j : i) {
                smin = Math.min(smin, j);
                smax = Math.max(smax, j);
            }

        double scale = smax - smin;

        //补上最后一个夹角
        double totangle = 0.0;
        for ( double angle : anglelist) {
            totangle += angle;
//            System.out.println(angle);
        }
        anglelist.add(360 - totangle);
//        System.out.println("tot: " + (360 - totangle));

        final int numOfPois = anglelist.size();
        //大于180°的-180
        for ( int i = 0; i < numOfPois; ++i) {
            if (anglelist.get(i) > 180.0) {
                anglelist.set(i, 360 - anglelist.get(i));
                direction.set(i, -1 * (direction.get(i)));
            }
        }

        List<Double[]> circles = new ArrayList<>();//存放圆心。一共有poi个元素，每个元素为[3][2],第一维代表两个圆心，第二维代表xy,[2][0]代表半径
        List<Double[]> results = new ArrayList<>();//存放交点。一共有poi个元素，每个元素为[2][2][2][2],
        //第一,二维代表相交圆的形态，第三维代表两个圆的两个交点，第四维代表xy


        //求出圆心
        for (int poi = 0; poi < numOfPois; ++poi) {
            int nextPoi = (poi + 1) % numOfPois;

            //取中点
            double midPointX = (corrdinatelist.get(poi)[0] + corrdinatelist.get(nextPoi)[0]) / 2;
            double midPointY = (corrdinatelist.get(poi)[1] + corrdinatelist.get(nextPoi)[1]) / 2;
            Double[] midPoint = {midPointX, midPointY};

            //中点指向圆心的向量（正负）
            double tangent = Math.abs(Math.tan(Math.toRadians(anglelist.get(poi))));//圆心角钝角
            double toCircleX = -(corrdinatelist.get(nextPoi)[1] - corrdinatelist.get(poi)[1]) / 2 / tangent;
            double toCircleY = (corrdinatelist.get(nextPoi)[0] - corrdinatelist.get(poi)[0]) / 2 / tangent;

            //两个对称的圆心:通过给定的方向,选择一个正确的圆心
            Double[] circle = new Double[3];
            circle[0] = midPointX + toCircleX;//圆心0 当a2>a1,b2>b1时，在上面
            circle[1] = midPointY + toCircleY;
            double x = circle[0], y = circle[1];
            double x1 = corrdinatelist.get(poi)[0], y1 = corrdinatelist.get(poi)[1];
            double x2 = corrdinatelist.get(nextPoi)[0], y2 = corrdinatelist.get(nextPoi)[1];
            double ans = (y - y1) * (x2 - x1) - (x - x1) * (y2 - y1);
            if (ans * direction.get(poi) > 0 ^ anglelist.get(poi) < 90.0) {
                circle[0] = midPointX - toCircleX;//圆心1 当a2>a1,b2>b1时，在下面
                circle[1] = midPointY - toCircleY;
            }
            //TODO:why abs?
            circle[2] = Math.abs(dis(midPoint, corrdinatelist.get(poi)) / Math.sin(Math.toRadians(anglelist.get(poi))));
//            System.out.println("poi: " + poi + " circle: " + Arrays.deepToString(circle));

            circles.add(circle);
//            drawCircle(circle[0], circle[1], circle[2], g);
        }

        //两两相交求出焦点
        for ( int poi = 0; poi < numOfPois; ++poi) {
            int nextPoi = (poi + 1) % numOfPois;

            //取圆心
            Double[] result = new Double[2];
            double a1 = circles.get(poi)[0];
            double b1 = circles.get(poi)[1];
            double r1 = circles.get(poi)[2];
            double a2 = circles.get(nextPoi)[0];
            double b2 = circles.get(nextPoi)[1];
            double r2 = circles.get(nextPoi)[2];

            //		System.out.println("a1: "+a1+" b1: "+b1+" r1: "+r1+" a2: "+a2+" b2: "+b2+" r2: "+r2);

            //方程(x-a1)2+(y-b1)2=r12和(x-a2)2+(y-b2)2=r22相减得到Ax+By+C=0的式子，其中A,B,C为
            double A = -2 * (a1 - a2);
            double B = -2 * (b1 - b2);
            double C = a1 * a1 - a2 * a2 + b1 * b1 - b2 * b2 - r1 * r1 + r2 * r2;
            //		System.out.println("ABC: "+A+" "+B+" "+C);
            //代入方程(x-a1)2+(y-b1)2=r12后得到形如AAx2+BBx+CC=0的式子，其中
            double AA = 1 + A * A / B / B;
            double BB = -2 * a1 + 2 * A / B * (C / B + b1);
            double CC = a1 * a1 + Math.pow(C / B + b1, 2) - r1 * r1;
            //		System.out.println("AABBCC: "+AA+" "+BB+" "+CC);

            //解出delta=BB2-4AACC
            double delta = BB * BB - 4 * AA * CC;

            if (delta < 0) {
                //返回单值：Ax+By+C=0和(x-a1)/(a2-a1)=(y-b1)/(b2-b1)联立的值
                //由于至少有一个相邻点的交点,不应该返回delta<0
                //当然, 这是仅有相邻点做交点的情况.4个点以上,加上不相邻点的情况,则另当别论
                System.out.println("error:delta<0");
            }
            double sqrtdelta = Math.sqrt(delta);
            result[0] = (-BB + sqrtdelta) / 2 / AA;
            result[1] = -(A * result[0] + C) / B;//两个交点,找那个不是公用交点的那个.
            if (dis_s(result, corrdinatelist.get(nextPoi)) < 1E-5) {
                result[0] = (-BB - sqrtdelta) / 2 / AA;
                result[1] = -(A * result[0] + C) / B;
            }

            results.add(result);
        }


        double max = 0;
        double midPointX = 0;
        double midPointY = 0;

        for ( int poi = 0; poi < numOfPois; ++poi)//计算最大边
        {
            int nextPoi = (poi + 1) % numOfPois;
            Double[] crosspoint = results.get(poi);
            Double[] nextpoint = results.get(nextPoi);
//            drawCircle(crosspoint[0], crosspoint[1], 0.2, g);

            max = Math.max(max, dis(crosspoint, nextpoint));
            midPointX += crosspoint[0];
            midPointY += crosspoint[1];
//	System.out.println(Arrays.toString(crosspoint)+"     "+dis_s(crosspoint,nextpoint));
        }

        Double[] answer = {midPointX / numOfPois, midPointY / numOfPois};
        answer=results.get(0);
//        System.out.println("dist:" + max + " pos: " + Arrays.toString(answer));

//        drawCircle(answer[0], answer[1], 0.2, g);

        return answer;
    }
}
