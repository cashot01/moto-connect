# Usa a imagem oficial do OpenJDK 17 (versão mais estável)
FROM openjdk:17

# Define o diretório de trabalho
WORKDIR /app

# Copia o build.gradle e settings.gradle primeiro (para aproveitar cache)
COPY build.gradle settings.gradle ./

# Copia os arquivos de source
COPY src ./src

# Executa o build do Gradle
RUN ./gradlew build --no-daemon

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "build/libs/moto-connect-0.0.1-SNAPSHOT.jar"]