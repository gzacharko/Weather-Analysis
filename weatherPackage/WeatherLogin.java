// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherLogin.java
// This class creates and holds the functionality for the "Login" window of the program.

package weatherPackage;

//imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class WeatherLogin extends JFrame {
	
	private static final long serialVersionUID = 1;
	
	// Creates GUI Frame and Panel
	JFrame loginGUI = new JFrame("Weather Data Analysis Program");
	JPanel loginPanel = new JPanel(new GridBagLayout());
	
	// Creates Labels for Username and Password
	JLabel userNameLabel = new JLabel("Enter a Username: ");
	JLabel passWordLabel = new JLabel("Enter a Password: ");
	
	// Creates Username and Password Text Boxes
	JTextField textFieldUsername = new JTextField("", 11);
	JTextField textFieldPassword = new JTextField("", 11);
		
	// Creates the "Login" Confirmation Button
	JButton loginButton = new JButton("Login");
		
	// Creates the "Cancel" Button
	JButton loginCancelButton = new JButton("Cancel");
	
	// Constructor with Configurations
	public WeatherLogin() {
		loginGUI.setSize(400, 250); // sets the width, then the height of the window
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(1, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 1; // Sets the Row Constraint to 1
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		loginPanel.add(userNameLabel, con); // Adds Username Label to the Panel
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		loginPanel.add(textFieldUsername, con); // Adds Username Text Box to Panel
		textFieldUsername.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 4; // Sets the Row Constraint to 4
		con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		loginPanel.add(passWordLabel, con); // Adds Password Label to the Panel
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 4; // Sets the Row Constraint to 4
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		loginPanel.add(textFieldPassword, con); // Adds Password Text Box to Panel
		textFieldPassword.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 10; // Sets the Row Constraint to 10
		con.insets = new Insets(40, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		loginPanel.add(loginButton, con); // Adds the "Login" Button to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 10; // Sets the Row Constraint to 10
		con.insets = new Insets(40, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns to the Left
		loginPanel.add(loginCancelButton, con); // Adds the "Cancel" Button to the Panel
		
		// sets a nice boarder for the panel
		loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Weather Data Anyalsis Project - Login"));
		
		loginGUI.add(loginPanel); // Adds the Panel to the GUI
		loginGUI.setResizable(false); // Makes User Unable to Resize the Window
		loginGUI.setLocationRelativeTo(null); // Makes Initial Window Location Centered on the Screen
		loginGUI.setVisible(true); // Makes the GUI visible
		// Presumes that the user clicked on the "X" in the right corner of the window, not the "Cancel" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the "Login" window is closed, the default option is to stop/terminate the whole program
		
		// the following is a class that uses action listeners to implement functionality of the Login button
		class LoginButtonListener implements ActionListener {
			// method to capture action/event
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\nWeatherLogin class: Login button was clicked.");
				// get the inputs that the user entered into the text fields
				String uName = textFieldUsername.getText();
				String pWord = textFieldPassword.getText();
				// input validation - verify the account's credentials
				boolean success = verifyAcc(uName, pWord);
						
				// flow of control for if the account is valid or not
				if(success == true)
				{
					JOptionPane.showMessageDialog(null, "\n       Login Successful!\n"); // Successful Login dialog box
					// system message to print username and password to the console for user reference
					System.out.println("\nUsername: " + uName + "\tPassword: " + pWord);
					loginGUI.dispose(); // get rid of the login window
					new WeatherGUI(); // create a new instance of the main Weather GUI
				}
				else
				{
					JOptionPane.showMessageDialog(null, "\n  Invalid Username and/or Password\n  or\n  Account does not exist!  \n\n  Please enter another Username and Password\n  or\n  Create an account.  \n");
					// system message to print invalid username and password to the console for user reference
					System.out.println("\nUsername: " + uName + "\tPassword: " + pWord);
					textFieldUsername.setText("");
					textFieldPassword.setText("");
				}
			} // end of method
		}; // end of CreateButtonListener class
		
		// Add the above ActionListener to the "Login" button
		loginButton.addActionListener(new LoginButtonListener());
		
		// the following class uses action listeners to implement functionality of the Cancel button
		class CancelButtonListener implements ActionListener {
			// method to capture action/event
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("WeatherLogin class: Cancel button was clicked.");
				loginGUI.dispose(); // get rid of the account creation window
				callWeatherDataMain(); // calls the method
			}
					
			// this method calls the main method of WeatherData to bring up the startup dialog box
			public void callWeatherDataMain() {
				WeatherData.main(null);
			}
		}; // end of CancelButtonListener class
				
		// Add the above Action Listener to the "Cancel" button
		loginCancelButton.addActionListener(new CancelButtonListener());
		
	} // end of constructor
	
	// verify account method
	boolean verifyAcc(String uName, String pWord) {
		
		// declare boolean variables for verification
		boolean userExists = true; // is the username text field not blank (does it have text in it)?
		boolean passExists = true; // is the password text field not blank (does it have text in it)?
		boolean goodAccount = false; // are username and password in the file?; flips to true if both are in the file
		
		// algorithm
		
		// if password field is blank
		if(pWord.contentEquals(""))
		{
			JOptionPane.showMessageDialog(null, "\n  Password field is blank!  \n  Please enter a valid password.  \n");
			passExists = false;
		}
		
		if(uName.contentEquals("")) // if username field is blank
		{
			JOptionPane.showMessageDialog(null, "\n  Username field is blank!  \n  Please enter a Username.  \n");
			userExists = false;
		}
		
		// if there is a username and password entered into the text fields
		if((userExists == true) && (passExists == true))
		{
			File accountFile = new File("AccountInfo.txt"); // declares new file to store valid account information
			
			try(Scanner scan = new Scanner(accountFile)) // declares new scanner to scan the file and tries it to see if it exists; if file does not exist, exception is thrown and handled
			{
				while((scan.hasNext()) && (goodAccount == false)) {
					String user = scan.next(); // reads up to the delimiter (whitespace); gets the username
					String pass = scan.next(); // reads up to the next delimiter (whitespace); gets the password
					
					// if both the inputted username and password are found in the file, then the account is found and valid
					if((user.contentEquals(uName)) && (pass.contentEquals(pWord))) {
						goodAccount = true;
					}
				} // end of while loop
				
				scan.close(); // closes Scanner when done
			}
			catch(FileNotFoundException e) {
				System.out.println("\nThe Account Info file cannot be found or opened.\n");
				e.printStackTrace();
			} // end of try-catch block
			
		} // end of if statement
		
		return goodAccount;
		
	} // end of verifyAcc method
	
}; // end of WeatherLogin class