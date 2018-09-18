
import java.awt.Color;
        import java.awt.FlowLayout;
        import java.awt.Graphics;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JButton;
        import javax.swing.JFrame;






@SuppressWarnings("serial")
public class MyFrame extends JFrame {

    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command=e.getActionCommand();
            if(command.equals("Line")){
                shape=new Line();
            }else if(command.equals("Oval")){
                shape=new Oval();
            }else{
                shape=new Rectangle();
            }
            shape.setStartX(MyUtil.random(0, 1000));
            shape.setStartY(MyUtil.random(0, 600));
            shape.setEndX(MyUtil.random(0, 1000));
            shape.setEndY(MyUtil.random(0, 600));
            // shape.setColor(MyUtil.randomColor());
            shape.setColor(Color.black);
            repaint();
        }

    }

    private JButton lineButton;
    private JButton ovalButton;
    private JButton recButton;


    //  private Line line = null;
//  private Rectangle rec = null;
//  private Oval oval = null;
    private Shape shape=null;
    public MyFrame() {
        this.setSize(1000, 600);
        this.setTitle("绘图窗口");
        // super.setTitle("我的第一个窗口");
        this.setResizable(false);
        this.setLocationRelativeTo(null);// 窗口居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置默认的关闭操作

        lineButton = new JButton("Line");
        ovalButton = new JButton("Oval");
        recButton = new JButton("Rectangle");
//      recButton.addActionListener(new ActionListener() {
//
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              rec = new Rectangle();
//              rec.setStartX(MyUtil.random(0, 1000));
//              rec.setStartY(MyUtil.random(0, 600));
//              rec.setEndX(MyUtil.random(0, 1000));
//              rec.setEndY(MyUtil.random(0, 600));
//              repaint();
//
//          }
//      });

//      ovalButton.addActionListener(new ActionListener() {
//
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              oval = new Oval();
//              oval.setStartX(MyUtil.random(0, 1000));
//              oval.setStartY(MyUtil.random(0, 600));
//              oval.setEndX(MyUtil.random(0, 1000));
//              oval.setEndY(MyUtil.random(0, 600));
//              repaint();
//
//          }
//      });
//      lineButton.addActionListener(new ActionListener() {
//          /**
//           * 点击按钮后要执行的方法
//           */
//          @Override
//          public void actionPerformed(ActionEvent e) {
//
//              line = new Line();
//              line.setStartX(MyUtil.random(0, 1000));
//              line.setStartY(MyUtil.random(0, 600));
//              line.setEndX(MyUtil.random(0, 1000));
//              line.setEndY(MyUtil.random(0, 600));
//
//              repaint();
//          }
//      });// 需要放一个Action监听器
        ActionListener handler=new ButtonHandler();
        lineButton.addActionListener(handler);
        ovalButton.addActionListener(handler);
        recButton.addActionListener(handler);
        this.setLayout(new FlowLayout());// 设置流式布局管理器
        this.add(lineButton);
        this.add(ovalButton);
        this.add(recButton);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(shape!=null){
            shape.draw(g);
            shape.calculate(g);
        }
//      if (line != null) {
//          line.draw(g);
//      }
//      if (rec != null) {
//          rec.draw(g);
//      }
//      if (oval != null) {
//          oval.draw(g);
//      }

    }
}
