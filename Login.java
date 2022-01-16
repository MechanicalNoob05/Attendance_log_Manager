// Importing Packages
import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;
//hishsi ubfwfbuwayfbaw//
public  class Login {
	    // Setting up arrays and variables
		static int i=0;
        static String data[][]={ 
							  {"1","AJIT LAXMAN KALE",""},
                              {"2","AMBOLKAR RIGVED SANTOSH",""}, 
                              {"3","BABAR SUSHANT GANESH",""},
                              {"4","BARE DEEPTI DEVENDRA",""},
                              {"5","BHIKULE MALHAR KISHOR",""},
                              {"6","CHECHARE PRATHAMESH POPAT",""},
                              {"7","CHOUDHARY PAYAL",""}, 
                              {"8","GADEKAR SHEJAL GANESH",""},
                              {"9","GAJJALA KAVYASHREE RAJASHEKHAR REDDY",""},
                              {"10","GULWE NEHA RAM",""},
                              {"11","GUPTA YASH MOHAN",""},
                              {"12","INAMDAR OMAR",""},
                              {"13","JADHAV MAYUR SHRIKANT",""},
                              {"14","JAYDEEP JAGDISHBHAI NANDOLA",""},
                              {"15","KATKAR PRATHAMESH KISHOR",""},
                              {"16","KHADE SAMARTH GANESH",""},
                              {"17","KHAN JIBRAN USMAN",""},
                              {"18","KIRTANE JAYESH RAJENDRA",""},
                              {"19","MADAS ADARSH ASHOK",""},
                              {"20","MANE KETAN SHIVAJI",""},
                              {"21","MISHRA BARUN BIPIN",""},
                              {"22","PATTIPAKA NITESH SUDHAKAR",""},
                              {"23","PAWAR RAJ KONDIRAM",""},
                              {"24","PHADATARE OMKAR VISHWANATH",""},
                              {"25","RAJDEV SUMAN SUDARSHAN",""},
                              {"26","RUSHIKESH KAKDE",""},
                              {"27","SAHIBOLE ALTAMASH ZAHID",""},
                              {"28","SANAP PAWAN RAMESH",""},
                              {"29","SHAIKH RUMMAN JUNAID",""},
                              {"30","SHINDE SUYASH VIJAY",""},
                              {"31","SHRIGADI NICKY RAMESH",""},
                              {"32","SINGH ARYAN RISHIRAJ",""},
                              {"33","SINGH MANISH MANOJ",""},
                              {"34","TAKALE ANIKET DATTA",""},
                              {"35","TAMBOLI AALAMGIR USMAN",""},
                              {"36","TETAME DEEPAK SHIVAJI",""},
                              {"37","THATAR RAHUL VIJAY",""},
                              {"38","TIKANDE PRATIK DILIP",""},
                              {"39","SHINDE SUYASH KISHOR",""},
                              {"40","WAGHMARE PRANAV SAMADHAN",""},
                              {"41","YADAV RISHIKUMAR AUDESH",""},
                              {"42","YADAV SAHIL DHANANJAY",""},
                              {"43","MAYEKAR TEJAS SUNIL",""},
                              {"44","HARDIK DEEPAK BHERE",""},
                              {"45","ARMAN KHAN",""},
                              {"46","GAIKWAD PRANAV SHAILESH",""}};    
        static String column[]={"Roll No.","NAME","Attendance"}; 
	    
	    public static void main(String[]args)throws Exception{
		//obtain current date
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd ");  
        LocalDateTime now =  LocalDateTime.now();
		
		//setting up frame and its objects
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setTitle("Attendance Log Manager");
		frame.setSize(500,280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		// Setting up Labels
		JLabel name = new JLabel("Name:");
		name.setBounds(10, 20, 80 , 25);
			
		JLabel Roll = new JLabel("Roll no.: ");
		Roll.setBounds(10, 60, 80 , 25);			
		JLabel Date = new JLabel("Date:");
		Date.setBounds(250, 20, 80 , 25);
			
		JLabel D = new JLabel(dtf.format(now));
		D.setBounds(300, 20, 165, 25);			
		JLabel N = new JLabel();
		N.setBounds(60, 20, 165, 25);
		JLabel R = new JLabel();
		R.setBounds(65, 60, 165, 25);

		//Setting up Buttons and their Actions		
		JButton Present = new JButton("Present");
		Present.setBounds(10,100,100,25);
		Present.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				try{
					N.setText(data[i][1]);
					R.setText(data[i][0]);
					data[i][2]="P";
					i++;
				}
				catch(Exception ce){
					N.setText("Attendace complete");
					R.setText("");
				}
            }  
        });
		
		JButton Absent = new JButton("Absent");
		Absent.setBounds(125,100,100,25);
		Absent.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				N.setText(data[i][1]);
				R.setText(data[i][0]);
				data[i][2]="A";
				i++;
            }  
        });
		
		JButton Back = new JButton("Back");
		Back.setBounds(250,100,100,25);
		Back.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                try{
					N.setText(data[i][1]);
					R.setText(data[i][0]);
					i--;
				}
				catch(Exception be){
					i=0;
				}
            }  
        });
		
		JButton Next = new JButton("Next");
		Next.setBounds(365,100,100,25);
		Next.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                
				
				try{
					N.setText(data[i][1]);
					R.setText(data[i][0]);
					i++;
				}
				catch(Exception ae){
					i=45;
				}
            }  
        });
	 	
		JButton Reset = new JButton("Reset");
		Reset.setBounds(10,200,100,25);
		Reset.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				
				for(i=0;i<46;i++)
					data[i][2]="";
				i=0;
            }  
        });
		
		JButton Generate = new JButton("Create");
		Generate.setBounds(365,200,100,25);
		Generate.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				    JFrame f;    
                    f=new JFrame();
					f.setTitle("Atendance");
                    JTable jt= new JTable(data,column);    
                    jt.setBounds(30,40,200,300);          
                    JScrollPane sp=new JScrollPane(jt);    
                    f.add(sp);          
                    f.setSize(300,400);    
                    f.setVisible(true);  
            }  
        });
		// Packing everything in the frame
			
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
			
		frame.setVisible(true);
	}
}
//Tejas
//Yash
