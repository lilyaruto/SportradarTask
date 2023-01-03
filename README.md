# Sportradar Task

Author: Yaroslav Vovkotrub

## How to build and run app?

1. Open folder sportradar-task in your IDE.
2. Download JDK (if nedded).
3. Run SportradarTaskApplication.java.
4. Open your browsera and search [http://localhost:8080/](http://localhost:8080/).
5. Click on one of the links ("Most probable events" redirect you to [http://localhost:8080/events](http://localhost:8080/events); "All competitors" to [http://localhost:8080/competitors](http://localhost:8080/competitors)).

## Additionally

If you want to check how database looks like:

1. Search [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
2. Feel fields like this:

	Driver Class: org.h2.Driver
	JDBC URL: jdbc:h2:file:./data/data
	User Name: yaruto
3. Press "Connect".

To change number of printed events you should change parameter int-value in EventController.java, method start(), line 25.

## Comments

The task was implemented mainly using Java 17, Spring Boot and H2 Database.
The main idea was to read JSON file with JSONPath and write it to database.
Application was built using MVC architecture.
In service-classes were created methods for alphbetical sorting, sorting by highest probability value and method that allows to change size of result list.
There were created controller-classe that are responsible for providing lists with objects to web representation.
Also I have added bootstrap links to my html files in order to make tables look better.

## Used dependencies

- Spring Boot Starter Web;
- Spring Boot Starter Data JPA;
- Spring Boot Starter Validation;
- Spring Boot Devtools;
- Spring Boot Configuration Processor;
- Spring Boot Starter Test;
- Spring Boot Starter Thymeleaf;
- H2 Database;
- Lombok;
- Jayway JSONPath.