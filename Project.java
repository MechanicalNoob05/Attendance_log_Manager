import javax.swing.*;  
import java.awt.event.*;

public class Project {  
	public static void main(String[] args) {  
		String profile[]={""};
		JFrame f=new JFrame();//creating instance of JFrame  
		f.setSize(500,280);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        JPanel p=new JPanel();
        p.setLayout(null);
        f.add(p);
        
        JLabel q = new JLabel("Are you a new or old user ?");
       	q.setBounds(10, 20, 800 , 25);
		p.add(q);
		
		JButton go = new JButton("go");
		go.setBounds(10,200,100,25);
		
		JButton old_user = new JButton("Old user");
		old_user.setBounds(10,100,100,25);
		old_user.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				p.remove(old_user);
				JComboBox cb=new JComboBox(profile);
				cb.setBounds(10,100,100,25); 
				p.add(cb);
				p.add(go);
            }  
        });
		p.add(old_user);
		
		
		
        JButton new_user = new JButton("Create new profile");
		new_user.setBounds(125,100,200,40);
		new_user.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
				
            }  
        });
		p.add(new_user);
		
		f.setVisible(true);//making the frame visible  
	}  
}  
