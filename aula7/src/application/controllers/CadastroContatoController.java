package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.dao.ContatoDAO;
import application.models.Contato;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CadastroContatoController implements Initializable{
	
	@FXML private BorderPane rootPane;
	@FXML private TextField txtNome;
	@FXML private TextField txtEmail;
	@FXML private TextField txtTelefone;
	@FXML private DatePicker dtNascimento;
	
	private boolean isEditar;
	
    @FXML
    void handleSalvar() {
    	Contato c;
    	if(this.isEditar) c = (Contato)rootPane.getUserData();
    	else c = new Contato();
    	c.setNome(txtNome.getText());
    	c.setEmail(txtEmail.getText());
    	c.setTelefone(txtTelefone.getText());
    	c.setDataNascimento(dtNascimento.getValue());
    	
    	ContatoDAO cDao = new ContatoDAO();
    	if(this.isEditar) cDao.update(c);
    	else cDao.insert(c);
    	
    	handleVoltar();
    }

    @FXML
    void handleVoltar() {
    	
    	Main.loadScene("TelaInicial");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(()->verificaTelaEdicao());
	}
	private void verificaTelaEdicao() {
		var contato= (Contato)rootPane.getUserData();
		if(contato==null) this.isEditar=false;
		else {
			this.isEditar=true;
			txtNome.setText(contato.getNome());
			txtEmail.setText(contato.getEmail());
			txtTelefone.setText(contato.getTelefone());
			dtNascimento.setValue(contato.getDataNascimento());
		}
	}

}
