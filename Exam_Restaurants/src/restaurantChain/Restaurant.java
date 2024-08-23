package restaurantChain;

import java.util.*;

public class Restaurant {
	private final String name;
	private int tables;
	private Map<String,Menu> menusMap= new HashMap<>();
	private Map<String,Integer> groupsMap= new HashMap<>();
	private int refused=0;
	private Map<String,Order> ordersMap= new HashMap<>();
	public Restaurant(String name, int tables) {
		this.name = name;
		this.tables = tables;
	}

	public String getName(){
		return name;
	}
	
	public void addMenu(String name, double price) throws InvalidName{
		if(menusMap.containsKey(name)) throw new InvalidName();
		menusMap.put(name, new Menu(name, price));
	}

	private int getnoftables(int persons){
		return persons/4 + persons%4==0?0:1;
	}
	
	public int reserve(String name, int persons) throws InvalidName{
		if(groupsMap.containsKey(name)) throw new InvalidName();
		if(tables-groupsMap.values().stream().mapToInt(Integer::intValue).sum()<getnoftables(persons)){
			refused+=persons;
			return 0;
		}
		groupsMap.put(name, getnoftables(persons));
		return getnoftables(persons);
	}
	
	public int getRefused(){
		return refused;
	}
	
	public int getUnusedTables(){
		return tables-groupsMap.values().stream().mapToInt(Integer::intValue).sum();
	}
	
	public boolean order(String name, String... menu) throws InvalidName{
		return false;
	}
	
	public List<String> getUnordered(){
		return null;
	}
	
	public double pay(String name) throws InvalidName{
		return -1.0;
	}
	
	public List<String> getUnpaid(){
		return null;
	}
	
	public double getIncome(){
		return -1.0;
	}

}
