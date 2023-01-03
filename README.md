# Sportradar Task

Author: Yaroslav Vovkotrub

## Comments

The task was implemented mainly using Java 17, Spring Boot and H2 Database.
The main idea was to read JSON file with JSONPath and write it to database.
Application was built using MVC architecture.
In service-classes were created methods for alphbetical sorting, sorting by highest probability value and method that allows to change size of result list.

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