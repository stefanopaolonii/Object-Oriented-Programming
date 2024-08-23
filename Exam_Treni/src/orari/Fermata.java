package orari;


public class Fermata {
  private final String stazione;
  private int ora;
  private int minuti;
  public Fermata(String stazione, int ora, int minuti) {
    this.stazione = stazione;
    this.ora = ora;
    this.minuti = minuti;
  }

  public String getStazione() {
    return stazione;
  }

  public int getOre() {
    return ora;
  }

  public int getMinuti() {
    return minuti;
  }

}
