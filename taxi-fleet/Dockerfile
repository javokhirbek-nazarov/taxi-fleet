FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/taxi-fleet-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9001

CMD ["java", "-jar", "app.jar"]