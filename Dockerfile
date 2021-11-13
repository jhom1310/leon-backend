FROM openjdk:11
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "target/leon-0.0.1-SNAPSHOT.jar"]