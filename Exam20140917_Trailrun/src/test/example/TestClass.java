package test.example;

import java.util.List;

import trail.Location;
import trail.Runner;
import trail.Trail;
import trail.TrailException;


public class TestClass {
	public static void main(String[] args) throws TrailException, InterruptedException {
        Trail trail = new Trail();
        int n1 = trail.newRunner("Franco", "Colle");
        int n2 = trail.newRunner("Bruno", "Brunod");
        
        System.out.println("Runners:");
        for(Runner r : trail.getRunners()){
            System.out.println("\t" + r.getSurname() + " " + r.getName() + " (" + r.getBibNumber() + ")");
        }
        
        trail.addLocation("Courmayeur");
        trail.addLocation("La Thuile");
        trail.addLocation("Valgrisanche");
        
        System.out.println("The trail path:");
        for(Location l : trail.getPath()){
            System.out.println(l.getOrderNum() + " - " + l.getName());
        }
        
        trail.newDelegate("Mario", "Rossi", "MRIRSS65B10A413B");
        
        trail.assignDelegate("Courmayeur", "MRIRSS65B10A413B");
        trail.assignDelegate("La Thuile", "MRIRSS65B10A413B");

        trail.recordPassage("MRIRSS65B10A413B", "Courmayeur", n1);
        Thread.sleep(400);
        trail.recordPassage("MRIRSS65B10A413B", "Courmayeur", n2);
		
        List<Runner> rank = trail.getRanking("Courmayeur");
        System.out.println("Ranking:");
        int pos=1;
        for(Runner r : rank){
            System.out.println(pos++ + "\t" + r.getSurname() + " " + r.getName() + " (" + r.getBibNumber() + ")");
        }
	}	
}
