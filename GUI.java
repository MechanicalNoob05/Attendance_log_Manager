import javax.swing.*;
import java.awt.event.*;
import java.awt.*;  
public class GUI{
	JFrame window;
	JPanel panel,panel1,panel2,panel3;
	JLabel name,Roll,Date,tab3,tab4,D,N,R,Roll1;
	JTabbedPane tp;
	public static void main(String[] args){
		new GUI();
	}
	public GUI(){
		createWindow();
		tabbedPane();
		createPanel();
		createPanel1();
		createPanel2();
		createPanel3();
		window.setVisible(true);
	}
	public void createWindow(){
		window = new JFrame("Test");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void tabbedPane(){
		tp=new JTabbedPane();  
   	 	tp.setBounds(50,50,200,200);
   	 	window.add(tp); 
	}
	public void createPanel(){
		panel =new JPanel();
		panel.setLayout(null);
		name = new JLabel("Name:");
		name.setBounds(10, 20, 80 , 25);	
		JLabel name = new JLabel("Name:");
		name.setBounds(10, 20, 80 , 25);
			
		Roll = new JLabel("Roll no.: ");
		Roll.setBounds(10, 60, 80 , 25);			
		Date = new JLabel("Date:");
		Date.setBounds(250, 20, 80 , 25);
			
		D = new JLabel("12/12/12");
		D.setBounds(300, 20, 165, 25);			
		N = new JLabel();
		N.setBounds(60, 20, 165, 25);
		R = new JLabel();
		R.setBounds(65, 60, 165, 25);

		//Setting up Buttons and their Actions		
		JButton Present = new JButton("Present");
		Present.setBounds(10,100,100,25);
		JButton Absent = new JButton("Absent");
		Absent.setBounds(125,100,100,25);
		JButton Back = new JButton("Back");
		Back.setBounds(250,100,100,25);
		JButton Next = new JButton("Next");
		Next.setBounds(365,100,100,25);
		JButton Reset = new JButton("Reset");
		Reset.setBounds(10,200,100,25);
		JButton Generate = new JButton("Create");
		Generate.setBounds(365,200,100,25);
		panel.add(name);
		panel.add(Roll);
		panel.add(Date);
		
		panel.add(N);
		panel.add(R);
		panel.add(D);
	
		panel.add(Present);
		panel.add(Absent);
		panel.add(Back);
		panel.add(Next);
		panel.add(Reset);
		panel.add(Generate);
		tp.add("Attendance",panel);
	}
	public void createPanel1(){
		panel1 =new JPanel();
		panel1.setLayout(null);
		Roll1 = new JLabel("Roll no.: ");
		Roll1.setBounds(10, 20, 80 , 25);			
		panel1.add(Roll1);
		
		tp.add("Add Department",panel1);
	}
	public void createPanel2(){
		panel2 =new JPanel();
		panel2.setLayout(null);
		tab3 = new JLabel("Yash darling");
		tab3.setBounds(10, 60, 80 , 25);			
		panel2.add(tab3);
		tp.add("View History",panel2);
	}
	public void createPanel3(){
		panel3 =new JPanel();
		panel3.setLayout(null);
		tab4 = new JLabel("Rigu ki hal");
		tab4.setBounds(10, 60, 80 , 25);			
		panel3.add(tab4);
		tp.add("Help",panel3);
	}
	
}
