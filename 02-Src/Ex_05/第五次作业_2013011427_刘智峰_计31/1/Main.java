import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends MouseAdapter{
	private JFrame f,f_2;
	private JButton jb,jb_2;
	private JTextArea jt;
	private JScrollPane scroll;
	private JTextField jtf;
	private JPasswordField jtf_2;
	private JPanel jp;
	private JLabel name,password;
	
	
	public static void main(String args[]){
		Main m = new Main();
		m.go();
	}
	
	public void go(){
		String s = "name: name" + '\r' +'\n' + "password: password" + '\n';
		
		f = new JFrame("Dialog");
		jb = new JButton("Dialog");
		jt = new JTextArea();
		jt.setText(s);
		scroll =new JScrollPane(jt);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		f.getContentPane().add(jb, "North");
		f.getContentPane().add(scroll);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
		
		jb.addMouseListener(this);
		
		f_2 = new JFrame("Name and Pass");
		name = new JLabel("Name");
		password = new JLabel("Pass");
		jtf = new JTextField(30);
		jtf_2 = new JPasswordField(30);
		jb_2 = new JButton("OK");
		jp = new JPanel();
		jp.setLayout(new GridLayout(2 , 2));
		jp.add(name); jp.add(jtf);
		jp.add(password); jp.add(jtf_2);
	
		f_2.getContentPane().add(jb_2, "South");
		f_2.getContentPane().add(jp, "Center");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_2.setSize(300, 120);
		
		jb_2.addMouseListener(this);
		
	}
	
	public void mouseClicked(MouseEvent e){
		f_2.setVisible(true);
		String s_1 = jtf.getText();
		String s_2 = jtf_2.getText();
		String s_3 = "name: " + s_1 + '\n' + "password; " + s_2 + '\n';
		jt.setText(s_3);
	}
}
