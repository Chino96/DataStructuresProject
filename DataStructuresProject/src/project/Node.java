package project;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {

	public char letter;
	public Node parent;
	public ArrayList<Node> children;
	public HashMap map;

	public Node(char letter) {
		this.letter = letter;
		parent = null;
		children = new ArrayList<Node>();

	}

	public void setChild(char c) {
		Node child = new Node(c);
		child.parent = this;
		this.children.add(child);
	}

	public boolean hasValue(char c) {
		if (this.letter == c) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Node n) {
		if (this.letter == n.letter)
			return true;

		return false;
	}

	public int indexOfChild(char c) {
		int pos = 0;

		for (int i = 0; i < this.children.size(); i++) {
			char b = this.children.get(i).letter;
			if (b == c) {
				break;
			} else {
				pos++;
			}
		}
		if (pos == this.children.size()) {
			pos = -1;
		}
		return pos;
	}

}
