package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.dao.ContatoDAO;
import application.models.Contato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemContatosController implements Initializable {
	private ObservableList<Contato> contatos;
	@FXML
	private TableColumn<Contato, Integer> clmId;
	@FXML
	private TableColumn<Contato, String> clmNome;
	@FXML
	private TableColumn<Contato, String> clmTelefone;
	@FXML
	private TableView<Contato> tabContatos;
	@FXML
	private Button btnAdicionaContato;

	private void carregaTable() {
		ContatoDAO cDao = new ContatoDAO();
		contatos = FXCollections.observableArrayList(cDao.list());
		tabContatos.setItems(this.contatos);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregaTable();
		clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
		clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clmTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}

	@FXML
	void handleAdicionaContato() {
		Main.loadScene("TelaForm");
	}

	@FXML
	void handleExcluiContato() {
		Contato alvo = this.tabContatos.getSelectionModel().getSelectedItem();
		if (alvo != null) {
			var alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Exclusão de Contato!");
			alerta.setContentText(
				"Você tem certeza que deseja excluir o contato de id "+alvo.getId()+"?");
			alerta.setTitle("Atenção");
			var ret = alerta.showAndWait();
			if (ret.isPresent() && ret.get() == ButtonType.OK) {
				ContatoDAO cDao = new ContatoDAO();
				cDao.delete(alvo.getId());
				Main.loadScene("TelaInicial");
			}
		} else {
			var alerta = new Alert(AlertType.INFORMATION);
			alerta.setHeaderText("Contato não selecionado!");
			alerta.setContentText("Você deve escolher um contato para poder excluir...");
			alerta.setTitle("Atenção");
			alerta.showAndWait();
		}
	}

	@FXML
	void handleEditarContato() {
		Contato alvo = this.tabContatos.getSelectionModel().getSelectedItem();
		if (alvo != null) {

			// acao editar
			Main.loadScene("TelaForm", alvo);
		} else {
			var alerta = new Alert(AlertType.INFORMATION);
			alerta.setHeaderText("Contato não selecionado!");
			alerta.setContentText("Você deve escolher um contato para poder editar...");
			alerta.setTitle("Atenção");
			alerta.showAndWait();
		}
	}
}
