# Etapa 1: Build con Maven
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom.xml primero para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y construir
COPY src ./src
RUN mvn clean package -DskipTests -B


# Etapa 2: Desarrollo / tests
FROM maven:3.9-eclipse-temurin-21 AS development

WORKDIR /app

COPY --from=build /app/pom.xml .
COPY --from=build /app/src ./src
COPY --from=build /app/target/*.jar app.jar

# Cachear dependencias también acá
RUN mvn dependency:go-offline -B

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "if [ \"$1\" = 'test' ]; then mvn test; else java -jar app.jar; fi", "--"]


# Etapa 3: Producción liviana
FROM eclipse-temurin:21-jre-jammy AS production

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]