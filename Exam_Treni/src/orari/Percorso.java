package orari;

import java.util.*;


public class Percorso {
  private final String id;
  private final String categoria;
  private boolean straordinario;
  
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
    this.straordinario=straordinario;
  }

  public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
    // TODO Auto-generated method stub
    return null;
  }

  public List getFermate() {
    // TODO Auto-generated method stub
    return null;
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
