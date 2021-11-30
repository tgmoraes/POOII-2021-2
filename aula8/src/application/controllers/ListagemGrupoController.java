package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.dao.GrupoDAO;
import application.models.Grupo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemGrupoController implements Initializable {

    @FXML private TableColumn<Grupo, Integer> clmId;
    @FXML private TableColumn<Grupo, String> clmNome;
    @FXML private TableView<Grupo> tabGrupos;

    @FXML
    void handleAdicionaContato(ActionEvent event) {
    	Main.loadScene("TelaFormGrupo");
    }

    @FXML
    void handleEditarContato(ActionEvent event) {
    	var alvo = this.tabGrupos.getSelectionModel().getSelectedItem();
		if (alvo != null) {
			Main.loadScene("TelaFormGrupo", alvo);
		} else {
			var alerta = new Alert(AlertType.INFORMATION);
			alerta.setHeaderText("Grupo não selecionado!");
			alerta.setContentText("Você deve escolher um grupo para poder editar...");
			alerta.setTitle("Atenção");
			alerta.showAndWait();
		}
    }

    @FXML
    void handleExcluiContato(ActionEvent event) {
    	var alvo = this.tabGrupos.getSelectionModel().getSelectedItem();
		if (alvo != null) {
			var alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Exclusão de Grupo!");
			alerta.setContentText(
				"Você tem certeza que deseja excluir o grupo de id "+alvo.getId()+"?");
			alerta.setTitle("Atenção");
			var ret = alerta.showAndWait();
			if (ret.isPresent() && ret.get() == ButtonType.OK) {
				var gdao = new GrupoDAO();
				gdao.delete(alvo.getId());
				Main.loadScene("TelaListaGrupo");
			}
		} else {
			var alerta = new Alert(AlertType.INFORMATION);
			alerta.setHeaderText("Grupo não selecionado!");
			alerta.setContentText("Você deve escolher um grupo para poder excluir...");
			alerta.setTitle("Atenção");
			alerta.showAndWait();
		}
    }

    @FXML
    void handleTelaInicial(ActionEvent event) {
    	Main.loadScene("TelaInicial");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populaTableView();
		
	}

	private void populaTableView() {
		var gdao = new GrupoDAO();
		var grupos = FXCollections.observableArrayList(gdao.list());
		this.tabGrupos.setItems(grupos);
		clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
		clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	}

}

