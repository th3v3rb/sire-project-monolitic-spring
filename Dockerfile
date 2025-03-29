# -------------------------------
# Etapa 1: Construcción con Maven
# -------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -Pprod

# -------------------------------
# Etapa 2: Imagen final de producción
# -------------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN test -f app.jar || (echo "Error: No se encontró el archivo JAR" && exit 1)

EXPOSE 7778
ENTRYPOINT ["java", "-jar", "app.jar"]
    