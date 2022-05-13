// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherGUI.java
// This class creates and holds the functionality for the main GUI interface of the program.

package weatherPackage;

// imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class WeatherGUI extends JFrame {
	
	private static final long serialVersionUID = 1;
	
	// Create the separate data display output window
	WeatherDataOutputWindow ddWindow = new WeatherDataOutputWindow();
	
	// Create the plot graph display window
	WeatherPlotGraphDisplayWindow plotWindow = new WeatherPlotGraphDisplayWindow();
	
	// Creates GUI Frame and Panel
	JFrame WeatherGUI = new JFrame("Weather Data Analysis Program");
	JPanel WeatherPanel = new JPanel(new GridBagLayout());
	
	// Create Labels for User Inputs and Results
	JLabel enterTempLabel = new JLabel("Enter the Temperature in Degrees Fahreheit: ");
	JLabel enterWindSpeedLabel = new JLabel("Enter the Wind Speed in MPH: ");
	JLabel enterDewPointLabel = new JLabel("Enter the Dew Point in Degrees Fahreheit: ");
	JLabel windChillResultLabel = new JLabel("The Wind Chill Factor in Degrees Fahrenheit is: ");
	JLabel cloudBaseResultLabel = new JLabel("The Cloud Base Altitude in Feet is: ");
	JLabel cloudTypeLabel = new JLabel("This Cloud Would Be Considered A: ");
	
	// Create Tempertature, Wind Speed, and Dew Point Text Boxes
	JTextField textFieldTemperature = new JTextField("", 8);
	JTextField textFieldWindSpeed = new JTextField("", 8);
	JTextField textFieldDewPoint = new JTextField("", 8);
	
	// Create Image Files
	File leftImageFile = new File("WindChill.png");
	File rightImageFile = new File("CloudTypes.jpg");
	File rightImageFile2 = new File("HighClouds.jpg");
	File rightImageFile3 = new File("MediumClouds.jpg");
	File rightImageFile4 = new File("LowClouds.jpg");
	JLabel rightImageLabel; // declare JLabel for the "rightImage" Files above
	BufferedImage rightImage; // declares a BufferedImage for the "rightImage" Files
	Boolean foundImage = true; // Boolean variable to determine if the image files exist or not
	
	// create labels for the resulting data values for the wind chill, cloud base, and cloud type
	JLabel windChillValueLabel = new JLabel();
	JLabel cloudBaseValueLabel = new JLabel();
	JLabel cloudTypeValueLabel = new JLabel();
	
	// Create Compute Confirmation Button
	JButton computeButton = new JButton("Compute");
	
	// Create Show File Data Button
	JButton showFileDataButton = new JButton("Show File Data");
	
	// Create Logout Button
	JButton logoutButton = new JButton("Logout");
	
	// Constructor with Configurations
	public WeatherGUI() {
		WeatherGUI.setSize(1000, 620); // sets the width, then the height of the window
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(1, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		
		// for image on the left of the interface panel
		try {
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 0; // Sets the Row Constraint to 0
			con.anchor = GridBagConstraints.NORTH; // Aligns Image to the North (Top)
			BufferedImage leftImage = ImageIO.read(leftImageFile); // makes the image file into a useable image
			JLabel leftImageLabel = new JLabel(new ImageIcon(leftImage)); // make image a JLabel so that the .add function below can use it
			WeatherPanel.add(leftImageLabel, con); // add image to panel
		}
		catch(Exception e) { // if image is not found/does not exist/can't be read by ImageIO.read
			foundImage = false; // set Boolean variable to false since image is missing
			JOptionPane.showMessageDialog(null, "\nWindChill.png image file not Found!\n"); // dialog box
			System.out.println("\nWindChill.png image file not found!\n"); // system error message
			e.printStackTrace();
		}
		
		// for image on the right of the interface panel
		try {
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 0; // Sets the Row Constraint to 0
			con.anchor = GridBagConstraints.NORTH; // Aligns Image to the North (Top)
			rightImage = ImageIO.read(rightImageFile); // makes the image file into a useable image by assigning it to the BufferedImage variable
			rightImageLabel = new JLabel(new ImageIcon(rightImage)); // make image a JLabel so that the .add function below can use it
			WeatherPanel.add(rightImageLabel, con); // add image to panel
		}
		catch(Exception e) { // if image is not found/does not exist/can't be read by ImageIO.read
			foundImage = false; // set Boolean variable to false since image is missing
			JOptionPane.showMessageDialog(null, "\nCloudTypes.jpg Image file not Found!\n"); // dialog box
			System.out.println("\nCloudTypes.jpg Image file not found!\n"); // system error message
			e.printStackTrace();
		}
		
		if(foundImage == true) // if all images exist and are found, then continue building the main interface; otherwise, program ends (code falls through)
		{
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 2; // Sets the Row Constraint to 2
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
			WeatherPanel.add(enterTempLabel, con); // Adds Temperature Label to the Panel
			
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 2; // Sets the Row Constraint to 2
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(textFieldTemperature, con); // Adds Temperature Text Box to Panel
			textFieldTemperature.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 4; // Sets the Row Constraint to 4
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
			WeatherPanel.add(enterWindSpeedLabel, con); // Adds Wind Speed Label to the Panel
			
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 4; // Sets the Row Constraint to 4
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(textFieldWindSpeed, con); // Adds Wind Speed Text Box to Panel
			textFieldWindSpeed.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 6; // Sets the Row Constraint to 6
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
			WeatherPanel.add(enterDewPointLabel, con); // Adds Dew Point Label to the Panel
			
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 6; // Sets the Row Constraint to 6
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(textFieldDewPoint, con); // Adds Dew Point Text Box to Panel
			textFieldDewPoint.setHorizontalAlignment(JTextField.RIGHT); // Sets the Alignment of the Text Box to the Right
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 8; // Sets the Row Constraint to 8
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
			WeatherPanel.add(windChillResultLabel, con); // Adds Wind Chill Results Label to the Panel
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 10; // Sets the Row Constraint to 10
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
			WeatherPanel.add(cloudBaseResultLabel, con); // Adds Cloud Base Results Label to the Panel
			
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 15; // Sets the Row Constraint to 15
			con.insets = new Insets(15, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.EAST; // Aligns to the Left
			WeatherPanel.add(computeButton, con); // Adds the "Compute" Button to the Panel
			
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 15; // Sets the Row Constraint to 15
			con.insets = new Insets(15, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(showFileDataButton, con); // Adds the "Show File Data" Button to the Panel
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 12; // Sets the Column Constraint to 12
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(cloudTypeLabel, con); // Adds the Cloud Type Label to the Panel
			
			con.gridx = 0; // Sets the Column Constraint to 0
			con.gridy = 15; // Sets the Row Constraint to 15
			con.insets = new Insets(15, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.WEST; // Aligns to the Left
			WeatherPanel.add(logoutButton, con); // Adds the "Logout" Button to the Panel
			
			// sets a nice boarder for the panel
			WeatherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Weather Data Anyalsis Project - Meteorological Calculations"));
			
			WeatherGUI.add(WeatherPanel); // Adds the Panel to the GUI
			WeatherGUI.setResizable(false); // Makes User Unable to Resize the Window
			WeatherGUI.setLocationRelativeTo(null); // Makes Initial Window Location Centered on the Screen
			WeatherGUI.setVisible(true); // Makes the GUI visible
			// Presumes that the user clicked on the "X" in the right corner of the window, not the "Exit" button
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the main GUI window is closed, the default option is to stop/terminate the whole program
			
			// Move the Main GUI to Appear In Front of the Weather Data Output Window
			WeatherGUI.toFront();
			
			// the following is a class that uses action listeners to implement functionality of the Compute button
			class ComputeButtonListener implements ActionListener {
				// method to capture action/event
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("\nWeatherGUI class: Compute button was clicked.");
					// get the inputs that the user entered into the text fields
					String temp = textFieldTemperature.getText();
					String wind = textFieldWindSpeed.getText();
					String dew = textFieldDewPoint.getText();
					// verify the wind chill; input validation for temperature, wind speed, and wind chill are within the method
					boolean validWindChill = verifyWindChill(temp, wind);
					// verify the cloud base; input validation for the dew point is within this method
					boolean validCloudBase = verifyCloudBase(dew);
					
					// if the wind chill and cloud base can be calculated, then calculate them and then display the data
					if((validWindChill == true) && (validCloudBase == true))
					{
						// calculate the wind chill
						double windChill = computeWindChill(temp, wind);
						
						// calculate the cloud base
						double cloudBase = computeCloudBase(temp, dew);
						
						// system message to print out the results to two decimal places
						System.out.println("\nWind Chill: " + String.format("%.2f", windChill) + "\tCloud Base: " + String.format("%.2f", cloudBase));
						
						// display the data on the main GUI interface; calls a method that conducts this operation
						displayDataMainGUI(windChill, cloudBase, con, rightImageFile2, rightImageFile3, rightImageFile4, rightImageLabel);
						
						// update the separate data display output window
						ddWindow.updateData(temp, wind, dew, windChill, cloudBase);
						
						// update the plot graph display window
						plotWindow.updateGraph(temp, windChill);
					}
					else // if the wind chill or cloud base can not be calculated, then it is invalid; do not display data
					{
						if(validWindChill == false) // if it is the wind chill that cannot be calculated
						{
							JOptionPane.showMessageDialog(null, "\n  Invalid Wind Chill!  \n  Please enter a Temperature less than or equal to 50.0 degrees Fahrenheit, and a Wind Speed greater than 3.0 MPH.  \n");
						}
						
						if(validCloudBase == false) // if it is the cloud base that cannot be calculated
						{
							JOptionPane.showMessageDialog(null, "\n  Invalid Cloud Base!  \n  Please enter a valid dew point (practically any number).  \n");
						}
						
						// reset the text boxes to be blank
						textFieldTemperature.setText("");
						textFieldWindSpeed.setText("");
						textFieldDewPoint.setText("");
					} // end of if else block
					
				} // end of method
				
			}; // end of ComputeButtonListener class
			
			// Add the above ActionListener to the "Compute" button
			computeButton.addActionListener(new ComputeButtonListener());
			
			// the following class uses action listeners to implement functionality of the "Show File Data" button
			class ShowFileDataListener implements ActionListener {
				// method to capture action/event
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("\nWeatherGUI class: Show File Data button was clicked.");
					ddWindow.openViewFileData(); // calls the openViewFileData method in the WeatherDataOutputWindow class
				}
			}; // end of ShowFileDataListener class
			
			// Add the above Action Listener to the "Show File Data" button
			showFileDataButton.addActionListener(new ShowFileDataListener());
			
			// the following class uses action listeners to implement functionality of the "Logout" button
			class LogoutButtonListener implements ActionListener {
				// method to capture action/event
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("\nWeatherGUI class: Logout button was clicked.");
					WeatherGUI.dispose(); // get rid of the main GUI interface of the program
					ddWindow.disposeOfWindow(); // get rid of the separate data display output window
					plotWindow.disposeOfWindow(); // get rid of the plot graph display window
					callWeatherDataMain(); // calls the method
				}
				
				// this method calls the main method of WeatherData to bring up the startup dialog box
				public void callWeatherDataMain() {
					WeatherData.main(null);
				}
			}; // end of LogoutButtonListener class
			
			// Add the above Action Listener to the "Logout" Button
			logoutButton.addActionListener(new LogoutButtonListener());
			
		} // end of if block
		
	} // end of constructor
	
	
	// verify wind chill method
	boolean verifyWindChill(String temp, String wind) {
		
		// declare boolean variables for verification
		boolean goodTemp = false; // is the temperature valid?
		boolean goodWindSpeed = false; // is the wind speed valid?
		boolean tempDigits = true; // is the temperature only digits (numbers)?
		boolean windSpeedDigits = true; // is the wind Speed only digits (numbers)?
		boolean goodWindChill = false; // is the wind chill valid/calculable?
		
		// algorithm
		
		// if the temperature text field is blank
		if(temp.contentEquals(""))
		{
			goodTemp = false; // temperature is invalid due to being blank
			JOptionPane.showMessageDialog(null, "\n  Temperature field is blank!  \n  Please enter a temperature.  \n");
		}
		else // otherwise, move on to next verification process
		{
			int length = temp.length(); // get the length of the temperature string
			
			// for each position of the temperature string, check if it is a digit
			// if there are any letters or other characters other than digits (if there is something that is not a digit), set the variable to false
			for(int i = 0; i < length; i++) {
				char c = temp.charAt(i); // get the character
				
				// if character is NOT a digit; so isDigit returns false
				if(!(Character.isDigit(c)))
				{
					tempDigits = false;
				}
				
				if(temp.charAt(i) == 0x002E) // if there is a decimal point in the text box, let it go through and accept it
				{
					tempDigits = true;
				}
				
				if(temp.charAt(i) == 0x002D) // if there is a negative sign in the text box, let it go through an accept it
				{
					tempDigits = true;
				}
			} // end of for loop
			
			// if the temperature is all digits (excluding a decimal point or negative sign), then temperature is good
			if(tempDigits == true) {
				goodTemp = true;
			}
			else // if there are non-digits in the temperature string
			{
				JOptionPane.showMessageDialog(null, "\n  WARNING: Temperature field has non-digits in it!  \n  Please enter a valid temperature.  \n");
			}
		} // end of if-else block for temperature verification
		
		// if the wind speed text field is blank
		if(wind.contentEquals(""))
		{
			goodWindSpeed = false; // wind speed is invalid due to being blank
			JOptionPane.showMessageDialog(null, "\n  Wind Speed field is blank!  \n  Please enter a wind speed.  \n");
		}
		else // otherwise, move on to next verification process
		{
			int length = wind.length(); // get the length of the wind speed string
			
			// for each position of the wind speed string, check if it is a digit
			// if there are any letters or other characters other than digits (if there is something that is not a digit), set the variable to false
			for(int i = 0; i < length; i++) {
				char c = wind.charAt(i); // get the character
						
				// if character is NOT a digit; so isDigit returns false
				if(!(Character.isDigit(c)))
				{
					windSpeedDigits = false;
				}
				
				if(wind.charAt(i) == 0x002E) // if there is a decimal point in the text box, let it go through and accept it
				{
					windSpeedDigits = true;
				}
			} // end of for loop
					
			// if the wind speed is all digits (excluding a decimal point), then wind speed is good
			if(windSpeedDigits == true) {
				goodWindSpeed = true;
			}
			else // if there are non-digits in the wind speed string
			{
				JOptionPane.showMessageDialog(null, "\n  WARNING: Wind Speed field has non-digits in it!  \n  Please enter a valid wind speed.  \n");
			}
		} // end of if-else block for wind speed verification
		
		// if both the temperature and wind speed are valid (consists of only digits), then check if the wind chill can be calculated
		if((goodTemp == true) && (goodWindSpeed == true))
		{
			// convert the temperature and wind speed strings into doubles
			double doubTemp = Double.parseDouble(temp);
			double doubWindSpeed = Double.parseDouble(wind);
			
			// if the temperature entered is above 50.0 degrees Fahrenheit, or if the wind speed is less than or equal to 3.0 MPH, then the wind chill is NOT calculable (the wind chill is invalid)
			if((doubTemp > 50.0) || (doubWindSpeed <= 3.0))
			{
				goodWindChill = false;
				System.out.println("\nThe wind chill is not calculable!\n");
			}
			else // if the temperature and wind speeds are within the right ranges for a wind chill to occur
			{
				goodWindChill = true;
				System.out.println("\nThe wind chill is calculable!\n");
			}
		}
		
		return goodWindChill;
		
	} // end of verifyWindChill method
	
	
	// verify cloud base method
	boolean verifyCloudBase(String dew) {
		
		// boolean variables for verification
		boolean dewDigits = true; // does the dew point consist of only digits?
		boolean goodDew = false; // is the dew point valid?
		boolean goodCloudBase = false; // is the cloud base valid?
		
		// if the Dew Point field is blank
		if(dew.contentEquals(""))
		{
			goodDew = false; // Dew Point is invalid due to being blank
			JOptionPane.showMessageDialog(null, "\n  Dew Point field is blank!  \n  Please enter a dew point.  \n");
		}
		else // if the Dew Point field is not blank, then continue with the verification process
		{
			int length = dew.length(); // get the length of the dew point string
			
			// for each position of the dew point string, check if it is a digit
			// if there are any letters or other characters other than digits (if there is something that is not a digit), set the variable to false
			for(int i = 0; i < length; i++) {
				char c = dew.charAt(i); // get the character
				
				// if character is NOT a digit; so isDigit returns false
				if(!(Character.isDigit(c)))
				{
					dewDigits = false;
				}
				
				if(dew.charAt(i) == 0x002E) // if there is a decimal point in the text box, let it go through and accept it
				{
					dewDigits = true;
				}
				
				if(dew.charAt(i) == 0x002D) // if there is a negative sign in the text box, let it go through an accept it
				{
					dewDigits = true;
				}
			} // end of for loop
			
			// if the dew point is all digits (excluding a decimal point or negative sign), then dew point is good
			if(dewDigits == true) {
				goodDew = true;
			}
			else // if there are non-digits in the dew point string
			{
				JOptionPane.showMessageDialog(null, "\n  WARNING: Dew Point field has non-digits in it!  \n  Please enter a valid dew point.  \n");
			}
		} // end of if-else block
		
		// if dew point is valid, then cloud base is valid
		if(goodDew == true)
		{
			goodCloudBase = true;
		}
		
		return goodCloudBase;
		
	} // end of verifyCloudBase method
	
	
	// compute wind chill method
	double computeWindChill(String temp, String wind) {
		
		// convert the temperature and wind speed strings into doubles
		double doubTemp = Double.parseDouble(temp);
		double doubWindSpeed = Double.parseDouble(wind);
		
		// calculate the wind chill using the equation or formula
		double windChill = 35.74 + (0.6215 * doubTemp) - (35.75 * (Math.pow(doubWindSpeed, 0.16))) + (0.4275 * doubTemp * (Math.pow(doubWindSpeed, 0.16)));
		
		return windChill;
		
	} // end of computeWindChill method
	
	
	// compute cloud base method
	double computeCloudBase(String temp, String dew) {
		
		// convert the temperature and dew point strings into doubles
		double doubTemp = Double.parseDouble(temp);
		double doubDew = Double.parseDouble(dew);
			
		// calculate cloud base using the equation or formula
		double cloudBase = ((doubTemp - doubDew) / 4.4) * 1000;
		
		// the cloud base cannot be negative; if it is negative, set the cloud base to 0
		if(cloudBase < 0) {
			cloudBase = 0;
		}
			
		return cloudBase;
			
	} // end of computeCloudBase method
	
	
	// display data in Main GUI method
	void displayDataMainGUI(double windChill, double cloudBase, GridBagConstraints con, File rightImageFile2, File rightImageFile3, File rightImageFile4, JLabel rightImageLabel) {
		
		// convert the resultant wind chill and cloud base values from doubles into formatted strings (to two decimal places)
		String windChillStr = String.format("%.2f", windChill);
		String cloudBaseStr = String.format("%.2f", cloudBase);
		
		// Concatenate the strings with their corresponding units (and format them to two decimal places)
		String wCS = (windChillStr + " dF");
		String cBS = (cloudBaseStr + " feet");
		
		// Assign the text in the wind chill and cloud base labels to be the concatenated strings
		windChillValueLabel.setText(wCS);
		cloudBaseValueLabel.setText(cBS);
		
		// Add the Labels to the Panel
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 8; // Sets the Row Constraint to 8
		con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
		WeatherPanel.add(windChillValueLabel, con); // Adds Wind Chill Value Label to the Panel
		
		con.gridx = 1; // Sets the Column Constraint to 1
		con.gridy = 10; // Sets the Row Constraint to 10
		con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
		con.anchor = GridBagConstraints.CENTER; // Aligns Text to the Center
		WeatherPanel.add(cloudBaseValueLabel, con); // Adds Cloud Base Value Label to the Panel
		
		// Determine what level the cloud is based on its cloud base; then add the corresponding image and cloud type label to the Panel
		if((cloudBase >= 0.00) && (cloudBase <= 7000.00)) // if the cloud base is between 0.00 feet and 7,000.00 feet, then it is considered a "low cloud"
		{
			// set the cloud type label as the text for the low cloud type and add it to the Panel
			cloudTypeValueLabel.setText("Low-Level Cloud (0 ft. to 7,000 ft.)");
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 12; // Sets the Column Constraint to 12
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(cloudTypeValueLabel, con); // Adds the Cloud Type Label to the Panel
			
			// Create a BufferedImage for the "LowClouds.jpg" image file for the right column of the panel, then add it to the panel by replacing the original image
			try {
				con.gridx = 1; // Sets the Column Constraint to 1
				con.gridy = 0; // Sets the Row Constraint to 0
				con.anchor = GridBagConstraints.NORTH; // Aligns Image to the North (Top)
				BufferedImage rightImage4 = ImageIO.read(rightImageFile4); // makes the image file into a useable image
				rightImageLabel.setIcon(new ImageIcon(rightImage4)); // sets the image as the "Icon" for the "rightImageLabel" variable
			}
			catch(Exception e) { // if image is not found/does not exist/can't be read by ImageIO.read
				JOptionPane.showMessageDialog(null, "\nLowClouds.jpg image file not Found!\n"); // dialog box
				System.out.println("\nLowClouds.jpg image file not found!\n"); // system error message
				e.printStackTrace();
			}
		} // end of block for low cloud
		else if((cloudBase > 7000.00) && (cloudBase <= 17000.00)) // if the cloud base is between 7,000.00 feet and 17,000.00 feet, then it is considered a "medium-level cloud" 
		{
			// set the cloud type label as the text for the medium cloud type and add it to the Panel
			cloudTypeValueLabel.setText("Medium-Level Cloud (7,000 ft. to 17,000 ft.)");
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 12; // Sets the Column Constraint to 12
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(cloudTypeValueLabel, con); // Adds the Cloud Type Label to the Panel
						
			// Create a BufferedImage for the "MediumClouds.jpg" image file for the right column of the panel, then add constraints and add it to the panel
			try {
				con.gridx = 1; // Sets the Column Constraint to 1
				con.gridy = 0; // Sets the Row Constraint to 0
				con.anchor = GridBagConstraints.NORTH; // Aligns Image to the North (Top)
				BufferedImage rightImage3 = ImageIO.read(rightImageFile3); // makes the image file into a useable image
				rightImageLabel.setIcon(new ImageIcon(rightImage3)); // sets the image as the "Icon" for the "rightImageLabel" variable
			}
			catch(Exception e) { // if image is not found/does not exist/can't be read by ImageIO.read
				JOptionPane.showMessageDialog(null, "\nMediumClouds.jpg image file not Found!\n"); // dialog box
				System.out.println("\nMediumClouds.jpg image file not found!\n"); // system error message
				e.printStackTrace();
			}
		} // end of block for medium cloud
		else if((cloudBase > 17000.00) && (cloudBase <= 35000.00)) // if the cloud base is between 17,000.00 feet and 35,000.00 feet, then it is considered a "high cloud"
		{
			// set the cloud type label as the text for the high cloud type and add it to the Panel
			cloudTypeValueLabel.setText("High-Level Cloud (17,000 ft. to 35,000 ft.)");
			con.gridx = 1; // Sets the Column Constraint to 1
			con.gridy = 12; // Sets the Column Constraint to 12
			con.insets = new Insets(20, 1, 1, 1); // Top, Left, Bottom, then Right - Padding
			con.anchor = GridBagConstraints.CENTER; // Aligns to the Center
			WeatherPanel.add(cloudTypeValueLabel, con); // Adds the Cloud Type Label to the Panel
									
			// Create a BufferedImage for the "HighClouds.jpg" image file for the right column of the panel, then add constraints and add it to the panel
			try {
				con.gridx = 1; // Sets the Column Constraint to 1
				con.gridy = 0; // Sets the Row Constraint to 0
				con.anchor = GridBagConstraints.NORTH; // Aligns Image to the North (Top)
				BufferedImage rightImage2 = ImageIO.read(rightImageFile2); // makes the image file into a useable image
				rightImageLabel.setIcon(new ImageIcon(rightImage2)); // sets the image as the "Icon" for the "rightImageLabel" variable
			}
			catch(Exception e) { // if image is not found/does not exist/can't be read by ImageIO.read
				JOptionPane.showMessageDialog(null, "\nHighClouds.jpg image file not Found!\n"); // dialog box
				System.out.println("\nHighClouds.jpg image file not found!\n"); // system error message
				e.printStackTrace();
			}
		} // end of block for high cloud
		
		// refresh the panel to update the data and images
		WeatherPanel.revalidate();
		WeatherPanel.repaint();
		
	} // end of displayDataMainGUI method
	
}; // end of WeatherGUI class