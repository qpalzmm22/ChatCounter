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
	 * The NDMdata of Mac files should be read first in order to avoid bugs(IMPORTANT) 
	 * 
	 * @param hashMap The collection of NDM data hashed by the name of the user
	 * @param data the NDMdata to be tested 
	 * @return if the data is duplicated the original hashMap is returned, if not newly added one is returned.
	 */
	protected HashMap<String, ArrayList<NDMdata>> addUnique(HashMap<String, ArrayList<NDMdata>> hashMap, NDMdata data){
		for(NDMdata e: hashMap.get(data.getName())) {
			if(e.equalsTo(data))
				return hashMap;			
		}
		hashMap.get(data.getName()).add(data);
		return hashMap; 
	}

}
