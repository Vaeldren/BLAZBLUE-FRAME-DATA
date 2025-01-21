# Use a base image with Java 17+ for Spring Boot
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/BLAZBLUE-FRAME-DATA-1.0-SNAPSHOT.jar FrameDataMain.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "FrameDataMain.jar"]
