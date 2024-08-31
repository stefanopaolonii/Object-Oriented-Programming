package transactions;
import java.util.*;
import java.util.stream.Collectors;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {
	public enum Type{REQUEST,OFFER};
	private Map<String,Region> regionsMap= new HashMap<>();
	private Map<String,Carrier> carriersMap= new HashMap<>();
	private Map<String,RequestOffer> roMap= new HashMap<>();
	private Map<String,Transaction> transactionsMap= new HashMap<>();
	private Set<String>placesSet= new HashSet<>();
//R1
	public List<String> addRegion(String regionName, String... placeNames) { 
		Set<String> newplaces=Arrays.asList(placeNames).stream().filter(place->!placesSet.contains(place)).collect(Collectors.toSet());
		regionsMap.put(regionName, new Region(regionName, newplaces));
		placesSet.addAll(newplaces);
		return regionsMap.get(regionName).getPlacesSet().stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		Carrier newCarrier= new Carrier(carrierName);
		Arrays.asList(regionNames).forEach(rname->newCarrier.addRegion(regionsMap.get(rname)));
		carriersMap.put(carrierName,newCarrier);
		return newCarrier.getRegionsSet().stream().map(Region::getName).sorted().collect(Collectors.toList());
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		return carriersMap.values().stream().filter(carrier->carrier.getRegionsSet().stream().map(Region::getName).collect(Collectors.toList()).contains(regionName)).map(Carrier::getName).collect(Collectors.toList());
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
		if(!regionsMap.values().stream().flatMap(region->region.getPlacesSet().stream()).collect(Collectors.toSet()).contains(placeName)) throw new TMException();
		if(roMap.containsKey(requestId)) throw new TMException();
		roMap.put(requestId, new RequestOffer(Type.REQUEST, requestId, placeName,regionsMap.values().stream().filter(region->region.getPlacesSet().contains(placeName)).findFirst().orElse(null), productId));
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
		if(!regionsMap.values().stream().flatMap(region->region.getPlacesSet().stream()).collect(Collectors.toSet()).contains(placeName)) throw new TMException();
		if(roMap.containsKey(offerId)) throw new TMException();
		roMap.put(offerId, new RequestOffer(Type.OFFER, offerId, placeName,regionsMap.values().stream().filter(region->region.getPlacesSet().contains(placeName)).findFirst().orElse(null),productId));
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
		if(!carriersMap.containsKey(carrierName)) throw new TMException();
		if(!roMap.containsKey(requestId)) throw new TMException();
		if(!roMap.containsKey(offerId)) throw new TMException();
		if(transactionsMap.values().stream().map(Transaction::getRequest).map(RequestOffer::getId).collect(Collectors.toList()).contains(requestId)) throw new TMException();
		if(transactionsMap.values().stream().map(Transaction::getOffer).map(RequestOffer::getId).collect(Collectors.toList()).contains(offerId)) throw new TMException();
		RequestOffer request=roMap.get(requestId);
		RequestOffer offer=roMap.get(offerId);
		if(request.getProductid()!=offer.getProductid()) throw new TMException();
		Set<String> carrierplaces=carriersMap.get(carrierName).getRegionsSet().stream().flatMap(region->region.getPlacesSet().stream()).collect(Collectors.toSet());
		if(!carrierplaces.contains(request.getPlace())) throw new TMException();
		if(!carrierplaces.contains(offer.getPlace())) throw new TMException();
		transactionsMap.put(transactionId, new Transaction(transactionId, carriersMap.get(carrierName), request, offer));
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		if(!transactionsMap.containsKey(transactionId)) return false;
		if(score<1 || score>10) return false;
		transactionsMap.get(transactionId).setRating(score);
		return true;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		return transactionsMap.values().stream().collect(Collectors.groupingBy(transaction->transaction.getRequest().getRegion().getName(),Collectors.counting())).entrySet().stream().collect(Collectors.groupingBy(entry->entry.getValue(),()-> new TreeMap<>(Collections.reverseOrder()),Collectors.mapping(entry->entry.getKey(), Collectors.collectingAndThen(Collectors.toList(), list->{Collections.sort(list); return list;}))));
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		return transactionsMap.values().stream().filter(transaction->transaction.getRating()>=minimumScore).collect(Collectors.groupingBy(transaction->transaction.getCarrier().getName(),TreeMap::new,Collectors.summingInt(transaction->transaction.getRating())));
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		return transactionsMap.values().stream().collect(Collectors.groupingBy(transaction->transaction.getRequest().getProductid(),TreeMap::new,Collectors.counting()));
	}
	
	
}

