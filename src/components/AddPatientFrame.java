package components;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.JButton;
import java.awt.Font;
import static restAPI.CRUD.fetch;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import static restAPI.CRUD.fetch;

public class AddPatientFrame {

	private JFrame frameAddPatient;
	private JTextField tfName;
	private JTextField tfContact;
	private JTextField tfBloodGroup;
	private JTextField tfDOB;
	private JTextField tfPartner;
	private JTextField tfEmail;
	private JTextField tbAddress;
	private JTextField tbTownCity;
	private JTextField tfPincode;
	private JTextField tfDoctorAppointed;
	private JTextField tfState;
	private JTextField tfCountry;

	public AddPatientFrame(String position) {
		initialize(position);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String position) {

		frameAddPatient = new JFrame();
		frameAddPatient.setBounds(100, 100, 505, 650);
		frameAddPatient.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameAddPatient.getContentPane().setLayout(null);
		frameAddPatient.setResizable(false);
		frameAddPatient.setVisible(true);
		
		JPanel addPatientFrame = new JPanel();
		addPatientFrame.setBounds(0, 0, 499, 611);
		frameAddPatient.getContentPane().add(addPatientFrame);
		addPatientFrame.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 501, 60);
		addPatientFrame.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblAddPatientDetails = new JLabel("Add Patient Details");
		lblAddPatientDetails.setFont(new Font("Arial", Font.PLAIN, 24));
		lblAddPatientDetails.setBounds(20, 11, 310, 38);
		headerPanel.add(lblAddPatientDetails);
		
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBounds(0, 59, 501, 552);
		addPatientFrame.add(detailsPanel);
		detailsPanel.setLayout(null);
		
		JLabel label = new JLabel("Name :");
		label.setBounds(22, 22, 104, 14);
		detailsPanel.add(label);
		
		JLabel label_1 = new JLabel("Primary Contact No :");
		label_1.setBounds(22, 47, 104, 14);
		detailsPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Blood Group :");
		label_2.setBounds(22, 72, 211, 14);
		detailsPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Maritial Status");
		label_3.setBounds(22, 103, 104, 14);
		detailsPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Partner Name :");
		label_4.setBounds(22, 153, 211, 14);
		detailsPanel.add(label_4);
		
		JLabel label_5 = new JLabel("DOB :");
		label_5.setBounds(22, 128, 61, 14);
		detailsPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Sex :");
		label_6.setBounds(206, 128, 68, 14);
		detailsPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Email :");
		label_7.setBounds(22, 181, 80, 14);
		detailsPanel.add(label_7);
		
		JLabel label_8 = new JLabel("Address :");
		label_8.setBounds(22, 205, 97, 14);
		detailsPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Pincode :");
		label_9.setBounds(223, 231, 61, 14);
		detailsPanel.add(label_9);
		
		JLabel label_10 = new JLabel("State :");
		label_10.setBounds(22, 256, 68, 14);
		detailsPanel.add(label_10);
		
		JLabel label_11 = new JLabel("Doctor Appointed :");
		label_11.setBounds(22, 284, 115, 14);
		detailsPanel.add(label_11);
		
		JLabel label_12 = new JLabel("Critical Information :");
		label_12.setBounds(22, 312, 174, 14);
		detailsPanel.add(label_12);
		
		JLabel label_13 = new JLabel("Medicine List :");
		label_13.setBounds(22, 450, 211, 14);
		detailsPanel.add(label_13);
		
		JLabel label_14 = new JLabel("Discharged :");
		label_14.setBounds(22, 379, 104, 14);
		detailsPanel.add(label_14);
		
		JLabel lblTowncity = new JLabel("Town/City :");
		lblTowncity.setBounds(22, 231, 68, 14);
		detailsPanel.add(lblTowncity);
		
		JLabel label_16 = new JLabel("Timing of Medicine :");
		label_16.setBounds(22, 404, 137, 14);
		detailsPanel.add(label_16);
		
