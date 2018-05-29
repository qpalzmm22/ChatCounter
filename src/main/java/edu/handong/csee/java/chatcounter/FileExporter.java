package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.io.*;
/**
 * This class exports a file with fileName.
 * This class gets its file name only with constructor
 * The makeItCSVFile method takes List<NMcount>and makes it and export it to fileName
 * @author qpalz
 *
 */
public class FileExporter {
	
	private String fileName = "TempName";
	
	/**
	 * A constructor that takes input of full-path name of export file name
	 * @param name full-path of the export file name
	 */
	public FileExporter(String name) {
		fileName = name;
	}
	
	
	/**
	 * This method takes List<NMcount> and export its content by csv file
	 * @param ncList List<NMcount> of final result. (AKA complete file to export)
	 */
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
