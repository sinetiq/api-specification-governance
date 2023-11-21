## Demonstration Setup

The environment for this demonstration is managed through a client computer with specific components downloaded:
- **CLI Component**: Deploy approved API specifications to the API specification repository.
- **Pipeline**: Download and use the pipeline for your environment, example Azure, Bitbucket, Github and Jenkins.
- **Service Explorer**: Explore approved API specifications and active API instances within the service registry.
- **API specification repository**: Download and deploy docker container for the API specification repository.
- **Service Registry (Consul from Hashicorp)**: Map API specifications and endpoints for establishing connections.

![Demo setup](demo-image "Demo setup")

## Installation Steps

1. **Download CLI**: Obtain from `sinetiq.se/link`. Configure the CLI for your GIT repository containing API specifications (YAML files).
2. **Visual Code Setup**: Download and configure Visual Studio Code as your demonstration editor. Install the SINETIQ Core Service Explorer plugin from the Visual Studio Code marketplace.
3. **Consul as Docker Container**: Download and initiate Consul in a Docker container.

   `> docker run consul`

_Your demonstration environment is now ready._

## Usage

- **Create API Specification**: Use your preferred editor to create a YAML file for the API specification and store it in your CLI-connected repository. Ensure each specification has an API identifier (`x-api-identifier`) and a unique name.
- **Publish API Specification**: Utilize the approval CLI to publish your API specification to the repository.
- **Verification**: Check the API specification registry UI to confirm the correct approval and deployment of your API specifications.
- **Application Development**: Develop applications, both producers and consumers, that comply with the API specification. Producers should publish endpoints with the unique API identifier, and consumers should discover and connect to producers using the same identifier.
- **Deployment**: Launch your application components.
- **Verification with Service Explorer**: Utilize Visual Studio Code and the Service Explorer to list, verify, and test the existing producers and their API instances.
