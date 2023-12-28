# Build the JAR in the first stage
FROM maven:3.9-amazoncorretto-21 AS builder

# Make sure project dependencies are cached in separate layers before building
COPY pom.xml ./
RUN mvn verify --fail-never
RUN mvn dependency:resolve-plugins
# Build application
COPY src src
RUN mvn compile assembly:single

# Set up the execution environment in the second stage
FROM amazoncorretto:21

# Copy the necessary files from the previous stage
COPY --from=builder target/temperature-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

ENV CFG_SERVER_PORT=8081
ENV CFG_SERVICE_NAME=se.sinetiq.example:temperature-sensor-rest-json:1.0.0
ENV CFG_SR_HOST=service-registry.sinetiq.net
ENV CFG_SR_PORT=443

# Expose the port on which your application will run
EXPOSE ${CFG_SERVER_PORT}

# The command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
