FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
ARG JAR_FILE
COPY /target/ms-goals-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT exec java -jar /app.jar -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS
