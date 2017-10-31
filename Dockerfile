FROM openjdk:8-jre-alpine
MAINTAINER Rolf E Kristensen <rolf@lightsaber.dk>
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/src/milageApp.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE psqldb
ENTRYPOINT ["java", "-jar", "/usr/src/milageApp.jar"]
