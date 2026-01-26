FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY .mvn/ .mvn/
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src/ src/
RUN ./mvnw -B clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=default

COPY --from=build /app/target/*.jar app.jar
EXPOSE 3048

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
