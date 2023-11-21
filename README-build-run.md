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



## References

- [SINETIQ Core](#link-to-github)
- [API Specification Registry](#link-to-github)
- [API Specification Publication CLI](#link-to-github)
- [SINETIQ Core Service Explorer](#link-to-vscode-marketplace)
