#######Build##########

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#######Runtime########

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /build/target/cricket-shop.jar app.jar

RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

