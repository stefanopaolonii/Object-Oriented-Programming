package abbigliamento;

public class Capo {
	private String nome;
	private final Modello modello;
	private final Materiale materiale;
	private final Colore colore;
	
	public Capo(String nome,Modello modello, Materiale materiale, Colore colore) {
		this.modello = modello;
		this.materiale = materiale;
		this.colore = colore;
		this.nome=nome;
	}

	public double prezzo() {
		return modello.getCosto()+modello.getQuantita()*materiale.getCosto();
	}
	@Override
	public String toString(){
		return modello.getNome()+" "+colore.getNome()+" di "+materiale.getNome();
	}
	public Modello getModello() {
		return modello;
	}
	public Materiale getMateriale() {
		return materiale;
	}
	public Colore getColore() {
		return colore;
	}

}
