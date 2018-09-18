
import javax.swing.JPanel;



        import org.jfree.chart.ChartFactory;

        import org.jfree.chart.ChartPanel;

        import org.jfree.chart.JFreeChart;

        import org.jfree.chart.axis.NumberAxis;

        import org.jfree.chart.plot.CategoryPlot;

        import org.jfree.chart.plot.PlotOrientation;

        import org.jfree.data.category.DefaultCategoryDataset;

        import org.jfree.ui.ApplicationFrame;

        import org.jfree.ui.RefineryUtilities;

public class LineChart extends ApplicationFrame {

    /**

     *

     */

    private static final long serialVersionUID = 1L;

    public LineChart(String s) {

        super(s);

        setContentPane(createDemoLine());

        this.pack();

        RefineryUtilities.centerFrameOnScreen(this);

        this.setVisible(true);

        // this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

public static void main(String[] args) {

   LineChart fjc = new LineChart("折线图");

   fjc.pack();

   RefineryUtilities.centerFrameOnScreen(fjc);

   fjc.setVisible(true);

   fjc.setDefaultCloseOperation(HIDE_ON_CLOSE);

   }

// 生成显示图表的面板

    public static JPanel createDemoLine() {

        JFreeChart jfreechart = createChart(createDataset());

        return new ChartPanel(jfreechart);

    }

// 生成图表主对象JFreeChart

    public static JFreeChart createChart(DefaultCategoryDataset linedataset) {

        // 定义图表对象

        JFreeChart chart = ChartFactory.createLineChart("历史记录曲线", //折线图名称

                "时间", // 横坐标名称

                "倾角（X轴、Y轴）", // 纵坐标名称

                linedataset, // 数据

                PlotOrientation.VERTICAL, // 水平显示图像

                true, // include legend

                true, // tooltips

                false // urls

        );

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setRangeGridlinesVisible(true); //是否显示格子线

        plot.setBackgroundAlpha(0.3f); //设置背景透明度

        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();

        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        rangeAxis.setAutoRangeIncludesZero(true);

        rangeAxis.setUpperMargin(0.20);

        rangeAxis.setLabelAngle(Math.PI / 2.0);

        return chart;

    }

// 生成数据

    public static DefaultCategoryDataset createDataset() {

        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();

        MyData md=new MyData();

        double x=md.getX();

        double y=md.getX();

        double t=md.getX();

        draw(x,y,t,linedataset);

        return linedataset;

    }

    public static DefaultCategoryDataset draw(double x,double y,double t,DefaultCategoryDataset linedataset){

        //data here!
        DefaultCategoryDataset lds = new DefaultCategoryDataset();

        String series1 = "difference";

        // 横轴名称(列名称)

        String type1 = "1月";

        String type2 = "2月";

        String type3 = "3月";

        String type4="4月";

        lds=linedataset;

        lds.addValue(4.2, series1, "");
        lds.addValue(3.2, series1, "");
        lds.addValue(2.2, series1, "");
        lds.addValue(1.2, series1, "");


        return linedataset;

    }

}

class MyData{
    double x;
    double y;
    double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}