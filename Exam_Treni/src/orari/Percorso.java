package orari;

import java.util.*;
import java.util.stream.Collectors;


public class Percorso {
  private final String id;
  private final String categoria;
  private boolean straordinario;
  private List<Fermata> fermateList= new ArrayList<>();
  public Percorso(String id, String categoria, boolean straordinario) {
    this.id = id;
    this.categoria = categoria;
    this.straordinario = straordinario;
  }

  public String getCodice() {
    return id;
  }

  public String getCategoria() {
    return categoria;
  }

  public boolean isStraordinario() {
    return straordinario;
  }

  public void setStraordinario(boolean b) {
    this.straordinario=b;
  }

  public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
    Fermata newfFermata= new Fermata(nomeStazione, ore, minuti);
    fermateList.add(newfFermata);
    return newfFermata;
  }

  public List<Fermata> getFermate() {
    return fermateList.stream().sorted(Comparator.comparingInt(Fermata::getOre).thenComparing(Fermata::getMinuti)).collect(Collectors.toList());
  }

  public int ritardoMassimo() {
    // TODO Auto-generated method stub
    return 0;
  }

  public int ritardoMedio() {
    // TODO Auto-generated method stub
    return 0;
  }

}
