FROM openjdk:17-oracle as builder

WORKDIR /app

COPY pom.xml .

COPY src /app/src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-oracle

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
