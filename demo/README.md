# Demo instructions
## Running the demo services
```bash
docker-compose up --build
```
(Will take quite some time first time)

For some event reporting in the docker logs, you can start a demo consumer:
```bash
docker-compose up --build temp-consumer
```
(Same here, takes a while to build first time you run the command)

## Observing late binding behaviour
The consumer that can be started separately binds to providers as late as possible. By taking the providers down and up,
you can see the consumer switching between them by checking the log messages. It will always pick the first provider that is running.
```bash
docker-compose down temp-provider-1
docker-compose up temp-provider-1
# ...etc
```
## Gateway
Optionally, you can attach the Sinetiq Gateway to the docker network to allow access to the Service Registry and
its registered endpoints from an external network (such as the docker host machine)
Contact Sinetiq for details on this setup.

## Using the vscode Service Explorer
(Currently not working)

# System overview
The Docker Compose project in this folder starts up some infrastructure for the control plane:
* An API Specification Registry
* A database for persisting the API specifications (for proper persistance a volume needs to be set up,
  or direct to an external Postgres database)
* A Service Registry

In addition, some example systems utilizing service discovery in their operation are included:
* Two instances of a temperature sensor data provider [spec](sinetiq-core-application-temperature-provider/src/main/resources/openapi.yaml)
* A consumer of the service provided by these two providers. This consumer does not start by default, see above