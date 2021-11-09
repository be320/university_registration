FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENV DBURL jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=UTC

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]