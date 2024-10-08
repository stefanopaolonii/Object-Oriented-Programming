package meet;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class MeetServer {
    private Set<String> categoriesSet= new HashSet<>();
    private Map<String,Meeting> meetingsMap= new HashMap<>();
    private int meetingCounter=0;
	/**
	 * adds a set of meeting categories to the list of categories
	 * The method can be invoked multiple times.
	 * Possible duplicates are ignored.
	 * 
	 * @param categories the meeting categories
	 */
	public void addCategories(String... categories) {
        categoriesSet.addAll(Arrays.asList(categories));
	}

	/**
	 * retrieves the list of available categories
	 * 
	 * @return list of categories
	 */
	public Collection<String> getCategories() {
		return categoriesSet;
	}
	
	
	/**
	 * adds a new meeting with a given category
	 * 
	 * @param title		title of meeting
	 * @param topic	    subject of meeting
	 * @param category  category of the meeting
	 * @return a unique id of the meeting
	 * @throws MeetException in case of non-existing category
	 */
	public String addMeeting(String title, String topic, String category) throws MeetException {
		if(!categoriesSet.contains(category)) throw new MeetException();
        String id=String.format("M%d", ++meetingCounter);
        meetingsMap.put(id, new Meeting(id, title, topic, category));
        return id;
	}

	/**
	 * retrieves the list of meetings with the given category
	 * 
	 * @param category 	required category
	 * @return list of meeting ids
	 */
	public Collection<String> getMeetings(String category) {
		return meetingsMap.values().stream().filter(meet->meet.getCategory().equals(category)).map(Meeting::getId).collect(Collectors.toList());
	}

	/**
	 * retrieves the title of the meeting with the given id
	 * 
	 * @param meetingId  id of the meeting 
	 * @return the title
	 */
	public String getMeetingTitle(String meetingId) {
        if(!meetingsMap.containsKey(meetingId)) return null;
		return meetingsMap.get(meetingId).getTitle();
	}

	/**
	 * retrieves the topic of the meeting with the given id
	 * 
	 * @param meetingId  id of the meeting 
	 * @return the topic of the meeting
	 */
	public String getMeetingTopic(String meetingId) {
		if(!meetingsMap.containsKey(meetingId)) return null;
		return meetingsMap.get(meetingId).getTopic();
	}

	// R2
	
	/**
	 * Add a new option slot for a meeting as a date and a start and end time.
	 * The slot must not overlap with an existing slot for the same meeting.
	 * 
	 * @param meetingId	id of the meeting
	 * @param date		date of slot
	 * @param start		start time
	 * @param end		end time
	 * @return the length in hours of the slot
	 * @throws MeetException in case of slot overlap or wrong meeting id
	 */
	public double addOption(String meetingId, String date, String start, String end) throws MeetException {
		if(!meetingsMap.containsKey(meetingId)) throw new MeetException();
        Slot newSlot= new Slot(date, start, end);
        if(meetingsMap.get(meetingId).getSlotsList().stream().anyMatch(slot->slot.overlap(newSlot))) throw new MeetException();
        meetingsMap.get(meetingId).addSlot(newSlot);
        return newSlot.slotinH();
	}

	/**
	 * retrieves the slots available for a given meeting.
	 * The returned map contains a key for each date and the corresponding value
	 * is a list of slots described as strings with the format "hh:mm-hh:mm",
	 * e.g. "14:00-15:30".
	 * 
	 * @param meetingId		id of the meeting
	 * @return a map date -> list of slots
	 */
	public Map<String, List<String>> showSlots(String meetingId) {
		if(!meetingsMap.containsKey(meetingId)) return null;
        return meetingsMap.get(meetingId).getSlotsList().stream().collect(Collectors.groupingBy(Slot::getDate,Collectors.mapping(Slot::toString, Collectors.toList())));
	}

	/**
	 * Declare a meeting open for collecting preferences for the slots.
	 * 
	 * @param meetingId	is of the meeting
	 */
	public void openPoll(String meetingId) {
        if(!meetingsMap.containsKey(meetingId)) return;
        meetingsMap.get(meetingId).setPoolopen(true);
	}   


	/**
	 * Records a preference of a user for a specific slot/option of a meeting.
	 * Preferences can be recorded only for meeting for which poll has been opened.
	 * 
	 * @param email		email of participant
	 * @param name		name of the participant
	 * @param surname	surname of the participant
	 * @param meetingId	id of the meeting
	 * @param date		date of the selected slot
	 * @param slot		time range of the slot
	 * @return the number of preferences for the slot
	 * @throws MeetException	in case of invalid id or slot
	 */
	public int selectPreference(String email, String name, String surname, String meetingId, String date, String slot) throws MeetException {
		if(!meetingsMap.containsKey(meetingId)) throw new MeetException();
        if(!meetingsMap.get(meetingId).isPoolopen()) throw new MeetException();
        String [] parts=slot.split("-");
        Slot tmpslot=new Slot(date, parts[0], parts[1]);
        if(!meetingsMap.get(meetingId).getSlotsList().stream().anyMatch(sslot->sslot.equals(tmpslot))) throw new MeetException();
        meetingsMap.get(meetingId).addPreference(new Preference(email, name, surname, meetingId, tmpslot));
        return (int) meetingsMap.get(meetingId).getPreferencesList().stream().map(Preference::getSlot).filter(sslot->sslot.equals(tmpslot)).count();
	}

	/**
	 * retrieves the list of the preferences expressed for a meeting.
	 * Preferences are reported as string with the format
	 * "YYYY-MM-DDThh:mm-hh:mm=EMAIL", including date, time interval, and email separated
	 * respectively by "T" and "="
	 * 
	 * @param meetingId meeting id
	 * @return list of preferences for the meeting
	 */
	public Collection<String> listPreferences(String meetingId) {
		if(!meetingsMap.containsKey(meetingId)) return null;
        return meetingsMap.get(meetingId).getPreferencesList().stream().map(preference->preference.getSlot().getDate()+"T"+preference.getSlot().toString()+"="+preference.getEmail()).collect(Collectors.toList());
	}

	/**
	 * close the poll associated to a meeting and returns
	 * the most preferred options, i.e. those that have receive the highest number of preferences.
	 * The options are reported as string with the format
	 * "YYYY-MM-DDThh:mm-hh:mm=##", including date, time interval, and preference count separated
	 * respectively by "T" and "="
	 * 
	 * @param meetingId	id of the meeting
	 */
	public Collection<String> closePoll(String meetingId) {
		if(!meetingsMap.containsKey(meetingId)) return null;
        meetingsMap.get(meetingId).setPoolopen(false);
        List<String> tmp= new ArrayList<>();
        tmp.add(meetingsMap.get(meetingId).getPreferencesList().stream().map(Preference::getSlot).collect(Collectors.groupingBy(slot->slot.getDate()+"T"+slot.toString(),Collectors.counting())).entrySet().stream().sorted(Comparator.comparingLong(entry->((Entry<String, Long>) entry).getValue()).reversed()).map(entry->entry.getKey()+"="+entry.getValue()).findFirst().orElse(null));
        return tmp;
    }

	
	/**
	 * returns the preference count for each slot of a meeting
	 * The returned map contains a key for each date and the corresponding value
	 * is a list of slots with preferences described as strings with the format 
	 * "hh:mm-hh:mm=###", including the time interval and the number of preferences 
	 * e.g. "14:00-15:30=2".
	 * 
	 * @param meetingId	the id of the meeting
	 * @return the map data -> slot preferences
	 */
	public Map<String, List<String>> meetingPreferences(String meetingId) {
		if(!meetingsMap.containsKey(meetingId)) return null;
        return meetingsMap.get(meetingId).getSlotsList().stream().collect(Collectors.toMap(slot->slot, slot->meetingsMap.get(meetingId).getPreferencesList().stream().map(Preference::getSlot).filter(sslot->sslot.equals(slot)).count())).entrySet().stream().collect(Collectors.groupingBy(entry->entry.getKey().getDate(),Collectors.mapping(entry->{if(entry.getValue()==0) return null; return entry.getKey().toString()+"="+entry.getValue();}, Collectors.toList()))).entrySet().stream().collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue().stream().filter(string->string!=null).collect(Collectors.toList())));
	}


	/**
	 * computes the number preferences collected for each meeting
	 * The result is a map that associates to each meeting id the relative count of preferences expressed
	 * 
	 * @return the map id : preferences -> count
	 */
	public Map<String, Integer> preferenceCount() {
		return meetingsMap.values().stream().collect(Collectors.toMap(meet->meet.getId(),meet->meet.getPreferencesList().size()));
	}

	
}