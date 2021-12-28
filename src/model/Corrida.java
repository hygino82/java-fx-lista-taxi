package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Corrida {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String nomeCliente;
    private Date dataCorrida;
    private String localCorrida;
    private Double valorCorrida;

    public Corrida() {
    }

    public Corrida(String nomeCliente, Date dataCorrida, String localCorrida, Double valorCorrida) {
        this.nomeCliente = nomeCliente;
        this.dataCorrida = dataCorrida;
        this.localCorrida = localCorrida;
        this.valorCorrida = valorCorrida;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataCorrida() {
        return dataCorrida;
    }

    public void setDataCorrida(Date dataCorrida) {
        this.dataCorrida = dataCorrida;
    }

    public String getLocalCorrida() {
        return localCorrida;
    }

    public void setLocalCorrida(String localCorrida) {
        this.localCorrida = localCorrida;
    }

    public Double getValorCorrida() {
        return valorCorrida;
    }

    public void setValorCorrida(Double valorCorrida) {
        this.valorCorrida = valorCorrida;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: " + nomeCliente + "\n");
        sb.append("Data: " + sdf.format(dataCorrida) + "\n");
        // sb.append("Data: " +
        // dataCorrida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
        sb.append("Local: " + localCorrida + "\n");
        sb.append("Valor: R$" + String.format("%.2f", valorCorrida));
        return sb.toString();
    }

    public String csvFormato() {
        return nomeCliente + ","
        // + dataCorrida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + sdf.format(dataCorrida) + ","
                + localCorrida + "," + valorCorrida;
    }
}
