package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.dao.ContatoDAO;
import application.dao.GrupoDAO;
import application.models.Contato;
import application.models.Grupo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CadastroGrupoController implements Initializable{

    @FXML private BorderPane rootPane;
    @FXML private TextField txtNome;
    private boolean isEditar;
	
    @FXML
    void handleSalvar() {
    	Grupo grupo;
    	if(this.isEditar) {
    		grupo = (Grupo)rootPane.getUserData();
    		grupo.setNome(txtNome.getText());
    	}
    	else grupo = new Grupo(txtNome.getText());
    	var gdao = new GrupoDAO();
    	if(this.isEditar) gdao.update(grupo);
    	else gdao.insert(grupo);
    	
    	handleVoltar();
    }

    @FXML
    void handleVoltar() {
    	
    	Main.loadScene("TelaListaGrupo");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Platform.runLater(()->verificaTelaEdicao());
	}
	
	private void verificaTelaEdicao() {
		var grupo= (Grupo)rootPane.getUserData();
		if(grupo==null) this.isEditar=false;
		else {
			this.isEditar=true;
			txtNome.setText(grupo.getNome());
		}
	}

}
