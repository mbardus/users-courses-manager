# Frontend Microservices Application

This project is a cloud-native web application built using a microservices architecture. It consists of three main microservices: Users, Courses, and Enrollments, which communicate via REST APIs and a message broker. The frontend is developed using Angular.

## Project Structure

- **users-service**: Microservice for user management.
- **courses-service**: Microservice for course management.
- **enrollments-service**: Microservice for enrollment management.
- **api-gateway**: Gateway for routing requests to the appropriate microservices.
- **rabbitmq**: Setup for message brokering between services.
- **frontend**: Angular application for user interaction.
- **k8s**: Kubernetes deployment configurations for all services.
- **.github**: CI/CD workflows for automated deployment.

## Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Cloud, MongoDB
- **Frontend**: Angular
- **DevOps**: Docker, Kubernetes (Minikube), GitHub Actions

## Getting Started

### Prerequisites

- Java 17
- Node.js and npm
- Docker
- Minikube
- MongoDB

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd cloud-microservices-app
   ```

2. Navigate to the frontend directory and install dependencies:
   ```
   cd frontend
   npm install
   ```

3. Build and run the backend services using Docker:
   ```
   cd users-service
   docker build -t users-service .
   cd ../courses-service
   docker build -t courses-service .
   cd ../enrollments-service
   docker build -t enrollments-service .
   ```

4. Start Minikube and deploy the services:
   ```
   minikube start
   kubectl apply -f k8s/
   ```

5. Run the Angular application:
   ```
   ng serve
   ```

### CI/CD

The project includes CI/CD pipelines configured with GitHub Actions for automated testing and deployment of each microservice and the frontend application.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License.