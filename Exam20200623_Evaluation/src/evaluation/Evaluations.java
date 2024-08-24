package evaluation;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Facade class for the research evaluation system
 *
 */
public class Evaluations {
    private Map<Integer,Integer> levelsMap= new HashMap<>();
    private Map<String,Journal> journalsMap= new HashMap<>();
    private int levelCounter=1;
    private Map<String,Group> groupsMap= new HashMap<>();
    //R1
    /**
     * Define number of levels and relative points.
     * 
     * Levels are numbered from 1 on, and points must be strictly decreasing
     *  
     * @param points points for the levels
     * @throws EvaluationsException thrown if points are not decreasing
     */
    public void addPointsForLevels (int... points) throws EvaluationsException {
        int max=points[0];
        for(int i=1;i<points.length;i++){
            if(points[i]>max) throw new EvaluationsException();
            levelsMap.put(levelCounter++, points[i]);
            max=points[i];
        }
    }

    /**
     * Retrieves the points for the given level.
     * 
     * @param level level for which points are required
     * @return points for the level
     */
    public int getPointsOfLevel (int level) {
        return levelsMap.get(level);
    }

    /**
     * Add a new journal for a given disciplines and provides the corresponding level.
     * 
     * The level determines the points for the article published in the journal.
     * 
     * @param name name of the new journal
     * @param discipline reference discipline for the journal
     * @param level level for the journal.
     * @throws EvaluationsException thrown if the specified level does not exist
     */
    public void addJournal (String name, String discipline, int level) throws EvaluationsException {
        if(!levelsMap.containsKey(level)) throw new EvaluationsException();
        journalsMap.put(name,new Journal(name, discipline, level));
    }

    /**
     * Retrieves number of journals.
     * 
     * @return journals count
     */
    public int countJournals() {
        return journalsMap.size();
    }

    /**
     * Retrieves all the journals for a given discipline.
     * 
     * @param discipline the required discipline
     * @return list of journals (sorted alphabetically)
     */
    public List<String> getJournalNamesOfAGivenDiscipline(String discipline) {
        return journalsMap.values().stream().filter(journal->journal.getDiscipline().equals(discipline)).map(Journal::getName).sorted().collect(Collectors.toList());
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws EvaluationsException thrown in case of duplicate name
     */
    public void addGroup (String name, String... disciplines) throws EvaluationsException {
        if(groupsMap.containsKey(name)) throw new EvaluationsException();
        groupsMap.put(name, new Group(name,disciplines));
    }

    /**
     * Define the members for a previously defined research group.
     * 
     * @param groupName name of the group
     * @param memberNames list of group members
     * @throws EvaluationsException thrown if name not previously defined.
     */
    public void setMembers (String groupName, String... memberNames) throws EvaluationsException {
        if(!groupsMap.containsKey(groupName)) throw new EvaluationsException();
        groupsMap.get(groupName).addMembers(memberNames);
    }

    /**
     * Return list of members of a group.
     * The list is sorted alphabetically.
     * 
     * @param groupName name of the group
     * @return list of members
     */
    public List<String >getMembers(String groupName){
        if(!groupsMap.containsKey(groupName)) return null;
        return groupsMap.get(groupName).getMembersSet().stream().sorted().collect(Collectors.toList());
    }

    /**
     * Retrieves the group names working on a given discipline
     * 
     * @param discipline the discipline of interest
     * @return list of group names sorted alphabetically
     */
    public List<String> getGroupNamesOfAGivenDiscipline(String discipline) {
        return groupsMap.values().stream().filter(group->group.getDisciplinesSet().contains(discipline)).map(Group::getName).collect(Collectors.toList());
    }

    //R3
    /**
     * Add a new journal articles, with a given title and the list of authors.
     * 
     * The journal must have been previously defined.
     * 
     * The authors (at least one) are members of research groups.
     * 
     * @param title title of the article
     * @param journalName name of the journal
     * @param authorNames list of authors
     * @throws EvaluationsException thrown if journal not defined or no author provided
     */
    public void addPaper (String title, String journalName, String... authorNames) throws EvaluationsException {
        if(!journalsMap.containsKey(journalName)) throw new EvaluationsException();
        if(authorNames.length==0) throw new EvaluationsException();
        journalsMap.get(journalName).addPaper(new Paper(title, journalName,authorNames));
    }



    /**
     * Retrieves the titles of the articles authored by a member of a research group
     * 
     * @param memberName name of the group member
     * @return list of titles sorted alphabetically
     */
    public List<String> getTitlesOfAGivenAuthor (String memberName) {
        return journalsMap.values().stream().flatMap(journal->journal.getPapersList().stream()).filter(paper->paper.getAuthrosSet().contains(memberName)).map(Paper::getTitle).sorted().collect(Collectors.toList());
    }


    //R4
    /**
     * Returns the points for a given group member.
     * 
     * Points are collected for each article the member authored.
     * The points are those corresponding to the level of the
     * journal where the article is published, divided by
     * the total number of authors.
     * 
     * The total points are eventually rounded to the closest integer.
     * 
     * @param memberName name of the group member
     * @return total points
     */
    public int getPointsOfAGivenAuthor (String memberName) {
        return -1;
    }

    /**
     * Computes the total points collected by all members of all groups
     *  
     * @return the total points
     */
    public int evaluate() {
        return -1;
    }


    //R5 Statistiche
    /**
     * For each group return the total points collected
     * by all the members of each research group.
     * 
     * Group names are sorted alphabetically.
     * 
     * @return the map associating group name to points
     */
    public SortedMap<String, Integer> pointsForGroup() {
        return null;
    }

    /**
     * For each amount of points returns a list of
     * the authors (group members) that achieved such score.
     * 
     * Points are sorted in decreasing order.
     * 
     * @return the map linking the number of point to the list of authors
     */
    public SortedMap<Integer, List<String>> getAuthorNamesPerPoints () {
        return null;
    }


}