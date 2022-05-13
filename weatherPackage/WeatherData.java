// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherData.java
// This class serves as the main operator for the flow of control of the program.

package weatherPackage;

// imports
import javax.swing.*;
import javax.swing.JOptionPane;

public class WeatherData extends JFrame {

	private static final long serialVersionUID = 1;
	
	public static void main(String[] args) {
		// When the program launches, a dialog box appears asking
		// the user if they are looking to create an account, login,
		// or cancel the program.
		Object[] options = {"Cancel", "Create Account", "Login"}; // choices for the dialog box
		int selection = JOptionPane.showOptionDialog(null, // no parent window, so it is null
				"\n\n\n\tWeather Data Analysis Program   \t\n\n\n\n", // text inside the dialog box
				"Weather Data Analysis Program", // text on the border of the dialog box
				JOptionPane.YES_NO_CANCEL_OPTION, // option type of dialog box
				JOptionPane.QUESTION_MESSAGE, // message type
				new ImageIcon("WeatherIcon.png"), // image icon 
				options, options[2]); // the options, and initial value (buttons)
		
		// user selects one of three options in the dialog box; flow of control
		if(selection == 1) // if user selects "Create Account", then create its window
		{
			System.out.println("Creating Account");
			new WeatherCreateAccount();
		}
		else if(selection == 2) // if user selects "Login", then create its window
		{
			System.out.println("Login");
			new WeatherLogin();
		}
		else // if user selects "Cancel", then close out the program
		{
			System.out.println("Cancel");
		}
	} // end of main

}; // end of class