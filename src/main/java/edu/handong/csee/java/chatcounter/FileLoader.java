package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

 /**
  * This class gets path as a parameter and provides tools to sort them by mac file 
  * and windows file. 
  * @author qpalzmm22
  *
  */
public class FileLoader {
	private File directory = null;
	private HashMap<String, ArrayList<NDMdata>> messages = new HashMap<String, ArrayList<NDMdata>>();
	
	
	public FileLoader(String path) {
		directory = new File(path);
	}

	public HashMap<String, ArrayList<NDMdata>> getMessages() {
		return messages;
	}
	
	public void loadMacFiles() {
		MacFileParser parser = new MacFileParser();
		
		for(File file: directory.listFiles()) {
			if(file.getName().contains(".csv")) {
				System.out.println("Reading csv files," + file.getName()+ "...");
				messages = parser.csvParser(messages, file);
			}
		} 
	}
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
