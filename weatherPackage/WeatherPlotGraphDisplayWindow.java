// Gregory Zacharko
// CSE 223: Spring 2021 Semester - Professor Simber
// WeatherPlotGraphDisplayWindow.java
// This class creates and holds the functionality of the plot graph display window for the program.

package weatherPackage;

// imports
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class WeatherPlotGraphDisplayWindow extends JFrame {
	
	private static final long serialVersionUID = 1;
	
	// Create the Plot Window's GUI Frame
	JFrame plotGraphWin = new JFrame("Weather Data Scatter Plot Graph Window");
	
	// Create an ArrayList to contain the data points
	ArrayList<Integer> dataList = new ArrayList<Integer>();
	
	// Constructor with Configurations
	public WeatherPlotGraphDisplayWindow() {
		// Set the Size and Background Color for the Window
		plotGraphWin.setSize(710, 500); // Width, Height
		plotGraphWin.setBackground(Color.WHITE);
		
		// Create New JComponent
		JComponent comp = new JComponent() {
			
			private static final long serialVersionUID = 1;
			
			// method that paints the graphics for the component
			public void paintComponent(Graphics graphic) {
				super.paintComponent(graphic);
				
				// create and set the fonts for the title and the text of the graph
				Font titleFont = new Font("Source Code Pro", Font.BOLD, 20);
				Font textFont = new Font("Consolas", Font.BOLD, 12);
				graphic.setFont(titleFont);
				graphic.setColor(Color.BLACK);
				graphic.drawString("Temperature and Wind Chill Scatter Plot Graph", 137, 33); // draw the title
				graphic.setFont(textFont);
				
				// this is the y-axis of the graph
				graphic.drawLine(50, 450, 50, 50);
				graphic.drawLine(51, 450, 51, 50);
				
				// labels for the y-axis
				graphic.drawString("-30", 20, 450);
				graphic.drawString("-20", 20, 405);
				graphic.drawString("-10", 20, 355);
				graphic.drawString("0", 33, 305);
				graphic.drawString("10", 27, 255);
				graphic.drawString("20", 27, 205);
				graphic.drawString("30", 27, 155);
				graphic.drawString("40", 27, 105);
				graphic.drawString("50", 27, 55);
				
				// this is the x-axis of the graph
				graphic.drawLine(50, 300, 680, 300);
				graphic.drawLine(50, 301, 680, 301);
				
				// set the color for the horizontal major axis lines (the lines indictating each increment of 10)
				graphic.setColor(Color.DARK_GRAY);
				
				// Horizontal Major Axis Lines
				graphic.drawLine(50, 450, 680, 450);
				graphic.drawLine(50, 400, 680, 400);
				graphic.drawLine(50, 350, 680, 350);
				graphic.drawLine(50, 300, 680, 300);
				graphic.drawLine(50, 250, 680, 250);
				graphic.drawLine(50, 200, 680, 200);
				graphic.drawLine(50, 150, 680, 150);
				graphic.drawLine(50, 100, 680, 100);
				graphic.drawLine(50, 50, 680, 50);
				
				// set the color for the horizontal minor axis lines (the lines indictating the increments of 5 inbetween each major axis line)
				graphic.setColor(Color.LIGHT_GRAY);
				
				// Horizontal Minor Axis Lines
				graphic.drawLine(50, 425, 680, 425);
				graphic.drawLine(50, 375, 680, 375);
				graphic.drawLine(50, 325, 680, 325);
				graphic.drawLine(50, 275, 680, 275);
				graphic.drawLine(50, 225, 680, 225);
				graphic.drawLine(50, 175, 680, 175);
				graphic.drawLine(50, 125, 680, 125);
				graphic.drawLine(50, 75, 680, 75);
				
				// x coordinate starting point
				int xCoord = 65;
				
				// for loop that actually plots the points
				for(int i = 0; i < dataList.size(); i++)
				{
					int temp = dataList.get(i); // gets the first item
					int tempHeight = temp * 5;
					graphic.setColor(Color.RED); // sets the color for these temperature points to be red
					graphic.drawString(temp + " dF", xCoord, 300 - tempHeight - 5);
					graphic.fillOval(xCoord, 300 - tempHeight, 5, 5);
					
					int windChill = dataList.get(i + 1); // gets the next item
					int windChillHeight = windChill * 5;
					graphic.setColor(Color.BLUE); // sets the color for these wind chill points to be blue
					graphic.drawString(windChill + " dF", xCoord, 300 - windChillHeight - 5);
					graphic.fillOval(xCoord, 300 - windChillHeight, 5, 5);
					i = i + 1;
					xCoord = xCoord + 45;
				} // end of for loop
			} // end of paintComponent block
		}; // end of JComponent block
		
		plotGraphWin.add(comp); // Add JComponent to the Frame
		plotGraphWin.setVisible(true); // Makes the Window visible
		plotGraphWin.setResizable(false); // Makes User Unable to Resize the Window
		plotGraphWin.setLocation(300, 325); // Makes Initial Window Location Slightly Offset from the Main GUI's and Output Window's Location
		// Presumes that the user clicked on the "X" in the right corner of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the window is closed, the default option is to stop/terminate the whole program
		
	} // end of constructor
	
	
	// update plot graph method
	public void updateGraph(String temp, double windChill) {
		// convert the temperature string into an double
		double doubleTemp = Double.parseDouble(temp);
		
		// add the temperature and wind chill to the ArrayList as integers
		dataList.add((int)doubleTemp);
		dataList.add((int)windChill);
		
		// call the repaint method
		plotGraphWin.repaint();
	} // end of updateGraph method
	
	
	// dispose of window method
	public void disposeOfWindow() {
		plotGraphWin.dispose();
	} // end of disposeOfWindow method

}; // end of WeatherPlotGraphDisplayWindow class