# Use Ubuntu as the base image
FROM ubuntu:24.04

# Set environment variables for Java and Maven
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV MAVEN_HOME=/opt/apache-maven-3.8.6
ENV PATH="${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"

# Install dependencies and tools (OpenJDK 21, Maven 3.8.6, wget, and tar)
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    wget \
    tar \
    && rm -rf /var/lib/apt/lists/*

# Download and install Maven 3.8.6
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz \
    && tar xzvf apache-maven-3.8.6-bin.tar.gz \
    && mv apache-maven-3.8.6 /opt/ \
    && rm apache-maven-3.8.6-bin.tar.gz

WORKDIR /app

# Copiamos solo el pom para aprovechar la caché
COPY pom.xml ./

# Descargamos dependencias
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto (sin tests)
RUN mvn clean package -DskipTests

# Copiar el archivo JAR generado desde la etapa de compilación
RUN cp /app/target/*.jar app.jar

# Puerto interno del contenedor (debe coincidir con server.port)
EXPOSE 8086

# Perfil por defecto (puedes cambiarlo o sobreescribirlo en docker run)
ENV SPRING_PROFILES_ACTIVE=dev

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
