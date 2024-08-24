Meeting Planner
===============

Write a program to support planning meeting and polling for preferences for slots.
Classes are located in the `it.polito.meet` package; the main class is `MeetServer`. The `TestApp` class in the `example` package shows usage examples for the main methods and examples of the requested checks. 
Only implement the requested checks. 
Exceptions in the methods described below are of `MeetException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Categories and Meetings
---------------------------

The `addCategories()` method allows adding meeting categories to the list of categories. It accepts the categories as input and adds them to the list.

The `getCategories()` method retrieves the list of available categories.

The `addMeeting()` method adds a new meeting with a specified category. It takes the title, topic, and category of the meeting as input and returns a unique ID for the meeting. If the specified category does not exist, it throws a `MeetException`.

The `getMeetings()` method retrieves the list of meetings that belong to a given category. It takes the category as input and returns a collection of meeting IDs.

The `getMeetingTitle()` method retrieves the title of a meeting based on its ID. It takes the meeting ID as input and returns the corresponding meeting's title.

The `getMeetingTopic()` method retrieves the subject of a meeting based on its ID. It takes the meeting ID as input and returns the corresponding meeting's topic.

R2: Meeting slot options
------------------------

The `MeetServer` class provides additional methods to manage meeting options and retrieve slot information for a meeting.

To add a new option slot for a meeting, you can use the `addOption()` method. This method requires the meeting ID, date, start time, and end time as parameters. It ensures that the new slot does not overlap with any existing slots for the same meeting. If the provided meeting ID is invalid, a `MeetException` is thrown. The method calculates the length of the slot in hours and returns this value.

If you need to retrieve information about the available slots for a specific meeting, you can use the `showSlots()` method. The returned value is a map where each date is a key, and the corresponding value is a list of slots described in the format "hh:mm-hh:mm". This format indicates the start and end times of each slot, allowing you to view the available time options for the meeting.



R3: Preferences
---------------

The `MeetServer` class provides a set of methods to manage preferences for meetings.

To declare a meeting open for collecting preferences, you can use the `openPoll()` method, that given the meeting ID, enables the collection of preferences for that meeting.

When participants want to record their preferences for a specific slot or option of a meeting, they can use the `selectPreference()` method. This method requires the participant's email, name, surname, meeting ID, date of the selected slot, and the time range of the slot. Preferences can only be recorded for meetings that have an opened poll. The method adds the preference and returns the total number of preferences recorded for that slot. If an invalid meeting ID or slot is provided, a `MeetException` is thrown.

Finally, if you need to retrieve the list of preferences expressed for a specific meeting, you can use the `listPreferences()` method. By providing the meeting ID, the method returns a collection of preferences expressed for that meeting. Each preference is represented as a string in the format "YYYY-MM-DDThh:mm-hh:mm=EMAIL", where the date, time interval, and email of the participant are separated by "T" and "=".


R4: Close poll
--------------

To close the poll associated with a meeting and determine the most preferred options, you can use the `closePoll()` method. By providing the meeting ID, the method retrieves the corresponding `Meeting` object. It then disables the poll, indicating that no further preferences can be recorded for that meeting.

The method returns a collection of strings representing the most preferred options. Each option is formatted as "YYYY-MM-DDThh:mm-hh:mm=##", where the date, time interval, and preference count are separated by "T" and "=". This format allows you to easily identify the date, time range, and the number of preferences received for each option.



R5: Stats
---------

Further methods provide useful information for analyzing meeting preferences and evaluating the level of participation or engagement among participants. They facilitate data-driven decision-making by presenting preference counts in an organized manner, allowing organizers or stakeholders to gain insights into attendee preferences and make informed choices based on the collected data.

The `meetingPreferences()` method retrieves the preference count for each slot of a meeting. It takes the meeting ID as input and returns a map where each key represents a date associated with the meeting, and the corresponding value is a list of slots with their preferences. Each slot is described as a string in the format "hh:mm-hh:mm=###", indicating the time interval and the number of preferences for that slot, such as "14:00-15:30=2". This method provides an overview of the preferences expressed for each slot within the meeting.

The `preferenceCount()` method calculates the total number of preferences collected for each meeting. It does not require any input parameters and returns a map where each key represents a meeting ID, and the corresponding value represents the count of preferences expressed for that meeting. This method allows for assessing the level of interest or popularity of each meeting based on the number of preferences received.
