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

### External Rest Server

The sibling directory "rest" exists to run a dummy rest server. In order to setup and run, please refer to its README.md. Some of the use cases presented in this "code" directory depend on "rest" service

## Examples:

### MID-162 - Camunda SCADA Interface Out Example 1

BPM: customer_order.bpmn

TODO: 

- Implement test case
- Parse date and time stamp
- Pass stored unique ID from DB back to service task

### MID-163 - Camunda SCADA Interface Out Example 2

BPM: count_orders.bpmn

### MID-164 - SCADA Interface In Example

BPM: update_orders.bpmn

### MID-165 OutSystems Order Validation

BPM: orders_validation.bpmn

### MID-166 OutSystems Order Placement Mockup

BPM: orders_workflow.bpmn

### MID-167 File Drop Interface IN Example

Monitor file system for changes and load the CSV into memory. There is also a test case DataUploadTest to confirm that the CSV is uploaded and saved in the DB.