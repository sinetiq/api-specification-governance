# Welcome to SINETIQ Core <span style="font-size:16px;">- API specification governance</span>

**This repository is a demonstration of how the SINETIQ Core could be integrated and function within an organization to successful handle governance of API specifications in the complete life cycle chain.**

It provides a comprehensive showcase of the SINETIQ Core methodology, focusing on application in an organizational setting for discussion, decision-making, and potential adoption.


## The architecture and concept

See the [Architecture & concept Guide](./README-arch-concept.md) for information about the architectural and conceptual aspects within SINETIQ Core.

### Extensive experience and knowledge

- Achieving faster results through reuse: 240 hours done in 80!
- 20% cost reduction yearly for complex maintenance and changes!
- Improving delivery precision, reducing complexity, and enhancing IT system quality by a factor of 4!
- Align suppliers with existing integrations reduces allocation and decreases product team involvement by 80%, resulting in annual savings of 1,000,000 SEK!
- Incorporating methods and best practices from reference projects since 2003, supported by a 5 billion SEK investment in R&D!

### Our unique approach

We use API specifications as the controlling tool. Each API specification consist of a unique api identifier, within the yaml-specifications we use x-api-identification and mark each api spec. The API specification is completely loosely coupled, no instances or deployment information. The API specification only mange the service as unique information object, ie. not system-api. Each API specification is implemented by one or more Providers and/or Consumers.

In runtime, each Provider publish and set its prescense within the Service Registry, we use Consule in our example but have also used Bind as a strong solution. The registration is the x-api-identifier value together wieh the specific instance endpoint, complete to establish a connection link for exchange information between consumer(s) and provider(s). The consumer(s) search(discover) wanted information sources (providers) by use the x-api-identifier and fetch the matching instances endpoints.

## Getting Started

See the [User Manual](./README-user-manual.md) for an in-depth explanation of all aspects of demo installation, configuration and behavior.

The example ships with [examples](./bitbucket/README.md) which you can run, inspect, & modify.
todo: pipelines, provider + consumer, example-yaml

## How to Build, etc.

See the [Cookbook Guide](./README-build-run.md) for details about modifying the example code, build, test, IDE integration, etc.

## Reach out

Please reach out by contact us throught email [Contact Us](mailto:info@sinetiq.se) for details, discussion, report an bug, request a feature, schedule a demonstration, etc.

## Reference components

- [SINETIQ Core](#link-to-github)
- [API Specification Registry](#link-to-github)
- [API Specification Publication CLI](#link-to-github)
- [SINETIQ Core Service Explorer](#link-to-vscode-marketplace)
