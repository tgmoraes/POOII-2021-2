package application.controllers;

import application.Main;
import javafx.fxml.FXML;

public class InicialController {

    @FXML
    void handleManterContatos() {
    	Main.loadScene("TelaListaContato");
    }

    @FXML
    void handleManterGrupos() {
    	Main.loadScene("TelaListaGrupo");
    }

}
