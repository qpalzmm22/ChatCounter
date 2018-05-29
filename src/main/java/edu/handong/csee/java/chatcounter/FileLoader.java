package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.io.*;

 /**
  * This class load .csv and .txt files to parse each by according parser 
  * This class gets its input directory by constructor
  * @author qpalzmm22
  *
  */
public class FileLoader {
	private File directory = null;
	private HashMap<String, ArrayList<NDMdata>> messages = new HashMap<String, ArrayList<NDMdata>>();
	
	/**
	 * This class gets its input directory by constructor
	 * @param path
	 */
	public FileLoader(String path) {
		directory = new File(path);
	}

	/**
	 * This method is the getter of messages.
	 * @return
	 */
	public HashMap<String, ArrayList<NDMdata>> getMessages() {
		return messages;
	}
	
	/**
	 * This method loads all the csv in the given directory and parse it using csvParser
	 */
	
	public void loadMacFiles() {
		MacFileParser parser = new MacFileParser();
		
		for(File file: directory.listFiles()) {
			if(file.getName().contains(".csv")) {
				System.out.println("Reading csv files," + file.getName()+ "...");
				messages = parser.csvParser(messages, file);
			}
		} 
	}
	
	/**
	 * This method loads all the txt files in the given directory and parse it using lineParser
	 */
	public void loadWindowsFiles() {
		WindowsFileParser parser = new WindowsFileParser();
		
		for(File file: directory.listFiles()) {
			if(file.getName().contains(".txt")) {
				System.out.println("Reading txt files," + file.getName()+ "...");
				messages = parser.lineParser(messages, file);
			}
		}
	}

}
