FROM maven:3.3.9 AS builder

# Make sure project dependencies are cached in a separate layer before building
COPY pom.xml ./
RUN mvn verify --fail-never
# Build application
COPY src src
RUN mvn -DskipTests=true package

WORKDIR application

# Second stage to create a minimal and secure image
FROM openjdk:11-jre-slim

WORKDIR application

# Copy the necessary files from the previous stage
COPY --from=builder target/temperature-provider-1.0.0.jar temperature-provider-1.0.0.jar

ENV CFG_SERVER_PORT=8081
ENV CFG_SERVICE_NAME=TempProvider001
ENV CFG_SR_HOST=service-registry.sinetiq.net
ENV CFG_SR_PORT=443

# Expose the port on which your application will run
EXPOSE ${CFG_SERVER_PORT}

# The command to start the application
ENTRYPOINT ["java", "-jar", "temperature-provider-1.0.0.jar", \
            "--server.port=${CFG_SERVER_PORT}", \
            "--service.name=${CFG_SERVICE_NAME}", \
            "--sr.host=${CFG_SR_HOST}", \
            "--sr.port=${CFG_SR_PORT}"]
