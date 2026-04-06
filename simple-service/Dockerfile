FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY .. /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/simple-service/target/simple-service-*.jar /opt/appication.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/opt/appication.jar"]