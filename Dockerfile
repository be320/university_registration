FROM openjdk:8-jdk-alpine

WORKDIR /var/www/html/University

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY . .

EXPOSE 8080
ENTRYPOINT ["java","-jar","/university-0.0.1-SNAPSHOT.jar"]