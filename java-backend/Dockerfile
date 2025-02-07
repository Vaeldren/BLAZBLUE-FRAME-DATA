## Use a base image with Java 17+ for Spring Boot
#FROM openjdk:21
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the built JAR file into the container
#COPY target/BLAZBLUE-FRAME-DATA-1.0-SNAPSHOT.jar FrameDataMain.jar
#
## Expose the port your application runs on
#EXPOSE 8080
#
## Command to run the application
#ENTRYPOINT ["java", "-jar", "FrameDataMain.jar"]

# Stage 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean install -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/*.jar FrameDataMain.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "FrameDataMain.jar"]
