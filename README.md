# API specification governance

**This repository provides a comprehensive showcase of how the Sinetiq Framework could be used to successful handle `governance of API specifications` in the complete life cycle chain.**

The covered processes are:

- API **creation** and **approval**; how to create and approve an API specification.
- API **implementation**; how to implement an API specification within a Provider or Consumer component.
- API **deployment**; how to deploy a provider, or consumer, component instance and its API instance(s).
- API **compliancy** test and **verification**; how to verify and test a provider API instance API specification implementation.

## Getting Started

This section will give an in-depth explanation of components used, the `demonstration setup` and hands-on step-by-step `installation guide`. If preferred you can step to the [Usage](#usage) section directly.

The demonstration showcase also ships with hands-on code `examples` and components, to run, inspect & modify.

### Prerequisites

Before proceeding, ensure that you have the following installed on your local machine:

## Docker & Docker Compose

- Docker: Docker is necessary for containerizing your application and its dependencies. You can download and install Docker from here.
- Docker Compose: Docker Compose is used to define and run multi-container Docker applications. It allows you to manage services such as databases or back-end systems in a single file. Docker Compose is bundled with Docker Desktop, but you can confirm the installation by running:
`docker-compose -- version`

## Node.js
Node.js: Ensure you have Node.js installed, as it will be required for running JavaScript on the server side. You can download Node.js from here.
Verify the installation by running:
`node --version`
`npx --version`

### Demonstration Setup

The environment for this demonstration is managed through a client computer and components listed below.

- **API specification registry**: The `Sinetiq API Specification Registry` holds approved and published API specifications and maps them to a unique and immutable identifier

- **CLI tool**: The `api-specification-registry-cli` is used to publish approved API specifications to the API specification reqistry.

- **Pipeline examples**: Download and use the `pipeline example` matching your environment, example Azure, Bitbucket, Github and Jenkins. The pipeline examples uses the `CLI tool`, enabling a complete devops (CI/CD) process, to review, approve and publish the api specification to the _API specification repository_.

- **Service Registry** (Consul from Hashicorp): An active service registry for mapping API specification identifiers and endpoints, used to establishing right connections for wanted information exchange.

- **Service Explorer**: List and view approved API specifications, published within the API specification registry, together with active provider API instances, published within the service registry. The `Sinetiq Service Explorer` is a VSCode extension that helps you find service instances, their specifications and verify the implementaition while developing.

![image](/docs/media/api-governance-processes.png)

### Installation Guide

#### Registry services

This will start the registry components, `API specification registry` and `Service Registry`.

1. Fetch the demonstration github repository.

   ```sh
   git clone git@github.com:sinetiq/api-specification-governance.git
   ```

2. Change directory to the root git folder where the docker-compose.yml can be found, run the docker command.

   ```sh
   cd api-specification-governance

   docker-compose up
   ```

#### Service explorer

Download and configure Visual Studio Code as your demonstration editor. Install the SINETIQ Service Explorer plugin. Follow the readme at the visual studio code marketplace [https://marketplace.visualstudio.com/items?itemName=Sinetiq.service-explorer](https://marketplace.visualstudio.com/items?itemName=Sinetiq.service-explorer).

Note: Modify the configuration of the `Service Explorer` to use your local registries.  The default URLs for the local registies are:
- Base URL of Sinetiq API Specification Registry: `[http://localhost:3333](http://localhost:3333/v1/)`
- Base URL of HashiCorp Consul instance: `[http://localhost:8500](http://localhost:8500/)`

_Your demonstration environment is now ready._

#### Verify installation

1. Verify the API Specification Registry.  
   Open a web browser, use url [http://localhost:3333](http://localhost:3333) to verify up-n-running.

2. Verify Service Registry.  
   Open a web browser, use url [http://localhost:8500](http://localhost:8500) to verify up-n-running.

3. Verify Service Explorer.  
   Open Visual Code, verify the `Service Explorer` according to user manual.

## Usage

The steps 1 to 5 will show how to run this demonstration and successfully manage `governance of API specifications` in the complete life cycle chain.

![image](/docs/media/api-governance-processes-steps.png)>

### Step 1: Create API Specification

Use your preferred editor to create a YAML file for the API specification and store it in your versioning repository (ex. git). An example specification used in the demonstration is provided in this repository as `temperature-api.yaml`.

```yaml
openapi: 3.0.0
info:
  description: This is a simple API for accessing temperature sensor data.
  title: Temperature Sensor API HTTP(S)-JSON
  version: 1.0.0
paths:
  /temperature:
    get:
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/TemperatureData"
          description: Temperature data
      summary: Get current temperature data
      x-accepts: application/json
```

### Step 2: Review and Publish API Specification

After the API specification is done, review it according to your organizations' best practices. Preferably, this is done using a pull request that once merged runs a pipeline to publish approved specifications to the registry. This repository contains configuration examples of the most common pipeline providers for this workflow.

For demonstration purposes you can use the CLI tool directly to publish the specification to your local registry.

```sh
 npx @sinetiq/api-specification-registry-cli \
    -g se.sinetiq.example \
    -a temperature-sensor-rest-json \
    -v 1.0.0 \
    -r http://localhost:3333/ \
    --ignore-already-registered \
    --open-api ./temperature-api.yaml
```

### Verification

Go to the service registry at [http://localhost:3333](http://localhost:3333) and locate your API specification in the list of published specifications.

### Step 3: Application Development

Develop applications, both providers and consumers, that comply with the API specification. Provider should publish endpoints with the unique API identifier, and consumers should discover and connect to providers using the identifier.

For example, the service provider(s) publish the endpoint and specifies the unique API identifier, in this example `se.sinetiq.example:temperature-rest-json:1.0.0`.

```java
    ServiceData sd = new ServiceData();
    sd.setHost(serviceProperties.getAdvertisedAddress());
    sd.setPort(serviceProperties.getAdvertisedPort());
    sd.setName('se.sinetiq.example:temperature-rest-json:1.0.0');
    sd.getProperties().put("path", serviceProperties.getBasePath());
    try {
        consulAPI.registerService(sd);
    } catch (NotRegisteredException e) {
        // Handle error state
    }
```

_See complete Provider example code [here](./example-temperature-provider/)._

### Step 4: Launch provider

Launch your application components with their API implementation to operating instances.

```sh
cd example-temperature-provider
docker-compose up
```

### Step 5: Verification with Service Explorer

Utilize Visual Studio Code and the Service Explorer to list, verify, and test the existing Providers and their API instances.

![Service explorer](./docs/media/service-explorer-preview.gif)

1. Explore API specifications within the `API specification registry`
2. Explore the `API instance registry`
3. Choose one API instance and test the provider API implementation
   a. Send a request and examine the response

## Reach out

Please reach out by contact us through email [Contact Us](mailto:info@sinetiq.se) for details, discussion, report an bug, request a feature, schedule a demonstration, etc.

Complete product documentation can be found at hour home page [Product Documentation](http://www.sinetiq.se) by registering to our Sinetiq Framework interest form.
