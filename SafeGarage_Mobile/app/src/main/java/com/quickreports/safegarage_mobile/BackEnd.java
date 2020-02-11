package com.quickreports.safegarage_mobile;


/* Currently a skeleton of the methods that need added across all fragments, all will be worked on as the corrisponding
   firmware code has been created. Class should be able to be called from all fragments. -- Nick */
   
public class BackEnd {
	// Pairs the server/SafeGarage device to the Mobile app.
	public void pair()
	{}
	
	// Initializes all variables and states
	public void initialize(){}
	
	/*
	Receive a message from the server and parse temperature and door status to be displayed on the screen.
	Uses the Rest API’s Retrofit API to convert Rest messages into a set()/get() class structure instead of Json.
	*/
	public void messageReceived(){}
	
	// Gets the temperature inside the garage.
	public void getTemperature(){}
	
	/* Toggles the door to either open or close based on the remote, will return a boolean and report whether or not the 
	door was successfully closed when attempted. */
	public void toggleDoor(){}
	
	/*	Alerts the user by displaying the type of alarm triggered and the best practices to safely handle such an alarm. Uses 
	Firebase API to send extra notifications to the User’s phone.*/
	public void alarm(){}
	
	// Sets the automatic closing time of the garage door based on the time that the user picks within the app.
	public void setClosingTime(String closingTime){}
}