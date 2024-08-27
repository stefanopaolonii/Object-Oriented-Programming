package trail;

import java.util.Collection;
import java.util.List;

public class Trail {

    public int newRunner(String name, String surname){
        return -1;
    }
    
    public Runner getRunner(int bibNumber){
        return null;
    }
    
    public Collection<Runner> getRunner(String surname){
        return null;
    }
    
    public List<Runner> getRunners(){
        return null;
    }

    public List<Runner> getRunnersByName(){
        return null;
    }
    
    public void addLocation(String location){
        
    }
//    public void addLocation(String name, double lat, double lon, double elevation){
//        
//    }

    public Location getLocation(String location){
        return null;
    }

    public List<Location> getPath(){
        return null;
    }
    
    public void newDelegate(String name, String surname, String id){
        
    }

    public List<String> getDelegates(){
        return null;
    }
    

    public void assignDelegate(String location, String delegate) throws TrailException {
       
    }
 
    public List<String> getDelegates(String location){
        return null;
    }
    
    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
        return -1;
    }
    
    public long getPassTime(String position, int bibNumber) throws TrailException {
        return -1;
    }
    
    public List<Runner> getRanking(String location){
        return null;
    }

    public List<Runner> getRanking(){
        return null;
    }
}
