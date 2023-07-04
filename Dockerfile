FROM openjdk:20-slim

RUN apt-get update && apt-get install -y findutils

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN chmod +x ./gradlew

RUN ./gradlew --version

COPY . .
RUN ./gradlew clean build

EXPOSE 8080

CMD ["java", "-jar", "/app/build/libs/ecommerce-0.0.1-SNAPSHOT.jar"]