FROM openjdk:8-jdk
ADD target/@pom.artifactId@-@pom.version@.jar /usr/src/milageApp.jar
EXPOSE 8080
ENV "SPRING_PROFILES_ACTIVE=psqldb"
ENTRYPOINT ["java", "-jar", "/usr/src/milageApp.jar"]