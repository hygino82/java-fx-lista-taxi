package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Corrida;

public class ViewController implements Initializable {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Double valorTotal = 0.0;
    private List<Corrida> minhaLista = new ArrayList<>();

    @FXML
    private Label lbTotal;

    @FXML
    private Button btListar;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCarregar;

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

        btCarregarAction();//carrega lista no início
    }

    public void getLocal(ActionEvent event) {
        String cidade = cbLocal.getValue();
        switch (cidade) {
            case "Coronel Vivida":
                txtValor.setText("60");
                break;

            case "Itapejara do Oeste":
                txtValor.setText("45");
                break;

            case "Pato Branco":
                txtValor.setText("150");
                break;

            case "Francisco Beltrão":
                txtValor.setText("160");
                break;

            case "São João":
                txtValor.setText("45");
                break;

            default:
                txtValor.setText("");
        }
    }

    @FXML
    public void btAdicionaAction() {
        try {
            String nome = txtNome.getText().toUpperCase();
            LocalDate myDate = dpData.getValue();

            String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Date data = sdf.parse(myFormattedDate);

            String local = cbLocal.getValue();
            Double valor = Double.parseDouble(txtValor.getText());
            Corrida novaCorrida = new Corrida(nome, data, local, valor);

            txtLista.clear();
            txtLista.appendText(novaCorrida.toString());

            minhaLista.add(novaCorrida);
            valorTotal += novaCorrida.getValorCorrida();
            lbTotal.setText("R$" + String.format("%.2f", valorTotal));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btLimpaAction() {
        txtLista.clear();
        txtNome.clear();
        txtValor.clear();
    }

    @FXML
    public void btListarAction() {
        txtLista.clear();
        for (Corrida corrida : minhaLista) {
            txtLista.appendText("\n-------------------------------------------\n");
            txtLista.appendText(corrida.toString());
        }
    }

    @FXML
    public void btCarregarAction() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String path = "C:\\temp\\out.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {

                String[] campos = line.split(",");
                String nome = campos[0];
                Date dataCorrida = sdf.parse(campos[1]);
                String local = campos[2];
                Double valor = Double.parseDouble(campos[3]);
                Corrida corrida = new Corrida(nome, dataCorrida, local, valor);
                minhaLista.add(corrida);

                valorTotal += valor;
                lbTotal.setText("R$" + String.format("%.2f", valorTotal));

                System.out.println(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            txtLista.clear();
            txtLista.appendText("Formato inválido");
        }
    }

    @FXML
    public void btSalvarAction() {

        String path = "C:\\temp\\out.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Corrida line : minhaLista) {
                bw.write(line.csvFormato());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
