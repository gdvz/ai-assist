FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app_assist/src
COPY pom.xml /home/app_assist
RUN mvn -f /home/app_assist/pom.xml clean install -DskipTests

# Package stage
FROM openjdk:17
COPY --from=build /home/app_assist/target/assist-1.jar assist-1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","assist-1.jar"]
