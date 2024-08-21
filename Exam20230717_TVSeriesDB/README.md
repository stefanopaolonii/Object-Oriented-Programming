TV Series Database
===============

Write a program to support the management of a movie database.
Classes are located in the `it.polito.tvseriesdb` package; the main class is `TvSeriesDB`. The `TestApp` class in the `example` package shows usage examples for the main methods and examples of the requested checks. 
Only implement the requested checks. 
Exceptions in the methods described below are of `TSException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Series Management
---------------------

The `addTransmissionService()` method adds a transmission service (e.g., Netflix, Prime Video, Disney+) to the list of available services. It takes a list of transmission services as input, removes duplicates, and adds them to the list. It returns the number of transmission services inserted so far.

The `addTVSeries()` method adds a new TV series given name, transmission service, and genre. If the transmission service is not available, it throws a `TSException`. TV series names must be unique, hence if a series with the same name already exists, it throws a `TSException`. It returns the number of TV series inserted so far.

The `addActor()` method adds actors to the database. It takes the actor's name, surname, and nationality as input. The name and surname pair is unique. If the actor was already inserted, a `TSException` is thrown. It returns the number of actors inserted so far.

The `addCast()` method adds cast information of a TV series. It requires the TV series and actors to exist in the database. If either the series or one of the actor in the list does not exist, it throws a `TSException`. The method returns the number of actors in the cast of the TV series.

R2: TV Series Episodes Management
---------------------------------

The `addSeason()` method adds a new season for a specific TV series. It takes the TV series name, number of episodes, and release date as input (format "day:month:year"). If the TV series does not exist, it throws a `TSException`. If the release date is before the release date of the last inserted season for the TV series, a `TSException` is thrown. The method returns the number of seasons for the TV series so far. 

The `addEpisode()` method adds the title of an episode to a season of a TV series and season (NB: season numbering starts from 1). It requires the TV series name, season number, episode title. Episodes are inserted in sequential order. If the TV series or its season do not exist, if the number of episodes inserted is already equal to the maximum number of expected episodes for the season or if the title of the episode is not unique in the season, the method throws a `TSException`.  The method returns the number of episodes inserted so far for that season of the TV series.

The `checkMissingEpisodes()` method returns a map that specifies which TV series have incomplete seasons. The map's key is the series name; the value is a list of incomplete season numbers. 

R3: User and Management
-----------------------

The `addUser()`method adds a new user to the application. It takes the user's username and favorite genre as input. The username is unique, if the user is already registered throws  `TSException`. It returns the number of registered users up to that moment.

The `likeTVSeries()` method adds a TV series to a user's list of favorite series. It requires both the TV series and user to exist in the database. If either the TV series or the user do not exist or if the TV series is already in the list, it throws a `TSException`. The method returns the number of TV series liked by the user so far.

The `suggestTVSeries()` method suggests TV series for a given user. It checks if the user exists (otherwise throws `TSException`) and returns a list of suggested TV series. The suggested TV series (not already included in the user's favorite list) are of the user's favorite genre. If no suggestion is found, a list containing only the empty string "" is returned.

R4: Reviews
------------

The `addReview()` method allows users to add reviews in the form of scores (ranging from 0 to 10) for a specific TV series. It requires the user and TV series to exist in the database and the score to be within the valid range; otherwise throws `TSException`. It returns the average review score for the series so far.

The `averageRating()` method calculates for a user the average rating of all the TV series in his/her list of favorites TV series. If no reviews are present then the rating is 0 (zero). 


R5: Statistics
---------------

The `mostAwaitedSeason()` method returns the most awaited season (yet to be released). It takes all the latest seasons of TV series that have yet to come out after the date passed in input (i.e. the release date is after the given date) and selects the TV series with the highest average review score as the most awaited. The return value is a string formatted as `"TvSeriesName seasonNumber"`. In case of a tie on the score, select the first series in alphabetical order.
If no season for any series is scheduled to be released after the given date, the method returns the empty string `“”`.

The `bestActors()` method returns the list of actor names who have exclusively worked in TV series with an average rating higher than 8 and available on the transmission service passed in input. If no actor matches the requirements, then an empty list is returned. If the transmission service does not exist it throws a `TSException`.
