# Cloud Microservices Application - API Gateway

This document provides an overview of the API Gateway microservice in the Cloud Microservices Application project.

## Overview

The API Gateway serves as the single entry point for all client requests to the microservices. It handles routing, composition, and protocol translation, allowing clients to interact with the Users, Courses, and Enrollments microservices seamlessly.

## Features

- **Routing**: Directs incoming requests to the appropriate microservice based on the request path.
- **Load Balancing**: Distributes incoming requests across multiple instances of microservices.
- **Security**: Implements authentication and authorization mechanisms to secure access to microservices.
- **Monitoring**: Provides insights into the performance and health of the microservices.

## Technologies Used

- **Java 17**: The programming language used for developing the microservice.
- **Spring Boot**: Framework for building the microservice.
- **Spring Cloud**: Provides tools for microservices architecture, including service discovery and configuration management.
- **Docker**: Containerization technology used to package the microservice.
- **Kubernetes**: Orchestration platform for managing containerized applications.
- **MongoDB**: NoSQL database used for data storage.

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker
- Kubernetes (Minikube)
- GitHub Actions for CI/CD

### Building the Project

To build the API Gateway microservice, navigate to the `api-gateway` directory and run:

```
mvn clean install
```

### Running the Microservice

To run the API Gateway locally, use the following command:

```
mvn spring-boot:run
```

### Docker

To build the Docker image for the API Gateway, run:

```
docker build -t api-gateway .
```

### Kubernetes Deployment

Refer to the `k8s/gateway-deployment.yaml` file for deployment configuration in a Kubernetes cluster.

## CI/CD

The CI/CD pipeline for the API Gateway is configured using GitHub Actions. Changes pushed to the main branch will trigger the pipeline defined in `.github/workflows/gateway-ci.yml`.

## Conclusion

The API Gateway is a crucial component of the Cloud Microservices Application, providing a unified interface for clients and facilitating communication between microservices. For more information, refer to the documentation of the individual microservices.