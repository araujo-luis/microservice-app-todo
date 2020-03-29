FROM openjdk:8-jdk-alpine
COPY java-jce/* /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"] 