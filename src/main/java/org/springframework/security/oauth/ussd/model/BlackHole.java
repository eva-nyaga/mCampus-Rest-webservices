package org.springframework.security.oauth.ussd.model;

import java.util.HashMap;
import java.util.Iterator;


/**
 * A wrapper class for showing a message to the 
 * @author mulama
 *
 */
public class BlackHole {
	
	private String message;
	
	public HashMap<Integer, String> BlackHole(String msg){
		
		return naviConverter(msg);
	}
	

	public HashMap<Integer, String> naviConverter(String texts) {

		HashMap<Integer, String> levels = new HashMap<Integer, String>();
		String firstletter = "";
		String letter = "";

		//String texts = "**9111#";
		//String texts = "*9111*1#";
		//String texts = "***************************9111*1*2*3*4*5*6*7*8*9*10#";
		//String texts = "*1*2*3*4*5#";
		//String texts = "1*2*3*4*5#";
		//String texts = "*9111*657567*2*3*4*5#";
		//String texts = "*91 11*1*2  *3*v  jg  hj*5#";
		
		texts  = texts.replaceAll("\\s+","");
		texts  = texts.replaceAll("\\#","");
		texts  = texts.replaceAll("9111","");
		firstletter = texts.substring(0, 1);
		
		if(firstletter.equals("*")){
			while (true) {
				if((!texts.equals("")) && texts.length()!=0){
					letter = texts.substring(0, 1);
					if((letter.equals("*"))){	
						texts = texts.startsWith("*") ? texts.substring(1) : texts;
					}else{
						break;
					}
				}else{
					break;
				}
			 }
		  }
		
		

		String[] textsArrays = texts.split("\\*");
	    Iterator<Integer> keySetIterator = levels.keySet().iterator();
	    int index = 0;
	    while(index < textsArrays.length)
	    {
	        if(levels.get(index) == null){
	        }
		    levels.put(index, textsArrays[index]);
	      index++;
	    }

	    return levels;
	}
	
	
	
	
	
	
	
	
	public HashMap<Integer, String> selectMapConvr(String texts) {

		HashMap<Integer, String> levels = new HashMap<Integer, String>();
		String firstletter = "";
		String letter = "";

		//String texts = ",,9111#";
		//String texts = ",9111,1#";
		//String texts = ",,,,,,,,,,,,,,,,,,,,,,,,,,,9111,1,2,3,4,5,6,7,8,9,10#";
		//String texts = ",1,2,3,4,5#";
		//String texts = "1,2,3,4,5#";
		//String texts = ",9111,657567,2,3,4,5#";
		//String texts = ",91 11,1,2  ,3,v  jg  hj,5#";
		
		texts  = texts.replaceAll("\\s+","");
		texts  = texts.replaceAll("\\#","");
		texts  = texts.replaceAll("9111","");
		firstletter = texts.substring(0, 1);
		
		if(firstletter.equals(",")){
			while (true) {
				if((!texts.equals("")) && texts.length()!=0){
					letter = texts.substring(0, 1);
					if((letter.equals(","))){	
						texts = texts.startsWith(",") ? texts.substring(1) : texts;
					}else{
						break;
					}
				}else{
					break;
				}
			 }
		  }
		
		

		String[] textsArrays = texts.split("\\,");
	    Iterator<Integer> keySetIterator = levels.keySet().iterator();
	    int index = 0;
	    while(index < textsArrays.length)
	    {
	        if(levels.get(index) == null){
	        }
		    levels.put(index, textsArrays[index]);
	      index++;
	    }

	    return levels;
	}


}
