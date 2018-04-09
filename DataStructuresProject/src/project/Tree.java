package project;

import java.util.ArrayList;

public class Tree {

	public Node root;
	public ArrayList<String> words;

	public Tree() {
		root = new Node('0');
		words = new ArrayList<String>();
	}

	public void add(String s) {
		sudoAdd(root, s, 0);
	}

	private void sudoAdd(Node n, String s, int stringPos) {
		if (stringPos < s.length()) {
			char c = s.charAt(stringPos);
			int index = n.indexOfChild(c);

			if (index != -1) {
				stringPos++;
				sudoAdd(n.children.get(index), s, stringPos);

			} else if (index == -1) {
				stringPos++;
				n.children.add(new Node(c));
				sudoAdd(n.children.get(n.children.size() - 1), s, stringPos);
			}
		}
	}
	
	public ArrayList<String> search(String s, ArrayList<String> arr) {
		sudoSearch(root, s, 0, arr, "");
		return arr;
	}

	private String sudoSearch(Node n, String s, int stringPos, ArrayList<String> arr, String word) {

		word += n.letter;
				
		if (n.children.size() == 0) {
			arr.add(word.substring(1, word.length()));
			return String.valueOf(n.letter);
		}
		
		if (stringPos < s.length()) {
			char c = s.charAt(stringPos);
			int index = n.indexOfChild(c);
			
			if (index != -1) {
				stringPos++;
				sudoSearch(n.children.get(index), s, stringPos, arr, word);
			}
		}
		else {
			for(int i = 0; i < n.children.size(); i++) {
				sudoSearch(n.children.get(i), s, stringPos, arr, word);
			}
		}

		return null;
	}

}
