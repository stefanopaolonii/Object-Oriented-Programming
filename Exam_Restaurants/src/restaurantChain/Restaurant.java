package restaurantChain;

import java.util.List;

public class Restaurant {
	
	public String getName(){
		return null;
	}
	
	public void addMenu(String name, double price) throws InvalidName{
	}
	
	public int reserve(String name, int persons) throws InvalidName{
		return -1;
	}
	
	public int getRefused(){
		return -1;
	}
	
	public int getUnusedTables(){
		return -1;
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
