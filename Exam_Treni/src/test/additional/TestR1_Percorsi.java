package test.additional;
import java.util.Collection;

import junit.framework.TestCase;
import orari.Orari;
import orari.Percorso;


public class TestR1_Percorsi extends TestCase {

  public TestR1_Percorsi(String arg0) {
    super(arg0);
  }

  public void testCreaPercorso() throws Exception {
  	Orari orari = new Orari();
  	String codice = "IC2345";
  	String categoria = "Intercity";
  	Percorso p = orari.creaPercorso(codice,categoria);
  	
  	assertTrue(p!=null);
		assertEquals(codice,p.getCodice());
		assertEquals(categoria,p.getCategoria());
		assertFalse("Un percorso per defaul NON � straordinario",
					p.isStraordinario());

		p.setStraordinario(true);
		assertTrue(p.isStraordinario());
  }
  
  public void testPercorsi() throws Exception {
		Orari orari = new Orari();
		String codice[] = {"IC2345","IR432"};
		String categoria[] = {"Intercity","Interregionale"};
		
		for(int i=0; i<codice.length; ++i){
			orari.creaPercorso(codice[i],categoria[i]);
		}
		
		Collection<Percorso> percorsi = orari.getPercorsi();
		
		assertEquals(codice.length,percorsi.size());
  }

  public void testPercorso() throws Exception {
		Orari orari = new Orari();
		String codice[] = {"IC2345","IR432"};
		String categoria[] = {"Intercity","Interregionale"};
		
		for(int i=0; i<codice.length; ++i){
			orari.creaPercorso(codice[i],categoria[i]);
		}
		
		for(int i=0; i<codice.length; ++i){
			Percorso percorso = orari.getPercorso(codice[i]);
			assertEquals(codice[i],percorso.getCodice());		
		}
  }
}
