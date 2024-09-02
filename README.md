# TXT Connector

## Introduction

TXT Connector is a connector designed to facilitate the construction of dataspaces and the secure exchange of data, guaranteeing data sovereignty.
 <br>
 <br>
It extends the functionality of the Eclipse Dataspace Components (EDC) framework by adding new policies, configuring the Omejdn DAPS, and easing database migration.
<br>

## Getting started

This section will guide you through the necessary steps to configure and start the TXT Connector in a local environment using Docker Compose,including certificate generation and configuration of the **Dynamic Attribute Provisioning Service (DAPS)**.

### Prerequisites

Before starting, make sure you have installed the following tools:

- **Docker**: If you don't already have it, you can download and install Docker from the official site [here](https://www.docker.com/).
- **Docker Compose**: Check that you have Docker Compose installed, it is usually included in Docker Desktop. You can check this with the command: 
    ```bash
    docker-compose --version
    ```
- Git: Needed to clone this repository. You can install it from [here](https://git-scm.com/).
- OpenSSL: Needed to generate certificates.

### Installation

Follow these steps to configure the environment and start the connector.

1. **Clone the repository**
<br><br>Start by cloning this repository in your local environment.
    ```bash
        git clone ...
        cd repo-name
    ```

2. **Generation of certificates**
    <br>
    The connector requires certificates to authenticate with DAPS. Follow the detailed instructions for generating certificates in the [certificate.md](./certificate.md) file.

3. **Set up environment variables**
    <br>
    The connector requires some specific environment variables to function correctly. These are defined in an .env file.
    - Creates an .env file based on the template provided.
        ```bash
            cp .env.example .env
            mv .env /services/connector/launchers
        ```

    - Edit the .env file to configure the necessary parameters. Here are some examples of variables you may need to set:
        ```
        DATABASE_URL=postgres://user:password@localhost:5432/yourdatabase
        DAPS_URL=https://daps.example.com
        ```
        Be sure to replace the example values with those specific to your environment.

4. **Start the connector with Docker Compose**
    <br>
    Once the .env file is configured, you can start the connector using the following command: 
    ```bash
        ./run.sh up
    ```
    This command will start all services defined in the `docker-compose.yml` file.


## Postman collection

The most popular requests, such as contract negotiation, asset retrieval, and data transfer  can be started using the postman collection. This collection contains endpoints designed to manage the entire process, from asset creation to data transfer, making it easy to interact with other connectors within a dataspace.

[Connector API Endpoints.postman\_collection](Connector%20API%20Endpoints.postman_collection.json)


[Demo enviroment.postman\_environment](Demo%20Environment.postman_environment.json)

Make sure to import the environment file as well, as this collection includes preconfigured environments.
