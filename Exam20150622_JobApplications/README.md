Jop Applications Handling
=========================

Develop a program to manage the job applications that a company receive for open positions. The main class is **HandleApplications**; all the classes classes are in the package **applications**. The class **Test** contains a sample code that illustrates the usage of the main methods.  
The exceptions thrown by the program are of type **ApplicationException**.

You can access a copy of the [JDK documentation](http://softeng.polito.it/courses/docs/api/index.html) on a local server.

R1: Skills and Positions
------------------------

The method **addSkills()** add a list of competencies given their names as a variable list of strings. It throws and exception if it finds any duplicate skill.

The method **addPosition()** add a new position (given its name) with the list of the required skills (whose names are provided. It throws an exception is a position with the same name has been alredy added or if any of the required skills has not been already added.

The method **getSkill()** returns a **Skill** object given the corresponding name or _null_ if not found.

The **getPositions()** method from class _Skill_ provides the list of position that require that skill, sorted by name. The method **getPosition()** returns a **Position** object given its name, or _null_ if not found.

Both _Skill_ and _Position_ provide the getter **getName()**.

R2: Applicants
--------------

The method **addApplicant()** enters a new applicant (with the name) and his/her capabilities as a single string, e.g. _"java:9,sql:7"_. Every capability is represented as the name of a skill, separated by _':'_, and the level (from 1 to 10) held by the applicant. Le individual capabilities are separated by commas (_','_). The method throws an exception if the applicant has already been added, the capability skill is not present, or the capability level is not in the valid range.

The method **getCapabilities()** returns the list of capabilities of an applicant (given its name) with the format described above; the capabilities are sorted alphabetically. If not capabilities are provided it returns the empty string. The method throws an exception if the applicant is not found.

R3: Applications
----------------

The method **enterApplication()** allows an applicant to apply for a position. It throws an exception if the applicant or the the position are not present, if the applicant has not a capacity corresponding to each skill required by the position, or if the applicant already applied for any position.  
For instance, if the position requires skills _uml_ and _java_, the applicant must possess among her capabilities, one for _java_ and one for _uml_.

The method **getApplicants()** of class _Position_ returns a list of the candidates sorted alphabetically.

R4: Winners
-----------

The method **setWinner()** defines the winner applicant for a given position. It throws an exception if the applicant did not apply for the position, if the position has already a winner, or if the sum of the levels of the capabilities, whose skills are required by the position, is not greater than six times the number of required skills. The method returns the sum of the levels of the capabilities which correspond to required capabilities.

The method **getWinner()** of class _Position_ returns the name of the winner or null if missing.

R5: Statistics
--------------

The metod **skill\_nApplicants()**returns the number of applicants per skill, the skill are sorted alphabetically by name.

The method **maxPosition()** returns the name of the position with the highest number of candidate.