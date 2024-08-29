package sports;
import java.util.*;
import java.util.stream.Collectors;

 
/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {
    private List<String> activitiesList= new ArrayList<>();
    private Map<String,Category> categoriesMap= new HashMap<>();
    private Map<String,Product> productsMap= new HashMap<>();
    //R1
    /**
     * Define the activities types treated in the portal.
     * The method can be invoked multiple times to add different activities.
     * 
     * @param actvities names of the activities
     * @throws SportsException thrown if no activity is provided
     */
    public void defineActivities (String... activities) throws SportsException {
        if(activities.length==0) throw new SportsException("");
        activitiesList.addAll(Arrays.asList(activities));
    }

    /**
     * Retrieves the names of the defined activities.
     * 
     * @return activities names sorted alphabetically
     */
    public List<String> getActivities() {
        Collections.sort(activitiesList);
        return activitiesList;
    }


    /**
     * Add a new category of sport products and the linked activities
     * 
     * @param name name of the new category
     * @param activities reference activities for the category
     * @throws SportsException thrown if any of the specified activity does not exist
     */
    public void addCategory(String name, String... linkedActivities) throws SportsException {
        if(!activitiesList.containsAll(Arrays.asList(linkedActivities))) throw new SportsException("");
        categoriesMap.put(name, new Category(name, linkedActivities));
    }

    /**
     * Retrieves number of categories.
     * 
     * @return categories count
     */
    public int countCategories() {
        return categoriesMap.size();
    }

    /**
     * Retrieves all the categories linked to a given activity.
     * 
     * @param activity the activity of interest
     * @return list of categories (sorted alphabetically)
     */
    public List<String> getCategoriesForActivity(String activity) {
        return categoriesMap.values().stream().filter(category->category.getLinkedActivities().contains(activity)).map(Category::getName).sorted().collect(Collectors.toList());
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws SportsException thrown in case of duplicate name
     */
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
        if(productsMap.containsKey(name)) throw new SportsException("");
        productsMap.put(name, new Product(name, activityName, categoriesMap.get(categoryName)));
    }

    /**
     * Retrieves the list of products for a given category.
     * The list is sorted alphabetically.
     * 
     * @param categoryName name of the category
     * @return list of products
     */
    public List<String> getProductsForCategory(String categoryName){
        return productsMap.values().stream().filter(product->product.getCategory().getName().equals(categoryName)).map(Product::getName).sorted().collect(Collectors.toList());
    }

    /**
     * Retrieves the list of products for a given activity.
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @return list of products
     */
    public List<String> getProductsForActivity(String activityName){
        return productsMap.values().stream().filter(product->product.getActivity().equals(activityName)).map(Product::getName).sorted().collect(Collectors.toList());
    }

    /**
     * Retrieves the list of products for a given activity and a set of categories
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @param categoryNames names of the categories
     * @return list of products
     */
    public List<String> getProducts(String activityName, String... categoryNames){
        return productsMap.values().stream().filter(product->product.getActivity().equals(activityName) && Arrays.asList(categoryNames).contains(product.getCategory().getName())).map(Product::getName).sorted().collect(Collectors.toList());
    }

    //    //R3
    /**
     * Add a new product rating
     * 
     * @param productName name of the product
     * @param userName name of the user submitting the rating
     * @param numStars score of the rating in stars
     * @param comment comment for the rating
     * @throws SportsException thrown numStars is not correct
     */
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
        if(!productsMap.containsKey(productName)) throw new SportsException("");
        if(numStars<0 || numStars>5) throw new SportsException("");
        Product searched= productsMap.get(productName);
        searched.addRating(new Rating(searched, userName, numStars, comment));
    }



    /**
     * Retrieves the ratings for the given product.
     * The ratings are sorted by descending number of stars.
     * 
     * @param productName name of the product
     * @return list of ratings sorted by stars
     */
    public List<String> getRatingsForProduct(String productName) {
        if(!productsMap.containsKey(productName)) return null;
        return productsMap.get(productName).getRatingList().stream().sorted(Comparator.comparingInt(Rating::getNumStars).reversed()).map(Rating::toString).collect(Collectors.toList());
    }


    //R4
    /**
     * Returns the average number of stars of the rating for the given product.
     * 
     * 
     * @param productName name of the product
     * @return average rating
     */
    public double getStarsOfProduct (String productName) {
        return -1.0;
    }

    /**
     * Computes the overall average stars of all ratings
     *  
     * @return average stars
     */
    public double averageStars() {
        return -1.0;

    }

    //R5 Statistiche
    /**
     * For each activity return the average stars of the entered ratings.
     * 
     * Activity names are sorted alphabetically.
     * 
     * @return the map associating activity name to average stars
     */
    public SortedMap<String, Double> starsPerActivity() {
        return null;
    }

    /**
     * For each average star rating returns a list of
     * the products that have such score.
     * 
     * Ratings are sorted in descending order.
     * 
     * @return the map linking the average stars to the list of products
     */
    public SortedMap<Double, List<String>> getProductsPerStars () {
        return null;
    }

}