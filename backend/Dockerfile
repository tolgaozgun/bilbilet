FROM eclipse-temurin:17-jdk-jammy as builder
RUN apt-get update && apt-get install -y dos2unix
WORKDIR /backend
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN chmod +x mvnw
RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
 
FROM eclipse-temurin:17-jre-jammy
WORKDIR /backend
EXPOSE 8080
COPY --from=builder /backend/target/*.jar /backend/app.jar
ENTRYPOINT ["java", "-jar", "/backend/app.jar" ]