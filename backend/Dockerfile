FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /backend
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
 
FROM eclipse-temurin:17-jre-jammy
WORKDIR /backend
EXPOSE 8080
COPY --from=builder /backend/target/*.jar /backend/app.jar
ENTRYPOINT ["java", "-jar", "/backend/app.jar" ]