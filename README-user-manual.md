## Demonstration Setup

The environment for this demonstration is managed through a client computer with specific components downloaded:
- **CLI Component**: Deploy approved API specifications to the API specification repository. CLI tool for publishing API specifications to a Sinetiq API Specification Registry.
- **Pipeline examples**: Download and use the pipeline example matching your environment, example Azure, Bitbucket, Github and Jenkins. The pipeline uses the CLI component to publish the api specification to the API specificationo repository.
- **Service Explorer**: Explore approved API specifications and active API instances within the service registry. The Sinetiq Service Explorer is a VSCode extension that helps you find service instances and their specifications while developing.
- **API specification repository**: Download and deploy docker container for the API specification repository. The Sinetiq Service Type Registry consist of published and approved api specification, mapping the identifier. TODO: describe domain/group.
- **Service Registry (Consul from Hashicorp)**: Map API specifications and endpoints for establishing connections. 

<img src="./docs/img/SITCore-processes.png" width="600">

## Installation Steps

1. **Download CLI component api-specification-registry-cli**: Obtain from [https://www.npmjs.com/package/@sinetiq/api-specification-registry-cli](https://www.npmjs.com/package/@sinetiq/api-specification-registry-cli). Configure the CLI for your GIT repository containing API specifications (YAML files).
   
   `> npm i @sinetiq/api-specification-registry-cli`

2. **Service explorer**: Download and configure Visual Studio Code as your demonstration editor. Install the SINETIQ Core Service Explorer plugin from the Visual Studio Code marketplace.
Use built in extension in vscode or at the command line, find the sinetiq.serivce-explorer extension.

   `> code --install-extension sinetiq.service-explorer`

1. **Registry services**: Download and start docker-compose file to start the API specification registry and Service Registry. The Sinetiq API specification registry consist of published and approved api specification, mapping the identifier. TODO: describe domain/group. The Service Registry consist of the api instances, endpoints mapped with their unique identifiers.

   [Download Docker compose file](./docker-compose.yml)

   `> docker compose up` 

_Your demonstration environment is now ready._

## Usage

- **Create API Specification**: Use your preferred editor to create a YAML file for the API specification and store it in your CLI-connected repository. Ensure each specification has an API identifier (`x-api-identifier`) and a unique name. TODO: Link to concept?! Or do we build this string in the api spec reg??

- **Publish API Specification**: Utilize the approval CLI to publish your API specification to the repository.
  
- **Verification**: Check the API specification registry UI to confirm the correct approval and deployment of your API specifications.

- **Application Development**: Develop applications, both producers and consumers, that comply with the API specification. Producers should publish endpoints with the unique API identifier, and consumers should discover and connect to producers using the same identifier.

- **Deployment**: Launch your application components.

- **Verification with Service Explorer**: Utilize Visual Studio Code and the Service Explorer to list, verify, and test the existing producers and their API instances.

## Reference components

- [SINETIQ Core](#link-to-github)
- [API Specification Registry](#link-to-github)
- [API Specification Publication CLI](#link-to-github)
- [SINETIQ Core Service Explorer](#link-to-vscode-marketplace)
