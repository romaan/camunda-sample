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

In a human task, the following data is entered into a form:

- Outlet ID
- Start Time
- End Time
- Flow Rate

The single record is saved to a customer_orders DB table and returned - Order ID

The single record is sent to an external REST API on the SCADA system

TODO: 

- Complete test case with mocking external HTTP call

### MID-163 - Camunda SCADA Interface Out Example 2

BPM: count_orders.bpmn

Entry point 1: An external system calls a REST API in Camunda with parameters:
- Start Time
- End Time

Entry point 2: A scheduled process triggers once an hour at XX:59:00 and determines the following:
- Start Time = The coming hour roll-over (i.e the time in 1 minute; 10:00:00 if triggered at 09:59:00)
- End Time = Start Time + 1 hour

The orders that fall between Start Time and End Time are retrieved from the customer_orders DB table

Those orders are sent to the same SCADA REST API as in Workflow #1 (You should decide whether it is preferable to iterate the records and sent them individually (in production there may be as many as 150 per batch) or to send them as a single payload. If a single payload, the SCADA API will need to be updated or a new one written)

To trigger a REST call make the following REST API call:

POST: http://localhost:8080/rest/message
```sh
{
	"messageName":"GetOrders",
	"processVariables": {
    	"startTime":{
        	"value": "2013-02-12T23:00:00.000Z",
        	"type": "string"
    	},
       "endTime":{
        	"value": "2015-02-12T23:00:00.000Z",
        	"type": "string"
    	}
	}
}
```

### MID-164 - SCADA Interface In Example

An external system (SCADA) calls a REST API in Camunda with parameters:
- Order ID (optional)
- Outlet ID
- Value (INT)
- Time

Camunda updates the structure_flowmeter DB table for the supplied Outlet ID as follows (insert a row if one does not exist):
- previous_value = current_value
- previous_value_dt = current_value_dt
- current_value = Value
- current_value_dt = Time
- qty_delivered = current_value - Value (or current_value - previous_value)

If Order ID is supplied, Camunda updates the customer_orders DB table for the supplied Order ID and Outlet ID as follows:
- qty_delivered = qty_delivered + (qty_delivered as calculated in the previous step)
- qty_delivered_dt = Time

BPM: update_orders.bpmn

POST http://localhost:8080/rest/message
```sh
{
	"messageName":"OrderUpdateEvent",
	"processVariables": {
        "outletID":{
            "value": "12312A",
            "type": "string"
        },
        "time":{
            "value": "2015-02-12T05:00:00.000Z",
            "type": "string"
        },
        "value": {
            "value": "12",
            "type": "integer"
        }
	}
}
```

### MID-165 OutSystems Order Validation

BPM: orders_validation.bpmn

### MID-166 OutSystems Order Placement Mockup

This functionality will eventually be created in OutSystems, but for demonstration (to run   MID-165 TO DO OutSystems Order Validation Example) and templating purposes, can be created here.

A User task UI requests the following information from a user:
- Outlet ID
- Start Time
- End Time
- Flow Rate

The data is written to the CUSTOMER_ORDERS table in the database. An Order ID is returned as the Identity PK of the inserted record.

An "Order Submitted" UI is loaded confirming the order details and giving the Order ID. The Status is listed as "Processing".

The workflow pauses at this point pending confirmation/rejection of the order from the  MID-165 TO DO  OutSystems Order Validation Example workflow (true/false).
- If True:
Display a success page confirming the order details.
- If false:
Display a failure message and a link back to the order placement page.

BPM: orders_workflow.bpmn

### MID-167 File Drop Interface IN Example

Monitor file system for changes and load the CSV into memory. There is also a test case DataUploadTest to confirm that the CSV is uploaded and saved in the DB.

## Camunda Rest

Get Engine Name:
 - http://localhost:8080/rest/engine
 
 
