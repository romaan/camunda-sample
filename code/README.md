# MIL Integration Code

### Minimum requirements

- Java 8
- Maven 3.5
- Camunda modeller https://camunda.org/download/modeler/


### How to start:

- [Setup a basic maven project](https://www.mkyong.com/maven/how-to-create-a-project-with-maven-template/)
- [Modify the POM.xml](https://docs.camunda.org/get-started/spring-boot/project-setup/)
- Run the spring boot project
```sh
mvn spring-boot:run -Dspring.profiles.active=dev
```

Now access http://localhost:8080 should lead you create user page and explore more

### How to setup Development environment

- Open this project in your favorite IDE (My preference is IntelliJ IDEA)
- Configure the project to run the development profile

NOTE: The development version uses H2 database, for more development configuration please refer to resources/config/application-dev.yml

In POM.xml you will find camunda-bpm-spring-boot-starter-webapp:2.3.0 and spring-boot-dependencies:2.3.0. This information comes from the [compatibility table](https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/version-compatibility/)

### Camunda Rest Service

Add the dependency: camunda-bpm-spring-boot-starter-rest to POM.xml

Visit the [URL to ensure REST service is working](http://localhost:8080/rest/engine) 