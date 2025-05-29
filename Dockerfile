# Etapa 1: Construcción del backend con Gradle
FROM gradle:8.13-jdk17 AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilar sin tests
RUN gradle build -x test

# Etapa 2: Imagen liviana para ejecutar el JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto del backend
EXPOSE 8080

# Comando de ejecución
CMD ["java", "-jar", "app.jar"]
