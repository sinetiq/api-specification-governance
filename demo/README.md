# Demo instructions
## Running the demo services
For the demo scenarios, the Sinetiq Framework "control plane" with infrastructure services needs to be running:
```bash
docker-compose -f compose-control-plane.yml up --build
```
(Will take quite some time first time)

With the control plane active, time to set up some demo scenarios:
### Scenario 1: Standalone ticker
This scenario starts up two temperature producers and one temperature consumer.
The consumer will report temperature readings to the logs.
```bash
docker-compose -f compose-demo-standalone.yml up --build
```
(Same here, takes a while to build first time you run the command)

_Observing late binding behaviour_

The consumer binds to providers as late as possible. By taking the providers down and up,
you can see the consumer switching between them by checking the log messages. It will always pick the first provider that is running.
```bash
docker-compose -f compose-demo-standalone.yml down temp-provider-1
docker-compose -f compose-demo-standalone.yml up temp-provider-1
# ...etc
```

### Scenario 2: Exploring the service registry using the vscode Service Explorer
In this scenario, we connect to the producers running in the local cloud from vscode running on the host machine.
1. Register the temperature API with the **API Specification Registry**:
   ```bash
   npx @sinetiq/api-specification-registry-cli \
      -g se.sinetiq.example \
      -a temperature-sensor-rest-json \
      -v 1.0.0 \
      -r http://localhost:3333/ \
      --open-api ./temperature-api.yaml
   ```
2. Start the producers with the (compose-demo-extern.yml)[./compose-demo-extern.yml] instead:
    ```bash
    docker-compose -f compose-demo-extern.yml up --build
    ```
3. Configure the vscode Service Explorer to attach to the **Service Registry** using http://localhost:8500/v1
4. Configure the vscode Service Explorer to attach to the **API Specification Registry** using http://localhost:3333/v1
5. Explore the service registry inside the vscode Service Explorer!
6. Remember to try to make some calls via the Service Explorer UI.

# System overview
The Docker Compose project in this folder starts up some infrastructure for the control plane:
* An API Specification Registry
* A database for persisting the API specifications (for proper persistance a volume needs to be set up,
  or direct to an external Postgres database)
* A Service Registry

In addition, some example systems utilizing service discovery in their operation are included:
* Two instances of a temperature sensor data provider [spec](sinetiq-core-application-temperature-provider/src/main/resources/openapi.yaml)
* A consumer of the service provided by these two providers. This consumer does not start by default, see above