FROM openjdk:17-alpine
COPY target/BotsCrewTestTask-0.0.1-SNAPSHOT.jar BotsCrewTestTask.jar
ENTRYPOINT ["java", "-jar", "BotsCrewTestTask.jar"]
