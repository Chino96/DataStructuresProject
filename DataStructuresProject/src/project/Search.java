package project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Search extends Application{

	public static void main(String[] args) throws Exception {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Button search = new Button("Search");
		TextArea textBox = new TextArea();
		
		
		textBox.setStyle("-fx-font-size: 24");
		textBox.setWrapText(true);

		pane.getChildren().addAll(search, textBox);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
		Dictionary dict = new Dictionary("Dict.txt");
		Tree tree = new Tree();
		dict.build(tree);

		ArrayList<String> arr = new ArrayList<String>();

		System.out.println(dict.getDeff(tree.search("Dog", arr).get(0)));

		
	}

}
