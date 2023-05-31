FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY /src/main/webapp/WEB-INF/views/flows/register/signup-flow.xml /WEB-INF/views/flows/register/signup-flow.xml
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]