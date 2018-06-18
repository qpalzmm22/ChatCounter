package edu.handong.csee.java.chatcounter;
/**
 * NDM stands for Name, Date, Message
 * This class implements data structure of storing name, date(hour and minutes), and messages information of a message
 * @author qpalz
 *
 */
public class NDMdata {
	private String name;
	private String date;
	private String message;
	
	/**
	 * This is the constructor to set name, date, message by given parameters
	 * @param name name of the message owner
	 * @param date hour and minute value of the message
	 * @param message message content(for windows, it only holds first one line )
	 */
	public NDMdata(String name, String date, String message) {
		this.name = name;
		this.date = date;
		this.message = message;
	}
		
	/**
	 * getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for date
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * setter for date
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * getter for message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * setter for message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;	
	}
	
	/**
	 * This method checks if two NDMdata is "equal"
	 * the NDMdata is considered equal if
	 * 1. they have same date(hour, minutes)
	 * 2. they have same name(owner of the message)
	 * 3. one message starts with another message(For this, mac should be added to the hashMap first)
	 * @param compare comparing NDMdata
	 * @return true if they are "equal" false if they aren't
	 */
	public boolean equalsTo(NDMdata compare) {
		if((this.date.equals(compare.getDate()) && this.name.equals(compare.getName())) && this.message.startsWith(compare.getMessage()))
			return true;
		return false;
	}
}
