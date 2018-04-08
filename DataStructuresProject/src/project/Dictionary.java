package project;

import java.util.HashMap;

public class Dictionary {

	private HashMap<String,String> dict;
	
	public Dictionary() {
		dict = new HashMap<String,String>();
	}

	public void setDeff(String word, String deff) {
		dict.put(word, deff);
	}
	
	public HashMap<String, String> getMap() {
		return dict;
	}
}
