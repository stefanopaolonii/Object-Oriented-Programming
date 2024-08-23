package test.additional;
import orari.Fermata;
import orari.Orari;
import orari.Passaggio;
import orari.Percorso;
import orari.Treno;
import junit.framework.TestCase;



public class TestR5_Statistiche extends TestCase {

  public TestR5_Statistiche(String arg0) {
    super(arg0);
  }

	public void testTrenoArrivato() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);		
		t.registraPassaggio("Torino Porta Nuova",10,20);
		t.registraPassaggio("Vercelli",11,15);
		t.registraPassaggio("Novara",11,46);
		
		assertFalse(t.arrivato());
		
		t.registraPassaggio("Milano Centrale",12,33);

		assertTrue(t.arrivato());
		
	}

	public void testStatTreno() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);		
		t.registraPassaggio("Torino Porta Nuova",10,20);
		t.registraPassaggio("Vercelli",11,15);
		t.registraPassaggio("Novara",11,46);
		t.registraPassaggio("Milano Centrale",12,33);
		
		assertEquals(10,t.ritardoMassimo());
		assertEquals(3,t.ritardoFinale());
	}

	public void testStatPercorso() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t1 = orari.nuovoTreno("IC2345",10,11,2004);		
		t1.registraPassaggio("Torino Porta Nuova",10,20);
		t1.registraPassaggio("Vercelli",11,15);
		t1.registraPassaggio("Novara",11,46);
		t1.registraPassaggio("Milano Centrale",12,33);
		Treno t2 = orari.nuovoTreno("IC2345",10,11,2004);		
		t2.registraPassaggio("Torino Porta Nuova",10,21);
		t2.registraPassaggio("Vercelli",11,8);
		t2.registraPassaggio("Novara",11,49);
		t2.registraPassaggio("Milano Centrale",12,35);
		
		assertEquals(5,p.ritardoMassimo());
		assertEquals(4,p.ritardoMedio());
	}


}
