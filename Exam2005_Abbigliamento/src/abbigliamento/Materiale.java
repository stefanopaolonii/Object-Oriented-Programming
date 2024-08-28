package abbigliamento;

public class Materiale {
	private final String nome;
	private final double cpsto;
	
	public Materiale(String nome, double cpsto) {
		this.nome = nome;
		this.cpsto = cpsto;
	}

	public String getNome(){
		return nome;
	}

	public double getCosto(){
		return cpsto;
	}
}
