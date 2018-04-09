package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {

	private HashMap<String, String> dict;
	private File file;
	private BufferedReader reader;
	private HashMap<Character, Integer> letters;

	public Dictionary(String fileName) {
		dict = new HashMap<String, String>();
		file = new File("Dict.txt");
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		letters = new HashMap<Character, Integer>();
	}

	public void setDeff(String word, String deff) {
		dict.put(word, deff);
	}
	
	public String getDeff(String word) {
		return this.dict.get(word);
	}

	public HashMap<String, String> getMap() {
		return dict;
	}

	public void build(Tree tree) throws IOException {
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith("-") && (line.charAt(0) - 'A') <= 26) {
				String word = line.substring(0, line.indexOf(" "));
				String deff = line.substring(line.indexOf(") ") + 2);
				this.setDeff(word, deff);
				tree.add(word);

				if (!letters.containsKey(word.charAt(0))) {
					letters.put(word.charAt(0), count);
					count++;
				}

			}
		}
	}
}
