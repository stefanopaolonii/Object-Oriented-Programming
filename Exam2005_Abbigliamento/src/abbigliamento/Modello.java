package abbigliamento;

public class Modello {
	private final String nome;
	private final double costoFisso;
	private final double quantitaTessuto;

	public Modello(String nome, double costoFisso, double quantitaTessuto) {
		this.nome = nome;
		this.costoFisso = costoFisso;
		this.quantitaTessuto = quantitaTessuto;
	}
	public String getNome(){
		return nome;
	}
	public double getCosto(){
		return costoFisso;
	}
	
	public double getQuantita() {
		return quantitaTessuto;
	}

}
