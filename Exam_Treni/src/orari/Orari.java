package orari;

import java.util.*;
import java.util.stream.Collectors;


public class Orari {
  private Set<String> categorie= new HashSet<>(Set.of("Intercity", "Eurostar", "Interregionale","Regionale"));
  private Map<String,Percorso> percorsiMap= new HashMap<>();
  private List<Treno> treniList= new ArrayList<>();
  public Percorso creaPercorso(String codice, String categoria) {
    if(!categorie.contains(categoria)) return null;
    percorsiMap.put(codice, new Percorso(codice, categoria, false));
    return percorsiMap.get(codice);
  }

  public Collection<Percorso> getPercorsi() {
    return percorsiMap.values().stream().collect(Collectors.toList());
  }

  public Percorso getPercorso(String codice) {
    if(!percorsiMap.containsKey(codice)) return null;
    return percorsiMap.get(codice);
  }

  public Treno nuovoTreno(String codice, int giorno, int mese, int anno) 
  	throws PercorsoNonValido {
    if(!percorsiMap.containsKey(codice)) throw new PercorsoNonValido();
    Treno newTreno= new Treno(percorsiMap.get(codice), giorno, mese, anno);
    treniList.add(newTreno);
    return newTreno;
  }

  public List<Treno> getTreni() {
    return treniList.stream().sorted(Comparator.comparingInt(Treno::getAnno).thenComparingInt(Treno::getMese).thenComparingInt(Treno::getGiorno).reversed()).collect(Collectors.toList());
  }

}
