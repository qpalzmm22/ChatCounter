package edu.handong.csee.java.chatcounter;

public class NMcount implements Comparable<NMcount>{
	private String name;
	private int count;
	
	public NMcount(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void print() {
		System.out.println(name + ", " + count);
	}

	public int compareTo(NMcount other) {
		if(this.count < other.getCount())
			return 1;
		else if(this.count > other.getCount())
			return -1;
		return 0;
	}
}
