package project;

public class Tree {

	public Node root;

	public Tree() {
		root = new Node('0');
	}

	public void add(String s) {
		sudoAdd(root, s, 0);
	}

	public void sudoAdd(Node n, String s, int stringPos) {
		if (stringPos < s.length()) {
			char b = n.letter;
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

}