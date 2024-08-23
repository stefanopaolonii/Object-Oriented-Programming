package restaurantChain;

import java.util.*;
import java.util.stream.Collectors;

public class Chain {	
	private Map<String,Restaurant> restaurantsMap= new HashMap<>();
	public void addRestaurant(String name, int tables) throws InvalidName{
		if(restaurantsMap.containsKey(name)) throw new InvalidName();
		restaurantsMap.put(name, new Restaurant(name, tables));
	}
	
	public Restaurant getRestaurant(String name) throws InvalidName{
		if(!restaurantsMap.containsKey(name)) throw new InvalidName();
		return restaurantsMap.get(name);
	}
	
	public List<Restaurant> sortByIncome(){
		return restaurantsMap.values().stream().sorted(Comparator.comparingDouble(Restaurant::getIncome).reversed()).collect(Collectors.toList());
	}
	
	public List<Restaurant> sortByRefused(){
		return restaurantsMap.values().stream().sorted(Comparator.comparingInt(Restaurant::getRefused)).collect(Collectors.toList());
	}
	
	public List<Restaurant> sortByUnusedTables(){
		return restaurantsMap.values().stream().sorted(Comparator.comparingInt(Restaurant::getUnusedTables)).collect(Collectors.toList());
	}
}