		tfName = new JTextField();
		tfName.setBounds(125, 19, 292, 20);
		detailsPanel.add(tfName);
		tfName.setColumns(10);
		
		tfContact = new JTextField();
		tfContact.setColumns(10);
		tfContact.setBounds(125, 44, 292, 20);
		detailsPanel.add(tfContact);
		
		tfBloodGroup = new JTextField();
		tfBloodGroup.setColumns(10);
		tfBloodGroup.setBounds(125, 72, 292, 20);
		detailsPanel.add(tfBloodGroup);
		
		tfDOB = new JTextField();
		tfDOB.setBounds(67, 125, 129, 20);
		detailsPanel.add(tfDOB);
		tfDOB.setColumns(10);
		
		tfPartner = new JTextField();
		tfPartner.setColumns(10);
		tfPartner.setBounds(125, 150, 292, 20);
		detailsPanel.add(tfPartner);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(125, 178, 292, 20);
		detailsPanel.add(tfEmail);
		
		tbAddress = new JTextField();
		tbAddress.setColumns(10);
		tbAddress.setBounds(125, 202, 292, 20);
		detailsPanel.add(tbAddress);
		
		tbTownCity = new JTextField();
		tbTownCity.setColumns(10);
		tbTownCity.setBounds(89, 227, 129, 20);
		detailsPanel.add(tbTownCity);
		
		tfPincode = new JTextField();
		tfPincode.setColumns(10);
		tfPincode.setBounds(286, 228, 129, 20);
		detailsPanel.add(tfPincode);
		
		tfDoctorAppointed = new JTextField();
		tfDoctorAppointed.setColumns(10);
		tfDoctorAppointed.setBounds(125, 281, 292, 20);
		detailsPanel.add(tfDoctorAppointed);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(89, 253, 129, 20);
		detailsPanel.add(tfState);
		
		tfCountry = new JTextField();
		tfCountry.setColumns(10);
		tfCountry.setBounds(288, 256, 129, 20);
		detailsPanel.add(tfCountry);
		
		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setBounds(223, 256, 61, 14);
		detailsPanel.add(lblCountry);
		
		final JComboBox<String> cbSex = new JComboBox<String>();
		cbSex.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female", "Other"}));
		cbSex.setBounds(241, 124, 176, 22);
		detailsPanel.add(cbSex);
		
		final JComboBox<String> cbDischarged = new JComboBox<String>();
		cbDischarged.setModel(new DefaultComboBoxModel<String>(new String[] {"Yes", "No"}));
		cbDischarged.setBounds(118, 375, 299, 22);
		detailsPanel.add(cbDischarged);
		
		final JTextPane tpCriticalInfo = new JTextPane();
		tpCriticalInfo.setBounds(135, 312, 282, 52);
		detailsPanel.add(tpCriticalInfo);
		
		final JTextPane tpTimingOfMadicine = new JTextPane();
		tpTimingOfMadicine.setBounds(135, 408, 282, 31);
		detailsPanel.add(tpTimingOfMadicine);
		
		final JTextPane tpMedicineList = new JTextPane();
		tpMedicineList.setBounds(135, 450, 282, 52);
		detailsPanel.add(tpMedicineList);
		
		final JComboBox<String> cbMaritialStatus = new JComboBox<String>();
		cbMaritialStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Married", "Unmarried"}));
		cbMaritialStatus.setBounds(125, 97, 292, 22);
		detailsPanel.add(cbMaritialStatus);
		
		if(position.equals("Doctor")) {
			tfName.setEnabled(false);
			tfContact.setEnabled(false);
			tfBloodGroup.setEnabled(false);
			cbMaritialStatus.setEnabled(false);
			tfPartner.setEnabled(false);
			tfDOB.setEnabled(false);
			cbSex.setEnabled(false);
			tfEmail.setEnabled(false);
			tbAddress.setEnabled(false);
			tbTownCity.setEnabled(false);
			tfPincode.setEnabled(false);
			tfState.setEnabled(false);
			tfCountry.setEnabled(false);
			tfDoctorAppointed.setEnabled(false);
			cbDischarged.setEnabled(false);
			
		}
		else if(position.equals("Attendent") || position.equals("Admin")) {
			tpCriticalInfo.setEnabled(false);
			tpTimingOfMadicine.setEnabled(false);
			tpMedicineList.setEnabled(false);
		}
		
