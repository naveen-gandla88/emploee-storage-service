# emploee-storage-service

# Technologies used to develop the services:

Java8

Springboot

Spring security

Spring Data JPA and Hibernate

Restful web services

H2 Database

Maven

Swagger API Documentation

IDE (STS)

How run in any IDE for ex: Eclipse, STS or IntelliJ

1. Import project folder( employee-storage-service) to your IDE

2. Run "mvn clean install"
     
3. Run EbfEmployeeServiceApplication.class (Right click and Run as spring boot App)

Application should start and running with port 8081.

All Rest API’s are documented using Swagger Documentation.

Swagger documentation can be accessed using the below link 
http://localhost:8081/swagger-ui/

Rest API's are secured with Authentication 

Credentials are: 
Username is: Admin
Password is: Admin123

Once login using above credentials, access Swagger-ui and able to be run all rest endpoints.

I have used In memory database here. Database can be accessed using the link: http://localhost:8081/h2-console/ 

Credentials are: 

Username is:  ebfds
Password is: ebf123
Data source url is: jdbc:h2:mem:emptest

Database properties are configured in application.properties file. 



