import javax.swing.*;

import org.apache.logging.log4j.util.Strings;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.FileDialog;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GUI {
	JFrame Main_window;
	static JFrame frame;
	JFrame Month_wise_view_window;
	JFrame Reset_confirm_window;
	JPanel Serially_Attendace_marking, panel1, panel2, Documentation_panel, Selective_Attendace_marking_panel, ADSnames,
			HIS, Reset_confirm_window_panel;
	JLabel name, Roll, Select_sub, CHDEP, CHDIV, CHNO, tab4, N, R, Roll1, PArb, ANo, SELDEP, SELDIV, SELNS, month1, month2,
			Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec, filename;
	JTabbedPane Attendace_Making_Tab, Attendace_Making_Type_Selection_tab;
	JTextField DEPNAME, DEPND, DEPNS, EntName, RONO;
	JComboBox<String> cb, cb1;
	Workbook wb;
	org.apache.poi.ss.usermodel.Sheet sh;
	FileInputStream fis;
	FileOutputStream fos;
	Row row;
	Cell c1, wCell;
	int i = 1, no_of_row = 0, update,Sheet_index,no_of_sheets;
	JFileChooser Openfile;
	String fileAddress, fileName;
	String Subs[]={"car","red","yellow"};
	JComboBox<String> Sel_sub;

	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		new GUI();
	}

	public GUI() throws EncryptedDocumentException, IOException {
		createWindow();
		tabbedPane();
		Marking_Attendance_panel();
		Marking_Attendance_panel_2();
		Select_department_panel();
		View_Monthly_panel();
		Help_Panel();
		Main_window.setVisible(true);
		Reset_confirm_window_panel();
		Excel_Data_Reading();
	}

	public void createWindow() {
		Main_window = new JFrame("Test");
		Main_window.setSize(800, 600);
		Main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = new JFrame("Confirmation");
		frame.setSize(400, 400);
		Month_wise_view_window = new JFrame("Monthly_display_box");
		Month_wise_view_window.setSize(210, 210);
		Reset_confirm_window = new JFrame("Confirmation");
		Reset_confirm_window.setSize(500, 200);
	}

	public void tabbedPane() {
		Attendace_Making_Tab = new JTabbedPane();
		Attendace_Making_Type_Selection_tab = new JTabbedPane();
		Attendace_Making_Type_Selection_tab.setBounds(50, 50, 200, 200);
		Attendace_Making_Tab.setBounds(50, 50, 200, 200);
		Main_window.add(Attendace_Making_Tab);
	}
	
	public void Marking_Attendance_panel() {
		Serially_Attendace_marking = new JPanel();
		Serially_Attendace_marking.setLayout(null);
		
		filename = new JLabel("No file selected!!");
		filename.setBounds(10, 20, 150, 25);
		
		name = new JLabel("Name:");
		name.setBounds(10, 40, 80, 25);
		
		Roll = new JLabel("Roll no.: ");
		Roll.setBounds(10, 80, 80, 25);
		Select_sub = new JLabel("Select Sub:");
		Select_sub.setBounds(250, 60, 100, 25);
		
		Sel_sub = new JComboBox<String>(Subs);
		Sel_sub.setBounds(350, 60, 165, 25);
		N = new JLabel();
		N.setBounds(60, 40, 165, 25);
		R = new JLabel();
		R.setBounds(65, 80, 165, 25);
		
		// Setting up Buttons and their Actions
		JButton Present = new JButton("Present");
		Present.setBounds(10, 120, 100, 25);
		Present.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (i != no_of_row) {
						Excel_Data_Writing(1,1);
						N.setText(Cell_to_string(i, 0));
						i++;
					} else {
						N.setText("Complete");
					}
				} catch (EncryptedDocumentException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				;

			}
		});

		JButton Absent = new JButton("Absent");
		Absent.setBounds(125, 120, 100, 25);
		Absent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (i != no_of_row) {
						Excel_Data_Writing(1, 0 		);
						N.setText(Cell_to_string(i, 0));
						i++;
					} else {
						N.setText("Complete");
					}
				} catch (EncryptedDocumentException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				;
				
			}
		});
		
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(300, 20, 165, 25);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Opening();
				filename.setText(fileName);
			}
		});
		JButton Next = new JButton("Next");
		Next.setBounds(365, 120, 100, 25);
		JButton Reset = new JButton("Reset");
		Reset.setBounds(10, 200, 100, 25);
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset_confirm_window_panel();
				Reset_confirm_window.setVisible(true);
				
			}
		});
		JButton Generate = new JButton("Create");
		Generate.setBounds(365, 200, 100, 25);
		Serially_Attendace_marking.add(filename);
		Serially_Attendace_marking.add(name);
		Serially_Attendace_marking.add(Roll);
		Serially_Attendace_marking.add(Select_sub);
		
		Serially_Attendace_marking.add(N);
		Serially_Attendace_marking.add(R);
		Serially_Attendace_marking.add(Sel_sub);
		
		Serially_Attendace_marking.add(Present);
		Serially_Attendace_marking.add(Absent);
		Serially_Attendace_marking.add(loadButton);
		Serially_Attendace_marking.add(Next);
		Serially_Attendace_marking.add(Reset);
		Serially_Attendace_marking.add(Generate);
		Attendace_Making_Type_Selection_tab.add("In Series ", Serially_Attendace_marking);
		
		Attendace_Making_Tab.add("Marking Attendance", Attendace_Making_Type_Selection_tab);
	}

	public void Reset_confirm_window_panel() {
		Reset_confirm_window_panel = new JPanel();
		JLabel Reset_confirm_Label = new JLabel("Are you sure You want to Reset all Marked Attendance ?");
		Reset_confirm_Label.setBounds(10, 20, 200, 25);
		JButton Yes = new JButton("Yes");
		JButton No = new JButton("No");
		Yes.setBounds(30, 80, 80, 25);
		No.setBounds(100, 60, 80, 25);
		Reset_confirm_window_panel.add(Reset_confirm_Label);
		Reset_confirm_window_panel.add(Yes);
		Reset_confirm_window_panel.add(No);
		Reset_confirm_window.add(Reset_confirm_window_panel);
	}

	public void Marking_Attendance_panel_2() {
		Selective_Attendace_marking_panel = new JPanel();
		Selective_Attendace_marking_panel.setLayout(null);
		JRadioButton Present_type_marking = new JRadioButton("Present");
		JRadioButton Absent_type_marking = new JRadioButton("Absent");
		Present_type_marking.setBounds(150, 20, 100, 30);
		Absent_type_marking.setBounds(250, 20, 100, 30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(Present_type_marking);
		bg.add(Absent_type_marking);
		PArb = new JLabel("Present/Absent: ");
		PArb.setBounds(10, 20, 120, 25);
		ANo = new JLabel("All Roll no. Seperated by '','' :");
		ANo.setBounds(10, 60, 220, 25);
		JTextArea area = new JTextArea("");
		area.setBounds(225, 65, 400, 50);
		JButton submit = new JButton("Confirm");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adding_student_panel();
				frame.setVisible(true);
				
			}
		});
		submit.setBounds(500, 125, 95, 30);
		Selective_Attendace_marking_panel.add(area);
		Selective_Attendace_marking_panel.add(submit);
		Selective_Attendace_marking_panel.add(PArb);
		Selective_Attendace_marking_panel.add(ANo);
		Selective_Attendace_marking_panel.add(Present_type_marking);
		Selective_Attendace_marking_panel.add(Absent_type_marking);
		Attendace_Making_Type_Selection_tab.add("By Preset/Absent", Selective_Attendace_marking_panel);
		
	}
	
	public void Select_department_panel() {
		panel1 = new JPanel();
		panel1.setLayout(null);
		
		SELDEP = new JLabel("Select Department Name: ");
		SELDEP.setBounds(10, 20, 200, 30);
		panel1.add(SELDEP);
		DEPNAME = new JTextField("");
		DEPNAME.setBounds(210, 20, 250, 30);
		panel1.add(DEPNAME);
		
		SELDIV = new JLabel("Select Number of Division: ");
		SELDIV.setBounds(10, 60, 200, 30);
		panel1.add(SELDIV);
		DEPND = new JTextField("");
		DEPND.setBounds(210, 60, 250, 30);
		panel1.add(DEPND);
		
		SELNS = new JLabel("Select Number of Student: ");
		SELNS.setBounds(10, 100, 200, 30);
		panel1.add(SELNS);
		DEPNS = new JTextField("");
		DEPNS.setBounds(210, 100, 250, 30);
		panel1.add(DEPNS);
		
		JButton ADS = new JButton("Add Student");
		ADS.setBounds(210, 140, 140, 30);
		panel1.add(ADS);
		
		Attendace_Making_Tab.add("Add Department", panel1);
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
		CHDEP = new JLabel("Select Department:");
		CHDEP.setBounds(10, 20, 200, 25);
		panel2.add(CHDEP);
		String Department[] = { "chaman", "bade chaman", "full chaman" };
		cb = new JComboBox<String>(Department);
		cb.setBounds(180, 20, 200, 20);
		panel2.add(cb);
		
		CHDIV = new JLabel("Select Division: ");
		CHDIV.setBounds(10, 60, 200, 30);
		panel2.add(CHDIV);
		String Division[] = { "chaman", "bade chaman", "full chaman" };
		cb1 = new JComboBox<String>(Division);
		cb1.setBounds(180, 60, 200, 20);
		panel2.add(cb1);
		
		CHNO = new JLabel("Enter Student Roll no.: ");
		CHNO.setBounds(10, 100, 200, 30);
		panel2.add(CHNO);
		
		RONO = new JTextField("");
		RONO.setBounds(180, 100, 50, 30);
		panel2.add(RONO);
		
		JButton View = new JButton("View");
		View.setBounds(210, 250, 140, 30);
		View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monthly_display_box();
				Month_wise_view_window.setVisible(true);
				
			}
		});
		panel2.add(View);
		
		Attendace_Making_Tab.add("View History", panel2);
	}

	public void Monthly_display_box() {
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
	
	public void Help_Panel() {
		Documentation_panel = new JPanel();
		Documentation_panel.setLayout(null);
		tab4 = new JLabel("How to use:");
		tab4.setBounds(10, 60, 80, 25);
		Documentation_panel.add(tab4);
		Attendace_Making_Tab.add("Help", Documentation_panel);
	}

	public void Excel_Data_Reading() throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheetAt(Sheet_index);
		int no_of_row = sh.getLastRowNum();
		System.out.println(no_of_row);
		
	}
	
	public void Excel_Data_Writing(int month,int act) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(fileName);
		wb = WorkbookFactory.create(fis);
		no_of_sheets = wb.getNumberOfSheets();
		System.out.println(no_of_sheets);
		sh = wb.getSheetAt(Sheet_index);
		row = sh.getRow(i);
		c1 = row.getCell(month);
		update = (int) c1.getNumericCellValue();
		c1.setCellValue(update + act);
		fos = new FileOutputStream(fileName);
		wb.write(fos);
		fos.flush();
		fos.close();
		no_of_row = sh.getLastRowNum();
		
		System.out.println("Done");
		
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

}
