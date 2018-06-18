package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class is a superclass of WindowsFileParser and MacFileParser
 * This class has addUnique, which added new data in hashMap if the hashMap doesn't contain the data
 * @author qpalz
 *
 */
public class FileParser {
	
	/**
	 * This method was implemented to add ONLY non-existing NDMdata in the hashMap to avoid duplication.
	 * 2018-6-6(Isaac) : implemented so that the order doenst matter now(MAC OR Windows can come first)
	 * 
	 * @param hashMap The collection of NDM data hashed by the name of the user
	 * @param data the NDMdata to be tested 
	 * @return if the data is duplicated the original hashMap is returned, if not newly added one is returned.
	 */
	public HashMap<String, ArrayList<NDMdata>> addUnique(HashMap<String, ArrayList<NDMdata>> hashMap, NDMdata data){
		
		String name = data.getName();
		if(!hashMap.containsKey(name))
			hashMap.put(name, new ArrayList<NDMdata>());
		
		for(NDMdata e: hashMap.get(data.getName())) {
			NDMdata longerNDM = e;
			NDMdata shorterNDM = data;
			
			if(e.getMessage().length() < data.getMessage().length()) {
				longerNDM = data;
				shorterNDM = e;
			}
				
			if(longerNDM.equalsTo(shorterNDM))
				return hashMap;			
		}
		hashMap.get(data.getName()).add(data);
		return hashMap; 
	}
}
