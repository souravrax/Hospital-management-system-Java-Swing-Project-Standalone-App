package components;

import java.awt.EventQueue;

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


public class DetailsFrame {

	private JFrame frame;

	public DetailsFrame(String userID, String position) {
		initialize(userID, position);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userID, String position) {
		frame = new JFrame();
		frame.setBounds(100, 100, 512, 264);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 502, 225);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 492, 64);
		panel.add(headerPanel);
		
		JLabel label = new JLabel(position + " Details of User ID : " + userID);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBounds(21, 11, 461, 30);
		headerPanel.add(label);
		
		JPanel detailsPanel = new JPanel();
		detailsPanel.setLayout(null);
		detailsPanel.setBounds(0, 65, 492, 158);
		panel.add(detailsPanel);
		
		JLabel nameLabel = new JLabel("Name :");
		nameLabel.setBounds(24, 27, 425, 14);
		detailsPanel.add(nameLabel);
		
		JLabel emailLable = new JLabel("Email :");
		emailLable.setBounds(24, 52, 425, 14);
		detailsPanel.add(emailLable);
		
		JLabel positionLabel = new JLabel("Position :");
		positionLabel.setBounds(24, 80, 425, 14);
		detailsPanel.add(positionLabel);
		
		JLabel usernameLable = new JLabel("username :");
		usernameLable.setBounds(23, 108, 426, 14);
		detailsPanel.add(usernameLable);
		
		JSONObject response = new JSONObject();
		
		try {
			String json = "{\n" + 
					"\"userID\": " + userID +
					"\n}";
			response = fetch(json, "POST", "http://localhost:4000/fetch_user_details");
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.get("username")!=null) {
			nameLabel.setText(nameLabel.getText() + " "+ response.get("name"));
			usernameLable.setText(usernameLable.getText() + " " + response.get("username"));
			positionLabel.setText(positionLabel.getText() + " " + response.get("position"));
			emailLable.setText(emailLable.getText() +  " " + response.get("email"));
		}
		else {
			showDialougeBox("Sorry the user is not found", "404");
		}
		
		
	}
	
	private void showDialougeBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
