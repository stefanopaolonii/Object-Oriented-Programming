package orari;


public class Treno {
  private Percorso percorso;
  private int giorno;
  private int mese;
  private int anno;
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
    // TODO Auto-generated method stub
    return null;
  }

  public boolean arrivato() {
    // TODO Auto-generated method stub
    return false;
  }

  public int ritardoMassimo() {
    // TODO Auto-generated method stub
    return 0;
  }

  public int ritardoFinale() {
    // TODO Auto-generated method stub
    return 0;
  }

}
