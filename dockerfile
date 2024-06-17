# Base image for MySQL
FROM mysql:8.0 as mysql

# Copy SQL scripts
COPY src/main/resources/mysql/init.sql /docker-entrypoint-initdb.d/
COPY src/main/resources/mysql/initData.sql /docker-entrypoint-initdb.d/

# Base image for the backend
FROM openjdk:22-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/almfit-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to start MySQL and then the Spring Boot application
CMD ["sh", "-c", "docker-entrypoint.sh mysqld & java -jar /app/app.jar"]
