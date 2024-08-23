package test.additional;
import java.util.List;
import java.lang.reflect.Method;
import orari.Orari;
import orari.Percorso;
import orari.PercorsoNonValido;
import orari.Treno;
import junit.framework.TestCase;



public class TestR3_Treni extends TestCase {

  public TestR3_Treni(String arg0) {
    super(arg0);
  }

	public void testNuovoTreno() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);
		assertSame(p,t.getPercorso());
		assertEquals(10,t.getGiorno());
		assertEquals(11,t.getMese());
		assertEquals(2004,t.getAnno());
	}

	public void testPercorsoNonValido() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		
		try{
			Treno t = orari.nuovoTreno("AA2345",10,11,2004);
			fail("Il percorso non dovrebbe essere valido");
		}catch(PercorsoNonValido pnv){
			assertTrue(true); // Ok
		}
	}

	public void testTreni() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t1 = orari.nuovoTreno("IC2345",10,11,2004);
		Treno t2 = orari.nuovoTreno("IC2345",11,11,2004);
		//List treni = p.getTreni();
		List<Object> treni = invokeList(p,"getTreni");
		
		assertSame(t2,treni.get(0));
		assertSame(t1,treni.get(1));
	}

	private List<Object> invokeList(Object obj, String method){
		Class c = obj.getClass();
		try{
		  Method m = c.getMethod(method,new Class[0]);
		  Object result = m.invoke(obj,new Object[0]);
		  return (List)result;
		}catch(Exception e){
			fail("Method " + method + " not present in class " + c.getName());
		}
  	    return null;
	}

}
