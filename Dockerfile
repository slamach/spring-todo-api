FROM openjdk:11
COPY build/libs/todoapi-*.jar todoapi.jar
ENTRYPOINT ["java", "-jar", "todoapi.jar"]
