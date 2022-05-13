// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherCreateAccount.java
// This class creates and holds the functionality for the "Create Account" window of the program.

package weatherPackage;

// imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class WeatherCreateAccount extends JFrame {
	
	private static final long serialVersionUID = 1;
	
	// Creates GUI Frame and Panel
	JFrame createAccGUI = new JFrame("Weather Data Analysis Program");
	JPanel createAccPanel = new JPanel(new GridBagLayout());
	
	// Creates Labels for Username and Password
	JLabel userNameLabel = new JLabel("Enter a unique Username: ");
	JLabel passWordLabel1 = new JLabel("Create a password. Password must:");
	JLabel passWordLabel2 = new JLabel("          - Have at least one (1) uppercase letter.");
	JLabel passWordLabel3 = new JLabel("          - Have at least one (1) lowercase letter.");
	JLabel passWordLabel4 = new JLabel("          - Be at least nine (9) characters long.");
	JLabel passWordLabel5 = new JLabel("          - Have at least one (1) digit.");
	
	// Creates Username and Password Text Boxes
	JTextField textFieldUsername = new JTextField("", 11);
	JTextField textFieldPassword = new JTextField("", 11);
	
	// Creates the "Create Account" Confirmation Button
	JButton createAccButton = new JButton("Create Account");
	
	// Creates the "Cancel" Button
	JButton createAccCancelButton = new JButton("Cancel");
	
	
	// Constructor With Configurations
	public WeatherCreateAccount() {
		createAccGUI.setSize(500, 350); // sets the width, then the height of the window
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(1, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 1; // Sets the Row Constraint to 1
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(userNameLabel, con); // Adds Username Label to the Panel
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		createAccPanel.add(textFieldUsername, con); // Adds Username Text Box to Panel
		textFieldUsername.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 4; // Sets the Row Constraint to 4
		con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(passWordLabel1, con); // Adds Password Label #1 to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 5; // Sets the Row Constraint to 5
		con.insets = new Insets(5, 3, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(passWordLabel2, con); // Adds Password Label #2 to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 6; // Sets the Row Constraint to 6
		con.insets = new Insets(5, 3, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(passWordLabel3, con); // Adds Password Label #3 to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 7; // Sets the Row Constraint to 7
		con.insets = new Insets(5, 3, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(passWordLabel4, con); // Adds Password Label #4 to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 8; // Sets the Row Constraint to 8
		con.insets = new Insets(5, 3, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns Text to the Left
		createAccPanel.add(passWordLabel5, con); // Adds Password Label #5 to the Panel
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 8; // Sets the Row Constraint to 8
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		createAccPanel.add(textFieldPassword, con); // Adds Password Text Box to Panel
		textFieldPassword.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 10; // Sets the Row Constraint to 10
		con.insets = new Insets(40, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.EAST; // Aligns to the Right
		createAccPanel.add(createAccButton, con); // Adds the "Create Account" Button to the Panel
		
		con.gridx = 0; // Sets the Column Constraint to 0
		con.gridy = 10; // Sets the Row Constraint to 10
		con.insets = new Insets(40, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.WEST; // Aligns to the Left
		createAccPanel.add(createAccCancelButton, con); // Adds the "Cancel" Button to the Panel
		
		// sets a nice boarder for the panel
		createAccPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Weather Data Anyalsis Project - Create Account"));
		
		createAccGUI.add(createAccPanel); // Adds the Panel to the GUI
		createAccGUI.setResizable(false); // Makes User Unable to Resize the Window
		createAccGUI.setLocationRelativeTo(null); // Makes Initial Window Location Centered on the Screen
		createAccGUI.setVisible(true); // Makes the GUI visible
		// Presumes that the user clicked on the "X" in the right corner of the window, not the "Cancel" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the "Create Account" window is closed, the default option is to stop/terminate the whole program
		
		// the fclickollowing is a class that uses action listeners to implement functionality of the Create Account button
		class CreateButtonListener implements ActionListener {
			// method to capture action/event
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\nWeatherCreateAccount class: Create Account button was clicked.");
				// get the inputs that the user entered into the text fields
				String uName = textFieldUsername.getText();
				String pWord = textFieldPassword.getText();
				// input validation - verify the account's credentials
				boolean success = verifyAcc(uName, pWord);
				
				// flow of control for if the account is valid or not
				if(success == true)
				{
					JOptionPane.showMessageDialog(null, "\n  Successful Account Creation!  \n"); // Successful Account Creation dialog box
					// system message to print username and password to the console for user reference
					System.out.println("\nUsername: " + uName + "\tPassword: " + pWord);
					createAccGUI.dispose(); // get rid of the account creation window
					callSuccAccDirectBox(); // calls the method
				}
				else
				{
					JOptionPane.showMessageDialog(null, "\n  Invalid Username and/or Password!  \n  Please enter another Username and Password.  \n");
					// system message to print invalid username and password to the console for user reference
					System.out.println("\nUsername: " + uName + "\tPassword: " + pWord);
					textFieldUsername.setText("");
					textFieldPassword.setText("");
				}
			} // end of method
			
			// this method calls the method that brings up a dialog box for the user to choose where to go next: either back to the "home" screen or straight to the login window
			public void callSuccAccDirectBox() {
				successAccountDirectBox();
			}
		}; // end of CreateButtonListener class
		
		// Add the above ActionListener to the "Create Account" button
		createAccButton.addActionListener(new CreateButtonListener());
		
		// the following class uses action listeners to implement functionality of the Cancel button
		class CancelButtonListener implements ActionListener {
			// method to capture action/event
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("WeatherCreateAccount class: Cancel button was clicked.");
				createAccGUI.dispose(); // get rid of the account creation window
				callWeatherDataMain(); // calls the method
			}
			
			// this method calls the main method of WeatherData to bring up the startup dialog box
			public void callWeatherDataMain() {
				WeatherData.main(null);
			}
		}; // end of CancelButtonListener class
		
		// Add the above Action Listener to the "Cancel" button
		createAccCancelButton.addActionListener(new CancelButtonListener());
		
	} // end of constructor
	
	
	// verify account method
	boolean verifyAcc(String uName, String pWord) {
		
		// declare boolean variables for verification; will flip to true if it exists in the username/password
		boolean goodPass = false; // does password have all required elements?
		boolean upperCase = false; // are there uppercase letters?
		boolean lowerCase = false; // are there lowercase letters?
		boolean digits = false; // are there digits?
		boolean longEnough = false; // is it greater than or equal to 9 characters?
		boolean goodUser = true; // does username fit requirements? as in: does an account already use it or not; flips to true if not already used
		boolean goodAccount = false; // are username and password valid?; flips to true if both are valid
		
		// algorithm
		
		// if password field is blank
		if(pWord.contentEquals(""))
		{
			JOptionPane.showMessageDialog(null, "\n  Password field is blank!  \n  Please enter a valid password.  \n");
		}
		else // otherwise, continue password verification process
		{
			int length = pWord.length(); // get the length of the password
			
			if(length >= 9) {
				longEnough = true; // set longEnough to true if password is 9 characters or longer
			}
			
			// for each character of the password, check if it is a digit, an uppercase letter, or a lowercase letter
			// if it is any of them, set that variable to true
			for(int i = 0; i < length; i++) {
				char c = pWord.charAt(i); // get character
				
				if(Character.isDigit(c)) {
					digits = true;
				}
				
				if(Character.isUpperCase(c)) {
					upperCase = true;
				}
				
				if(Character.isLowerCase(c)) {
					lowerCase = true;
				}
			} // end of for loop
			
			// if all password requirements are met, flip goodPass to true
			if((longEnough == true) && (digits == true) && (upperCase == true) && (lowerCase == true))
			{
				goodPass = true;
			}
		} // end of if-else block for the password verification
		
		// if the username field is left blank, display error message
		if(uName.contentEquals(""))
		{
			JOptionPane.showMessageDialog(null, "\n  Username field is blank!  \n  Please enter a Username.  \n");
		}
		else // otherwise, continue with account verification
		{
			File accountFile = new File("AccountInfo.txt"); // declares new file to store valid account information
			
			try(Scanner in = new Scanner(accountFile)) // declares new scanner to scan the file and tries it to see if it exists; if file does not exist, exception is thrown and handled
			{
				if(accountFile.length() == 0) // if there are no accounts on file; nothing in text file
				{
					System.out.println("\nUsername is available!"); // system message
					goodUser = true;
					
					// if username and password satisfies all requirements, then it is verified!
					if((goodUser == true) && (goodPass == true)) {
						goodAccount = true; // password is valid!
						
						// now write the valid account information to the file
						FileWriter FW;
						try {
							FW = new FileWriter(accountFile, true);
							BufferedWriter BW = new BufferedWriter(FW);
							PrintWriter PW = new PrintWriter(BW);
							PW.println(uName + "\t" + pWord); // prints the valid username and password into the file, they are on one line but tab delimited
							PW.close(); // close print writer
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					} // end of inside if statement
				}
				else // if there are accounts on file
				{
					while((in.hasNext()) && (goodUser == true)) {
						String text = in.next(); // reads up to the delimiter (whitespace)
						// if the read content equals the username entered, then the username already exists
						if(text.contentEquals(uName))
						{
							JOptionPane.showMessageDialog(null, "\n  Username already in use!  \n  Please enter another Username.  \n");
							goodUser = false;
						}
						else if(text.contentEquals(pWord)) // if what is read is a password that is the same as the inputted one
						{
							System.out.println("\n CAUTION: Identical password detected on file. Please consider changing it."); // system message
						}
					} // end of while loop
					
					in.close(); // closes Scanner when done
					
					if(goodUser == true) // if the username is not detected, then it is avaliable
					{
						System.out.println("\nUsername is available!"); // system message
						goodUser = true;
					}
					
					// if username and password satisfies all requirements, then it is verified!
					if((goodUser == true) && (goodPass == true)) {
						goodAccount = true; // password is valid!
						
						// now write the valid account information to the file
						FileWriter FW;
						try {
							FW = new FileWriter(accountFile, true);
							BufferedWriter BW = new BufferedWriter(FW);
							PrintWriter PW = new PrintWriter(BW);
							PW.println("\n" + uName + "\t" + pWord); // prints the valid username and password into the file, they are on one line but tab delimited
							PW.close(); // close print writer
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					} // end of inside if statement
				} // end of else block
			} // end of try block
			catch(FileNotFoundException e) {
				System.out.println("\nThe Account Info input file cannot be found or opened.\n");
				e.printStackTrace();
			} // end of catch block
			
		} // end of outside else block
		
		return goodAccount;
		
	} // end of verifyAcc method
	
	
	// Successful Account Completion Directive Dialog Box Method
	public void successAccountDirectBox() {
		// After successful account creation, this dialog box will appear asking the user where they want to go next
		Object[] options = {"Go to Home Screen", "Go to Login Screen"}; // choices for the dialog box
		int selection = JOptionPane.showOptionDialog(null, // no parent window, so it is null
				"\n\n\nWhere Would You Like To Go Next?\n\n\n", // text inside the dialog box
				"Weather Data Analysis Program", // text on the border of the dialog box
				JOptionPane.YES_NO_OPTION, // option type of dialog box
				JOptionPane.QUESTION_MESSAGE, // message type
				null, // no image icon 
				options, options[1]); // the options, and initial value (buttons)
		
		// if user selects to go to the Home menu
		if(selection == 0)
		{
			System.out.println("\nGoing to Home Screen...\n"); // system message
			WeatherData.main(null); // calls method for main startup of program
		}
		else if(selection == 1) // if the user selects to go to the Login screen directly
		{
			System.out.println("\nGoing to Login Screen...\n"); // system message
			new WeatherLogin(); // calls the constructor for the Login window
		}
		else // otherwise, like clicking the "X" in the top right of the window, close out the window and program
		{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	} // end of successAccountDirectBox method
	
}; // end of WeatherCreateAccount class