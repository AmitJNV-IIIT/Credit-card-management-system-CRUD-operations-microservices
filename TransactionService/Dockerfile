FROM maven:3.8.3-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package
EXPOSE 8083
ENTRYPOINT ["java","-jar","app/target/TransactionService-0.0.1-SNAPSHOT.jar"]