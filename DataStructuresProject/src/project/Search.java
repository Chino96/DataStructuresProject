package project;

import java.util.ArrayList;

public class Search{

	public static void main(String[] args) throws Exception {

		Dictionary dict = new Dictionary("Dict.txt");
		Tree tree = new Tree();
		dict.build(tree);

		ArrayList<String> arr = new ArrayList<String>();

		System.out.println(dict.getDeff(tree.search("Dog", arr).get(0)));

	}

}
