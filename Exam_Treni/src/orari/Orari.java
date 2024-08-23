package orari;

import java.util.*;
import java.util.stream.Collectors;


public class Orari {
  private Set<String> categorie= new HashSet<>(Set.of("Intercity", "Eurostar", "Interregionale","Regionale"));
  private Map<String,Percorso> percorsiMap= new HashMap<>();
  public Percorso creaPercorso(String codice, String categoria) {
    if(!categorie.contains(categoria)) return null;
    percorsiMap.put(codice, new Percorso(codice, categoria, false));
    return percorsiMap.get(codice);
  }

  public Collection getPercorsi() {
    return percorsiMap.values().stream().collect(Collectors.toList());
  }

  public Percorso getPercorso(String codice) {
    if(!percorsiMap.containsKey(codice)) return null;
    return percorsiMap.get(codice);
  }

  public Treno nuovoTreno(String codice, int giorno, int mese, int anno) 
  	throws PercorsoNonValido {
    // TODO Auto-generated method stub
    return null;
  }

  public List getTreni() {
    // TODO Auto-generated method stub
    return null;
  }

}
