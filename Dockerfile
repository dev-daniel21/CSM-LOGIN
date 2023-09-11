FROM openjdk:17

ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/.urandom"

WORKDIR application

COPY target/csm-login-0.0.1-SNAPSHOT.jar csm-login.jar

ENTRYPOINT ["java", "-jar", "csm-login.jar"]