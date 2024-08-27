package trail;

import java.util.*;
import java.util.stream.Collectors;

public class Trail {
    private Map<Integer,Runner> runnersMap= new HashMap<>();
    private int runnerCounter=1;
    private int locationCounter=0;
    private Map<String,Location> locationsMap= new HashMap<>();
    private Map<String,Delegate> delegatesMap= new HashMap<>();
    private List<Passage> passagesList= new ArrayList<>();
    public int newRunner(String name, String surname){
        runnersMap.put(runnerCounter, new Runner(runnerCounter, name, surname));
        return runnerCounter++;
    }
    
    public Runner getRunner(int bibNumber){
        return runnersMap.get(bibNumber);
    }
    
    public Collection<Runner> getRunner(String surname){
        return runnersMap.values().stream().filter(runner->runner.getSurname().equals(surname)).sorted(Comparator.comparingInt(Runner::getBibNumber)).collect(Collectors.toList());
    }
    
    public List<Runner> getRunners(){
        return runnersMap.values().stream().sorted(Comparator.comparingInt(Runner::getBibNumber)).collect(Collectors.toList());
    }

    public List<Runner> getRunnersByName(){
        return runnersMap.values().stream().sorted(Comparator.comparingInt(Runner::getBibNumber)).sorted(Comparator.comparing(Runner::getSurname).thenComparing(Runner::getName)).collect(Collectors.toList());
    }
    
    public void addLocation(String location){
        locationsMap.put(location, new Location(location, locationCounter++));
    }
//    public void addLocation(String name, double lat, double lon, double elevation){
//        
//    }

    public Location getLocation(String location){
        return locationsMap.get(location);
    }

    public List<Location> getPath(){
        return locationsMap.values().stream().sorted(Comparator.comparingInt(Location::getOrderNum)).collect(Collectors.toList());
    }
    
    public void newDelegate(String name, String surname, String id){
        delegatesMap.put(id, new Delegate(name, surname, id));
    }

    public List<String> getDelegates(){
        return delegatesMap.values().stream().map(delegate->delegate.getSurname()+", "+delegate.getName()+", "+delegate.getSsn()).sorted().collect(Collectors.toList());
    }
    

    public void assignDelegate(String location, String delegate) throws TrailException {
       if(!locationsMap.containsKey(location)) throw new TrailException();
       if(!delegatesMap.containsKey(delegate)) throw new TrailException();
       locationsMap.get(location).addDelegate(delegatesMap.get(delegate));
    }
 
    public List<String> getDelegates(String location){
        if(!locationsMap.containsKey(location)) return null;
        return locationsMap.get(location).getDelegatesList().stream().map(delegate->delegate.getSurname()+", "+delegate.getName()+", "+delegate.getSsn()).sorted().collect(Collectors.toList());
    }
    
    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
        if(!delegatesMap.containsKey(delegate)) throw new TrailException();
        if(!locationsMap.containsKey(location)) throw new TrailException();
        //if(!runnersMap.containsKey(bibNumber)) throw new TrailException();
        long time= System.currentTimeMillis();
        passagesList.add(new Passage(delegatesMap.get(delegate), locationsMap.get(location), runnersMap.get(bibNumber), time));
        return time;
    }
    
    public long getPassTime(String position, int bibNumber) throws TrailException {
        if(!locationsMap.containsKey(position)) throw new TrailException();
        if(!runnersMap.containsKey(bibNumber)) throw new TrailException();
        return passagesList.stream().filter(passage->passage.getLocation().getName().equals(position) && passage.getRunner().getBibNumber()==bibNumber).mapToLong(Passage::getTime).findFirst().orElse(-1);
    }
    
    public List<Runner> getRanking(String location){
        return passagesList.stream().filter(passage->passage.getLocation().getName().equals(location)).sorted(Comparator.comparingLong(Passage::getTime)).map(Passage::getRunner).collect(Collectors.toList());
    }

    public List<Runner> getRanking(){
        return passagesList.stream().sorted(Comparator.comparingInt((Passage passage)->passage.getLocation().getOrderNum()).reversed().thenComparing(Passage::getTime)).map(Passage::getRunner).distinct().collect(Collectors.toList());
    }
}
