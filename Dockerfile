FROM openjdk:11-jdk-oracle
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=k8sproduction", "/app.jar"]