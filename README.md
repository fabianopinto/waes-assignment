# WAES Assignment - Scalable Web

WAES Assignment to show coding and software engineering skills.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run this software, you need to have the following components properly installed:

* [Open JDK 8](http://openjdk.java.net/install/) or similar
* [Apache Maven 3.5.2](https://maven.apache.org/download.cgi) or superior
* [MongoDB Community Server 3.6.3](https://www.mongodb.com/download-center?jmp=nav#community) or superior
* Optionally, [Postman 6.1.4](https://www.getpostman.com/apps) or superior
* Optionally, [curl 7.58.0](https://curl.haxx.se/) or similar

### Installing & Running

Download a local copy of the project using the following command:

```
git clone https://github.com/fabianopinto/waes-assignment.git 
```

To build this software and store it in your local Maven repository use the following command:

```
mvn clean install -U 
```

To run this software in DEVELOPMENT mode, use the following command:


```
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```

To run this software in PRODUCTION mode additional settings are needed in the `src/main/resources/application.properties` file, especially the parameters for connecting to MongoDB.

## Running the tests

To run unit and integration tests use the following command:

```
mvn test
```

## Web API details

The following endpoints are available:

URL | Method | Payload | Description
--- | --- | --- | ---
`<host>/v1/diff/<ID>/left` | POST | First JSON Base64 data file  | Processes and stores first file
`<host>/v1/diff/<ID>/right` | POST | Second JSON Base64 data file | Processes and stores second file
`<host>/v1/diff/<ID>` | GET | -none- | Compares received files and processes DIFF
`<host>/v1/diff/<ID>` | DELETE | -none- | Deletes the data specified by ID

Additionally the following end-points are available for code instrumentation:

URL | Description
--- | ---
`<host>/actuator` | Code instrumentation end-points
`<host>/actuator/auditevents` | 
`<host>/actuator/beans` | Event audit
`<host>/actuator/health` | System health
`<host>/actuator/conditions` | System conditions details
`<host>/actuator/configprops` | System configuration details
`<host>/actuator/env` | Dumps full system environment
`<host>/actuator/env/<name>` | Dumps system environment property
`<host>/actuator/info` | Lists system-specific information
`<host>/actuator/loggers` | Lists available loggers
`<host>/actuator/loggers/<name>` | Logger details by name
`<host>/actuator/heapdump` | Download heap memory
`<host>/actuator/threaddump` | Dumps threads details
`<host>/actuator/metrics` | Dumps system metrics
`<host>/actuator/metrics/<name>` | Dumps system metrics by name
`<host>/actuator/scheduledtasks` | Lists system scheduled tasks
`<host>/actuator/httptrace` | Traces HTTP requests 
`<host>/actuator/mappings` | List end-point mappings details

For security reasons, in the production environment only the end points `health` and 'info` are made available.

## Suggestions for improvement

Aiming vertical scalability there are two main alternatives:

1. Scalability on web server - You will need some "stick server" strategy, so that requests from the same client will always be answered by the same server. The strategy may consider the source IP or be a random choice and maintained during the session.
2. Scalability in the database - Some kind of synchronization between DB instances is required so that different servers can service any client because the data will be shared.

Other possibilities for extension:

1. Implement the specification more rigidly by validating the structure of headers and data records. The current implementation is more loose considering the final JSON object data structure. The rules about headers and type conversions aren't considered, so any valid JSON structure will be correctly parsed.
2. The receipt of the payload by the controller can be implemented using input streams, in this way the use of resources will be more efficient.

## Author

**Fabiano da Silveira Pinto** - [fabianopinto@gmail.com](fabianopinto@gmail.com)

## Acknowledgments

I would like to thank WAES for this opportunity.
