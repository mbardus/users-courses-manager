# Enrollments Microservice

This microservice is responsible for managing enrollments in courses. It provides RESTful APIs to create, read, update, and delete enrollment records. The service communicates with the Users and Courses microservices to validate user and course information.

## Features

- Create new enrollments
- Retrieve enrollment details
- Update existing enrollments
- Delete enrollments
- Integration with Users and Courses microservices

## Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- MongoDB
- Docker
- Kubernetes
- REST APIs

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker
- Minikube (for local Kubernetes)

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd enrollments-service
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application locally:
   ```
   mvn spring-boot:run
   ```

### Docker

To build the Docker image, run:
```
docker build -t enrollments-service .
```

### Kubernetes

To deploy the service on Kubernetes, apply the deployment configuration:
```
kubectl apply -f ../k8s/enrollments-deployment.yaml
```

## API Endpoints

- `POST /enrollments` - Create a new enrollment
- `GET /enrollments/{id}` - Get enrollment details by ID
- `PUT /enrollments/{id}` - Update an existing enrollment
- `DELETE /enrollments/{id}` - Delete an enrollment

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License.