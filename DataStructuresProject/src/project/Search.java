package project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Search extends Application {

	public static void main(String[] args) throws Exception {

		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Dictionary dict = new Dictionary("Dict.txt");
		Tree tree = new Tree();
		ArrayList<String> arr = new ArrayList<String>();

		Pane pane = new Pane();
		pane.setPrefSize(500, 500);
		TextField searchBar = new TextField();
		Button searchButton = new Button("Search");
		ListView<String> words = new ListView<String>();
		Label output = new Label();
		Label title = new Label("Dictionary");

		dict.build(tree);

		title.setLayoutX(125);
		title.setLayoutY(10);
		title.setStyle("-fx-font-size: 48; -fx-font-weight: bold;");
		title.setTextFill(Color.web("#0076a3"));

		words.setOrientation(Orientation.VERTICAL);
		words.setVisible(false);
		words.setStyle("-fx-font-size: 24; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
		words.setLayoutX(50);
		words.setLayoutY(130);
		words.setPrefWidth(350);

		searchBar.setLayoutX(50);
		searchBar.setLayoutY(100);
		searchBar.setPrefSize(350, 10);
		searchBar.setStyle("-fx-font-size: 14; -fx-focus-color: #0076a3;");
		searchBar.setPromptText("Enter a Word");

		searchButton.setLayoutX(searchBar.getLayoutX() + 350);
		searchButton.setLayoutY(99);
		searchButton.setPrefSize(70, 33);

		output.setLayoutX(50);
		output.setLayoutY(150);
		output.setPrefWidth(350);
		output.setStyle("-fx-font-size: 20");
		output.setWrapText(true);

		pane.getChildren().addAll(title, searchButton, searchBar, words, output);

		Scene s = new Scene(pane);
		stage.setScene(s);
		stage.show();

		
		searchBar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				output.setVisible(false);
			}

		});

		searchBar.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				output.setVisible(false);
				if (searchBar.getText().length() > 0) {
					words.setVisible(true);
					words.getItems().clear();
					try {
						words.getItems().addAll(tree.search(searchBar.getText().substring(0, 1).toUpperCase()
								+ searchBar.getText().substring(1).toLowerCase(), arr));
						words.setMaxHeight(300);
					} catch (IndexOutOfBoundsException e) {
						words.setVisible(false);
					}
				} else {
					words.setVisible(false);
				}
				if (searchBar.getText().length() > 0 && key.getCode().equals(KeyCode.ENTER)) {
					if (dict.getDeff(searchBar.getText()) != null) {
						words.setVisible(false);
						String deff = dict.getDeff(searchBar.getText());
						output.setText(searchBar.getText() + " - " + deff);
						output.setVisible(true);
					} else {
						words.setVisible(false);
						try {
							String deff = dict.getDeff(words.getItems().get(0));
							output.setText(words.getItems().get(0) + " - " + deff);
							output.setVisible(true);
							searchBar.setText(words.getItems().get(0));
						} catch (IndexOutOfBoundsException e) {
							words.setVisible(false);
						}
					}
				} else if (searchBar.getText().length() > 0 && key.getCode().equals(KeyCode.DOWN)) {

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							words.getSelectionModel().selectNext();
							words.getFocusModel().focus(0);
							words.requestFocus();
						}
					});
				}

				else if (key.getCode().equals(KeyCode.BACK_SPACE)) {
					output.setVisible(false);
				}
			}

		});

		words.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ENTER)) {
					words.setVisible(false);
					try {
						String deff = dict.getDeff(words.getItems().get(0));
						output.setText(words.getSelectionModel().getSelectedItem() + " - " + deff);
						output.setVisible(true);
						searchBar.setText(words.getSelectionModel().getSelectedItem());
					} catch (IndexOutOfBoundsException e) {
						words.setVisible(false);
					}

				}
				
				else if(key.getCode().equals(KeyCode.UP) && words.getSelectionModel().getSelectedItem().equals(words.getItems().get(0))) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							searchBar.requestFocus();
						}
					});
				}

			}

		});

		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (dict.getDeff(searchBar.getText()) != null) {
					words.setVisible(false);
					String deff = dict.getDeff(searchBar.getText());
					output.setText(searchBar.getText() + " - " + deff);
					output.setVisible(true);
				} else {
					words.setVisible(false);
					try {
						String deff = dict.getDeff(words.getItems().get(0));
						output.setText(words.getItems().get(0) + " - " + deff);
						output.setVisible(true);
						searchBar.setText(words.getItems().get(0));
					} catch (IndexOutOfBoundsException e) {
						words.setVisible(false);
					}
				}

			}

		});
	}

}
