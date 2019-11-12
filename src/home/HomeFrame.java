package home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.net.*;

public class HomeFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeFrame() throws ParseException {
		initialize();
		try {
			POSTRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 350, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log In");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 27));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 119, 61);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 83, 172, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 186, 172, 20);
		frame.getContentPane().add(passwordField);
		
		JRadioButton rdbtnDoctor = new JRadioButton("Doctor");
		rdbtnDoctor.setBounds(10, 110, 89, 23);
		frame.getContentPane().add(rdbtnDoctor);
		
		JRadioButton radioButton = new JRadioButton("Attendent");
		radioButton.setBounds(10, 136, 172, 23);
		frame.getContentPane().add(radioButton);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(108, 110, 74, 23);
		frame.getContentPane().add(rdbtnAdmin);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(10, 217, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblRegisterUsingUnique = new JLabel("Register Using Unique ID");
		lblRegisterUsingUnique.setBounds(10, 247, 172, 14);
		frame.getContentPane().add(lblRegisterUsingUnique);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(10, 66, 172, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 167, 172, 14);
		frame.getContentPane().add(lblPassword);
	}
	
	private static void POSTRequest() throws IOException, ParseException {
	    final String POST_PARAMS = "{\n" + 
	    		"\"username\": \"soumen\",\r\n" +
	    		"\"password\": \"jkfgtioeoi4ffd@h\",\r\n" +
	    		"\"position\": \"Student\"" +
	    		"\n}";
	    System.out.println(POST_PARAMS);
	    URL obj = new URL("http://localhost:4000/login");
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("username", "soravrax");
	    postConnection.setRequestProperty("Content-Type", "application/json");
	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    int responseCode = postConnection.getResponseCode();
	    if (responseCode == 200) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(response.toString());
	        
	        System.out.println(json.get("email"));
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	}
}
