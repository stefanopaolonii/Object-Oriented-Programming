package orari;

import java.util.*;
import java.util.stream.Collectors;

public class Treno {
  private Percorso percorso;
  private int giorno;
  private int mese;
  private int anno;
  private List<Passaggio> passaggiList= new ArrayList<>();
  public Treno(Percorso percorso, int giorno, int mese, int anno) {
    this.percorso = percorso;
    this.giorno = giorno;
    this.mese = mese;
    this.anno = anno;
  }

  public Percorso getPercorso() {
    return percorso;
  }

  public int getGiorno() {
    return giorno;
  }

  public int getMese() {
    return mese;
  }

  public int getAnno() {
    return anno;
  }

  public Passaggio registraPassaggio(String string, int i, int j) 
  	throws StazioneNonValida {
    Fermata searched=percorso.getFermate().stream().filter(fermata->fermata.getStazione().equals(string)).findFirst().orElse(null);
    if(searched==null) throw new StazioneNonValida();
    Passaggio newPassaggio= new Passaggio(searched, j, i);
    passaggiList.add(newPassaggio);
    return newPassaggio;
  }

  public boolean arrivato() {
    return passaggiList.stream().map(Passaggio::getStazione).collect(Collectors.toList()).contains(percorso.getFermate().get(percorso.getFermate().size()-1).getStazione());
  }

  public int ritardoMassimo() {
    return passaggiList.stream().max(Comparator.comparingInt(Passaggio::ritardo)).map(Passaggio::ritardo).orElse(-1);
  }

  public int ritardoFinale() {
    if(passaggiList.isEmpty()) return -1;
    return passaggiList.get(passaggiList.size()-1).ritardo();
  }

}
