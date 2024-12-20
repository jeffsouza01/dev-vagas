FROM ubuntu:latest as build

RUN apt-get update --fix-missing
RUN apt-get upgrade
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y

RUN mvn clean install

FROM openjdk:17-alpine

EXPOSE 8080

COPY --from=build /target/dev_vagas-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]