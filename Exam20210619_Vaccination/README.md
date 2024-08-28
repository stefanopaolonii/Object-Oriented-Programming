Vaccination Campaign
====================

Develop a system to allow planning of a vaccine campaign in an area where severa l vaccination hubs are available.

All classe must be included in the package `it.polito.oop.vaccinaton`. _Facade_ class `Vaccines` allows performing all required operations; each new instance of this class represents a distinct vaccination system with its own people adn hubs. All error and anomaly notification takes place using the `VaccineException` exception class.

Class `TestApp` in default package contains a short test for the application.

JDK documentation is accessible through URL [http://softeng.polito.it/courses/docs/api/index.html](http://softeng.polito.it/courses/docs/api/index.html).

R1: Population
--------------

Population to be vaccined is made up of persons for which we know the SSN (codice fiscale), first name, last name and birth year.

A new person can be added through method `addPerson()` accepting as arguments first and last name, SSN, and birth year. If a person with the same SSN already exists, the method discard the new entry and returns _false_, otherwise it returns _true_. The number of people in the system is returned by method `countPeople()`.

Method `getPerson()` accepts an SSN and returns a string containing SSN, last and first names separated by `","`. Method `getAge()` returns the age of a person given their SSN.

Information about persons can be read from a file in CSV format. Reading takes places through method `loadPeople()` that reads a CSV file containing for each person (a person per line) the SSN, last name, first name, and birth year, all separated by commas without spaces. E.g.,: `MLNLCU50Z403RE,Melone,Luca,1950`.

The file has the header (first line) with at least the columns `SSN`, `LAST`, `FIRST` e `YEAR`, in that order. It the header is not correct, an exception is thrown.

The people read from the CSV are inserted as with the method `addPerson()`. In case of duplicate SSN or incomplete information the line is discarded.

The method acceots a `Reader` and returns the number of people that have been read without problems.

In case of I/O error, the exception must be relayed to the caller.

*   Hint: to read one line at a time the method `readLine()` from `BufferedReader` can be used.

R2 : Age intervals
------------------

Persons can be divided into age intervals that are defined using the method `setAgeIntervals()` that accepts as argument a series of integers corresponding to the ages the divide the intervals. All intervals are closed on the lower end and open on the upper one.

For instance `setAgeIntervals(40,50,60)` defines four intervals: _\[0,40), \[40,50), \[50,60), \[60,∞)_.

Method `getAgeIntervals()` returns a sorted collection with the labels of the age intervals formatted as `"[40,50)"`, where in place of the infinity symbol the character `'+'` is used.

Method `getInInterval()` accepts the label of an interval and returns the list of SSNs of the persons whose age (computed by subtracting the year of birth from the current year) falls in that interval. If no persons fall in the interval, an empty collection is returned.

*   Hint: the get the current year it is possible to use `java.time.LocalDate.now().getYear()`

R3: Vaccination Hubs
--------------------

Vaccination hubs are defined with method `defineHub()` that accepts a name, the method throws and exception in case of duplicate name.

The list of hubs is returned by method `getHubs()`.

Methdo `setStaff()` allows defining the staff configuration for a given hub in terms of doctors, nurses, and other personnel. The method throws an exception if no hub with the given name is found or if any of the counts is <= 0.

Method `estimateCapacity()` accepts the name of a vaccination hub and returns the estimation of the hourly vaccination capacity. It is possible to estimate the hourly capacity of a hub based on the staff; in particular it is computed as the minimum among:

*   10 \* number of doctors,
*   12 \* number of nurses,
*   20 \* number of other personnel.

This method throws an exception if there is no hub with the given name or if no staff has been defined for the hub yet.

R4: Weekly timetable
--------------------

Method `setHours()` accepts an array with the number of hours for each day of the week from monday (0) to sunday (6). The same hours apply to all hubs. The method throws an exception if the argument has not exactly 7 elements or if any day has more than 12 hours.

Method `getHours()` returns a list (one element per day of the week) whose elements are the time slots lists with the format `"hh:mm"`. Time slots are defined every 15 minuts (4 per hour), starting at 9:00 and extending for the number of hours defined with the previous method.

Method `getDailyAvailable()` accepts as arguments the name of a hub and the day of the week number (0=Monday) and returns the vaccination capability of the hub for that day.

The vaccination capability – i.e., the number of vaccinations that can be administered in the hub on the given day – is equal to the number of hours multiplied by the hourly capacity (as estimated by method `estimateHourlyCapacity()`).

Method `getAvailable()` returns a map that associates the name of hubs to the vaccination capabilities in each day of the week, computed as described above.

*   Hint: to print an integer on two characters with the leading 0 is possible the use `String.format("%02d", i)`

R5: Planning
------------

Method `allocate()` accepts as arguments the name of a hub and the number of the day of the week (0=Monday).

The method tries to allocate a number of people for the given hub and day, giving precedence to the higher age intervals. The total number of persons to allocates is equal to the capability (as return by method `getDailyAvailable()`).

For every time interval the method allocates a number of people equal to 40% of the places still available (truncated value).

The following algorithm is used:

*   be _n_ the number of people to allocate
*   iterate on all age interval starting from the highest:
    *   select at most _n\*0.4_ persons (truncated) not yet allocated from the considered age interval,
    *   if there are less persons than required in the age interval, all are allocated,
    *   the persons are marked as allocated for the hub and day,
    *   the number of persons to be allocated is updated as _n = n - a_ where _a_ is the number of persons allocated
*   if at the end of iterations fewer person than required are allocated (i.e. _n != 0_)
    *   all available persons are added starting from the highest interval until all available places are covered.

Therefore if we have six intervals, and supposing capability to be 100, from the 6^th interval 40 persons will be allocated, then 24 from the 5^th, 14 from the 4^th interval, 8 from the 3^rd, 5 from the 2^nd, and 3 from the 1^st. Six position are still available that are assigned to people from 6^th interval (if available).

Clearly it is not possible to assign more persons the the maximum.

The method returns the list of SSNs of the persons allocated in the given hub and day of the week. The persons in the list are marked as allocated and are no more considered in next calls to the method.

Allocations can be cancelled with the method `clearAllocation()` that marks all persons as not allocated (free).

The general week planning is performed by method `weekAllocate()`.

The method returns a list, with one element per day of the week, of maps that associate the name of the hubs to the list of SSNs allocated for that day and hub.

*   Note: according to previous algorithm, by construction, after the first phase there remain some unassigned places (~ 8%), therefore from the highest interval are assigned roughly 48% (40% + 8%) of the available places, from the next one 24% (40% \* 60%), etc.

R6 Plan stats
-------------

After a weekly plan has been defined using method `weekAllocate()`, it is possible to assess its characteristics using the following methods that work on the latest computed weekly plan.

Method `propAllocated()` returns the proportion of persons allocated in the plan w.r.t. the total number of persons present in the system.

Method `propAllocatedAge()` returns a map that associates the age intervals to the proportion of persons persons in that age interval that have been allocated w.r.t. the persons in that interval.

Methodo `distributionAllocated()` returns a map that associates the age intervals to the distribution of the allocated persons, that is the number of allocated persons in an intervale divided by the total number of allocated persons.

R7: Advanced file read
----------------------

It is possible to activate an advanced management of the reading (in extension to what described in requirement R1) by calling the method `setLoadListener()` before invoking the reading method.

This method accepts a listener object that implements the interface `BiConsumer<Integer,String>`.

If a listener has been defined, when during the reading of a CSV an error is found (incomplete line or duplicate SSN) the method `accept()` of the listener must be called passing the number of the line that caused the problem and the line itself. Lines are numbered from 1, where line 1 is the header line.

The method `accept()` of the listener must also be invoked before throwing the exception relative the a wring header.