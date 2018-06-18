package edu.handong.csee.java.chatcounter;

import java.util.*;

import com.google.common.io.Files;

import java.io.*;

 /**
  * This class load .csv and .txt files to parse each by according parser 
  * This class gets its input directory by constructor
  * @author qpalzmm22
  *
  */
public class FileLoader {
	private ArrayList<NDMdata> messages = new ArrayList<NDMdata>();

	/**
	 * This method is the getter of messages.
	 * @return
	 */
	public ArrayList<NDMdata> getMessages() {
		return messages;
	}
	
	/**
	 * This method loads a csv file in the given directory and parse it using csvParser
	 */
	
	public ArrayList<NDMdata> loadMacFile(File file) {
		MacFileParser parser = new MacFileParser();
		System.out.println("Reading csv file," + file.getName() + "...");
		messages = parser.csvParser(messages, file);
		System.out.println("Reading csv file" + file.getName() + "finished");
		return messages;
	}
	
	/**
	 * This method loads a text file in the given directory and parse it using lineParser
	 */
	public ArrayList<NDMdata> loadWindowsFile(File file) {
		WindowsFileParser parser = new WindowsFileParser();
		System.out.println("Reading txt files," + file.getName()+ "...");
		messages = parser.lineParser(messages, file);
		System.out.println("Reading csv file" + file.getName() + "finished");
		return messages; 
	}

}
