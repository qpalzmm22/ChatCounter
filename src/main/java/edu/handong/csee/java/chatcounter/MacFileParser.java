package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
/**
 * This class uses commons-csv open source version 1.4
 * This class parses csv file by comma and save the NDMdata of it into hashMap, messages.
 * This class extends FileParser to use addUnique method 
 * @author qpalz
 *
 */
public class MacFileParser extends FileParser{
	/**
	 * This method parses name, time(hour and minutes) and messages and saves it as a NDMdata
	 * @param messages the collection of unique NDMdata.
	 * @param file The file to be read and parsed
	 * @return the new collection of NDMdata after parsing and adding unique messages to the collection
	 */
	public ArrayList<NDMdata>csvParser(ArrayList<NDMdata> messages, File file) {
		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record : records) {
				String time = record.get(0).substring(11,16);
				String user = record.get(1);
				String messageString = record.get(2);
				
				 //messages = addUnique(messages, new NDMdata(user, time, messageString));
				 NDMdata data = new NDMdata(user, time, messageString);
				 messages.add(data);
			}
		}
		catch(Exception e) {
		}
		
		return messages;
	}
}
