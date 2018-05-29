package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;

public class FileParser {
	private HashMap<String, ArrayList<NDMdata>> messages = new HashMap<String, ArrayList<NDMdata>>();
	
	protected HashMap<String, ArrayList<NDMdata>> addUnique(HashMap<String, ArrayList<NDMdata>> hashMap, NDMdata data){
		for(NDMdata e: hashMap.get(data.getName())) {
			if(e.equalsTo(data))
				return hashMap;			
		}
		hashMap.get(data.getName()).add(data);
		return hashMap; 
	}

}
