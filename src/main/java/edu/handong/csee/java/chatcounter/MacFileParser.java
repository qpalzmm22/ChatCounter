package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

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
	 * This method must used before WindowsParser for correct behavior
	 * @param messages the collection of unique NDMdata.
	 * @param file The file to be read and parsed
	 * @return the new collection of unique NDMdata after parsing and adding unique messages to the collection
	 */
	public HashMap<String, ArrayList<NDMdata>> csvParser(HashMap<String, ArrayList<NDMdata>> messages, File file) {
		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record : records) {
				String time = record.get(0).substring(11,16);
				String user = record.get(1);
				String messageString = record.get(2);
				
				if(!messages.containsKey(user))
					messages.put(user, new ArrayList<NDMdata>());
				
				 messages = addUnique(messages, new NDMdata(user, time, messageString));
			}
		}
		catch(Exception e) {
		}
		
		return messages;
	}
}
