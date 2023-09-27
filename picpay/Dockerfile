FROM gradle:8.2.1-jdk17

COPY . /app

WORKDIR /app

RUN gradle build

CMD ["java", "-jar", "build/libs/picpay-0.0.1-SNAPSHOT.jar"]
