# Imagem base do docker
FROM gradle:8.2.1-jdk17

MAINTAINER Jamil Apicela <trab.jamilsouza@gmail.com>

# workdir
WORKDIR  /app

COPY . .

# gera o jar
RUN ./gradlew build

# Copia o artefato JAR para o diretório /app
COPY build/libs/picpay-0.0.1-SNAPSHOT.jar /app/picpay_jamil_apicela.jar

EXPOSE 8080
# starta o app
ENTRYPOINT ["java", "-jar", "/app/picpay_jamil_apicela.jar"]

