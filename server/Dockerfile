# Etapa 1: Build con Maven
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

# Instalar primero la libreria externa en el repositorio Maven local del contenedor
COPY media/pom.xml ./media/pom.xml
COPY media/src ./media/src
RUN mvn -f media/pom.xml install -DskipTests -B

# Copiar el build del CMS y cachear dependencias
COPY CMS/server/pom.xml ./CMS/server/pom.xml
COPY CMS/server/cms-server/pom.xml ./CMS/server/cms-server/pom.xml
RUN mvn -f CMS/server/pom.xml -pl cms-server dependency:go-offline -B

# Copiar el codigo fuente del CMS y construir
COPY CMS/server/src ./CMS/server/src
RUN mvn -f CMS/server/pom.xml -pl cms-server -am clean package -DskipTests -B


# Etapa 2: Desarrollo / tests
FROM build AS development

WORKDIR /workspace/CMS/server

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "if [ \"$1\" = 'test' ]; then mvn -pl cms-server -am test; else java -jar cms-server/target/*.jar; fi", "--"]


# Etapa 3: Producción liviana
FROM eclipse-temurin:21-jre-jammy AS production

WORKDIR /app

COPY --from=build /workspace/CMS/server/cms-server/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
