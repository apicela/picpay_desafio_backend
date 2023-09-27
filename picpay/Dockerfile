# Imagem base do docker
FROM gradle:7.3.3-jdk17

MAINTAINER Jamil Apicela <trab.jamilsouza@gmail.com>

# workdir
ADD . /app

# gera o jar
RUN ./gradlew build

# Copia o artefato JAR para o diretório /app
COPY build/libs/picpay-0.0.1-SNAPSHOT.jar /app/picpay_jamil_apicela.jar

# apenas poupando linhas invés de digitar `docker run -p 8080:8080 <imagem>`
EXPOSE 8080
# starta o app
ENTRYPOINT ["java", "-jar", "/app/picpay_jamil_apicela.jar"]
