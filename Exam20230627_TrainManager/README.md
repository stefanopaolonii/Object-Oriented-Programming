Train Management
================

Write a program to support booking and checking seats in trains.
Classes are located in the `it.ffss.train` package; the main class is `TrainManager`. The `TestApp` class in the `example` package shows usage examples for the main methods and examples of the requested checks. 
Only implement the requested checks. 
Exceptions in the methods described below are of `TrainException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Classes and cars
--------------------

The `TrainManager` class focuses on managing travel classes and train cars. It provides several methods to add, retrieve, and manipulate information about the classes and cars.

The `addClasses()` method allows adding a set of travel classes to the list of classes offered on the train. It accepts multiple class names as parameters and ensures that duplicates are ignored.

To retrieve the list of classes defined for the train, the `getClasses()` method can be used. It returns a collection of class names.

Adding a new car to the train is facilitated by the `addCar()` method. It requires a unique ID, the number of rows in the car, the last seat letter, and the class of the car. Upon successful addition, it returns the number of available seats in the train car. In case of duplicate car IDs or non-existing classes, a `TrainException` is thrown.

To obtain a list of cars belonging to a specific class, the `getCarsByClass()` method is available. It takes the required class as a parameter and returns a collection of car IDs.

For determining the number of seats in a particular car, the `getNumSeats()` method can be used. It requires the ID of the car and returns the total number of seats in that car.


R2: Stops and seats
-------------------

The `TrainManager` class also includes methods related to defining stops and finding available seats on a train trip.

The `defineStops()` method allows defining the stop stations for a train. It takes the stop names as parameters and assigns a unique index to each stop. The method returns the number of segments defined, which is equal to the number of stops minus one.

To retrieve the available seats on a given trip from the source to the destination, the `findSeats()` method is used. It requires the initial stop, final stop, and car class as parameters. The method returns a map that represents the available seats by car. Each entry in the map corresponds to a car that has available seats for the specified class and in all the segments from the beginning to the end of the trip. The seats are represented as a list of strings in the format `"#X"`, where `#` represents the row number and `X` represents the seat position. Only the seats that are not booked are included in the result.

The `findSeats()` method retrieves the relevant cars for the specified class and collects the information about available seats in each car. The resulting map associates each car ID with a list of available seats.


R3: Bookings
----------------

The `TrainManager` class includes several methods related to booking seats, retrieving booking information, and listing bookings for a specific seat.

The `bookSeat()` method is used to book a seat for a person traveling from a beginning station to an end station on a given car. It requires the passenger's SSN, name, surname, the initial stop, final stop, car ID, and seat number, as parameters. The method returns a unique booking code. It checks if the car and seat are valid, validates the stops, and ensures that the seat is available for all the segments of the trip. If any validation fails, a TrainException is thrown.

To retrieve the car associated with a specific booking, the `getBookingCar()` method is used. It takes the booking ID as a parameter and returns the car ID.

The `getBookingPassenger()` method retrieves the SSN (Social Security Number) of the person who made a particular booking. It takes the booking ID as a parameter and returns the SSN of the passenger.

The `getBookingSeat()` method retrieves the seat associated with a specific booking. It takes the booking ID as a parameter and returns the seat number.

The `getBookingTrip()` method retrieves the trip information for a specific booking. The trip is described as the initial and final stop separated by a hyphen ("-"). It takes the booking ID as a parameter and returns the trip information.

To list all bookings for a given seat, the `listBookings()` method is used. It takes the car ID and seat number, as parameters and returns a collection of bookings represented as strings. Each booking is in the format "begin-end:SSN", where "begin" and "end" represent the initial and final stops, respectively, and "SSN" represents the Social Security Number of the booked person.

These methods provide functionalities to manage and retrieve booking information, making it easier to track and handle seat reservations for train journeys.


R4: Checking passengers
-----------------------

The `TrainManager` class also includes methods related to managing and checking seat bookings.

The `setLastStop()` method is used to define the last station where the train stopped. This information is essential for determining which bookings are still valid for a seat. It takes the stop name as a parameter and sets it as the last stop. The method returns the total number of people who have booked seats on the train after the specified stop. It calculates this count by filtering the bookings based on whether they include the last stop and counting the number of matching bookings.

To check the booking ID associated with a given seat, the `checkSeat()` method is used. It considers the last stop and identifies the booking that starts at or before the last stop and has not yet terminated. If such a booking exists for the specified seat, the method marks it as checked and returns its booking ID. If no such booking is available, the method returns `null`. This functionality allows for validating and managing seat reservations, ensuring that only valid bookings are considered.

These methods contribute to the overall functionality of the `TrainManager` class by providing the ability to set the last stop and check seat bookings based on the last stop. They help maintain accurate and up-to-date information about seat availability and valid bookings.


R5: Stats
---------

The `TrainManager` class includes additional methods related to computing and analyzing seat bookings.

The `showFillRatio()` method computes the fill ratio for all the seats of a given class. The fill ratio is determined by dividing the number of seats that have at least one booking by the total number of seats in that class across all cars.

The `checkCoverage()` method computes the check count per class. It returns a map that reports, for each class, the number of bookings that have been checked.

The `showOccupationRatio()` method computes the occupation ratio for all the seats of a given class and for each segment of the train path. The occupation ratio is determined by dividing the number of occupied seats in each segment by the total number of seats multiplied by the number of segments.