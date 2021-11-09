module aula7 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics;
	opens application.controllers to javafx.fxml;
	opens application.models to javafx.base;
}
