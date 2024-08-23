package restaurantChain;

import java.util.*;
import java.util.stream.Collectors;

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
		if(tables-getnoftables(groupsMap.values().stream().mapToInt(Integer::intValue).sum())<getnoftables(persons)){
			refused+=persons;
			return 0;
		}
		groupsMap.put(name, persons);
		return getnoftables(persons);
	}
	
	public int getRefused(){
		return refused;
	}
	
	public int getUnusedTables(){
		return tables-getnoftables(groupsMap.values().stream().mapToInt(Integer::intValue).sum());
	}
	
	public boolean order(String name, String... menu) throws InvalidName{
		if(!groupsMap.containsKey(name)) throw new InvalidName();
		if(!menusMap.keySet().containsAll(Arrays.asList(menu))) throw new InvalidName();
		if(Arrays.asList(menu).size()<groupsMap.get(name)) return false;
		Order neworder= new Order(name);
		for(String smenu: menu){
			if(!menusMap.containsKey(smenu)) throw new InvalidName();
			neworder.addManu(menusMap.get(smenu));
		}
		ordersMap.put(name, neworder);
		return true;
	}
	
	public List<String> getUnordered(){
		return groupsMap.keySet().stream().filter(name->!ordersMap.values().stream().map(Order::getName).distinct().collect(Collectors.toList()).contains(name)).sorted().collect(Collectors.toList());
	}
	
	public double pay(String name) throws InvalidName{
		if(!groupsMap.containsKey(name)) throw new InvalidName();
		if(!ordersMap.values().stream().map(Order::getName).distinct().collect(Collectors.toList()).contains(name)) return 0;
		ordersMap.get(name).setPayed(true);
		return ordersMap.get(name).getMenusList().stream().mapToDouble(Menu::getPrice).sum();
	}
	
	public List<String> getUnpaid(){
		return ordersMap.values().stream().filter(order->!order.isPayed()).map(Order::getName).collect(Collectors.toList());
	}
	
	public double getIncome(){
		return ordersMap.values().stream().filter(order->order.isPayed()).flatMap(order->order.getMenusList().stream()).mapToDouble(Menu::getPrice).sum();
	}

}
