package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.io.*;

public class FileExporter {
	
	String fileName = "TempName";
	
	public FileExporter(String name) {
		fileName = name;
	}
	
	public void makeItCSVFile(List<NMcount> ncList) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8"));
			
			bufferedWriter.write("kakao_id, count\n");
			for(NMcount e:ncList) {
					bufferedWriter.write(e.getName() + ", " + e.getCount() + "\n");
			}
			bufferedWriter.close();
		} catch(FileNotFoundException e) {
			System.out.println("Problem opening the file " + fileName);
		} catch (IOException e) {
			System.out.println("Problem with output to file " + fileName);
		}
	}
}
