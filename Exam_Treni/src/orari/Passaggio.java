package orari;


public class Passaggio {
  private Fermata fermata;
  private int ora;
  private int minuti;
  public Passaggio(Fermata fermata, int ora, int minuti) {
    this.fermata = fermata;
    this.ora = ora;
    this.minuti = minuti;
  }

  public String getStazione() {
    return fermata.getStazione();
  }

  public int getOra() {
   return ora;
  }

  public int getMinuti() {
    return minuti;
  }

  public int ritardo() {
    return (ora*60+minuti)-(fermata.getOre()*60+fermata.getMinuti());
  }

}
