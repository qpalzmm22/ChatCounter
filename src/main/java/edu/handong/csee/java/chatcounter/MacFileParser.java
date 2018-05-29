package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class MacFileParser extends FileParser{
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
