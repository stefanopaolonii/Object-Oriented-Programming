package library;

import java.util.*;
import java.util.stream.Collectors;;


public class LibraryManager {
	    private Map<String,Book> booksMap= new TreeMap<>();
		private Map<String,Reader> readersMap= new HashMap<>();
		private Map<String,Rental> rentalsMap= new TreeMap<>();

		private int bookCounter=1000;
		private int readerCounter=1000;
    // R1: Readers and Books 
    
    /**
	 * adds a book to the library archive
	 * The method can be invoked multiple times.
	 * If a book with the same title is already present,
	 * it increases the number of copies available for the book
	 * 
	 * @param title the title of the added book
	 * @return the ID of the book added 
	 */
    public String addBook(String title) {
		String id= String.format("%d",bookCounter++);
		booksMap.put(id, new Book(title, id));
        return id;
    }
    
    /**
	 * Returns the book titles available in the library
	 * sorted alphabetically, each one linked to the
	 * number of copies available for that title.
	 * 
	 * @return a map of the titles liked to the number of available copies
	 */
    public SortedMap<String, Integer> getTitles() {
		return booksMap.values().stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.groupingBy(Book::getTitle,TreeMap::new,Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }
    
    /**
	 * Returns the books available in the library
	 * 
	 * @return a set of the titles liked to the number of available copies
	 */
    public Set<String> getBooks() {    	    	
        return booksMap.keySet();
    }
    
    /**
	 * Adds a new reader
	 * 
	 * @param name first name of the reader
	 * @param surname last name of the reader
	 */
    public void addReader(String name, String surname) {
		String id= String.format("%d",readerCounter++);
		readersMap.put(id, new Reader(name, surname, id));
    }
    
    
    /**
	 * Returns the reader name associated to a unique reader ID
	 * 
	 * @param readerID the unique reader ID
	 * @return the reader name
	 * @throws LibException if the readerID is not present in the archive
	 */
    public String getReaderName(String readerID) throws LibException {
		if(!readersMap.containsKey(readerID)) throw new LibException();
        return readersMap.get(readerID).toString();
    }    
    
    
    // R2: Rentals Management
    
    
    /**
	 * Retrieves the bookID of a copy of a book if available
	 * 
	 * @param bookTitle the title of the book
	 * @return the unique book ID of a copy of the book or the message "Not available"
	 * @throws LibException  an exception if the book is not present in the archive
	 */
    public String getAvailableBook(String bookTitle) throws LibException {
		if(!booksMap.values().stream().anyMatch(book->book.getTitle().equals(bookTitle))) throw new LibException();
        return booksMap.values().stream().filter(book->book.getTitle().equals(bookTitle) && !book.isInrental()).map(Book::getId).findFirst().orElse("Not available");
    }   

    /**
	 * Starts a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param startingDate the starting date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is already renting a book, or if the book copy is already rented
	 */
	public void startRental(String bookID, String readerID, String startingDate) throws LibException {
		if(!booksMap.containsKey(bookID)) throw new LibException();
		if(!readersMap.containsKey(readerID)) throw new LibException();
		if(readersMap.get(readerID).isInrental()) throw new LibException();
		if(booksMap.get(bookID).isInrental()) throw new LibException();
		rentalsMap.put(readerID+bookID, new Rental(readersMap.get(readerID), booksMap.get(bookID), startingDate));
		booksMap.get(bookID).setInrental(true);
		readersMap.get(readerID).setInrental(true);
	}
    
	/**
	 * Ends a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param endingDate the ending date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is not renting a book, or if the book copy is not rented
	 */
    public void endRental(String bookID, String readerID, String endingDate) throws LibException {
		if(!rentalsMap.containsKey(readerID+bookID)) throw new LibException();
		if(!booksMap.get(bookID).isInrental()) throw new LibException();
		if(!readersMap.containsKey(readerID)) throw new LibException();
		rentalsMap.get(readerID+bookID).setEndDate(endingDate);
		booksMap.get(bookID).setInrental(false);
		readersMap.get(readerID).setInrental(false);
    }
    
    
   /**
	* Retrieves the list of readers that rented a specific book.
	* It takes a unique book ID as input, and returns the readers' reader IDs and the starting and ending dates of each rental
	* 
	* @param bookID the unique book ID of the book copy
	* @return the map linking reader IDs with rentals starting and ending dates
	* @throws LibException  an exception if the book copy or the reader are not present in the archive,
	* if the reader is not renting a book, or if the book copy is not rented
	*/
    public SortedMap<String, String> getRentals(String bookID) throws LibException {
		if(!booksMap.containsKey(bookID)) throw new LibException();
		SortedMap<String,String> searchedMap= new TreeMap<>();
		rentalsMap.values().stream().filter(rental->rental.getBook().getId().equals(bookID)).forEach(rental->{if(rental.getEndDate()==null) searchedMap.put(rental.getReader().getId(),rental.getStartDate()+" ONGOING");else searchedMap.put(rental.getReader().getId(),rental.getStartDate()+" "+rental.getEndDate());});
		return searchedMap;
	}
    
    
    // R3: Book Donations
    
    /**
	* Collects books donated to the library.
	* 
	* @param donatedTitles It takes in input book titles in the format "First title,Second title"
	*/
    public void receiveDonation(String donatedTitles) {
		String[] books= donatedTitles.split(",");
		Arrays.asList(books).stream().forEach(title->addBook(title));
    }
    
    // R4: Archive Management

    /**
	* Retrieves all the active rentals.
	* 
	* @return the map linking reader IDs with their active rentals

	*/
    public Map<String, String> getOngoingRentals() {
        return readersMap.values().stream().filter(reader->reader.isInrental()).collect(Collectors.toMap(Reader::getId, reader->rentalsMap.values().stream().filter(rental->rental.getReader().equals(reader) && rental.getEndDate()==null).map(rental->rental.getBook().getId()).findFirst().orElse("")));
    }
    
    /**
	* Removes from the archives all book copies, independently of the title, that were never rented.
	* 
	*/
    public void removeBooks() {
		List<Book> toremove=rentalsMap.values().stream().map(Rental::getBook).collect(Collectors.toList());
		booksMap.values().removeIf(book->!toremove.contains(book));
	}
    	
    // R5: Stats
    
    /**
	* Finds the reader with the highest number of rentals
	* and returns their unique ID.
	* 
	* @return the uniqueID of the reader with the highest number of rentals
	*/
    public String findBookWorm() {
        return rentalsMap.values().stream().collect(Collectors.groupingBy(rental->rental.getReader().getId(),TreeMap::new,Collectors.counting())).entrySet().stream().max(Comparator.comparingLong(entry->entry.getValue())).map(entry->entry.getKey()).orElse(null);
    }
    
    /**
	* Returns the total number of rentals by title. 
	* 
	* @return the map linking a title with the number of rentals
	*/
    public Map<String,Integer> rentalCounts() {
        return booksMap.values().stream().collect(Collectors.groupingBy(Book::getTitle,Collectors.summingInt(book->(int) rentalsMap.values().stream().filter(rental->rental.getBook().equals(book)).count())));
    }

}
