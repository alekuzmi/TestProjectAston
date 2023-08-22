FROM maven AS builder
WORKDIR /TestProjectAston/
COPY . .
RUN mvn clean package


FROM openjdk:17.0.1-jdk-buster
COPY --from=builder /TestProjectAston/target/demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar /TestProjectAston-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TestProjectAston-1.0.jar"]
