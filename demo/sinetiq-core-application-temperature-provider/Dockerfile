FROM maven:3.9-amazoncorretto-21 AS builder

# Make sure project dependencies are cached in a separate layer before building
COPY pom.xml ./
RUN mvn verify --fail-never
# Build application
COPY src src
RUN mvn -DskipTests=true package

WORKDIR application

# Second stage to create a minimal and secure image
FROM amazoncorretto:21

WORKDIR application

# Copy the necessary files from the previous stage
COPY --from=builder target/temperature-provider-1.0.0.jar temperature-provider-1.0.0.jar

# Expose the port on which your application will run
EXPOSE ${CFG_SERVER_PORT}

# The command to start the application
ENTRYPOINT ["java", "-jar", "temperature-provider-1.0.0.jar"]
