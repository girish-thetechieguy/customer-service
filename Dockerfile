ARG JDK_VERSION
FROM openjdk:${JDK_VERSION}
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} customer-service.jar
ENTRYPOINT ["java","-jar","/customer-service.jar"]