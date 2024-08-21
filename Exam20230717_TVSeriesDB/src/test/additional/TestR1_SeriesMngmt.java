package test.additional;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import tvseriesdb.TSException;
import tvseriesdb.TVSeriesDB;

public class TestR1_SeriesMngmt {

	private TVSeriesDB tvsdb;
	
	@Before
	public void setUp() {
		tvsdb = new TVSeriesDB();
	}

	private final static String[] ts = {"Netflix","Disney+","Amazon Prime Video"};
	private final static String[] tvseries = {"Daredevil","The marvelous Mrs. Maisel","The Bear"};
	
	
	@Test
	public void testTransmissionServices() {
		
		assertEquals("Wrong number of added services", 
				    3,tvsdb.addTransmissionService(ts));		
		assertEquals("should discard duplicate services of existing services", 
				    4, tvsdb.addTransmissionService("Netflix", "Hulu"));		
		assertEquals("should discard duplicate services of existing services", 
					5, tvsdb.addTransmissionService("Peacok TV", "Peacok TV"));
		
	}

	
	@Test
	public void testAddTVSeriesNoService() {
		
		tvsdb.addTransmissionService(ts);
		assertThrows("Series must be on existign service",TSException.class,
				 	()-> tvsdb.addTVSeries("The White Lotus", "Now TV", "Drama"));
			
	}
	
	@Test
	public void testAddTVSeries() throws TSException {
		tvsdb.addTransmissionService(ts);
		assertEquals("Wrong number of series", 1, tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy"));
		
		tvsdb.addTVSeries(tvseries[1], "Amazon Prime Video", "Comedy");
		assertEquals("Wrong number of series",3,tvsdb.addTVSeries(tvseries[2], "Disney+", "Drama"));
	}
	

	@Test
	public void testAddTVSeriesDup() throws TSException {
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy");
		tvsdb.addTVSeries(tvseries[1], "Amazon Prime Video", "Comedy");
		assertThrows("Cannot add duplicate series", TSException.class,
				()->tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy"));
		assertThrows("Cannot add duplicate series", TSException.class,
				()->tvsdb.addTVSeries(tvseries[0], "Disney+", "Drama"));
	}
	
	@Test
	public void testAddActor() throws TSException {
		
		assertEquals("Wrong number of actors", 1,
				      tvsdb.addActor("Charlie", "Cox", "English"));
		tvsdb.addActor("Vincent", "D'Onofrio", "American");
		tvsdb.addActor("Vincent", "Cassel", "French");
		assertEquals("Wrong number of actors",4,
				tvsdb.addActor("Steve", "Carell", "American"));
		assertEquals("Wrong number of actors",5,
				tvsdb.addActor("Paul", "D'Onofrio", "Brasilian"));
	}
	
	@Test
	public void testAddActorDupl() throws TSException {
		
		tvsdb.addActor("Charlie", "Cox", "English");
		tvsdb.addActor("Vincent", "D'Onofrio", "American");
		assertThrows("Cannot add duplicate actor", TSException.class,
				()->tvsdb.addActor("Vincent", "D'Onofrio", "Spanish"));
		assertEquals("Wrong number of actors",3, 
				tvsdb.addActor("Jon", "Berntal", "Portuguese"));
	}


	@Test
	public void testAddCast() throws TSException {
		
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy");
		
		tvsdb.addActor("Charlie", "Cox", "English");
		tvsdb.addActor("Vincent", "D'Onofrio", "American");
		tvsdb.addActor("Jon Bernthal", "Cassel", "French");
		assertEquals("Wrong cast size", 2, 
				      tvsdb.addCast("Daredevil", "Charlie Cox", "Vincent D'Onofrio"));
	}
	
	@Test
	public void testAddCastWrongActor() throws TSException {
		
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy");
		
		tvsdb.addActor("Charlie", "Cox", "English");
		tvsdb.addActor("Vincent", "D'Onofrio", "American");
		tvsdb.addActor("Jon Bernthal", "Cassel", "French");
		assertThrows("Cannot add non existing actor", TSException.class,
				    ()->tvsdb.addCast(tvseries[0], "Steve Carell", "Vincent D'Onofrio"));
	}
    
	@Test
	public void testAddCastWrongTvSeries() throws TSException {
		
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries(tvseries[0], "Netflix", "Fantasy");
		
		tvsdb.addActor("Charlie", "Cox", "English");
		tvsdb.addActor("Vincent", "D'Onofrio", "American");
		tvsdb.addActor("Jon Bernthal", "Cassel", "French");
		assertThrows("Cannot add cast to non existing series",TSException.class,
					()->tvsdb.addCast("The Office", "Vincent D'Onofrio"));
			
	}
}
