# Utility

Requirements in Italian are available [here](README_it.md). 

Create a program for managing the billing of a public utility company (e.g., water, electricity, gas).

The interaction occurs through the `Utility` facade class. All classes are contained in the `it.polito.po.utility` package.

## R1. Service Points and Meters

A service point represents the point where end users can connect to the service supply.

A service point is defined through the `defineServicePoint()` method, which receives the municipality, address, and geographic location in terms of latitude and longitude as parameters. The method returns a unique alphanumeric code automatically assigned by the system. The code must start with `"SP"` and can be followed by digits and letters.

A service point can be connected to a meter.

A meter can be added to the inventory using the `addMeter()` method, which receives a serial number, brand, model, and unit of measurement as parameters. The method returns a unique code with the prefix `"MT"`.

It is possible to connect a meter to a service point using the `installMeter()` method, which receives the service point code and the meter code as parameters.

It is possible to get the codes of all service points using the `getServicePoints()` method, which returns a collection of codes. To know the details of a service point, the `getServicePoint()` method can be used, which returns a `ServicePoint` object given the unique code. The `ServicePoint` interface, to be implemented in the solution, provides the following getter methods: `getID()`, `getMunicipality()`, `getAddress()`, `getPosition()`, and `getMeter()`.

To know the details of a meter, the `getMeter()` method can be used, which returns a `Meter` object given the unique code. The `Meter` interface, to be implemented in the solution, provides the following getter methods: `getSN()`, `getBrand()`, `getModel()`, `getUnit()`, and `getServicePoint()`.

## R2. Users and Contracts

Users are the recipients of the services. They can be of two types:

- `ResidentialUser` represents a domestic user. It is characterized by a tax code, first name, last name, physical address, and email address.
- `BusinessUser` represents a commercial user. It is characterized by a VAT number, company name, physical address, and email address.

Both types of users can be registered using the `addUser()` method, which receives the user's data and returns a unique code with the prefix `"U"`.

It is possible to get the codes of all users using the `getUsers()` method, which returns a collection of codes.

To know the details of a user, the `getUser()` method can be used, which returns a `User` object given the unique code.

The `User` interface, to be implemented in the solution,  provides the following getter methods: `getID()`, `getCF()`, `getName()`, `getSurname()`, `getAddress()`, and `getEmail()`.

A service contract can be signed with a user using the `signContract()` method, which receives the user code and the service point code as parameters. The method returns a unique code with the prefix `"C"`.

If the user code or service point code does not exist, or if the service point does not have a connected meter, the method throws a `UtilityException`.

To know the details of a contract, the `getContract()` method can be used, which returns a `Contract` object given the unique code. The `Contract` interface, to be implemented in the solution,  provides the following getter methods: `getID()`, `getUser()`, `getServicePoint()`.

## R3. Readings

Meter readings are periodically taken to calculate a user's consumption.

A reading is recorded using the `addReading()` method, which receives the contract code, meter code, reading date, and the recorded value as parameters. An exception is thrown  if the contract and meter do not match.

Given a contract, it is possible to get all readings using the `getReadings()` method, which returns a map associating the reading date with the recorded value.

Given a contract, it is possible to get the last recorded value using the `getLastReading()` method, which returns the value recorded in the last reading.

## R4. Bills

Starting from a given date, it is possible to get the estimated reading of a contract using the `getEstimatedReading()` method, which receives the contract code and the reference date as parameters.
The reading is estimated as follows:

- if the date corresponds to an actual reading, that reading is returned;
- if the date falls between two readings, the estimate ($\hat y$) is computed through a linear interpolation based on the difference in days:

    $$ \hat y = y_1 + ( t - t_1) \cdot \frac{y_2 - y_1}{t_2 - t_1} $$

    where, $y_1$ and $y_2$ are the readings on the dates $t_1$ and $t_2$ that immediately precedes a nd follow the required date $t$, i.e. $t_1 \lt t \lt t_2$.

- if the date is after the latest date, a linear estimated is computed based on the trend existing between the latest two readings, the formula is the same a above but $y_1$ and $y_2$ are the readints on the latest two available dates $t_1$ and $t_2$.

- if the date is before the first reading or there are not at least two readings, an exception is thrown.

Starting from a given date, it is possible to calculate the consumption of a contract using the `getConsumption()` method, which receives the contract code and the reference date as parameters.
The method throws an `UtilityException` if the contract id is not valid or a reading cannot be estimated for the dates

It is possible to get the breakdown of a bill using the `getBillBreakdown()` method, which receives the contract code and the start and end months as parameters. For each month of the bill, it reports the readings on the 1st of the current month and the 1st of the following month, along with the related consumption. The method returns a list of strings formatted as follows: `"<start-date>..<end-date>: <start-reading> -> <end-reading> = <consumption>"`.


Hint:

- it is possible to use method `toEpochDay()` of class `LocalDate` to get the number of days since 1970-01-01. E.g., `LocalDate.parse("2024-07-24").toEpochDay()` returns 19928.
- Concerning the breakdown, if the dates do not match the exact reading dates, it is necessary to use estimates calculated using the `getEstimatedReading()` method.