		JButton btnSubmitDetails = new JButton("Submit Details");
		btnSubmitDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(position.equals("Attendent") || position.equals("Admin")) {
					String name = tfName.getText();
					String primaryContactNumber = tfContact.getText();
					String bloodGroup = tfBloodGroup.getText();
					String maritialStatus = cbMaritialStatus.getSelectedItem().toString();
					String partnername = tfPartner.getText();
					String dob = tfDOB.getText();
					String sex = cbSex.getSelectedItem().toString();
					String email = tfEmail.getText();
					String address = tbAddress.getText();
					String townCity = tbTownCity.getText();
					String pincode = tfPincode.getText();
					String state = tfState.getText();
					String country = tfCountry.getText();
					String doctorAppointed = tfDoctorAppointed.getText();
					String discharged = cbDischarged.getSelectedItem().toString();
					insertPatient(name, primaryContactNumber, bloodGroup, maritialStatus, partnername, dob, sex, email, address, townCity, pincode, state, country, doctorAppointed, discharged);
				}
				else if(position.equals("Doctor")){
					String criticalInfo = tpCriticalInfo.getText();
					String medicineList = tpMedicineList.getText();
					String timingOfMedicine = tpTimingOfMadicine.getText();
					updateDoctor(criticalInfo, medicineList, timingOfMedicine);
				}
				else {
					
				}
				
			}
		});
		btnSubmitDetails.setBounds(22, 518, 395, 23);
		detailsPanel.add(btnSubmitDetails);
		
		
	}
	
	private void insertPatient(String name, String primaryContactNumber,String bloodGroup,  String maritialStatus, String partnerName,
			String dob, String sex, String email, String address, String townCity, String pincode, String state, String country,
			String doctorAppointed, String discharged) {
			boolean isDischarged = false;
			if(discharged == "Yes") {
				isDischarged = true;
			}
			
		String queryString = "{\n" +
				"\"name\" : \"" + name + "\",\r\n" +
				"\"primaryContactNumber\" : " + primaryContactNumber + ",\r\n" +
				"\"bloodGroup\" : \"" + bloodGroup + "\",\r\n" +
				"\"married\" : \"" + maritialStatus + "\",\r\n" +
				"\"partnerName\" : \"" + partnerName + "\",\r\n" +
				"\"dob\" : \"" + dob + "\",\r\n" +
				"\"sex\" : \"" + sex + "\",\r\n" +
				"\"email\" : \"" + email + "\",\r\n" +
				"\"address\" : \"" + address + "\",\r\n" +
				"\"townCity\" : \"" + townCity + "\",\r\n" +
				"\"pincode\" : " + pincode + ",\r\n" +
				"\"state\" : \"" + state + "\",\r\n" +
				"\"country\" : \"" + country + "\",\r\n" +
				"\"discharged\" : " +	isDischarged + ",\r\n" +
				"\"doctorAppointed\" : \"" + doctorAppointed + "\""
				+"\n}";
				System.out.println(queryString);
		
		try {
			JSONObject response = fetch(queryString, "POST", "http://localhost:4000/insert_patient");
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateDoctor(String criticalInfo, String medicineList, String timingOfMedicine) {
		String queryString = "{\n" + 
//				"\"serialNo\" : " + serialNo + ",\r\n" + 
				"\"criticalInfo\" : \"" + criticalInfo + "\",\r\n" +
				"\"medicineList\" : \"" + medicineList + "\",\r\n" +
				"\"timingOfMedicine\" : \"" + timingOfMedicine + "\""
				+"\n}";
		try {
			JSONObject response = fetch(queryString, "POST", "http://localhost:4000/doctor_update_patients_details");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
