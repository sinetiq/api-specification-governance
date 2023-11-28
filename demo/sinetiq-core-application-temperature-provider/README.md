# SINETIQ CORE DEMO APPLICATION PROVIDER

## RUN
java -jar target/temperature-provider-1.0.0.jar TemperatureProvider

## BUILD JAVA SPRING APPLICATION
mvn clean package

## BUILD DOCKER IMAGE
docker build -t temperature-provider:1.0 .

## RUN DOCKER IMAGE
docker run -p 8081:8081 temperature-provider:1.0

## RUN DOCKER IMAGE - SPECIFIC VALUES
docker run -p 8082:8082 -e CFG_SERVER_PORT=8082 -e CFG_SERVICE_NAME=TempProvider002 temperature-provider:1.0

## CONFIG
java -jar target/temperature-provider-1.0.0.jar --server.port=8081 --service.name=TempProvider002 TemperatureProvider

Override with application.properties or application.yml within config/ folder.

server.host=localhost
server.port=8080
sr.host=127.0.0.1
sr.port=8500
service.name=TempProvider001
service.type=temperature-sensor-rest-json
service.basePath=base-path-sensor/
spring.jackson.date-format=se.sinetiq.core.demo.application.provider.RFC3339DateFormat
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false
