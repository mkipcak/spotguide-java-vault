FROM maven:3.5-jdk-11-slim as build

COPY pom.xml /usr/src/myapp/
RUN mvn -f /usr/src/myapp/pom.xml dependency:resolve dependency:resolve-plugins
COPY src /usr/src/myapp/src
RUN mvn -f /usr/src/myapp/pom.xml clean package -DskipTests -o

FROM openjdk:11-jre-slim
COPY --from=build /usr/src/myapp/target/app.jar app.jar
ENTRYPOINT java -jar app.jar
