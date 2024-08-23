package social;

import java.util.*;
import java.util.stream.Collectors;


public class Social {
	private Map<String,Account> accountsMap= new HashMap<>();
	private Map<String,Group> groupsMap= new HashMap<>();
	/**
	 * Creates a new account for a person
	 * 
	 * @param code	nickname of the account
	 * @param name	first name
	 * @param surname last name
	 * @throws PersonExistsException in case of duplicate code
	 */
	public void addPerson(String code, String name, String surname)
			throws PersonExistsException {
		if(accountsMap.containsKey(code)) throw new PersonExistsException();
		accountsMap.put(code, new Account(code, name, surname));

	}

	/**
	 * Retrieves information about the person given their account code.
	 * The info consists in name and surname of the person, in order, separated by blanks.
	 * 
	 * @param code account code
	 * @return the information of the person
	 * @throws NoSuchCodeException
	 */
	public String getPerson(String code) throws NoSuchCodeException {
		if(!accountsMap.containsKey(code))throw new NoSuchCodeException();
		return accountsMap.get(code).toString();
	}

	/**
	 * Define a friendship relationship between to persons given their codes.
	 * 
	 * Friendship is bidirectional: if person A is friend of a person B, that means that person B is a friend of a person A.
	 * 
	 * @param codePerson1	first person code
	 * @param codePerson2	second person code
	 * @throws NoSuchCodeException in case either code does not exist
	 */
	public void addFriendship(String codePerson1, String codePerson2)
			throws NoSuchCodeException {
			if(!accountsMap.containsKey(codePerson1)) throw new NoSuchCodeException();
			if(!accountsMap.containsKey(codePerson2)) throw new NoSuchCodeException();
			accountsMap.get(codePerson1).addFriend(codePerson2,accountsMap.get(codePerson2));
			accountsMap.get(codePerson2).addFriend(codePerson1, accountsMap.get(codePerson1));
	}

	/**
	 * Retrieve the collection of their friends given the code of a person.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return the list of person codes
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> listOfFriends(String codePerson)
			throws NoSuchCodeException {
		if(!accountsMap.containsKey(codePerson)) throw new NoSuchCodeException();
		return accountsMap.get(codePerson).getFriendsMap().keySet();
	}

	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriends(String codePerson)
			throws NoSuchCodeException {
		if(!accountsMap.containsKey(codePerson)) throw new NoSuchCodeException();
		return accountsMap.get(codePerson).getFriendsMap().values().stream().flatMap(account->account.getFriendsMap().values().stream()).map(Account::getId).filter(code->!code.equals(codePerson)).collect(Collectors.toList());
	}

	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * The result has no duplicates.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriendsNoRepetition(String codePerson)
			throws NoSuchCodeException {
		if(!accountsMap.containsKey(codePerson)) throw new NoSuchCodeException();
		return accountsMap.get(codePerson).getFriendsMap().values().stream().flatMap(account->account.getFriendsMap().values().stream()).map(Account::getId).distinct().filter(code->!code.equals(codePerson)).collect(Collectors.toList());
	}

	/**
	 * Creates a new group with the given name
	 * 
	 * @param groupName name of the group
	 */
	public void addGroup(String groupName) {
		groupsMap.put(groupName, new Group(groupName));
	}

	/**
	 * Retrieves the list of groups.
	 * 
	 * @return the collection of group names
	 */
	public Collection<String> listOfGroups() {
		return groupsMap.keySet();
	}

	/**
	 * Add a person to a group
	 * 
	 * @param codePerson person code
	 * @param groupName  name of the group
	 * @throws NoSuchCodeException in case the code or group name do not exist
	 */
	public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
		if(!accountsMap.containsKey(codePerson)) throw new NoSuchCodeException();
		if(!groupsMap.containsKey(groupName)) throw new NoSuchCodeException();
		groupsMap.get(groupName).addAccount(codePerson, accountsMap.get(codePerson));
	}

	/**
	 * Retrieves the list of people on a group
	 * 
	 * @param groupName name of the group
	 * @return collection of person codes
	 */
	public Collection<String> listOfPeopleInGroup(String groupName) {
		if(!groupsMap.containsKey(groupName)) return null;
		return groupsMap.get(groupName).getAccountsMap().keySet();
	}

	/**
	 * Retrieves the code of the person having the largest
	 * group of friends
	 * 
	 * @return the code of the person
	 */
	public String personWithLargestNumberOfFriends() {
		return accountsMap.values().stream().max(Comparator.comparingInt(account->account.getFriendsMap().size())).map(Account::getName).orElse(null);
	}

	/**
	 * Find the code of the person with largest number
	 * of second level friends
	 * 
	 * @return the code of the person
	 */
	public String personWithMostFriendsOfFriends() {
		return accountsMap.values().stream().max(Comparator.comparingInt(account->(int) account.getFriendsMap().values().stream().flatMap(acc->acc.getFriendsMap().values().stream()).count())).map(Account::getId).orElse(null);
	}

	/**
	 * Find the name of group with the largest number of members
	 * 
	 * @return the name of the group
	 */
	public String largestGroup() {
		return groupsMap.values().stream().max(Comparator.comparingInt(group->group.getAccountsMap().size())).map(Group::getName).orElse(null);
	}

	/**
	 * Find the code of the person that is member of
	 * the largest number of groups
	 * 
	 * @return the code of the person
	 */
	public String personInLargestNumberOfGroups() {
		return accountsMap.values().stream().map(Account::getId).max(Comparator.comparingLong(account->groupsMap.values().stream().filter(group->group.getAccountsMap().containsKey(account)).count())).orElse(null);
	}

	/**
	 * add a new post by a given account
	 * @param author the id of the post author
	 * @param text the content of the post
	 * @return a unique id of the post
	 */
    public String post(String author, String text) {
		return null;
    }

	/**
	 * retrieves the content of the given post
	 * @param author	the author of the post
	 * @param pid		the id of the post
	 * @return the content of the post
	 */
    public String getPostContent(String author, String pid) {
		return null;
    }

	/**
	 * retrieves the timestamp of the given post
	 * @param author	the author of the post
	 * @param pid		the id of the post
	 * @return the timestamp of the post
	 */
    public long getTimestamp(String author, String pid) {
		return -1;
    }

	/**
	 * returns the list of post of a given author paginated 
	 * 
	 * @param author	author of the post
	 * @param pageNo	page number (starting at 1)
	 * @param pageLength page length
	 * @return the list of posts id
	 */
    public List<String> getPaginatedUserPosts(String author, int pageNo, int pageLength) {
		return null;
    }

	/**
	 * returns the paginated list of post of friends 
	 * 
	 * the returned list contains the author and the id of a post separated by ":"
	 * 
	 * @param author	author of the post
	 * @param pageNo	page number (starting at 1)
	 * @param pageLength page length
	 * @return the list of posts key elements
	 */
	public List<String> getPaginatedFriendPosts(String author, int pageNo, int pageLength) {
		return null;
	}
}