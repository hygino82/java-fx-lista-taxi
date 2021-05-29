package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.Trajeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewController implements Initializable {

	private String[] trecho = { "ASFALTO", "CHÃO" };
	private Boolean asfalto;
	private Double valorTotal;

	private List<Trajeto> listaTrajeto = new ArrayList<>();

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btListar;

	@FXML
	private TextField txtInicio;

	@FXML
	private TextField txtFim;

	@FXML
	private ChoiceBox<String> cbEstrada;

	@FXML
	private TextArea txtListagem;

	@FXML
	public void btAdicionarAction() {
		Double inicio, fim;
		inicio = Double.parseDouble(txtInicio.getText());
		fim = Double.parseDouble(txtFim.getText());
		Trajeto obj = new Trajeto(inicio, fim, asfalto);
		// System.out.println(obj);
		listaTrajeto.add(obj);
	}

	@FXML
	public void btListarAction() {
		valorTotal = 0.0;
		for (Trajeto obj : listaTrajeto) {
			valorTotal += obj.valorTrecho();
			 txtListagem.appendText(obj.toString());
			 txtListagem.appendText("\n----------------------");
		}
		txtListagem.appendText("Valor total: R$"+String.format("%.2f", valorTotal));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbEstrada.getItems().addAll(trecho);
		cbEstrada.setOnAction(this::getEstrada);
	}

	public void getEstrada(ActionEvent event) {

		String estrada = cbEstrada.getValue();
		if (estrada == "ASFALTO") {
			asfalto = true;
		} else {
			asfalto = false;
		}
	}

}
