package restaurantChain;

import java.util.*;

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
		return null;
	}
	
	public List<Restaurant> sortByRefused(){
		return null;
	}
	
	public List<Restaurant> sortByUnusedTables(){
		return null;
	}
}
