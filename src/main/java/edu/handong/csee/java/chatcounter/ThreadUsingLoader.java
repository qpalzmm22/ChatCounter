package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.ArrayList;

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
	
	public ThreadUsingLoader(File file) {
		this.file = file;
		this.dataPool = new ArrayList<NDMdata>();
	}
	
	public ArrayList<NDMdata> getDataPool() {
		return dataPool;
	}
	
}
