package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Corrida;

public class ViewController implements Initializable {
   // private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private List<Corrida> minhaLista = new ArrayList<>();

    @FXML
    private Button btLimpa;

    @FXML
    private Button btAdiciona;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtValor;

    @FXML
    private DatePicker dpData;

    @FXML
    private TextArea txtLista;

    @FXML
    private ChoiceBox<String> cbLocal;

    private String[] locais = { "Coronel Vivida", "Itapejara do Oeste", "Pato Branco", "Francisco Beltrão", "São João",
            "Outro" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbLocal.getItems().addAll(locais);
        cbLocal.setOnAction(this::getLocal);
    }

    public String getLocal(ActionEvent event) {
        String meuLocal = cbLocal.getValue();
        return meuLocal;
    }

    @FXML
    public void btAdicionaAction() {
        try {
            String nome = txtNome.getText();
            LocalDate data = dpData.getValue();
            String local = cbLocal.getValue();
            Double valor = Double.parseDouble(txtValor.getText());
            Corrida novaCorrida = new Corrida(nome, data, local, valor);
            
            txtLista.appendText(novaCorrida.toString());

            minhaLista.add(novaCorrida);
        } 
        catch (NumberFormatException e) {
            e.printStackTrace();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btLimpaAction() {

    }
}
