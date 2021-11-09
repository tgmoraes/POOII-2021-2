package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage ps) {
		Label lblTexto = new Label("Olá mundo!");
		Label lblNome = new Label("Digite seu Nome: ");
		TextField txtNome = new TextField();
		Button btnOk = new Button("Ok");
		
		btnOk.setOnAction(e -> lblTexto.setText("Olá "+txtNome.getText()+"!"));
		
		HBox agrupaHorizontal = new HBox();
		agrupaHorizontal.getChildren().addAll(lblNome, txtNome, btnOk);
		
		BorderPane layout = new BorderPane();
		layout.setTop(agrupaHorizontal);
		
		layout.setCenter(lblTexto);
		Scene scene = new Scene(layout,400,400);
		ps.setScene(scene);
		ps.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
