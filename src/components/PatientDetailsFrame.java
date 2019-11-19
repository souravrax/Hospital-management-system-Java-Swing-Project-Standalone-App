package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;

import static restAPI.CRUD.fetch;

public class PatientDetailsFrame {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PatientDetailsFrame(String serialNo) {
		initialize(serialNo);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String serialNo) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel patientDetailsPanel = new JPanel();
		patientDetailsPanel.setBounds(0, 0, 434, 511);
		frame.getContentPane().add(patientDetailsPanel);
		patientDetailsPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 434, 66);
		patientDetailsPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPatientDetails.setBounds(12, 16, 274, 30);
		headerPanel.add(lblPatientDetails);
		
		JLabel lblSerialNo = new JLabel("Serial No :");
		lblSerialNo.setBounds(324, 30, 77, 14);
		headerPanel.add(lblSerialNo);
		
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBounds(0, 67, 434, 444);
		patientDetailsPanel.add(detailsPanel);
		detailsPanel.setLayout(null);
		
		JLabel lbName = new JLabel("Name :");
		lbName.setBounds(22, 22, 211, 14);
		detailsPanel.add(lbName);
		
		JLabel lbContact = new JLabel("Primary Contact No :");
		lbContact.setBounds(21, 42, 211, 14);
		detailsPanel.add(lbContact);
		
		JLabel lbBloodGroup = new JLabel("Blood Group :");
		lbBloodGroup.setBounds(22, 65, 211, 14);
		detailsPanel.add(lbBloodGroup);
		
		JLabel lbMaritial = new JLabel("Maritial Status");
		lbMaritial.setBounds(22, 86, 211, 14);
		detailsPanel.add(lbMaritial);
		
		JLabel lbPartner = new JLabel("Partner Name :");
		lbPartner.setBounds(22, 108, 211, 14);
		detailsPanel.add(lbPartner);
		
		JLabel lbDOB = new JLabel("DOB :");
		lbDOB.setBounds(22, 128, 157, 14);
		detailsPanel.add(lbDOB);
		
		JLabel lbSex = new JLabel("Sex :");
		lbSex.setBounds(195, 128, 181, 14);
		detailsPanel.add(lbSex);
		
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setBounds(22, 146, 211, 14);
		detailsPanel.add(lbEmail);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(22, 164, 211, 14);
		detailsPanel.add(lblAddress);
		
		JLabel lbPincode = new JLabel("Pincode :");
		lbPincode.setBounds(195, 200, 168, 14);
		detailsPanel.add(lbPincode);
		
		JLabel lbState = new JLabel("State :");
		lbState.setBounds(22, 225, 168, 14);
		detailsPanel.add(lbState);
		
		JLabel lbDoctorAppointed = new JLabel("Doctor Appointed :");
		lbDoctorAppointed.setBounds(22, 250, 357, 14);
		detailsPanel.add(lbDoctorAppointed);
		
		JLabel lbCritical = new JLabel("Critical Information :");
		lbCritical.setBounds(22, 275, 357, 14);
		detailsPanel.add(lbCritical);
		
		JLabel lbMedicineList = new JLabel("Medicine List :");
		lbMedicineList.setBounds(22, 324, 211, 14);
		detailsPanel.add(lbMedicineList);
		
		JLabel lbDischarged = new JLabel("Discharged :");
		lbDischarged.setBounds(22, 419, 211, 14);
		detailsPanel.add(lbDischarged);
		
		JLabel lblTowncity = new JLabel("Town/City :");
		lblTowncity.setBounds(22, 200, 163, 14);
		detailsPanel.add(lblTowncity);
		
		JLabel lbTIming = new JLabel("Timing of Medicine :");
		lbTIming.setBounds(22, 370, 369, 14);
		detailsPanel.add(lbTIming);
		
		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setBounds(195, 225, 196, 14);
		detailsPanel.add(lblCountry);
		
		JSONObject response = new JSONObject();
		
		try {
			String json = "{\n" + 
					"\"serialId\": " + serialNo +
					"\n}";
			response = fetch(json, "POST", "http://localhost:4000/patientRetrieveBySerialNo");
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.get("patient_name")!=null) {
			lbName.setText(lbName.getText() + " " + response.get("patient_name"));
			lbContact.setText(lbContact.getText() + " " + response.get("primary_contact_no"));
			lbBloodGroup.setText(lbBloodGroup.getText() + " " + response.get("blood_group"));
			lbMaritial.setText(lbMaritial.getText() + " " + response.get("maritial_status"));
			lbPartner.setText(lbPartner.getText() + " " + response.get("dob"));
			lbSex.setText(lbSex.getText() + " " + response.get("sex"));
			lbEmail.setText(lbEmail.getText() + " " + response.get("primary_email"));
			lblAddress.setText(lblAddress.getText() + " " + response.get("address"));
			lblTowncity.setText(lblTowncity.getText() + " " + response.get("town_city"));
			lbPincode.setText(lbPincode.getText() + " " + response.get("pincode"));
			lbState.setText(lbState.getText() + " " + response.get("state"));
			lblCountry.setText(lblCountry.getText() + " " + response.get("country"));
			lbDoctorAppointed.setText(lbDoctorAppointed.getText() + " " + response.get("doctor_appointed"));
			lbCritical.setText(lbCritical.getText() + " " + response.get("critical_information"));
			lbMedicineList.setText(lbMedicineList.getText() + " " + response.get("medicine_list"));
			lbTIming.setText(lbTIming.getText() + " " + response.get("timing_of_medicine"));
			lbDischarged.setText(lbDischarged.getText() + " " + response.get("discharged"));
		}
		else {
			showDialougeBox("Sorry the user is not found", "404");
		}
	}
	
	private void showDialougeBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
