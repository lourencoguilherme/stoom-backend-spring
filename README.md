# Before You Start

### Guides
The following guides illustrate how to use some features correctly:
* [REST API Local Documentation](http://localhost:8080/api/swagger-ui.html)

### Build project

``
 mvn clean install
``

### Start project
``
java -jar backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=test & disown
``

### Docker project
``
docker build -t gui/spring-gml .

docker run -d -p 8080:8080 -t gui/spring-gml
``