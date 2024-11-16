FROM openjdk:17-jdk-slim as builder

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x mvnw

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/shopping-cart-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9193

ENTRYPOINT ["java", "-jar", "app.jar"]
