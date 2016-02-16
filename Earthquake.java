import java.io.*;


public class Earthquake {
	private String magnitude;
	private String location;
	private String date;
	private String time;

	
	// Constructor
	Earthquake(String magnitude, String location, String date, String time){	
		this.magnitude = magnitude;
		this.location = location;
		this.date = date;
		this.time = time;
	}
	
	public String toString(){
		return ("Magnitude: " + magnitude + "\nLocation: " + location + "\nDate: " +date+ "\nTime: " + time);
	}

	/**
	 * @return the magnitude
	 */
	public String getMagnitude() {
		return magnitude;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	
}
