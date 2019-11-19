package components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.simple.parser.ParseException;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import static restAPI.CRUD.fetch;

public class AddFrame {

	private JFrame addInfoFrame;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddFrame window = new AddFrame("Doctor");
//					window.addInfoFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AddFrame(String position) {
		initialize(position);
	}

	private void showDialougeBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String position) {
		addInfoFrame = new JFrame();
		addInfoFrame.setBounds(100, 100, 522, 356);
		addInfoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addInfoFrame.getContentPane().setLayout(null);
		addInfoFrame.setVisible(true);
		addInfoFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 506, 317);
		addInfoFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 506, 64);
		panel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblAdd = new JLabel("Add new " + position);
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdd.setBounds(21, 11, 264, 30);
		headerPanel.add(lblAdd);
		
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBounds(0, 65, 506, 252);
		panel.add(detailsPanel);
		detailsPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(24, 27, 112, 14);
		detailsPanel.add(lblName);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(24, 52, 112, 14);
		detailsPanel.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(24, 80, 112, 14);
		detailsPanel.add(lblPassword);
		
		JLabel lblUsername = new JLabel("username :");
		lblUsername.setBounds(23, 108, 112, 14);
		detailsPanel.add(lblUsername);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkAvailability(usernameField.getText())) {
					performSubmit(position, nameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText());
				}
				else {
					showDialougeBox("Sorry Username is not available", "Not available");
				}
			}
		});
		btnSubmit.setBounds(197, 153, 143, 23);
		detailsPanel.add(btnSubmit);
		
		nameField = new JTextField();
		nameField.setBounds(146, 24, 325, 20);
		detailsPanel.add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(146, 49, 325, 20);
		detailsPanel.add(emailField);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(146, 77, 325, 20);
		detailsPanel.add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(145, 105, 143, 20);
		detailsPanel.add(usernameField);
		
		JButton btnCheckAvailability = new JButton("Check Availability");
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckAvailability.setBounds(298, 104, 172, 23);
		detailsPanel.add(btnCheckAvailability);
		
		JLabel lblAvailabilityinfo = new JLabel("availabilityInfo");
		lblAvailabilityinfo.setForeground(Color.BLACK);
		lblAvailabilityinfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAvailabilityinfo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAvailabilityinfo.setBounds(350, 127, 120, 14);
		detailsPanel.add(lblAvailabilityinfo);
	}

	private void performSubmit(String position, String name, String username, String password, String email) {
		// TODO Auto-generated method stub
		String params = "{\n" +
				"\"name\" : \"" + name + "\",\r\n" +
				"\"username\" : \"" + username + "\",\r\n" +
				"\"password\" : \"" + password + "\", \r\n" +
				"\"position\" : \"" + position + "\", \r\n" +
				"\"email\" : \"" + email + "\"" +
				"\n}";
		try {
			fetch(params, "POST", "http://localhost:4000/add");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkAvailability(String username) {
		boolean isAvailable = true;
		return isAvailable;
	}
}
