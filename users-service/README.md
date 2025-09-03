# Users Microservice

This is the Users microservice of the cloud-native application built using a microservices architecture. It is responsible for managing user-related operations such as creating, updating, retrieving, and deleting user information.

## Technologies Used

- **Java 17**: The programming language used for backend development.
- **Spring Boot**: Framework for building the microservice.
- **Spring Cloud**: For cloud-native features and service discovery.
- **MongoDB**: NoSQL database for storing user data.
- **Docker**: For containerization of the microservice.
- **Kubernetes**: For orchestration and management of containerized applications.

## Project Structure

- **src/main/java/com/example/users**: Contains the main application code.
  - **controller**: Handles HTTP requests and responses.
  - **service**: Contains business logic for user management.
  - **model**: Represents user entities.
  - **repository**: Interfaces for database operations.
- **src/main/resources**: Contains configuration files.
  - **application.yml**: Configuration properties for the microservice.
  - **bootstrap.yml**: Spring Cloud configuration.

## Running the Application

1. **Build the Docker Image**:
   Run the following command in the `users-service` directory:
   ```
   docker build -t users-service .
   ```

2. **Run the Docker Container**:
   ```
   docker run -p 8080:8080 users-service
   ```

3. **Access the API**:
   The Users microservice can be accessed at `http://localhost:8080/api/users`.

## API Endpoints

- **POST /api/users**: Create a new user.
- **GET /api/users/{id}**: Retrieve user details by ID.
- **PUT /api/users/{id}**: Update user information.
- **DELETE /api/users/{id}**: Delete a user.

## CI/CD

This microservice is integrated with GitHub Actions for continuous integration and deployment. The workflow is defined in the `.github/workflows/users-ci.yml` file.

## License

This project is licensed under the MIT License.