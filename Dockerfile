# Utiliza la imagen base de OpenJDK 19
FROM openjdk:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# copy comple jar aplicaiton
COPY ./target/PizonAcevedo-0.0.1-SNAPSHOT.jar /app

# Expone el puerto en el que se ejecutará tu aplicación (cambia 8080 si es necesario)
EXPOSE 8082

# Comando para ejecutar la aplicación Spring Boot al iniciar el contenedor
CMD ["java", "-jar", "PizonAcevedo-0.0.1-SNAPSHOT.jar"]