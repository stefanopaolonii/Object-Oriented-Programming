package abbigliamento;

import java.util.*;
import java.util.stream.Collectors;

public class Collezione {
	private String name;
	private List<Capo> capoList= new ArrayList<>();
	public Collezione(String name) {
		this.name = name;
	}

	public void add(Capo capo) {
		capoList.add(capo);
		
	}

	public Collection trova(Colore colore) {
		return capoList.stream().filter(capo->capo.getColore().getNome().equals(colore.getNome())).collect(Collectors.toList());
	}

	public Collection<Capo>  trova(Materiale materiale) {
		return capoList.stream().filter(capo->capo.getMateriale().getNome().equals(materiale.getNome())).collect(Collectors.toList());
	}

	public Collection<Capo>  trova(Modello modello) {
		return capoList.stream().filter(capo->capo.getModello().getNome().equals(modello.getNome())).collect(Collectors.toList());
	}
	public List<Capo> getCapoList() {
		return capoList;
	}


}
