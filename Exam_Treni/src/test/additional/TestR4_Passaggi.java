package test.additional;
import orari.Fermata;
import orari.Orari;
import orari.Passaggio;
import orari.Percorso;
import orari.StazioneNonValida;
import orari.Treno;
import junit.framework.TestCase;



public class TestR4_Passaggi extends TestCase {

  public TestR4_Passaggi(String arg0) {
    super(arg0);
  }

	public void testRegistraPassaggio() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		Fermata f1 = p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);
		
		Passaggio pass = t.registraPassaggio("Torino Porta Nuova",10,20);
		
		assertEquals("Torino Porta Nuova",pass.getStazione());
		assertEquals(10,pass.getOra());
		assertEquals(20,pass.getMinuti());		
	}

	public void testStazioneNonValida() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		Fermata f1 = p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);
		
		try{
			t.registraPassaggio("Porta Susa",10,20);
			fail("La stazione non dovrebbe essere corretta");
		}catch(StazioneNonValida snv){
			assertTrue(true); // ok
		}
	}

	public void testRitardo() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		Fermata f1 = p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);
		
		Passaggio pass = t.registraPassaggio("Torino Porta Nuova",10,20);
		
		assertEquals(5,pass.ritardo());
	}
}
