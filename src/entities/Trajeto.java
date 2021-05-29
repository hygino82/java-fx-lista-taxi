package entities;

public class Trajeto {

	private Double inicio;
	private Double fim;
	private Boolean asfalto;

	public Trajeto() {
	}

	public Trajeto(Double inicio, Double fim, Boolean asfalto) {
		this.inicio = inicio;
		this.fim = fim;
		this.asfalto = asfalto;
	}

	public Double getInicio() {
		return inicio;
	}

	public void setInicio(Double inicio) {
		this.inicio = inicio;
	}

	public Double getFim() {
		return fim;
	}

	public void setFim(Double fim) {
		this.fim = fim;
	}

	public Boolean getAsfalto() {
		return asfalto;
	}

	public void setAsfalto(Boolean asfalto) {
		this.asfalto = asfalto;
	}

	private Double distancia() {
		return Math.abs(fim - inicio);
	}

	public Double valorTrecho() {
		if (asfalto) {
			return 1.5 * distancia();
		} else {
			return 2.0 * distancia();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nInicio: " + String.format("%.2f", inicio));
		sb.append("\nFim: " + String.format("%.2f", fim));
		sb.append("\nDistância: " + String.format("%.2f", distancia()));
		sb.append("\nEstrada de ");
		if (asfalto) {
			sb.append("asfalto");
		} 
		else {
			sb.append("chão");
		}
		sb.append("\nValor do trecho R$" + String.format("%.2f", valorTrecho()));
		return sb.toString();

	}

}
