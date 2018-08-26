import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main implements ActionListener{
	private JFrame f,f_2;
	private JTextField jtf,jtf_2;
	private JButton jb,jb_2,jb_3,jb_4,jb_5;
	private JLabel label,label_2,label_3;
	private JPanel jp,jp_2;
	private JFileChooser fc,fc_2;
	private Container c;
	private File myFile_1;
	private File myFile_2;
	private JProgressBar jpb;
	private Timer timer;
	private boolean ctn;
	public static void main(String args[]){
		new Main().go();
	}
	
	public void go(){
		f = new JFrame("Choose file");
		f_2 = new JFrame("Warning");
		jp = new JPanel();
		jp_2 = new JPanel();
		jb = new JButton("Open");
		jb_2 = new JButton("Open");
		jb_3 = new JButton("开始复制");
		jb_4 = new JButton("是");
		jb_5 = new JButton("否");
		jtf = new JTextField(30);
		jtf_2 = new JTextField(30);
		label = new JLabel("源文件路径");
		label_2 = new JLabel("目标位置路径");
		label_3 = new JLabel("警告，出现未知错误，继续复制可能会损坏文件。是否继续？");
		
		jp.setLayout(new GridLayout(2,3));
		jp.add(label);  jp.add(jtf);  jp.add(jb);
		jp.add(label_2);  jp.add(jtf_2);  jp.add(jb_2);
		jp_2.setLayout(new FlowLayout());
		jp_2.add(jb_4);
		jp_2.add(jb_5);
		c= f.getContentPane();
		jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		jpb.setValue(0);
		jpb.setStringPainted(true);
		jpb.setPreferredSize(new Dimension(350, 20));
		jpb.setBackground(Color.white);
		jpb.setForeground(Color.blue);
		
		f.getContentPane().add(jp, "Center");
		f.getContentPane().add(jb_3, "South");
		f.getContentPane().add(jpb, "North");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 140);
		f.setVisible(true);
		
		f_2.getContentPane().add(label_3, "North");
		f_2.getContentPane().add(jp_2, "South");
		f_2.setSize(400, 120);
		f_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fc = new JFileChooser();
		fc_2 = new JFileChooser();
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int selected = fc.showOpenDialog(c);
				if(selected == JFileChooser.APPROVE_OPTION){
					myFile_1 = fc.getSelectedFile();
					jtf.setText(myFile_1.getPath());
				}
			}
		});
		
		jb_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fc_2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int selected_2 = fc_2.showOpenDialog(c);
				if(selected_2 == JFileChooser.APPROVE_OPTION){
					myFile_2 = fc_2.getSelectedFile();
					jtf_2.setText(myFile_2.getPath());
				}
			}
		});
		timer = new Timer(100,this);
		jb_3.addActionListener(this);


	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == jb_3){
			timer.start();
		}
		ctn = true;
		if(e.getSource() == timer){
			int value = jpb.getValue();
			if(value < 20){
				jpb.setValue(++value);
			}
			if(value == 20){
				timer.stop();
				f_2.setVisible(true);
				
				jb_4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ctn = true;
						f_2.setVisible(false);
						timer.start();
						
					}
				});
				
				jb_5.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ctn = false;
						f_2.setVisible(false);
						jpb.setValue(0);
					}
				});
			}
			
			if(ctn == true){
				jpb.setValue(++value);
			}

		}
	}
}
