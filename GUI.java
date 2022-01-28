import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class GUI{
	JFrame window,frame;
	JPanel panel,panel1,panel2,panel3,panel4,ADSnames;
	JLabel name,Roll,Date,tab3,tab4,D,N,R,Roll1,PArb,ANo,SELDEP,SELDIV,SELNS;
	JTabbedPane tp,tp1;
	JTextField DEPNAME,DEPND,DEPNS,EntName;
	public static void main(String[] args){
		new GUI();
	}
	public GUI(){
		createWindow();
		tabbedPane();
		createPanel();
		createPanel4();
		createPanel1();
		createPanel2();
		createPanel3();
		window.setVisible(true);
	}
	public void createWindow(){
		window = new JFrame("Test");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = new JFrame("mansi");
		frame.setSize(400, 400);
	}
	public void tabbedPane(){
		tp=new JTabbedPane();
		tp1=new JTabbedPane();
		tp1.setBounds(50,50,200,200);
   	 	tp.setBounds(50,50,200,200);
   	 	window.add(tp);
	}
	public void createPanel(){
		panel =new JPanel();
		panel.setLayout(null);
		name = new JLabel("Name:");
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
		tp1.add("In Series ",panel);

		tp.add("Attendance",tp1);
	}
	public void createPanel4(){
		panel4 =new JPanel();
		panel4.setLayout(null);
		JRadioButton r1=new JRadioButton("Present");
		JRadioButton r2=new JRadioButton("Absent");
		r1.setBounds(150,20,100,30);
		r2.setBounds(250,20,100,30);
		ButtonGroup bg=new ButtonGroup();
    bg.add(r1);bg.add(r2);
		PArb =new JLabel("Present/Absent: ");
		PArb.setBounds(10,20,120,25);
		ANo = new JLabel("All Roll no. Seperated by '','' :");
		ANo.setBounds(10, 60, 220 , 25);
		JTextArea area=new JTextArea("");
        	area.setBounds(225,65, 400,50);
        	JButton submit=new JButton("Submit");
    		submit.setBounds(500,125,95,30);
        	panel4.add(area);
        	panel4.add(submit);
		panel4.add(PArb);
		panel4.add(ANo);
		panel4.add(r1);
		panel4.add(r2);
		tp1.add("By Preset/Absent",panel4);

	}
	public void createPanel1(){
		panel1 =new JPanel();
		panel1.setLayout(null);

		SELDEP=new JLabel("Select Department Name: ");
		SELDEP.setBounds(10,20, 200,30);
		panel1.add(SELDEP);
		DEPNAME=new JTextField("");
		DEPNAME.setBounds(210,20, 250,30);
		panel1.add(DEPNAME);

		SELDIV=new JLabel("Select Number of Division: ");
		SELDIV.setBounds(10,60, 200,30);
		panel1.add(SELDIV);
		DEPND=new JTextField("");
		DEPND.setBounds(210,60, 250,30);
		panel1.add(DEPND);

		SELNS=new JLabel("Select Number of Student: ");
		SELNS.setBounds(10,100, 200,30);
		panel1.add(SELNS);
		DEPNS=new JTextField("");
		DEPNS.setBounds(210,100, 250,30);
		panel1.add(DEPNS);

		JButton ADS=new JButton("Add Student");
		ADS.setBounds(210,140,140,30);
		panel1.add(ADS);
		ADS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					createADSpanel();
					frame.setVisible(true);

        }
    }
	);
		tp.add("Add Department",panel1);
	}
	public void createADSpanel(){
		ADSnames=new JPanel();
		ADSnames.setLayout(null);
		ADSnames.setBounds(10,20,200,200);

		JLabel NTS=new JLabel("Name of the Student");
		NTS.setBounds(10, 20, 150 , 30);
		ADSnames.add(NTS);
		EntName=new JTextField("");
		EntName.setBounds(160,20, 200,30);
		ADSnames.add(EntName);
		String data[][]={ {"101","Amit"},
                          {"102","Jai"},
                          {"101","Sachin"}};
    String column[]={"ID","NAME"};


    JTable jt=new JTable(data,column);
    jt.setBounds(10,60,350,150);

		ADSnames.add(jt);
		JButton Confirm =new JButton("Confirm");
		Confirm.setBounds(210,250,140,30);
		ADSnames.add(Confirm);

		frame.add(ADSnames);
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
