// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherDataOutputWindow.java
// This class creates and holds the functionality of the separate data output window for the program.

package weatherPackage;

// imports
import javax.swing.filechooser.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.io.*;

public class WeatherDataOutputWindow extends JFrame {
	
	private static final long serialVersionUID = 1;
	
	// Create the Window's GUI Frame
	JFrame ddWin = new JFrame("Weather Data Output Window");
	// Create the Window's GUI Panel
	JPanel ddPanel = new JPanel();
	
	// Create the Data Output Text Area
	JTextArea dataOutputTextArea = new JTextArea(40, 95); // 40 rows, 95 columns
	
	// Create A Vertical Scroll Bar for the Side of the dataOutputTextArea
	JScrollPane dataWindowScroll = new JScrollPane(dataOutputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	// String that Holds the Data Output (Used in the updateData method)
	String dataOutputString = ("");
			 
	// Constructor with Configurations
	public WeatherDataOutputWindow() {
		// Set the Size and Background Color for the Window and Panel
		ddWin.setSize(733, 308);
		ddPanel.setSize(733, 308);
		ddPanel.setBackground(Color.white);
		 
		// Create the headers' text areas and the headers themselves
		JTextArea titleHeaderTextArea = new JTextArea(1, 18); // 1 row, 18 columns
		JTextArea labelsHeaderTextArea = new JTextArea(0, 95); // 0 rows after the header text, 95 columns
		String titleHeader = ("\n         Weather Ouput Data\n");
		String labelsHeader = ("     Temperature     Wind Speed     Dew Point     Wind Chill     Cloud Base     Cloud Type" + "\n-----------------------------------------------------------------------------------------------");
		
		// Set the Font and Text for the Title Header Text Area, and Then Add the Title Header Text Area to the Panel
		titleHeaderTextArea.setFont(new Font("Source Code Pro", Font.BOLD, 20));
		titleHeaderTextArea.setText(titleHeader);
		titleHeaderTextArea.setEditable(false); // Makes It So The Text In The Header Can't Be Edited
		ddPanel.add(titleHeaderTextArea, BorderLayout.CENTER);
		
		// Set the Font and Text for the Labels Header Text Area, and Then Add the Labels Header Text Area to the Panel
		labelsHeaderTextArea.setFont(new Font("Consolas", Font.BOLD, 12));
		labelsHeaderTextArea.setText(labelsHeader);
		labelsHeaderTextArea.setEditable(false); // Makes It So The Text In The Header Can't Be Edited
		ddPanel.add(labelsHeaderTextArea, BorderLayout.CENTER);
		
		// Set the Preferred Size for the Scroll Bar and the Font for the Data Output Text Area, and Add the Scroll Bar To the Panel (The Scoll Bar contains the Text Area Where the Columned Data Will Go)
		dataOutputTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
		dataOutputTextArea.setEditable(false); // Makes It So The Data Output Can't Be Edited
		dataWindowScroll.setPreferredSize(new Dimension(664, 110)); // Sets the Size of the Scroll Bar/dataOutputTextArea
		dataWindowScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Sets the Border of the Scroll Bar
		ddPanel.add(dataWindowScroll, BorderLayout.CENTER); // Add the Scroll Bar to the Side of the dataOutputTextArea
		
		// Set the Font for the Panel
		ddPanel.setFont(new Font("Consolas", Font.PLAIN, 12));
		
		// Add a Menu Bar to the Window for File Saving Functionality
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveAsItem = new JMenuItem("Save As");
		fileMenu.add(saveAsItem); // Adds the Item to the Menu
		menuBar.add(fileMenu); // Adds the Dropdown Menu to the Menu Bar of the Window
		
		// the following is a class that uses action listeners to detect if the user selected the "save as" item in the menu bar and conduct the "save as" function
		class MenuItemListener implements ActionListener {
			// method to capture action/event
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\n'Save As' Was Selected.");
				performSaveAs(labelsHeader); // calls the method that performs the 'Save As' Functionality
			} // end of method
			
		}; // end of MenuItemListener class
		
		// Adds the above Action Listener to the 'Save As' Menu Item
		saveAsItem.addActionListener(new MenuItemListener());
		
		// Adds the Menu Bar to the Window
		ddWin.setJMenuBar(menuBar);
		
		ddWin.add(ddPanel); // Adds the Panel to the Frame
		ddWin.setVisible(true); // Makes the Window visible
		ddWin.setResizable(false); // Makes User Unable to Resize the Window
		ddWin.setLocation(200, 200); // Makes Initial Window Location Slightly Offset from the Main GUI's Location
		// Presumes that the user clicked on the "X" in the right corner of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the window is closed, the default option is to stop/terminate the whole program
		 
	} // end of constructor
	
	
	// update data method
	public void updateData(String temp, String wind, String dew, double windChill, double cloudBase) {
		// Declare a StringBuilder to Make Concadenation of the Data Output Easier
		StringBuilder dataString = new StringBuilder();
		String item = ("");
		
		// convert the temperature, wind speed, and dew point strings into doubles
		double doubTemp = Double.parseDouble(temp);
		double doubWindSpeed = Double.parseDouble(wind);
		double doubDew = Double.parseDouble(dew);
		
		DecimalFormat df = new DecimalFormat("#,##0.00"); // Sets the Formatting for the Data
		
		// for loop that checks the data and sees what piece it is (ex. temp or windChill) to add the appropiate units
		for(int count = 1; count < 6; count++)
		{
			// these if statements actually check what the data is and adds its units
			if(count == 1) // this is the temp
			{
				item = df.format(doubTemp);
				item = item + " dF  ";
				item = formatItem(item, count); // this method formats the data by adding spaces before placing it on the dataOutputTextArea
				dataString.append(item);
			}
			
			if(count == 2) // this is the wind (speed)
			{
				item = df.format(doubWindSpeed);
				item = item + " MPH ";
				item = formatItem(item, count);
				dataString.append(item);
			}
			
			if(count == 3) // this is the dew (point)
			{
				item = df.format(doubDew);
				item = item + " dF";
				item = formatItem(item, count);
				dataString.append(item);
			}
			
			if(count == 4) // this is the windChill
			{
				item = df.format(windChill);
				if(item.charAt(0) == '-') // if the windChill is negative
				{
					item = item + " dF ";
				}
				else // otherwise, if the windChill is not negative
				{
					item = " " + item + " dF ";
				}
				
				item = formatItem(item, count);
				dataString.append(item);
			}
			
			if(count == 5) // this is the cloudBase
			{
				item = df.format(cloudBase);
				item = item + " ft";
				item = formatItem(item, count);
				dataString.append(item);
			}
			
		} // end of the for loop
		
		// Determines what level the cloud is based on its cloud base; then adds the corresponding cloud type to the dataString
		if((cloudBase >= 0.00) && (cloudBase <= 7000.00)) // if the cloud base is between 0.00 feet and 7,000.00 feet, then it is considered a "low cloud"
		{
			int count = 6; // sets the count for the formatItem method
			item = (""); // Reset the item string
			
			// set the item string text for a low cloud, format it, and append it to the dataString
			item = item + "Low    ";
			item = formatItem(item, count);
			dataString.append(item);
		} // end of block for low cloud
		else if((cloudBase > 7000.00) && (cloudBase <= 17000.00)) // if the cloud base is between 7,000.00 feet and 17,000.00 feet, then it is considered a "medium cloud" 
		{
			int count = 7; // sets the count for the formatItem method
			item = (""); // Reset the item string
			
			// set the item string text for a medium cloud, format it, and append it to the dataString
			item = item + "Medium  ";
			item = formatItem(item, count);
			dataString.append(item);
		} // end of block for medium cloud
		else if((cloudBase > 17000.00) && (cloudBase <= 35000.00)) // if the cloud base is between 17,000.00 feet and 35,000.00 feet, then it is considered a "high cloud"
		{
			int count = 8; // sets the count for the formatItem method
			item = (""); // Reset the item string
			
			// set the item string text for a high cloud, format it, and append it to the dataString
			item = item + "High   ";
			item = formatItem(item, count);
			dataString.append(item);
		} // end of block for high cloud
		
		dataString.append("\n"); // Add a Line Feed
		
		// Create or Append the Empty String to Create One Long Line of Data
		dataOutputString = dataOutputString + dataString.toString();
		
		// Add the Long Line of Data to the Data Output Text Area
		dataOutputTextArea.setText(dataOutputString);
		
	} // end of updateData method
	
	
	// format item method
	public String formatItem(String item, int count)
	{
		int stringLength = item.length(); // gets the length of the String item
		int spaces = 0;
		
		// Each column width is a different length (as in it is not uniform), so these if statements will place the appropiate amount of spaces within the string for the unique width of each column
		if(count == 1) // this is the temp, whose column width is 21, but it is 16 if you go to the end of the header text
		{
			spaces = 16 - stringLength;
		}
		else if(count == 2) // this is the wind (speed), whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		else if(count == 3) // this is the dew (point), whose column width is 19, but it is 14 if you go to the end of the header text
		{
			spaces = 14 - stringLength;
		}
		else if(count == 4) // this is the windChill, whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		else if(count == 5) // this is the cloudBase, whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		else if(count == 6) // this is the Low Cloud Type, whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		else if(count == 7) // this is the Medium Cloud Type, whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		else if(count == 8) // this is the High Cloud Type, whose column width is 20, but it is 15 if you go to the end of the header text
		{
			spaces = 15 - stringLength;
		}
		
		String spacing = (""); // String variable for the following for loop
		
		// This for loop incorporates the spaces into the string
		for(int i = 0; i < spaces; i++)
		{
			spacing = spacing + " ";
		}
		
		// append the amount of spaces to the string
		spacing = spacing + item;
		item = spacing;
		
		return item;
	} // end of formatItem method
	
	
	// dispose of window method
	public void disposeOfWindow() {
		ddWin.dispose();
	} // end of disposeOfWindow method
	
	
	// file saving method
	public void performSaveAs(String labelsHeader) {
		// Declare a JFileChooser for the user to choose a text file to save the output window's contents to
		File workingDir = new File(System.getProperty("user.dir"));
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a Text File (.txt) to Save To");
		fileChooser.setCurrentDirectory(workingDir);
		
		// declare a filter for a specific file extension: .txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT");
		fileChooser.setFileFilter(filter);
		
		int userSelection = fileChooser.showSaveDialog(null); // no parent frame/component
		
		if(userSelection == JFileChooser.APPROVE_OPTION) // if user chooses to save to a file
		{
			// get the filename of the selected file
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save As File: " + fileToSave.getAbsolutePath());
			
			// Now we write the text that is in the JTextArea to the File
			try // try to see if file exists; if not, exception is thrown and handled
			{
					FileWriter fileWrite = new FileWriter(fileToSave);
					BufferedWriter buffWrite = new BufferedWriter(fileWrite);
					PrintWriter printWrite = new PrintWriter(buffWrite);
					printWrite.println(labelsHeader + "\n" + dataOutputString);
					
					// close the PrintWriter
					printWrite.close();
			}
			catch(IOException e) { // if FileWriter throws an exception, as in the file does not exist but cannot be created or the file cannot be opened
				e.printStackTrace();
				System.out.println("\nThe file you are looking to save to either: does not exist but cannot be created or it cannot be opened.\n");
			} // end of try-catch block
		}
		else // if user does not select a file to save to
		{
			System.out.println("File Saving Canceled: NO FILE SELECTED!");
		}
	} // end of performSaveAs method
	
	
	// open and view saved data method
	public void openViewFileData() {
		// Declare a JFileChooser for the user to choose a text file to open and view the contents of in the output window
		File workingDir = new File(System.getProperty("user.dir"));
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a Text File (.txt) to Open and View");
		fileChooser.setCurrentDirectory(workingDir);
				
		// declare a filter for a specific file extension: .txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT");
		fileChooser.setFileFilter(filter);
		
		int userSelection = fileChooser.showOpenDialog(null); // no parent frame/component
		
		// Now we read the data from the File and write it to the JTextArea
		try { // try to see if file exists; if not, exception is thrown and handled
			if(userSelection == JFileChooser.APPROVE_OPTION) // if user selects a file to open
			{
				// get the filename of the selected file
				File fileToOpen = fileChooser.getSelectedFile();
				System.out.println("\nOpening File: " + fileToOpen.getAbsolutePath());
				
				Scanner in = new Scanner(fileToOpen);
				StringBuilder data = new StringBuilder();
				
				// loop that gets the data from the file
				while(in.hasNext()) // while the end of the file has not be reached
				{
					// temportary variables to get the full header into this method for use to compare to the lines of data in the file
					String header1 = ("     Temperature     Wind Speed     Dew Point     Wind Chill     Cloud Base     Cloud Type");
					String header2 = ("-----------------------------------------------------------------------------------------------");
					
					data.append(in.nextLine()); // get the next line from the file
					String dataString = data.toString(); // convert the read line to a String
					
					if(dataString.equals(header1)) // if the String line that was read is the first part of the header, the labels
					{
						data.delete(0, 90); // deletes or remove the header from the stringBuilder
					}
					else if(dataString.equals(header2)) // if the String line that was read is the second part of the header, the dashed line
					{
						data.delete(0, 105); // deletes or remove the header from the stringBuilder
					}
					else // if the String line that was read is the actual data
					{
						// Append the dataOutputString to Create One Long Line of Data
						dataOutputString = dataOutputString + dataString + "\n";
						
						// Add the Long Line of Data to the Data Output Text Area
						dataOutputTextArea.setText(dataOutputString);
					}
				} // end of while loop
				
				in.close(); // close the Scanner
			}
			else // if user does not select a file to open
			{
				System.out.println("\nFile Opening Canceled: NO FILE SELECTED!");
			} // end of if-else block
		}
		catch(FileNotFoundException e) { // if Scanner throws an exception, as in the file can not be found or opened
			JOptionPane.showMessageDialog(null, "\n  The file you are looking to open cannot be found or opened.  \n");
			System.out.println("\nThe file you are looking to open cannot be found or opened.\n");
			e.printStackTrace();
		} // end of try-catch block
	} // end of openViewFileData method
	
}; // end of WeatherDataOutputWindow class