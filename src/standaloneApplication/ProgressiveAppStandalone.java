package standaloneApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static restAPI.CRUD.*;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.JList;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Importing other components

import components.AddFrame;
import components.AddPatientFrame;
import components.DetailsFrame;
import components.PatientDetailsFrame;

public class ProgressiveAppStandalone{

	private JFrame frmHospitalManagementSystem;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLayeredPane cardContainer;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField txtEnterName;
	private JTextField textField_7;
	
	
	private void showDialougeBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgressiveAppStandalone window = new ProgressiveAppStandalone();
					window.frmHospitalManagementSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//FRAME IS CREATED :
	
	private void createFrame() {
		frmHospitalManagementSystem = new JFrame();
		frmHospitalManagementSystem.setBackground(Color.BLACK);
		frmHospitalManagementSystem.setFont(new Font("Arial", Font.PLAIN, 11));
		frmHospitalManagementSystem.setTitle("Hospital Management System");
		frmHospitalManagementSystem.setBounds(100, 100, 778, 400);
		frmHospitalManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHospitalManagementSystem.setResizable(false);
		frmHospitalManagementSystem.getContentPane().setLayout(null);
	}
	
	public ProgressiveAppStandalone() {
		createFrame();
//		JSONObject response = new JSONObject();
		loginPanel();
//		adminPanel(response);
//		doctorPanel(response);
//		nursePanel(response);
//		attendentPanel(response);
	}

	
	
	
	//--------------------------------------------------------------------------------------------------//
	
	//Login Panel And Login Operation Section:
	
	//--------------------------------------------------------------------------------------------------//
	
