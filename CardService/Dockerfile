FROM maven:3.8.3-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package
EXPOSE 8082
ENTRYPOINT ["java","-jar","app/target/CardService-0.0.1-SNAPSHOT.jar"]