package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		loadScene("TelaInicial");
		primaryStage.show();

	}

	public static void loadScene(String view, Object dados) {
		view = "/views/" + view + ".fxml";
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(Main.class.getResource(view));
			Scene scene = new Scene(root);
			if(dados!=null) root.setUserData(dados);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void loadScene(String view) {
		loadScene(view, null);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
