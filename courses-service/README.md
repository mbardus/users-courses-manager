# Courses Microservice

This microservice is responsible for managing courses in the cloud-native application. It provides RESTful APIs for creating, retrieving, updating, and deleting courses. The service is built using Spring Boot and communicates with a MongoDB database for data persistence.

## Features

- **Course Management**: Create, read, update, and delete courses.
- **RESTful API**: Exposes endpoints for interaction with the courses data.
- **MongoDB Integration**: Uses MongoDB for storing course information.
- **Microservices Architecture**: Part of a larger microservices ecosystem, communicating with other services like Users and Enrollments.

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker
- MongoDB

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the courses-service directory:
   ```
   cd courses-service
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

### Docker

To build and run the Docker container:

1. Build the Docker image:
   ```
   docker build -t courses-service .
   ```

2. Run the Docker container:
   ```
   docker run -p 8080:8080 courses-service
   ```

### API Endpoints

- `POST /courses`: Create a new course.
- `GET /courses`: Retrieve all courses.
- `GET /courses/{id}`: Retrieve a specific course by ID.
- `PUT /courses/{id}`: Update a specific course by ID.
- `DELETE /courses/{id}`: Delete a specific course by ID.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bugs.

## License

This project is licensed under the MIT License. See the LICENSE file for details.