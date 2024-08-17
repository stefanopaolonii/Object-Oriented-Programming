package utility;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents the facade class for the utility company.
 */
public class Utility {
    Map<String,ServicePoint> servicespointMap= new HashMap<>();
    Map<String,Meter> metersMap= new HashMap<>();
    Map<String,User> usersMap= new HashMap<>();
    Map<String,Contract> contractsMap= new HashMap<>();
    private int servicepointCounter=1;
    private int meterCounter=1;
    private int userCounter=1;
    private int contractCounter=1;

    /**
     * Defines a new service point.
     *
     * @param municipality the municipality of the service point
     * @param address      the address of the service point
     * @param lat          the latitude of the service point
     * @param lon          the longitude of the service point
     * @return the id of the service point
     */
    public String defineServicePoint(String municipality, String address, double lat, double lon) {
        String code=String.format("SP%d", servicepointCounter++);
        servicespointMap.put(code, new ServicePointImpl(code, municipality, address, new Point(lon, lat)));
        return code;
    }

    /**
     * Returns the list of service points.
     *
     * @return the list of service points
     */
    public Collection<String> getServicePoints() {
        return servicespointMap.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Returns the service point with the given id.
     *
     * @param spId the id of the service point
     * @return the service point with the given id
     */
    public ServicePoint getServicePoint(String spId) {
        return servicespointMap.get(spId);
    }

    /**
     * Adds a new meter to the utility company.
     *
     * @param sn    serial number of the meter
     * @param brand brand of the meter
     * @param model model of the meter
     * @param unit  unit of measure
     * @return the assigned unique id of the meter
     */
    public String addMeter(String sn, String brand, String model, String unit) {
        String code=String.format("MT%d",meterCounter++);
        metersMap.put(code, new MeterImpl(code, sn, brand, model, unit));
        return code;
    }

    /**
     * Connects a meter to a service point.
     *
     * @param spId    the id of the service point
     * @param meterId the id of the meter
     */
    public void installMeter(String spId, String meterId) {
        if(!servicespointMap.containsKey(spId) || !metersMap.containsKey(meterId)) return;
        servicespointMap.get(spId).setMeter(metersMap.get(meterId));
        metersMap.get(meterId).setServicePoint(servicespointMap.get(spId));
    }

    /**
     * Returns the meter with the given id.
     *
     * @param mid the id of the meter
     * @return the meter with the given id
     */
    public Meter getMeter(String mid) {
        return metersMap.get(mid);
    }

    //----
    // R2 User and contracts

    /**
     * Adds a new user to the utility company.
     *
     * @param ssn      the social security number of the user
     * @param name    the name of the user
     * @param surname the surname of the user
     * @param address the address of the user
     * @param email   the email of the user
     * @return the id of the user
     */
    public String addUser(String ssn, String name, String surname, String address, String email) {
        String code=String.format("UR%d",userCounter++);
        usersMap.put(code, new UserImpl(ssn, code, name, surname, address, email));
        return code;
    }

    /**
     * Adds a new business user to the utility company.
     *
     * @param ssn           the social security number or tax code of the user
     * @param businessName the name of the business
     * @param address      the address of the business
     * @param email        the email of the business
     * @return the id of the user
     */
    public String addUser(String ssn, String businessName, String address, String email) {
        String code=String.format("UB%d",userCounter++);
        usersMap.put(code, new UserImpl(ssn, code, businessName, address, email));
        return code;
    }

    /**
     * Returns the user with the given id.
     *
     * @param uid the id of the user
     * @return the user with the given id
     */
    public User getUser(String uid) {
        return usersMap.get(uid);
    }

    /**
     * Returns all users
     *
     * @return a collection of users' id
     */
    public Collection<String> getUsers() {
        return usersMap.keySet();
    }

    /**
     * Signs a new contract with a user that is provided through a service point.
     *
     * @param user the id of the user
     * @param pdp  the id of the service point
     * @return the id of the contract
     */
    public String signContract(String user, String pdp) throws UtilityException {
        if(!usersMap.containsKey(user)) throw new UtilityException("");
        if(!servicespointMap.containsKey(pdp)) throw new UtilityException("");
        if(servicespointMap.get(pdp).getMeter().isEmpty()) throw new UtilityException("");
        String id=String.format("C%d",contractCounter++);
        contractsMap.put(id, new ContractImpl(id, usersMap.get(user), servicespointMap.get(pdp)));
        return id;
    }

    /**
     * Returns the contract with the given id.
     *
     * @param contractId the id of the contract
     * @return the contract with the given id
     */
    public Contract getContract(String contractId) {
        return contractsMap.get(contractId);
    }

    //----
    // R3 Reading

    /**
     * Adds a new reading for a given meter.
     *
     * @param contractId
     * @param meterId
     * @param date
     * @param value
     * @throws UtilityException if the contract and meter do not match
     */
    public void addReading(String contractId, String meterId, String date, double value) throws UtilityException {
        if(!contractsMap.containsKey(contractId)) throw new UtilityException("");
        if(!metersMap.containsKey(meterId)) throw new UtilityException("");
        Optional<Meter> meter=contractsMap.get(contractId).getServicePoint().getMeter();
        if(!meter.isPresent() || !meter.get().getId().equals(meterId)) throw new UtilityException("");
        contractsMap.get(contractId).addReading(meterId, date, value);
    }

    /**
     * Adds a new reading for a given meter.
     *
     * @param contractId id of the contract
     * @return a map that links dates and metering values
     */      
    public Map<String,Double> getReadings(String contractId) {
        return contractsMap.get(contractId).getReadings().stream().collect(Collectors.toMap(Reading::getDate, Reading::getValue));
    }

    /**
     * Read latest reading
     * 
     * @param contractId id of the contract
     * @return a metering value 
     */
    public double getLatestReading(String contractId) {
        Optional<Reading> lastreading=contractsMap.get(contractId).getReadings().stream().sorted(Comparator.comparing(Reading::getDate).reversed()).findFirst();
        if(lastreading.isPresent()) return lastreading.get().getValue();
        return Double.NaN;
    }

    //----
    // R4 Tariffe

    /**
     * Computes the estimated reading for a given contract and date.
     * The estimated reading is computed as the linear interpolation of the latest two readings.
     *
     * @param contractId the id of the contract
     * @param date       the date for which the reading is estimated
     * @return the estimated reading
     * @throws UtilityException if estimation cannot be computed
     */
    public double getEstimatedReading(String contractId, String date) throws UtilityException {
        if(!contractsMap.containsKey(contractId)) throw new UtilityException("");
        List<Reading> readings=contractsMap.get(contractId).getReadings().stream().sorted(Comparator.comparing(Reading::getDate)).collect(Collectors.toList());
        LocalDate targetdDate=LocalDate.parse(date);
        if(readings.size()<2) throw new UtilityException("");
        if(targetdDate.isBefore(LocalDate.parse(readings.get(0).getDate()))) throw new UtilityException("");
        for(int i=0;i<readings.size();i++){
            LocalDate currentDate=LocalDate.parse(readings.get(i).getDate());
            if(currentDate.isEqual(targetdDate)) return readings.get(i).getValue();
            if(currentDate.isAfter(targetdDate)) return interpolate(readings.get(i-1),readings.get(i),targetdDate);
        }
        return interpolate(readings.get(readings.size()-2), readings.get(readings.size()-1), targetdDate);
    }

    private double interpolate(Reading r1, Reading r2, LocalDate targetDate) {
        LocalDate t1 = LocalDate.parse(r1.getDate()), t2 = LocalDate.parse(r2.getDate());
        return r1.getValue() + (targetDate.toEpochDay() - t1.toEpochDay()) * (r2.getValue() - r1.getValue()) / (t2.toEpochDay() - t1.toEpochDay());
    }

    /**
     * Computes the consumption between two dates
     * 
     * @param contractId    the id of the contract
     * @param dateInitial   the initial date
     * @param dateFinal     the final date
     * @return  the total consumption between the two dates
     * @throws UtilityException if the contract id is not valid or a reading cannot be estimated for the dates
     */
    public double getConsumption(String contractId, String dateInitial, String dateFinal) throws UtilityException {
        return getEstimatedReading(contractId, dateFinal) - getEstimatedReading(contractId, dateInitial);
    }

        /**
     * Returns the consumption breakdown (month by month) 
     * 
     * @param contractId    id of the contrac
     * @param monthStart    initial month
     * @param monthEnd      final month
     * @param year          year of reference
     * @return the breakdown
     * @throws UtilityException in case contract is not valid, or it is not possible to get reading estimates
     */
    public List<String> getBillBreakdown(String contractId, int monthStart, int monthEnd, int year) throws UtilityException {
        List<String> breakdown = new ArrayList<>();
        for (int month = monthStart; month <= monthEnd; month++) {
            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = startDate.plusMonths(1).withDayOfMonth(1);
            double startReading = getEstimatedReading(contractId, startDate.toString());
            double endReading = getEstimatedReading(contractId, endDate.toString());
            breakdown.add(startDate + ".." + endDate + ": " + startReading + " -> " + endReading + " = " + (endReading - startReading));
        }
        return breakdown;    
    }
}
