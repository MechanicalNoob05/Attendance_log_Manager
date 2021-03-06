import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.FileDialog;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class GUI {
	JFrame Main_window;
	static JFrame frame;
	JFrame Month_wise_view_window;
	JFrame Reset_confirm_window;
	JPanel Serially_Attendace_marking, panel1, panel2, Documentation_panel, Selective_Attendace_marking_panel, ADSnames,
	HIS, Reset_confirm_window_panel;
	JLabel name, Roll, Date, CHDEP, CHDIV, CHNO, Month, N, R, Roll1, PArb, ANo, SELECT_MONTH, SELDIV, SELNS, month1, month2,
	Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec, filename;
	JTextArea tab4;
	JTabbedPane Attendace_Making_Tab, Attendace_Making_Type_Selection_tab;
	JTextField DEPNAME, DEPND, DEPNS, EntName, RONO;
	JComboBox<String> monthSelBox;
	Workbook wb;
	org.apache.poi.ss.usermodel.Sheet sh;
	FileInputStream fis;
	FileOutputStream fos;
	Row row;
	Cell c1, wCell;
	int i = 1, no_of_row = 0, Sheet_index,no_of_sheets,Selected_month;
	int update;
	JFileChooser Openfile;
	String fileAddress, fileName;
	String Months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	boolean changes[];
	Calendar Calender = new Calendar();
	
	
	public static void main(String[] args) throws Exception, EncryptedDocumentException, IOException {
		new GUI();
	}

	public GUI() throws EncryptedDocumentException, IOException {
		createWindow();
		tabbedPane();
		Marking_Attendance_panel();
		View_Monthly_panel();
		Help_Panel();
		Main_window.setVisible(true);
		Reset_confirm_window_panel();
		//Excel_Data_Reading();
		
	}

	public void createWindow() {
		Main_window = new JFrame("Attendance Marking");
		Main_window.setSize(790, 500);
		Main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = new JFrame("Confirmation");
		frame.setSize(400, 400);
		Month_wise_view_window = new JFrame("Monthly_display_box");
		Month_wise_view_window.setSize(210, 210);
		Reset_confirm_window = new JFrame("Confirmation");
		Reset_confirm_window.setSize(420, 100);
	}

	public void tabbedPane() {
		Attendace_Making_Tab = new JTabbedPane();
		Attendace_Making_Tab.setBounds(50, 50, 200, 200);
		Main_window.add(Attendace_Making_Tab);
	}
	
	public void Marking_Attendance_panel() {
		Serially_Attendace_marking = new JPanel();
		Serially_Attendace_marking.setLayout(null);
		
		filename = new JLabel("No file selected!!");
		filename.setBounds(10, 20, 150, 25);
		
		name = new JLabel("Name:");
		name.setBounds(10,80, 80, 25);
		
		Roll = new JLabel("Roll no.: ");
		Roll.setBounds(10, 130, 80, 25);
		Date = new JLabel("Month:");
		Date.setBounds(510, 80, 80, 25);
		
		monthSelBox = new JComboBox<String>(Months);
		monthSelBox.setBounds(580, 80, 165, 25);
		

		N = new JLabel();
		N.setBounds(60, 80, 165, 25);
		R = new JLabel();
		R.setBounds(70, 130, 165, 25);
		
		// Setting up Buttons and their Actions
		JButton Present = new JButton("Present");
		Present.setBounds(10, 220, 100, 25);
		Present.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Excel_Data_Writing(Selected_month,1,i);
					N.setText(Cell_to_string(i, 0));
					R.setText(Integer.toString(i-1));
					//i++;					
					
				} catch (EncryptedDocumentException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				;

			}
		});

		JButton Absent = new JButton("Absent");
		Absent.setBounds(130, 220, 100, 25);
		Absent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Excel_Data_Writing(Selected_month, 0,i);
					N.setText(Cell_to_string(i, 0));
					R.setText(Integer.toString(i-1));
					//i++;
					
				} catch (EncryptedDocumentException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				;
				
			}
		});
		
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(580, 20, 165, 25);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Opening();
				filename.setText(fileName);
				System.out.println(i);
				System.out.println(Selected_month);
				try {
					fis = new FileInputStream(fileName);
					wb = WorkbookFactory.create(fis);
					sh = wb.getSheetAt(Sheet_index);
					no_of_row = sh.getLastRowNum();
				} catch (EncryptedDocumentException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		JButton Month = new JButton("Select");
		Month.setBounds(650, 220, 100, 25);
		Month.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				Selected_month=monthSelBox.getSelectedIndex()+1;
				System.out.println(Selected_month);
			}
		});
		JButton viewCalender = new JButton("Calender");
		viewCalender.setBounds(540,220,100,25);
		viewCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calender.calender();
				
			}
		});

		JButton Reset = new JButton("Reset");
		Reset.setBounds(250, 400, 100, 25);
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset_confirm_window_panel();
				Reset_confirm_window.setVisible(true);
				
				
			}
		});
		JButton Generate = new JButton("Selective");
		Generate.setBounds(370, 400, 100, 25);
		Generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Check_box_marking();
				} catch (EncryptedDocumentException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		Serially_Attendace_marking.add(filename);
		Serially_Attendace_marking.add(name);
		Serially_Attendace_marking.add(Roll);
		Serially_Attendace_marking.add(Date);
		
		Serially_Attendace_marking.add(N);
		Serially_Attendace_marking.add(R);
		Serially_Attendace_marking.add(monthSelBox);
		
		Serially_Attendace_marking.add(Present);
		Serially_Attendace_marking.add(Absent);
		Serially_Attendace_marking.add(loadButton);
		Serially_Attendace_marking.add(Month);
		Serially_Attendace_marking.add(viewCalender);
		Serially_Attendace_marking.add(Reset);
		Serially_Attendace_marking.add(Generate);
		
		Attendace_Making_Tab.add("Marking Attendance", Serially_Attendace_marking);
	}

	public void Reset_confirm_window_panel() {
		Reset_confirm_window_panel = new JPanel();
		JLabel Reset_confirm_Label = new JLabel("Are you sure You want to Reset all Marked Attendance ?");
		Reset_confirm_Label.setBounds(10, 20, 200, 25);
		JButton Yes = new JButton("Yes");
		Yes.setBounds(100, 80, 80, 25);
		Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reset();
				} catch (EncryptedDocumentException | IOException e1) {
					
					e1.printStackTrace();
				}
				Reset_confirm_window.setVisible(false);
			}
		});
		JButton No = new JButton("No");
		No.setBounds(100, 60, 80, 25);
		No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset_confirm_window.setVisible(false);
			}
		});
		Reset_confirm_window_panel.add(Reset_confirm_Label);
		Reset_confirm_window_panel.add(Yes);
		Reset_confirm_window_panel.add(No);
		Reset_confirm_window.add(Reset_confirm_window_panel);
	}
	

	
	public void Check_box_marking() throws EncryptedDocumentException, IOException{

		JFrame frame = new JFrame();
        JPanel panel = new JPanel();
		DefaultTableModel model = new DefaultTableModel(){
			public Class<?> getColumnClass(int column){
				switch(column){
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return Boolean.class;
					default:
						return String.class;
				}
			}
		};
		
		String list[]=Excel_Data_Reading();
		String[] list2=new String[no_of_row];
		Boolean entry[]= new Boolean[no_of_row];
		for(int m=0;m<no_of_row;m++){
			list2[m]= Integer.toString(m+1);
		}
		model.addColumn("Roll", list2);
		model.addColumn("Name", list);

		model.addColumn("Present", entry);
   
        panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Student List", TitledBorder.LEFT, TitledBorder.TOP));
      	
		JTable table = new JTable(model);
		JButton sub = new JButton("Submit");
		sub.setBounds(200, 200, 100, 25);

		JRadioButton Present_type_marking = new JRadioButton("Select All");
		Present_type_marking.setBounds(150, 20, 100, 30);
		Present_type_marking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int n=0;n<no_of_row;n++){
					model.setValueAt(true, n, 2);
				}				
			}
		});
		boolean result[]=new boolean[table.getRowCount()];
		
		
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int m = 0;m<no_of_row;m++){
					if(model.getValueAt(m, 2) == null){
						model.setValueAt(false, m, 2);
					}
					result[m]=(boolean) model.getValueAt(m, 2);
				}
				for(int m = 0;m<no_of_row;m++){
					if(result[m]==true){
						try {
							Excel_Data_Writing(Selected_month, 1, m+1);
						} catch (EncryptedDocumentException | IOException e1) {
						
							e1.printStackTrace();
						}
					}
				}
				frame.setVisible(false);
					
			}
		});
		

		panel.add(new JScrollPane(table));
		panel.add(sub);

		panel.add(Present_type_marking);
	

      	frame.add(panel);
      	frame.setSize(900, 400);
      	frame.setVisible(true);
	}
	
	
	public void Adding_student_panel() {
		ADSnames = new JPanel();
		ADSnames.setLayout(null);
		JButton Confirm = new JButton("Confirm");
		Confirm.setBounds(210, 250, 140, 30);
		ADSnames.add(Confirm);
		Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setTitle("Attendace Marked");
				
			}
		});
		
		frame.add(ADSnames);
	}
	
	public void View_Monthly_panel() {
		panel2 = new JPanel();
		panel2.setLayout(null);
		
		CHNO = new JLabel("Enter Student Roll no.: ");
		CHNO.setBounds(20, 40, 200, 30);
		panel2.add(CHNO);
		
		
		RONO = new JTextField();
		RONO.setBounds(200, 40, 50, 30);
		panel2.add(RONO);
		
		JButton View = new JButton("View");
		View.setBounds(210, 100, 100, 30);
		View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String input = RONO.getText();
					Monthly_display_box();
					Month_update(input);
				} catch (EncryptedDocumentException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Month_wise_view_window.setVisible(true);
				
			}
		});
		panel2.add(View);
		
		Attendace_Making_Tab.add("View History", panel2);
	}

	public void Monthly_display_box() throws EncryptedDocumentException, IOException {
		HIS = new JPanel();
		HIS.setLayout(null);
		HIS.setBounds(10, 20, 200, 200);
		month1 = new JLabel("Jan  Feb  Mar  Apr  May  Jun");
		month1.setBounds(10, 20, 200, 25);
		month2 = new JLabel("Jul  Aug  Sep  Oct  Nov  Dec");
		month2.setBounds(10, 100, 200, 25);
		HIS.add(month1);
		HIS.add(month2);
		

		Jan = new JLabel("0");
		Jan.setBounds(10, 60, 20, 25);
		HIS.add(Jan);
		
		Feb = new JLabel("0");
		HIS.add(Feb);
		Feb.setBounds(40, 60, 20, 25);
		
		Mar = new JLabel("0");
		Mar.setBounds(80, 60, 20, 25);
		HIS.add(Mar);
		
		Apr = new JLabel("0");
		Apr.setBounds(110, 60, 20, 25);
		HIS.add(Apr);
		
		May = new JLabel("0");
		May.setBounds(150, 60, 20, 25);
		HIS.add(May);
		
		Jun = new JLabel("0");
		Jun.setBounds(180, 60, 20, 25);
		HIS.add(Jun);
		
		Jul = new JLabel("0");
		Jul.setBounds(10, 140, 20, 25);
		HIS.add(Jul);
		
		Aug = new JLabel("0");
		HIS.add(Aug);
		Aug.setBounds(40, 140, 20, 25);
		
		Sep = new JLabel("0");
		Sep.setBounds(80, 140, 20, 25);
		HIS.add(Sep);
		
		Oct = new JLabel("0");
		Oct.setBounds(110, 140, 20, 25);
		HIS.add(Oct);
		
		Nov = new JLabel("0");
		Nov.setBounds(150, 140, 20, 25);
		HIS.add(Nov);
		
		Dec = new JLabel("0");
		Dec.setBounds(180, 140, 20, 25);
		HIS.add(Dec);
		
		Month_wise_view_window.add(HIS);
	}
	
	public void Help_Panel() throws IOException {
		Documentation_panel = new JPanel();
		Documentation_panel.setLayout(null);
		tab4 = new JTextArea();
		tab4.setBounds(10, 10, 760, 800);
		String temp = "Help.txt";
		try{
		BufferedReader br = new BufferedReader(new FileReader(temp));
		tab4.setText("");
		String line = null;
		while((line = br.readLine()) != null){
			tab4.append(line+"\n");
		}
		br.close();
		}catch(Exception e){

		}
		Documentation_panel.add(tab4);
		Attendace_Making_Tab.add("Help", Documentation_panel);
	}

	public String[] Excel_Data_Reading() throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheetAt(Sheet_index);
		no_of_row = sh.getLastRowNum();
		String list[]=new String[no_of_row];
		for(int m = 0;m<no_of_row;m++){
			row = sh.getRow(m+1);
			c1 =row.getCell(0);
			if(c1 !=null){
				String temp = c1.getStringCellValue();
				list[m]=temp; 
			}
		}
		return list;
	}
	public void Month_update(String input) throws EncryptedDocumentException, IOException{
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheetAt(Sheet_index);
		no_of_row = sh.getLastRowNum();
		int roll = Integer.parseInt(input);
		row = sh.getRow(roll);
		
		c1=row.getCell(1);
		int temp1 = (int) c1.getNumericCellValue();
		c1=row.getCell(2);
		int temp2 = (int) c1.getNumericCellValue();
		c1=row.getCell(3);
		int temp3 = (int) c1.getNumericCellValue();
		c1=row.getCell(4);
		int temp4 = (int) c1.getNumericCellValue();
		c1=row.getCell(5);
		int temp5 = (int) c1.getNumericCellValue();
		c1=row.getCell(6);
		int temp6 = (int) c1.getNumericCellValue();
		c1=row.getCell(7);
		int temp7 = (int) c1.getNumericCellValue();
		c1=row.getCell(8);
		int temp8 = (int) c1.getNumericCellValue();
		c1=row.getCell(9);
		int temp9 = (int) c1.getNumericCellValue();
		c1=row.getCell(10);
		int temp10 = (int) c1.getNumericCellValue();
		c1=row.getCell(11);
		int temp11 = (int) c1.getNumericCellValue();
		c1=row.getCell(12);
		int temp12 = (int) c1.getNumericCellValue();

		Jan.setText(Integer.toString(temp1));
		Feb.setText(Integer.toString(temp2));
		Mar.setText(Integer.toString(temp3));
		Apr.setText(Integer.toString(temp4));
		May.setText(Integer.toString(temp5));
		Jun.setText(Integer.toString(temp6));
		Jul.setText(Integer.toString(temp7));
		Aug.setText(Integer.toString(temp8));
		Sep.setText(Integer.toString(temp9));
		Oct.setText(Integer.toString(temp10));
		Nov.setText(Integer.toString(temp11));
		Dec.setText(Integer.toString(temp12));
		
	}
	
	public void Excel_Data_Writing(int month,int act , int lane) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		no_of_sheets = wb.getNumberOfSheets();
		System.out.println(no_of_sheets);
		sh = wb.getSheetAt(Sheet_index);
		no_of_row = sh.getLastRowNum();
		row = sh.getRow(lane);
		if(row != null){
			if(act==1){
				System.out.println("true");
			}
			else{
				System.out.println("false");
			}
			c1 = row.getCell(month);	
			update = (int) c1.getNumericCellValue();
			c1.setCellValue(update + act);
			fos = new FileOutputStream(fileName);
			wb.write(fos);
			fos.flush();
			fos.close();
			System.out.println("Done");
			
		}
		else{
			N.setText("Completed !");
		}
		if(i<no_of_row){
			i++;
		}
		
	}
	
	
	public String Cell_to_string(int Row_no, int Cell_no) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheetAt(Sheet_index);
		row = sh.getRow(Row_no);
		c1 = row.getCell(Cell_no);
		String convert = c1.toString();
		return convert;
	}

	public void File_Opening() {
		FileDialog chooser = new FileDialog(GUI.frame, "Choose Attendace sheet", FileDialog.LOAD);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
			fileName = chooser.getFile();
			fileAddress = chooser.getDirectory();
		}
	}
	public void reset() throws EncryptedDocumentException, IOException{
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheetAt(Sheet_index);
		for(int m =1;m<=12;m++){
			for(int n=1;n<=sh.getLastRowNum();n++){
				row = sh.getRow(n);
				c1 = row.getCell(m);
				if(c1 == null){
					c1 = row.createCell(m);
					c1.setCellValue(0);;
				}
				else{
					c1.setCellValue(0);
				}
			}
		}
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.flush();
		fos.close();
		


	}
	
	

}
