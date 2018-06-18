package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.ArrayList;
/**
 * This class uses Thread to implement concurrency
 * takes a File and parse and save the NDM data in dataPool according to their
 * according file type (txt or csv)
 * @author qpalz
 *
 */
public class ThreadUsingLoader extends Thread{
	private File file;
	private ArrayList<NDMdata> dataPool;
	
	public void run() {
		if(file.getName().endsWith(".csv")) {
			FileLoader loader = new FileLoader();
			dataPool = loader.loadMacFile(file);
		}
		if(file.getName().endsWith(".txt")) {
			FileLoader loader = new FileLoader();
			dataPool = loader.loadWindowsFile(file);
		}
	}
	/**
	 * a constructor to take file 
	 * @param file a file to read
	 */
	public ThreadUsingLoader(File file) {
		this.file = file;
		this.dataPool = new ArrayList<NDMdata>();
	}
	
	/**
	 * simple getter of dataPool
	 * @return
	 */
	public ArrayList<NDMdata> getDataPool() {
		return dataPool;
	}
	
}
