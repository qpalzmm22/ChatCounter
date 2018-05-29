package edu.handong.csee.java.chatcounter;

/**
 * NMcount stands for Name, Message count
 * This class implements data structure of Name and message count
 * This class implements Comparable<NMcount> to be able to use sort method in Collections class
 * @author qpalz
 *
 */
public class NMcount implements Comparable<NMcount>{
	private String name;
	private int count;
	
	/**
	 * This is the constructor to set name and message count by given parameters
	 * @param name
	 * @param count
	 */
	public NMcount(String name, int count) {
		this.name = name;
		this.count = count;
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
	 * getter for count
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * setter for count
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/** 
	 * this method prints the name and count by csv form
	 */
	public void print() {
		System.out.println(name + ", " + count);
	}

	/**
	 * This method compares other NMcount to be able to sort the NMList
	 * This method is intended to descend
	 */
	public int compareTo(NMcount other) {
		if(this.count < other.getCount())
			return 1;
		else if(this.count > other.getCount())
			return -1;
		return 0;
	}
}
