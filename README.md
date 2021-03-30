# Finartz-Ticket Project

Demo Application for FINARTZ
----------------------------------
This  application is prepared for the "**Finartz**" job task.  This application is a simple web service API for ticketing ticket for airlines.

How to run the project:
-----------------------

On a separate terminal and since this is a maven project you just need to go to the root of the project and perform the command:
```
mvn clean install
```
or if you don't have installed maven on your OS

```
mvnw clan install
```

Since this is a Spring Boot project, you can also run the project with below command;
```
mvn spring-boot:run
```

or if you don't have installed maven on your OS
```
mvnw spring-boot:run
```

You can reach the index page by typing;

*  http://localhost:8080/

The project will run on port 8080 (configured as default).

Collections of POSTMAN
-----------------------
Collections are in /collections folder.

How to get document of API
-----------------------

To access the Swagger UI, just go to;
* http://localhost:8080/swagger-ui.html


To login, below credentials must be written to login screen and then click **Connect**;

| Part  | Input |
| ------------- | ------------- |
| Driver Class  | org.h2.Driver |
| JDBC URL  | jdbc:h2:mem:flight
| User Name | sa  |
| Password   |

All requests and responses can be displayed with select query on **AIRPORT** table;
```
SELECT * FROM AIRPORT;


