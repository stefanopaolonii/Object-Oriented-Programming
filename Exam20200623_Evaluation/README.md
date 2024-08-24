Evaluations
===========

The program simulates the evaluation of research groups in an institute. The evaluation is based on the score obtained from article publication in scientific journals. The journals are classified into levels and each level has a different score.

All the classes have to be in **evaluation** package. The main class is **Evaluations**. The class **TestApp** in the package **example** contains an example. The exceptions are thrown using the class **EvaluationsException**.

It is possible to access [Java API documentation](https://oop.polito.it/api/index.html).

R1: Levels and journals
-----------------------

The method **addPointsForLevels (int... points)** defines points for each of the levels. Number of levels is supposed to be equal to the number of points provided as arguments. Levels are progressive, starting from 1. The points are given in a descending manner (more points are assigned to level 1 than to level 2, and so on); the method should launch an exception if the points are not provided in a descending order.

The method **getPointsOfLevel(int level)** gives the points of the corresponding level.

The method **addJournal(String name, String discipline, int level)** adds a journal, with its name, relevant discipline, and level; furthermore, it defines a discipline. An exception is thrown if the indicated level has not been previously defined.

The method **countJournals()** returns the number of journals that have been defined.

The method **getJournalNamesOfAGivenDiscipline(String discipline)** generates an alphabetically sorted list containing the journal names related to the given discipline. An empty list is returned if for a given discipline no journal has been defined.

R2: Groups and members
----------------------

The method **addGroup(String name, String... disciplines)** adds a (research) group with its name and disciplines of interest. The method throws an exception if a group with the same name already exists.

The method **setMembers(String groupName, String... memberNames)** defines the group members. It throws an exceptions if a group has not been defined.

The method **getMembers()** returns a list of members of the given group, sorted alphabetically. If a group has not been defined or does not have any members, an empty list is returned.

The method **getGroupNamesOfAGivenDiscipline(String discipline)** produces a sorted list of the names of the groups related to the indicated discipline. It returns an empty list if the discipline has not been defined or if no groups exist for the indicated discipline.

R3: Papers
----------

The method **addPaper(String title, String journalName, String... memberNames)** adds a paper, defined by its title, journal, and authors (at least one). It throws an exception if a journal has not been defined or if there is not at least one author.

The method **getTitlesOfAGivenAuthor(String authorName)** produces a list of paper titles for a given author (group member). If an author does not have any paper, an empty list is returned.

R4: Evaluations
---------------

The method **getPointsOfAGivenAuthor(String memberName)** returns a score for a research group member that has been given. For each of the papers they wrote, a member receives a number of points defined for a journal in which the paper has been published, divided by the number of authors of that paper. The result of the method is a sum of such points for all of the member’s (author’s) papers, rounded to the closest integer, using the method **Math.round()**.

The method **evaluate** calculates the total number of points for all of the papers (of the corresponding journals). Note that the total number of points for the papers is not necessarily equal to the sum of points of the authors, as a consequence of rounding.

R5: Statistics
--------------

The method **pointsForGroup()** returns a map that associates a group name to the number of group points. The names of the groups are sorted alphabetically. The number of points associated to a group is equal to the sum of points belonging to each member of that group.

The method **getAuthorNamesPerPoints()** returns a map that associates a number of points with the list of names of the authors who have such number of points. The scores are sorted in descending order while the names of the authors are sorted alphabetically. Authors with the score 0 are discarded.