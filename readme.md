
###dependencies
####Spring Boot Starter
Web : starter for building web applications using Spring MVC
uses Tomcat as the default embedded container

Data JPA : starter for using Spring Data JPA with Hibernate

Security : for using Spring Security

Test : starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito

Actuator : starter for using Spring Boot's Actuator which provides production ready features to help you monitor and manage the application
automatically exposes endpoints for metrics out-of-the-box, prefixed with: /actuator
- health : health information
- info : empty by default, info set in application.properties
- mappings : shows a collated list of all @RequestMapping paths

more endpoints: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.endpoints

####Spring Boot
Devtools : Spring boot Developer tools, no more need for restarting project when code gets updated

####PostgreSQL
PostgreSQL JDBC driver

####Hibernate
Validator : hibernate's jakarta bean validation reference implemntation

####Project Lombok
Spice up your java: Automatic Resource Management, automatic generation of getters, setters, equals, hasCode and toString ect.

####JSON Web Token
JJWT 