	private void loginPanel() {
		
		final JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 772, 371);
		frmHospitalManagementSystem.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		final JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 784, 74);
		loginPanel.add(headerPanel);
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login to your account");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 30));
		lblLogin.setBackground(Color.BLACK);
		lblLogin.setBounds(10, 11, 556, 52);
		headerPanel.add(lblLogin);
		
		final JPanel workspacePanel = new JPanel();
		workspacePanel.setBounds(0, 73, 772, 298);
		loginPanel.add(workspacePanel);
		workspacePanel.setBackground(Color.ORANGE);
		workspacePanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBounds(224, 42, 318, 20);
		workspacePanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));
		passwordField.setBounds(224, 88, 318, 20);
		workspacePanel.add(passwordField);
		
		JButton btnLoginToYour = new JButton("Login");
		btnLoginToYour.setBorderPainted(false);
		btnLoginToYour.setFont(new Font("Arial", btnLoginToYour.getFont().getStyle() | Font.BOLD, btnLoginToYour.getFont().getSize()));
		btnLoginToYour.setBackground(Color.WHITE);
		
		btnLoginToYour.setBounds(333, 119, 102, 30);
		workspacePanel.add(btnLoginToYour);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(224, 24, 318, 14);
		workspacePanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(224, 73, 318, 14);
		workspacePanel.add(lblPassword);
		
		JLabel lblForgetYourPassword = new JLabel("forget your password?");
		lblForgetYourPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				forgetPasswordResolver();
			}
		});
		lblForgetYourPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgetYourPassword.setBounds(224, 159, 318, 14);
		workspacePanel.add(lblForgetYourPassword);
		
		
		btnLoginToYour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doLoginOperations(loginPanel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
	}
	
	private void forgetPasswordResolver() {
		// TODO Auto-generated method stub
		
	}
	
	private void doLoginOperations(JPanel loginPanel) throws IOException, ParseException {
		String username = textField.getText();
		String password =  String.valueOf(passwordField.getPassword());
		
		
		JSONObject response = fetch("{\n" + 
		"\"username\": \"" + username + "\",\r\n" +
		"\"password\": \""+ password + "\"" +
		"\n}", "POST", "http://localhost:4000/login");
		
		
		if(response.get("position")!=null) {
			String position = response.get("position").toString().toLowerCase();
			if(position.equals("doctor")) {
				loginPanel.setVisible(false);
				doctorPanel(response);
			}
			else if(position.equals("attendent")) {
				loginPanel.setVisible(false);
				attendentPanel(response);
			}
			else if(position.equals("nurse")) {
				loginPanel.setVisible(false);
				nursePanel(response);
			}
			else if(position.equals("Admin".toLowerCase())) {
				System.out.println("admin called");
				loginPanel.setVisible(false);
				adminPanel(response);
			}
			else{
				System.out.println("Tradeoff happened");
			}
		}
		else {
			showDialougeBox("Sorry you are not recognised as a user", "No user found");
		}
	}
	
	
	//-------------------------------------------------------------------------------------------------//
	
	//SECTION ADMIN PANEL :
	
	//--------------------------------------------------------------------------------------------------//
	
	private void adminPanel(JSONObject response) {
		System.out.println("adminPanel opened");
		
		final JPanel adminPanel = new JPanel();
		adminPanel.setBounds(0, 0, 772, 371);
		frmHospitalManagementSystem.getContentPane().add(adminPanel);
		adminPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 762, 47);
		adminPanel.add(headerPanel);
		
		headerPanel.setLayout(null);
		
		JLabel lbWelcomeText = new JLabel("Welcome " + response.get("name").toString());
		lbWelcomeText.setFont(new Font("Arial", Font.BOLD, 24));
		lbWelcomeText.setBounds(23, 11, 455, 25);
		headerPanel.add(lbWelcomeText);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", btnLogOut.getFont().getStyle() | Font.BOLD, 11));
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBackground(Color.RED);
		btnLogOut.setBounds(660, 11, 92, 23);
		headerPanel.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adminPanel.setVisible(false);
				loginPanel();
			}
		});
		
		JLabel lblUsername_1 = new JLabel(response.get("username").toString());
		lblUsername_1.setBounds(560, 15, 92, 14);
		headerPanel.add(lblUsername_1);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.ORANGE);
		sidePanel.setBounds(0, 47, 169, 324);
		adminPanel.add(sidePanel);
		sidePanel.setLayout(null);
		
		JButton btnDoctorDetails = new JButton("Doctor Details");
		btnDoctorDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(doctorDetailsPanel());
			}
		});
		
		btnDoctorDetails.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new EmptyBorder(0, 0, 0, 0)));
		btnDoctorDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDoctorDetails.setBounds(0, 0, 169, 36);
		sidePanel.add(btnDoctorDetails);
		
		JButton btnPatientDetails = new JButton("Patient Details");
		btnPatientDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(patientDetailsPanel());
			}
		});
		btnPatientDetails.setBorder(UIManager.getBorder("Button.border"));
		btnPatientDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPatientDetails.setBounds(0, 47, 169, 36);
		sidePanel.add(btnPatientDetails);
		
		JButton btnNurseDetails = new JButton("Nurse Details");
		btnNurseDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(nurseDetailsPanel());
			}
		});
		btnNurseDetails.setBorder(UIManager.getBorder("Button.border"));
		btnNurseDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNurseDetails.setBounds(0, 94, 169, 36);
		sidePanel.add(btnNurseDetails);
		
		JButton btnAttendentDetails = new JButton("Attendent Details");
		btnAttendentDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(attendentDetailsPanel());
			}
		});
		btnAttendentDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAttendentDetails.setBorder(UIManager.getBorder("Button.border"));
		btnAttendentDetails.setBounds(0, 141, 169, 36);
		sidePanel.add(btnAttendentDetails);
		
		JLabel lblUserId = new JLabel("User ID : " + response.get("user_id").toString());
		lblUserId.setBounds(10, 282, 149, 14);
		sidePanel.add(lblUserId);
		
		cardContainer = new JLayeredPane();
		cardContainer.setBounds(171, 47, 601, 324);
		adminPanel.add(cardContainer);
		cardContainer.setLayout(new CardLayout(0, 0));
		
		doctorDetailsPanel();
		nurseDetailsPanel();
		patientDetailsPanel();
		attendentDetailsPanel();		
	}
	
	private void switchPanel(JPanel panel) {
		cardContainer.removeAll();
		cardContainer.add(panel);
		cardContainer.repaint();
		cardContainer.revalidate();
	}
	
	private JPanel doctorDetailsPanel() {

		JPanel doctorDetailsPanel = new JPanel();
		cardContainer.add(doctorDetailsPanel, "Doctor Pane");
		doctorDetailsPanel.setLayout(null);
		
		final DefaultListModel<String> model = new DefaultListModel<String>();
		final JList list = new JList(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(23, 38, 308, 265);
		doctorDetailsPanel.add(list);
		
		final String URL = "http://localhost:4000/retrieve";
		doMagic(model, "Doctor", URL);
		
		JLabel lblAvailableDoctor = new JLabel("Available Doctors");
		lblAvailableDoctor.setBounds(23, 13, 308, 14);
		doctorDetailsPanel.add(lblAvailableDoctor);
		
		JButton btnAddNewDoctor = new JButton("Add New Doctor");
		btnAddNewDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFrame("Doctor");
				doMagic(model, "Doctor", URL);
			}
		});
		btnAddNewDoctor.setFont(new Font("Arial", btnAddNewDoctor.getFont().getStyle() | Font.BOLD, btnAddNewDoctor.getFont().getSize()));
		btnAddNewDoctor.setBounds(366, 42, 203, 34);
		doctorDetailsPanel.add(btnAddNewDoctor);
		
		JLabel lblSearchByDoctors = new JLabel("Search By Doctor's Name :");
		lblSearchByDoctors.setBounds(366, 97, 203, 14);
		doctorDetailsPanel.add(lblSearchByDoctors);
		
		textField_2 = new JTextField();
		textField_2.setBounds(366, 122, 203, 20);
		doctorDetailsPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("Remove");
		button.setFont(new Font("Arial", Font.PLAIN, 11));
		button.setBounds(480, 153, 89, 23);
		doctorDetailsPanel.add(button);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSearch.setBounds(366, 153, 89, 23);
		doctorDetailsPanel.add(btnSearch);
		
		JButton btnSeeDoctorDetails = new JButton("See Doctor Details");
		btnSeeDoctorDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeeDoctorDetails.setBounds(366, 187, 203, 23);
		doctorDetailsPanel.add(btnSeeDoctorDetails);
		btnSeeDoctorDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new DetailsFrame(getIdFromSelectedItem(list), "Doctor");
			}
		});
		
		JButton btnPatientsAppointedTo = new JButton("Patients Appointed to this Doctor");
		btnPatientsAppointedTo.setFont(new Font("Arial", Font.PLAIN, 10));
		btnPatientsAppointedTo.setBounds(366, 218, 203, 23);
		doctorDetailsPanel.add(btnPatientsAppointedTo);
		
		
		return doctorDetailsPanel;
	}

	private JPanel nurseDetailsPanel() {
		JPanel nurseDetailsPanel = new JPanel();
		cardContainer.add(nurseDetailsPanel, "Nurse Pane");
		nurseDetailsPanel.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		final JList list = new JList(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(21, 36, 308, 265);
		nurseDetailsPanel.add(list);
		
		doMagic(model, "Nurse", "http://localhost:4000/retrieve");
		
		JLabel lblNurseAvailable = new JLabel("Nurses Available");
		lblNurseAvailable.setBounds(21, 11, 308, 14);
		nurseDetailsPanel.add(lblNurseAvailable);
		
		JButton btnAddNewNurse = new JButton("Add New Nurse");
		btnAddNewNurse.setBounds(364, 40, 203, 34);
		nurseDetailsPanel.add(btnAddNewNurse);
		btnAddNewNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFrame("Nurse");
			}
		});
		
		JLabel lblSearchByNurses = new JLabel("Search By  Nurse's Name :");
		lblSearchByNurses.setBounds(364, 95, 203, 14);
		nurseDetailsPanel.add(lblSearchByNurses);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(364, 120, 203, 20);
		nurseDetailsPanel.add(textField_4);
		
		JButton button_1 = new JButton("Remove");
		button_1.setFont(new Font("Arial", Font.PLAIN, 11));
		button_1.setBounds(478, 151, 89, 23);
		nurseDetailsPanel.add(button_1);
		
		JButton button_2 = new JButton("Search");
		button_2.setFont(new Font("Arial", Font.PLAIN, 11));
		button_2.setBounds(364, 151, 89, 23);
		nurseDetailsPanel.add(button_2);
		
		JButton btnSeeNuressDetails = new JButton("See Nures's Details");
		btnSeeNuressDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeeNuressDetails.setBounds(364, 185, 203, 23);
		nurseDetailsPanel.add(btnSeeNuressDetails);
		btnSeeNuressDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DetailsFrame(getIdFromSelectedItem(list), "Nurse");
			}
		});
		
		return nurseDetailsPanel;
	}

	private JPanel patientDetailsPanel() {
		JPanel patientDetailsPanel = new JPanel();
		cardContainer.add(patientDetailsPanel, "Patient Pane");
		patientDetailsPanel.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		final JList list = new JList(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(21, 36, 308, 265);
		patientDetailsPanel.add(list);
		
		doMagic(model, "", "http://localhost:4000/get_patients");
		
		
		
		JLabel lblPatientsAdmitted = new JLabel("Patients Admitted");
		lblPatientsAdmitted.setBounds(21, 11, 308, 14);
		patientDetailsPanel.add(lblPatientsAdmitted);
		
		JButton btnAddNewPatient = new JButton("Add New Patient");
		btnAddNewPatient.setBounds(364, 40, 203, 34);
		patientDetailsPanel.add(btnAddNewPatient);
		btnAddNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPatientFrame("Admin");
			}
		});
		
		JLabel lblSearchByPatientss = new JLabel("Search By Patient's Name :");
		lblSearchByPatientss.setBounds(364, 95, 203, 14);
		patientDetailsPanel.add(lblSearchByPatientss);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(364, 120, 203, 20);
		patientDetailsPanel.add(textField_3);
		
		JButton button_1 = new JButton("Remove");
		button_1.setFont(new Font("Arial", Font.PLAIN, 11));
		button_1.setBounds(478, 151, 89, 23);
		patientDetailsPanel.add(button_1);
		
		JButton button_2 = new JButton("Search");
		button_2.setFont(new Font("Arial", Font.PLAIN, 11));
		button_2.setBounds(364, 151, 89, 23);
		patientDetailsPanel.add(button_2);
		
		JButton btnSeePatientDetails = new JButton("See Patient Details");
		btnSeePatientDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeePatientDetails.setBounds(364, 185, 203, 23);
		patientDetailsPanel.add(btnSeePatientDetails);
		btnSeePatientDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PatientDetailsFrame(getIdFromSelectedItem(list));
			}
		});
		
		
		return patientDetailsPanel;
	}

	private JPanel attendentDetailsPanel() {
		JPanel attendentDetailsPanel = new JPanel();
		cardContainer.add(attendentDetailsPanel, "Attendent Pane");
		attendentDetailsPanel.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		final JList list = new JList(model);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(23, 36, 308, 265);
		attendentDetailsPanel.add(list);
		
		doMagic(model, "Attendent", "http://localhost:4000/retrieve");
		
		
		
		JLabel lblAvailableAttendents = new JLabel("Available Attendents");
		lblAvailableAttendents.setBounds(23, 11, 308, 14);
		attendentDetailsPanel.add(lblAvailableAttendents);
		
		JButton btnAddNewAttendent = new JButton("Add New Attendent");
		btnAddNewAttendent.setBounds(366, 40, 203, 34);
		attendentDetailsPanel.add(btnAddNewAttendent);
		btnAddNewAttendent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFrame("Attendent");
			}
		});
		
		JLabel lblSearchByAttendents = new JLabel("Search By Attendent's Name :");
		lblSearchByAttendents.setBounds(366, 95, 203, 14);
		attendentDetailsPanel.add(lblSearchByAttendents);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(366, 120, 203, 20);
		attendentDetailsPanel.add(textField_5);
		
		JButton button_1 = new JButton("Remove");
		button_1.setFont(new Font("Arial", Font.PLAIN, 11));
		button_1.setBounds(480, 151, 89, 23);
		attendentDetailsPanel.add(button_1);
		
		JButton button_2 = new JButton("Search");
		button_2.setFont(new Font("Arial", Font.PLAIN, 11));
		button_2.setBounds(366, 151, 89, 23);
		attendentDetailsPanel.add(button_2);
		
		JButton btnSeeAttendentsDetails = new JButton("See Attendent's Details");
		btnSeeAttendentsDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeeAttendentsDetails.setBounds(366, 185, 203, 23);
		attendentDetailsPanel.add(btnSeeAttendentsDetails);
		btnSeeAttendentsDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DetailsFrame(getIdFromSelectedItem(list), "Attendent");
			}
		});
		
		return attendentDetailsPanel;
	}

	private void doMagic(DefaultListModel<String> model, String position, String URL) {
		model.removeAllElements();
		
		String param = "{ \n" +
				"\"position\" :" + "\"" + position + "\""
				+ "\n}";
		
		
		JSONArray response = new JSONArray();
		JSONParser parser = new JSONParser();
		try {
			response = specialFetch(param, "POST", URL);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0; i<response.size(); i++) {
			JSONObject obj = new JSONObject();
			try {
				obj = (JSONObject) parser.parse(response.get(i).toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(URL.equals("http://localhost:4000/retrieve")) {
				model.add(i, "User ID : " + obj.get("user_id").toString() + " Name : " + obj.get("name"));
			}
			else {
				model.add(i, "Serial No : " + obj.get("serial_no").toString() + " Name : " + obj.get("patient_name"));
			}
		}
	}
	
	private String getIdFromSelectedItem(JList list) {
		String selectedValue = list.getSelectedValue().toString();
		System.out.println(selectedValue);
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(selectedValue);
        String userId = null;
        while(m.find()){
            System.out.println(m.group());
            userId = m.group();
        }
        return userId;
	}
	//-------------------------------------------------------------------------------------------------//
	
	//SECTION DOCTOR PANEL :
		
	//--------------------------------------------------------------------------------------------------//
	
	private void doctorPanel(JSONObject response) {
		final JPanel doctorPanel = new JPanel();
		doctorPanel.setBounds(0, 0, 772, 371);
		frmHospitalManagementSystem.getContentPane().add(doctorPanel);
		doctorPanel.setLayout(null);
		
		JPanel workspacePanel = new JPanel();
		workspacePanel.setBackground(Color.ORANGE);
		workspacePanel.setLayout(null);
		workspacePanel.setBounds(0, 57, 772, 314);
		doctorPanel.add(workspacePanel);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(21, 36, 388, 265);
		workspacePanel.add(list);
		
		JLabel lblPatientsAppointed = new JLabel("Patients Appointed");
		lblPatientsAppointed.setBounds(21, 11, 308, 14);
		workspacePanel.add(lblPatientsAppointed);
		
		JLabel lbInstruction = new JLabel("Search By Patient's Name :");
		lbInstruction.setBounds(490, 36, 203, 14);
		workspacePanel.add(lbInstruction);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(490, 61, 203, 20);
		workspacePanel.add(textField_6);
		
		
		JButton btnSeePatientDetails = new JButton("See Patient Details");
		btnSeePatientDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeePatientDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeePatientDetails.setBounds(490, 126, 203, 23);
		workspacePanel.add(btnSeePatientDetails);
		
		JButton btnAddPatientTiming = new JButton("Add Details");
		btnAddPatientTiming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddPatientTiming.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddPatientTiming.setBounds(490, 161, 203, 23);
		workspacePanel.add(btnAddPatientTiming);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(490, 92, 203, 23);
		workspacePanel.add(btnSearch);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 772, 57);
		doctorPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblWelcomeDoctor = new JLabel("Welcome Doctor");
		lblWelcomeDoctor.setBounds(23, 21, 243, 14);
		headerPanel.add(lblWelcomeDoctor);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctorPanel.setVisible(false);
			}
		});
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBackground(Color.RED);
		btnLogOut.setBounds(661, 17, 89, 23);
		headerPanel.add(btnLogOut);
	}

	//-------------------------------------------------------------------------------------------------//
	
	//SECTION ATTENDENT PANEL :
		
	//--------------------------------------------------------------------------------------------------//
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	private void attendentPanel(JSONObject response) {

		final JPanel attendentPanel = new JPanel();
		attendentPanel.setBounds(0, 0, 772, 371);
		frmHospitalManagementSystem.getContentPane().add(attendentPanel);
		attendentPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 772, 62);
		attendentPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblWelcomeAttendent = new JLabel("Welcome Attendent");
		lblWelcomeAttendent.setFont(new Font("Arial", Font.PLAIN, 30));
		lblWelcomeAttendent.setBounds(36, 11, 473, 40);
		headerPanel.add(lblWelcomeAttendent);
		
		JButton btnLogOut_1 = new JButton("Log Out");
		btnLogOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attendentPanel.setVisible(false);
			}
		});
		btnLogOut_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogOut_1.setForeground(Color.WHITE);
		btnLogOut_1.setBorderPainted(false);
		btnLogOut_1.setBackground(Color.RED);
		btnLogOut_1.setBounds(658, 21, 89, 23);
		headerPanel.add(btnLogOut_1);
		
		JLabel lblUserid = new JLabel("user_id");
		lblUserid.setBounds(598, 25, 48, 14);
		headerPanel.add(lblUserid);
		
		JPanel workspacePanel = new JPanel();
		workspacePanel.setBackground(Color.ORANGE);
		workspacePanel.setBounds(0, 61, 772, 310);
		attendentPanel.add(workspacePanel);
		workspacePanel.setLayout(null);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(22, 35, 373, 251);
		workspacePanel.add(list);
		
		JLabel lblPatientsAdmitted_1 = new JLabel("Patients Admitted :");
		lblPatientsAdmitted_1.setBounds(22, 10, 373, 14);
		workspacePanel.add(lblPatientsAdmitted_1);
		
		JButton btnAddNewPatient_1 = new JButton("Add New Patient");
		btnAddNewPatient_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewPatient_1.setBounds(416, 33, 331, 53);
		workspacePanel.add(btnAddNewPatient_1);
		
		JLabel lblSearchPatients = new JLabel("Search Patients");
		lblSearchPatients.setBounds(416, 114, 159, 14);
		workspacePanel.add(lblSearchPatients);
		
		txtEnterName = new JTextField();
		txtEnterName.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterName.setText("");
			}
		});
		
		txtEnterName.setText("Enter Name");
		txtEnterName.setBounds(416, 139, 331, 20);
		workspacePanel.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch_1.setBounds(416, 170, 159, 23);
		workspacePanel.add(btnSearch_1);
		
		JButton btnNewButton = new JButton("Discharge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(585, 170, 162, 23);
		workspacePanel.add(btnNewButton);
		
		JButton btnSeePatientDetails_1 = new JButton("See Patient Details");
		btnSeePatientDetails_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeePatientDetails_1.setBounds(416, 204, 331, 23);
		workspacePanel.add(btnSeePatientDetails_1);
		
	}

	
	//-------------------------------------------------------------------------------------------------//
	
	//SECTION NURSE PANEL :
		
	//--------------------------------------------------------------------------------------------------//
	
	private void nursePanel(JSONObject response) {
		final JPanel nursePanel = new JPanel();
		nursePanel.setLayout(null);
		nursePanel.setBounds(0, 0, 772, 371);
		frmHospitalManagementSystem.getContentPane().add(nursePanel);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 772, 62);
		nursePanel.add(headerPanel);
		
		JLabel lblWelcomeNurse = new JLabel("Welcome Nurse");
		lblWelcomeNurse.setFont(new Font("Arial", Font.PLAIN, 30));
		lblWelcomeNurse.setBounds(36, 11, 473, 40);
		headerPanel.add(lblWelcomeNurse);
		
		JButton button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nursePanel.setVisible(false);
				loginPanel();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBorderPainted(false);
		button.setBackground(Color.RED);
		button.setBounds(658, 21, 89, 23);
		headerPanel.add(button);
		
		JLabel label_1 = new JLabel("user_id");
		label_1.setBounds(598, 25, 48, 14);
		headerPanel.add(label_1);
		
		JPanel workspacePanel = new JPanel();
		workspacePanel.setBackground(Color.ORANGE);
		workspacePanel.setLayout(null);
		workspacePanel.setBounds(0, 61, 772, 310);
		nursePanel.add(workspacePanel);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(22, 35, 373, 251);
		workspacePanel.add(list);
		
		JLabel label_2 = new JLabel("Patients Admitted :");
		label_2.setBounds(22, 10, 373, 14);
		workspacePanel.add(label_2);
		
		JLabel label_3 = new JLabel("Search Patients");
		label_3.setBounds(417, 35, 159, 14);
		workspacePanel.add(label_3);
		
		textField_7 = new JTextField();
		textField_7.setText("Enter Name");
		textField_7.setColumns(10);
		textField_7.setBounds(417, 60, 331, 20);
		workspacePanel.add(textField_7);
		
		JButton button_2 = new JButton("Search");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(417, 91, 331, 23);
		workspacePanel.add(button_2);
		
		JButton button_4 = new JButton("See Patient Details");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setBounds(417, 125, 331, 23);
		workspacePanel.add(button_4);
	}
}
