package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Search {

	public static void main(String[] args) throws Exception {

		Dictionary dict = new Dictionary();
		Tree tree = new Tree();

		File file = new File("Dict.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));

		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();

		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith("-") && (line.charAt(0) - 'A') <= 26) {
				String word = line.substring(0, line.indexOf(" "));
				String deff = line.substring(line.indexOf(") ") + 2);
				dict.setDeff(word, deff);
				tree.add(word);

				if (!letters.containsKey(word.charAt(0))) {
					letters.put(word.charAt(0), count);
					word.startsWith("i");
					System.out.println(word + " " + count);
				}
				count++;

			}
		}

		System.out.println(tree.root.children.get(0).children.size());

	}

}
