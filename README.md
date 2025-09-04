# Cloud Microservices Application

This project is a cloud-native web application built using a microservices architecture. It consists of three main microservices: Users, Courses, and Enrollments, which communicate via REST APIs and a message broker. The application is developed using Java 17, Spring Boot, Spring Cloud, and MongoDB for the backend, and Angular for the frontend. 

## Project Structure

```
cloud-microservices-app
├── users-service          # Microservice for user management
├── courses-service        # Microservice for course management
├── enrollments-service    # Microservice for enrollment management
├── api-gateway            # API Gateway for routing requests
├── rabbitmq               # Message broker setup
├── mongo                  # Mongo DB config
├── eureka                 # Service discovery
├── common                 # Common configurations
├── frontend               # Angular frontend application
├── k8s                    # Kubernetes deployment configurations
├── .github                # GitHub Actions for CI/CD
├── README.md              # Project documentation
└── docker-compose.yml     # Docker Compose configuration
```

## Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Cloud, MongoDB
- **Frontend**: Angular
- **Containerization**: Docker
- **Orchestration**: Kubernetes (managed locally with Minikube)
- **CI/CD**: GitHub Actions

## Microservices Overview

1. **Users Service**: Manages user data and authentication.
2. **Courses Service**: Handles course information and management.
3. **Enrollments Service**: Manages user enrollments in courses.

## Getting Started

### Prerequisites

- Java 17
- Docker
- Kubernetes (Minikube)
- Node.js and npm (for Angular)
- Maven

### Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   cd cloud-microservices-app
   ```

2. Build and run the microservices using Docker:
   ```
   docker-compose up --build
   ```

3. Access the application through the API Gateway.

4. For local development of the frontend, navigate to the `frontend` directory and run:
   ```
   npm install
   ng serve
   ```

### Deployment

Deployment configurations for Kubernetes are located in the `k8s` directory. Use the provided YAML files to deploy each microservice and the MongoDB instance.

### CI/CD

Continuous Integration and Deployment pipelines are set up using GitHub Actions. Each microservice has its own workflow file located in the `.github/workflows` directory.

## License

This project is licensed under the MIT License. See the LICENSE file for details.