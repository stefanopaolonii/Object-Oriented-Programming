package test.example;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import tvseriesdb.TSException;
import tvseriesdb.TVSeriesDB;


public class TestExample {

	@Test
	public void testAll() throws TSException {
		TVSeriesDB tvsdb = new TVSeriesDB();
		
		// R1 
		
		int numTrs=tvsdb.addTransmissionService("Netflix", "Disney+");
		
		assertEquals(2, numTrs);
		
		tvsdb.addTVSeries("The Office", "Netflix", "Comedy");
		tvsdb.addTVSeries("Daredevil", "Netflix", "Fantasy");
		int numTVS=tvsdb.addTVSeries("Loki", "Disney+", "Fantasy");
		
		assertEquals(3, numTVS);
		
		tvsdb.addActor("Steve", "Carell", "American");
		tvsdb.addActor("Charlie", "Cox", "English");
		tvsdb.addActor("John", "Kransinski", "American");
		tvsdb.addActor("Tom", "Hiddleston", "English");
		tvsdb.addActor("Henry", "Cavill", "English");
		int numAct=tvsdb.addActor("Owen", "Wilson", "American");
		
		assertEquals(6, numAct);
		
		assertEquals(3,tvsdb.addCast("The Office", "Steve Carell", "John Kransinski", "Henry Cavill"));

		assertEquals(2, tvsdb.addCast("Daredevil", "Charlie Cox", "Henry Cavill"));
		
		assertEquals(2,tvsdb.addCast("Loki", "Tom Hiddleston", "Owen Wilson"));
		
		
		
		
		// R2 
		tvsdb.addSeason("The Office", 8, "21:03:2022");
		assertEquals(2, tvsdb.addSeason("The Office", 1, "10:11:2023"));
		
		tvsdb.addEpisode("The Office", 1, "Pilot");
		assertEquals(2,tvsdb.addEpisode("The Office", 1, "Diversity Day"));
		
		assertEquals(1,tvsdb.addEpisode("The Office", 2, "Pilot"));
		
		Map<String, List<Integer>> missing=tvsdb.checkMissingEpisodes();
		List<Integer> incomplete=missing.get("The Office");
		assertEquals(1, incomplete.get(0).intValue());
		
		// R3
		assertEquals(1,tvsdb.addUser("FuckingMetalLover", "Fantasy"));
		
		assertEquals(1, tvsdb.likeTVSeries("FuckingMetalLover", "Loki"));
		
		
		List<String> suggested=tvsdb.suggestTVSeries("FuckingMetalLover");
		assertEquals("Daredevil", suggested.get(0));
		
		tvsdb.addUser("_____roberta", "Horror");
		
	    suggested=tvsdb.suggestTVSeries("_____roberta");
		assertEquals("", suggested.get(0));
		
		//R4
		assertEquals(10.0, tvsdb.addReview("FuckingMetalLover", "Loki", 10), 0);
		assertEquals(8.0, tvsdb.addReview("_____roberta", "Loki", 6),0);
		
		tvsdb.addReview("_____roberta", "Daredevil", 10);
		tvsdb.addReview("_____roberta", "The Office", 8);
		
		tvsdb.likeTVSeries("_____roberta", "Daredevil");
		tvsdb.likeTVSeries("_____roberta", "The Office");
		
		assertEquals(9.0, tvsdb.averageRating("_____roberta"), 0);
		
		//R5
		
		tvsdb.addSeason("Daredevil", 8, "10:03:2024");
		tvsdb.addSeason("Loki", 8, "30:09:2025");
		
		assertEquals("Daredevil 1", tvsdb.mostAwaitedSeason("13:07:2023"));
		
		tvsdb.addReview("_____roberta", "The Office", 9);
		
		List<String> bestActors=tvsdb.bestActors("Netflix");
		assertEquals("Charlie Cox", bestActors.get(0));
		assertEquals("Henry Cavill", bestActors.get(1));
	}
		
}
