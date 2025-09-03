# Kubernetes Configuration Documentation

This directory contains Kubernetes deployment configurations for the microservices in the cloud-native application. Each microservice has its own deployment YAML file, which defines how the service should be deployed in a Kubernetes cluster.

## Microservices Deployment Files

- **users-deployment.yaml**: Configuration for deploying the Users microservice.
- **courses-deployment.yaml**: Configuration for deploying the Courses microservice.
- **enrollments-deployment.yaml**: Configuration for deploying the Enrollments microservice.
- **gateway-deployment.yaml**: Configuration for deploying the API Gateway.
- **mongo-deployment.yaml**: Configuration for deploying the MongoDB database.
- **rabbitmq-deployment.yaml**: Configuration for deploying the message broker.
- **eureka-deployment.yaml**: Configuration for deploying Eureka server.

## Usage

To deploy the microservices, use the following command:

```bash
kubectl apply -f <deployment-file>.yaml
```

Replace `<deployment-file>` with the name of the desired deployment file.

## Notes

Ensure that your Kubernetes cluster is running and configured correctly before applying these configurations. You may also need to set up necessary services, ingress, and persistent storage based on your application requirements.