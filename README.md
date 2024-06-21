# Spring Boot + React Application

This project combines a Spring Boot backend with a React frontend.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 11 or higher
- Node.js (for npm package management) recommended Node 16 or have nvm
- MySQL database
- Docker (optional, for running the application in Docker)

## Running Locally
1. **Clone the repository:**

```bash
git clone https://github.com/YoNightFury/campus-placement-secured
cd campus-placement-secured
```


### Configuration

- **Customizing Ports:**

  Modify `application.properties` and `package.json` for backend and frontend ports respectively.

  - **Database Configuration:**

    Update `application.properties` for database connection details. Modify the following properties in `application.properties` to configure the database connection:

    - `spring.datasource.url`: JDBC URL of the database.
    - `spring.datasource.username`: Username for database authentication.
    - `spring.datasource.password`: Password for database authentication.

  - **Environment Variables:**

    Use environment variables for configuration when running in Docker or different environments. Provide these variables during container runtime to override properties in `application.properties`. Common environment variables for configuration include:

    - `SERVER_PORT`: Specifies the port for the Spring Boot application.
    - `SPRING_DATASOURCE_URL`: JDBC URL of the database.
    - `SPRING_DATASOURCE_USERNAME`: Username for database authentication.
    - `SPRING_DATASOURCE_PASSWORD`: Password for database authentication.
    - `JWT_SECRET`: Secret key for JWT token generation.
    - `JWT_JWTEXPIRATIONINMS`: Expiration time for JWT tokens in milliseconds.

    Example Docker command with environment variables:

```bash
    docker run -e SERVER_PORT=9090 -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydatabase \
               -e SPRING_DATASOURCE_USERNAME=myuser -e SPRING_DATASOURCE_PASSWORD=mypassword \
               -e JWT_SECRET=myjwtsecret -e JWT_JWTEXPIRATIONINMS=3600000 \
               my-spring-react-app
```

### Frontend (React)

1. **Navigate to the frontend directory:**

```bash
cd placement-portal-front
```
2. **Install dependencies and build the React app:**

   - Install npm dependencies.

   - Build the React app using npm.
```bash
npm install
npm run build
```
### Backend (Spring Boot)

1. **Build and run the backend:**

   - Build the Spring Boot application (skip tests).

   - Run the application using the generated JAR file.
```bash
# Copy React builds
mkdir -p ../src/main/resources/static/
rm -rf ../src/main/resources/static/*
mv build/* ../src/main/resources/static/
cd ..

# Build the Spring Boot application (skip tests)
./mvnw clean package -DskipTests=true

# Run the application
java -jar target/CampusPlacementPortal*.jar
```
## Running with Docker

1. **Build Docker Image:**

   Build the Docker image for the application using the following command:

```bash
   docker build -t my-spring-react-app .
```
  Replace my-spring-react-app with your desired image name.

2. **Run Docker Container:**
  Run the Docker container with the built image using the following command:
  This command exposes port 8080 on your local machine to access the application running inside the Docker container. Adjust the port mapping (-p hostPort:containerPort) as needed based on your application configuration.

  If you need to pass environment variables for configuration, include them in the docker run command as shown in the previous section.

```bash
docker run --network=host -e SERVER_PORT=9090 -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydatabase \
            -e SPRING_DATASOURCE_USERNAME=myuser -e SPRING_DATASOURCE_PASSWORD=mypassword \
            -e JWT_SECRET=myjwtsecret -e JWT_JWTEXPIRATIONINMS=3600000 \
            my-spring-react-app
```
